package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.RefundService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Refund;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/refund")
public class RefundController {
    private RefundService refundService;

    @Autowired
    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }


    @GetMapping("/get-all")
    public DataResult<List<Refund>> getAll(){
       return this.refundService.findAllRefunds();
    }

    @PostMapping("/add-refund")
    public Result addRefund(@RequestBody Refund refund){
        return this.refundService.add(refund);
    }

    @DeleteMapping("/delete-refund")
    public Result deleteRefund(int refundId){
        return this.refundService.delete(refundId);
    }

    @PostMapping("/request-refund") // Refund işlemi için bu kullanılır.
    public Result requestRefund(@RequestParam String cause,@RequestParam int orderDetailId){
        return this.refundService.startRefund(cause,orderDetailId);
    }
}
