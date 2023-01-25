package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.SizeService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Size;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/size")
public class SizeController {
    private SizeService sizeService;

    @Autowired
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("/get-all")
    public DataResult<List<Size>> listAll(){
        return this.sizeService.getAllSizes();
    }
    @PostMapping("/add")
    public Result add(@RequestBody Size size){
        return this.sizeService.addSize(size);
    }
    @DeleteMapping("/delete")
    public Result delete(int sizeId){
        return this.sizeService.deleteSize(sizeId);
    }


}
