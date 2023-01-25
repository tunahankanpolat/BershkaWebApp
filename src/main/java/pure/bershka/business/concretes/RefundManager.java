package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.RefundService;
import pure.bershka.core.utilities.result.*;
import pure.bershka.dataAccess.abstracts.*;
import pure.bershka.entities.concretes.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RefundManager implements RefundService {
    private RefundDao refundDao;
    private CustomerDao customerDao;
    private OrderDetailDao orderDetailDao;
    private StockDao stockDao;
    private ProductDao productDao;
    private OrderDao orderDao;
    @Autowired
    public RefundManager(RefundDao refundDao, CustomerDao customerDao,
                         OrderDetailDao orderDetailDao, StockDao stockDao, ProductDao productDao,OrderDao orderDao) {
        this.refundDao = refundDao;
        this.customerDao = customerDao;
        this.orderDetailDao = orderDetailDao;
        this.stockDao = stockDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }



    @Override
    public DataResult<List<Refund>> findAllRefunds() {
        return new SuccessDataResult<>(this.refundDao.findAll());
    }

    @Override
    public Result add(Refund refund) {
        this.refundDao.save(refund);
        return new SuccessResult("refund talebi done.");
    }

    @Override
    public Result delete(int refundId) {
        Refund deletedRefund = this.refundDao.findById(refundId).get();
        this.refundDao.delete(deletedRefund);
        return new SuccessResult("refund silindi.");
    }

    @Override
    public Result startRefund(String cause, int orderDetailId) {

        OrderDetail productToRefund = this.orderDetailDao.findById(orderDetailId).get();
        Customer whoRefund = productToRefund.getOrder().getCustomer();
        Order order = productToRefund.getOrder();
        Product product = productToRefund.getProduct();
        Stock stock = this.stockDao.getProduct(product.getId(),productToRefund.getSize().getId());
        int refundMoney = 0;
        if(product.getDiscountPercentage().intValue() != 0){
            int discount = product.getPrice().intValue() *
                    product.getDiscountPercentage().intValue() / 100;
            int realPrice = product.getPrice().intValue() - discount;
            refundMoney = realPrice;
        }else
            refundMoney = product.getPrice().intValue();
        for (int i = 0; i < whoRefund.getOrders().size(); i++) {
            if (whoRefund.getOrders().get(i).getId() == productToRefund.getOrder().getId()){
                stock.setCount(stock.getCount()+1);
                this.stockDao.save(stock);
                if(order.getDiscountCode() != null){
                    order.getDiscountCode().setAmount(order.getDiscountCode().getAmount()+1);
                }
                this.orderDao.save(order);
                whoRefund.setBudget(whoRefund.getBudget()+refundMoney);
                this.customerDao.save(whoRefund);
                LocalDateTime now = LocalDateTime.now();
                Refund refund = new Refund(cause,now,true,whoRefund,productToRefund);
                this.add(refund);
                productToRefund.setRefunded(true);
                this.orderDetailDao.save(productToRefund);
                return new SuccessResult("Ürün iade edildi.");
            }
        }

        return new ErrorResult("Ürün iadesi gerçekleştirilemedi!!");
    }


}
