package vnpt.Exams.pages;

import vnpt.Exams.MyPageObject;

public class LoginPage extends MyPageObject {
	private static final String loginFailedMsg = "//p[contains(text(),'Wrong login/password')]";
	private static final String logoutLink = "//a[contains(text(),'Đăng xuất')]";
	private static final String fullnameLink = "//span[@class='oe_topbar_name']";
	private static final String resetPwdLink = "//a[@class='btn btn-link pull-right']";
	private static final String loginBtn = "//button[@class='btn btn-primary']";
	private static final String passwordBox = "//input[@id='password']";
	private static final String emailBox = "//input[@id='login']";
	private static final String INPUT_REQUIRED = "input[required=required]";

	public void enterEmail(String email) {
		XH(emailBox).sendKeys(email);
	}

	public void enterPassword(String pwd) {
		XH(passwordBox).sendKeys(pwd);
	}

	public void clickIntoLoginBtn() {
		XH(loginBtn).click();
	}

	public void clickIntoResetPwdLink() {
		XH(resetPwdLink).click();
	}

	public void clickToLogoutLink() {
		XH(fullnameLink).click();
		XH(logoutLink).click();
	}

	public String getMsgError() {
		return XH(loginFailedMsg).getText();
	}

	public boolean exist_login_error_message() {
		return isElementExistNow(loginFailedMsg);
	}

	public String validationMessageEmail() {
		return XH(emailBox).getAttribute("validationMessage");
	}

	public boolean exist_validation_Message_email() {
		if (validationMessageEmail().isEmpty()) {
			return false;
		}
		return true;
	}

	public String validationMessagePassword() {
		return XH(passwordBox).getAttribute("validationMessage");
	}

	public boolean exist_validation_Message_Password() {
		if (validationMessagePassword().isEmpty()) {
			return false;
		}
		return true;
	}
}
