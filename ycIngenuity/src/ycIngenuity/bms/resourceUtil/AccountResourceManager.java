package ycIngenuity.bms.resourceUtil;

public class AccountResourceManager extends ResourceManager<Account> {

	public AccountResourceManager(ResourceSLU<Account> rslu) {
		//super(rslu) initiate ResourseFileSystem and load resource 
		super(rslu);
	}
	

	
	public Boolean checkIDPW(String email, String pw) {
		for(Account account : resourceList) {
			if(account.getEmail().equals(email) && account.getPassword().equals(pw)) {
				return true;
			}			
		}
		return false;
	}

}
