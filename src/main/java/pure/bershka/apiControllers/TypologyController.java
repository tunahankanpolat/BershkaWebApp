package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.TypologyService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Gender;
import pure.bershka.entities.concretes.Typology;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/typology")
public class TypologyController {
    private TypologyService typologyService;

    @Autowired
    public TypologyController(TypologyService typologyService) {
        this.typologyService = typologyService;
    }

    @GetMapping("/list-typologies")
    public DataResult<List<Typology>> listTypologies(int categoryId){
        return this.typologyService.listCategories(categoryId);
    }
    @GetMapping("/list-typologies-gender")
    public DataResult<List<Typology>> listTypologiesGender(int categoryId,String gender){
        return this.typologyService.listCategoriesWithGender(categoryId, gender);
    }
   @PostMapping("/add")
    public Result add(@RequestBody Typology typology){
        return this.typologyService.addTypology(typology);
    }
    @PutMapping("/update")
    public Result update(int typologyId, @RequestBody Typology typology){
        return this.typologyService.updateTypology(typologyId,typology);
    }
    @DeleteMapping("/delete")
    public Result delete(int typologyId){
        return this.typologyService.deleteTypology(typologyId);
    }
}
