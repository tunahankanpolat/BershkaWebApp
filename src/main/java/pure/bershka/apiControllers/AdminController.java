package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pure.bershka.business.abstracts.AdminService;
import pure.bershka.core.utilities.result.Result;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService=adminService;
	}

	@PostMapping("/set-active")
	public Result setActive(int customerId){
		return this.adminService.setActive(customerId);
	}
	@PostMapping("/set-inactive")
	public Result setInActive(int customerId){
		return this.adminService.setInActive(customerId);
	}

}
