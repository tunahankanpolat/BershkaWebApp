/*
package pure.bershka.core.utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pure.bershka.business.abstracts.LogService;

@Component
public class SQLogMonitor {
    private static final Logger logger = LoggerFactory.getLogger(SQLogMonitor.class);
    @Autowired
    private LogService logManager;

    public void monitorDebugLogs() {
        java.util.logging.Logger lgr = java.util.logging.Logger.getLogger("Debug");
        String a = lgr.toString();
        logManager.saveLog("Debug log çıktı");
    }
}

*/
