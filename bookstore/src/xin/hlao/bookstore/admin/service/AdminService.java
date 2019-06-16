package xin.hlao.bookstore.admin.service;

import xin.hlao.bookstore.admin.dao.AdminDao;
import xin.hlao.bookstore.admin.domain.Admin;

public class AdminService {
	private AdminDao ad=new AdminDao();

	public void adminlogin(Admin admin) throws AdminException {
		// TODO Auto-generated method stub
		Admin admin1=ad.findadminbyname(admin.getAdminname());
		if(admin1==null) throw new AdminException("账号不存在！");
		if(!admin.getAdminpassword().equals(admin1.getAdminpassword()))throw new AdminException("密码错误！");
	}
}
