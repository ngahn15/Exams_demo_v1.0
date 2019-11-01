package vnpt.Exams.steps.serenity;

import net.thucydides.core.annotations.Step;
import vnpt.Exams.pages.CurrentPage;
import vnpt.Exams.pages.LoginPage;

public class LoginSteps {
	LoginPage loginPage;
	CurrentPage currentPage;

	/*
	 * @Step public void user_login(Account a) { launchApplication();
	 * enterEmail(a.getEmail()); enterPassword(a.getPassword()); clickToLoginBtn();
	 * }
	 */

	@Step
	public void launchApplication() {
		loginPage.open();
	}
	
	@Step
	public void getTitle() {
		loginPage.getTitle();
	}

	@Step
	public void enterEmail(String email) {
		loginPage.enterEmail(email);
	}

	@Step
	public void enterPassword(String pwd) {
		loginPage.enterPassword(pwd);
	}

	@Step
	public void clickToLoginBtn() {
		loginPage.clickIntoLoginBtn();
	}

	@Step
	public void clickIntoResetPwdLink() {
		loginPage.clickIntoResetPwdLink();
	}

	@Step
	public void clickLogout() {
		loginPage.clickToLogoutLink();
	}
	
	@Step
	public String getTitleAfterLoginSuccessful() {
		return currentPage.getTitle();
	}
	
	@Step
	public String getMessageError() {
		return loginPage.getMsgError();
	}
	
	@Step
	public boolean exist_login_error_message() {
		return loginPage.exist_login_error_message();
	}
	
	@Step
	public String validationMessageEmail() {
		return loginPage.validationMessageEmail();
	}
	
	@Step
	public boolean exist_validation_Message_email() {
		return loginPage.exist_validation_Message_email();
	}
	
	@Step
	public String validationMessagePassword() {
		return loginPage.validationMessagePassword();
	}
	
	@Step
	public boolean exist_validation_Message_Password() {
		return loginPage.exist_validation_Message_Password();
	}

}
