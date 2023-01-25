package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Location;

import java.util.List;

public interface LocationService {
    Result add(Location location);
    Result delete(int locationId);
    Result updateLocation(int locationId, Location location);

    DataResult<List<Location>> getAll();
}
