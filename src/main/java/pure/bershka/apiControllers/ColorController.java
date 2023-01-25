package pure.bershka.apiControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.ColorService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Color;

@CrossOrigin
@RestController
@RequestMapping("/api/color")
public class ColorController {
    private ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
    	super();
        this.colorService = colorService;
    }
    
	@PostMapping(value = "/add")
	public Result add(@RequestBody  Color color) {
		return this.colorService.add(color);
	}

	@GetMapping("/get-all")
	public DataResult<List<Color>> getAll() {
		return this.colorService.getAll();
	};
	@DeleteMapping("/delete")
	public Result delete(int colorId){
		return this.colorService.deleteColor(colorId);
	}
}
    
