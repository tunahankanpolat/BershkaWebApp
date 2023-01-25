package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.LocationService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.LocationDao;
import pure.bershka.entities.concretes.Location;

import java.util.List;

@Service
public class LocationManager implements LocationService {
    private LocationDao locationDao;

    @Autowired
    public LocationManager(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Result add(Location location) {
        this.locationDao.save(location);
        return new SuccessResult("address eklendi");
    }

    @Override
    public Result delete(int locationId) {
        Location deletedLocation = this.locationDao.findById(locationId).get();
        this.locationDao.delete(deletedLocation);
        return  new SuccessResult("address silindi");
    }

    @Override
    public Result updateLocation(int locationId, Location location) {
        Location updatedLocation = this.locationDao.findById(locationId).get();
        updatedLocation.setLocation(location);
        this.locationDao.save(updatedLocation);
        return new SuccessResult("location updated");
    }

    @Override
    public DataResult<List<Location>> getAll() {
        return new SuccessDataResult<>(this.locationDao.findAll());
    }
}
