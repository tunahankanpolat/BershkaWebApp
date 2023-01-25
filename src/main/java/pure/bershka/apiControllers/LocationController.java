package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.LocationService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Location;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/location")
public class LocationController {
    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/add-location")
    public Result addLocation(@RequestBody Location location){
        return this.locationService.add(location);
    }
    @DeleteMapping("/delete-location")
    public Result deleteLocation(int locationId){
        return this.locationService.delete(locationId);
    }
    @PutMapping("/update-location")
    public Result updateLocation(int locationId, @RequestBody Location location){
        return this.locationService.updateLocation(locationId, location);
    }
    @GetMapping("/get-all")
    public DataResult<List<Location>> getAll(){
        return this.locationService.getAll();
    }
}
