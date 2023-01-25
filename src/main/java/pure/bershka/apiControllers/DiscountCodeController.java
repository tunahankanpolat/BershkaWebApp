package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.DiscountCodeService;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.DiscountCode;

@CrossOrigin
@RestController
@RequestMapping("/api/discount-code")
public class DiscountCodeController {
    private DiscountCodeService discountCodeService;

    @Autowired
    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody DiscountCode discountCode){
       return this.discountCodeService.add(discountCode);
    }
    @DeleteMapping("/delete")
    public Result delete(int discountCodeId){
        return this.discountCodeService.delete(discountCodeId);
    }
    @PutMapping("/update")
    public Result update(int discountCodeId, @RequestBody DiscountCode discountCode){
        return this.discountCodeService.update(discountCodeId, discountCode);
    }
}
