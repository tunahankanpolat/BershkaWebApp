package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.LogService;
import pure.bershka.core.utilities.Log;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.dataAccess.abstracts.LogDao;

import java.util.Date;
import java.util.List;

@Service
public class LogManager implements LogService {
    private LogDao logDao;

    @Autowired
    public LogManager(LogDao logDao) {
        this.logDao = logDao;
    }

    public void saveLog(String message) {
        Log log = new Log();
        log.setMessage(message);
        log.setDate(new Date());
        logDao.save(log);
    }

    @Override
    public void saveLog(Log log) {
        logDao.save(log);
    }

    @Override
    public DataResult<List<Log>> getAll() {
        return new SuccessDataResult<>(this.logDao.findAll());
    }
}
