package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.Log;
import pure.bershka.core.utilities.result.DataResult;

import java.util.List;

public interface LogService {
    void saveLog(String message);
    void saveLog(Log log);
    DataResult<List<Log>> getAll();

}
