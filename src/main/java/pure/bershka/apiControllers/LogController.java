
package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.LogService;
import pure.bershka.core.utilities.Log;
import pure.bershka.core.utilities.result.DataResult;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/log")
public class LogController {
    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/add")
    public void add(){
        logService.saveLog("sa");
    }

    @GetMapping("/get-all")
    public DataResult<List<Log>> getAll(){
        return this.logService.getAll();
    }
}

