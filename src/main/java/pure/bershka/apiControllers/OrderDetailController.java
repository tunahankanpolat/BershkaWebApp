package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.OrderDetailService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.entities.concretes.OrderDetail;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

}
