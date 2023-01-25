package pure.bershka.business.concretes;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.OrderService;
import pure.bershka.core.utilities.result.*;
import pure.bershka.dataAccess.abstracts.*;
import pure.bershka.entities.concretes.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager implements OrderService {
    private OrderDao orderDao;
    private CustomerDao customerDao;
    private StockDao stockDao;
    private ProductDao productDao;
    private SizeDao sizeDao;
    private OrderDetailDao orderDetailDao;
    private DiscountCodeDao discountCodeDao;
    private LocationDao locationDao;

    @PersistenceUnit
    EntityManagerFactory emf;

    @Autowired
    public OrderManager(OrderDao orderDao, CustomerDao customerDao,
                        StockDao stockDao,ProductDao productDao,
                        SizeDao sizeDao,OrderDetailDao orderDetailDao,
                        DiscountCodeDao discountCodeDao, LocationDao locationDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
        this.stockDao = stockDao;
        this.productDao = productDao;
        this.sizeDao = sizeDao;
        this.orderDetailDao = orderDetailDao;
        this.discountCodeDao = discountCodeDao;
        this.locationDao = locationDao;

    }



    //FOR ADMIN
    @Override
    public DataResult<List<Order>> getAllOrdersForAdmin() {
        return new SuccessDataResult<List<Order>>(this.orderDao.findAll());
    }
    @Override
    public Result deleteOrder(int orderId) {
        Order deletedOrder = this.orderDao.findById(orderId).get();
        List<OrderDetail> orderDetailList = deletedOrder.getOrderDetails();
        for (int i = 0; i < orderDetailList.size(); i++) {
            Stock stock = this.stockDao.getProduct(orderDetailList.get(i).getProduct().getId(),
                    orderDetailList.get(i).getSize().getId());
            stock.setCount(stock.getCount()+1);
            orderDetailList.get(i).setCanceled(true);
            this.orderDetailDao.save(orderDetailList.get(i));
        }
        deletedOrder.setCanceled(true);
        int previousBudget = deletedOrder.getCustomer().getBudget();
        int orderPrice = deletedOrder.getTotalPrice();
        deletedOrder.getCustomer().setBudget(previousBudget + orderPrice);
        deletedOrder.getDiscountCode().setAmount(deletedOrder.getDiscountCode().getAmount()+1);
        LocalDateTime now = LocalDateTime.now();
        deletedOrder.setCancelDate(now);
        this.orderDao.save(deletedOrder);
        return new SuccessResult("Sipariş iptal edildi.");
    }

    @Override
    public Result updateOrder(int orderId, Order order) {
        Order updatedOrder = this.orderDao.findById(orderId).get();
        updatedOrder.setOrder(order);
        this.orderDao.save(updatedOrder);
        return new Result(true);
    }

    @Override
    public Result addOrder(Order order) {
        this.orderDao.save(order);
        return new SuccessResult("order given.");
    }

    @Override
    public Result giveOrder(int customerId, int billLocationId, int locationId, String discountCodeName) {
        Customer whoBuys = this.customerDao.findById(customerId).get();
        Location location = this.locationDao.findById(locationId).get();
        Location billLocation = this.locationDao.findById(billLocationId).get();
        DiscountCode discountCode = this.discountCodeDao.getByCode(discountCodeName); // default sıfır indirim için id = 101
        int totalPrice = 0;
        for (int i = 0; i < whoBuys.getBasket().size(); i++) {
            if (whoBuys.getBasket().get(i).getProduct().getDiscountPercentage().intValue() != 0){
                int discount = whoBuys.getBasket().get(i).getProduct().getPrice().intValue() *
                        whoBuys.getBasket().get(i).getProduct().getDiscountPercentage().intValue() / 100;
                int newPrice = whoBuys.getBasket().get(i).getProduct().getPrice().intValue() - discount;
                totalPrice += newPrice * whoBuys.getBasket().get(i).getAmount();
            }
            else
                totalPrice += whoBuys.getBasket().get(i).getProduct().getPrice().intValue()
                        *whoBuys.getBasket().get(i).getAmount();
        }
        if (totalPrice <= whoBuys.getBudget()){
            for (int i = 0; i < whoBuys.getBasket().size(); i++) {
                if ((this.stockDao.getProduct(whoBuys.getBasket().get(i).getProduct().getId(),
                        whoBuys.getBasket().get(i).getSize().getId()).getCount() == 0) ||
                        (this.stockDao.getProduct(whoBuys.getBasket().get(i).getProduct().getId(),
                        whoBuys.getBasket().get(i).getSize().getId()).getCount()-whoBuys.getBasket().get(i).getAmount() < 0))
                    return new ErrorResult("There is no enough stock for '"+
                            whoBuys.getBasket().get(i).getProduct().getName() + "' named item!!");
            }
            for (int i = 0; i < whoBuys.getBasket().size(); i++) {
                this.stockDao.getProduct(whoBuys.getBasket().get(i).getProduct().getId(),
                        whoBuys.getBasket().get(i).getSize().getId()).decreaseCount(whoBuys.getBasket().get(i).getAmount());
                this.stockDao.save(this.stockDao.getProduct(whoBuys.getBasket().get(i).getProduct().getId(),
                        whoBuys.getBasket().get(i).getSize().getId()));
            }
        }
        else
            return new ErrorResult("There is no enough budget!!");
        LocalDateTime now = LocalDateTime.now();
        totalPrice -= discountCode.getPrice();
        discountCode.setAmount(discountCode.getAmount()-1);
        this.discountCodeDao.save(discountCode);
        boolean isCanceled = false;
        Order order = new Order(totalPrice,now,null,whoBuys,location,
                billLocation,discountCode,isCanceled);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();

        this.orderDao.save(order);

        for (int i = 0; i < whoBuys.getBasket().size(); i++) {
            OrderDetail orderDetail = new OrderDetail(order,whoBuys.getBasket().get(i).getProduct(),
                    this.sizeDao.findById(whoBuys.getBasket().get(i).getSize().getId()).get(),whoBuys.getBasket().get(i).getAmount(),false,false);
            this.orderDetailDao.save(orderDetail);
        }
        this.addOrder(order);
        int newBudget = whoBuys.getBudget()-totalPrice;
        whoBuys.setBudget(newBudget);
        whoBuys.setBasket(null);
        whoBuys.setLastLocation(location);
        this.customerDao.save(whoBuys);

        return new SuccessResult("Buying successfull");
    }


}
