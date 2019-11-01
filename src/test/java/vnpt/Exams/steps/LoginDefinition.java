package vnpt.Exams.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import vnpt.Exams.steps.serenity.LoginSteps;

public class LoginDefinition {

	@Steps
	LoginSteps loginStep;

	@Given("^browse to user login page$")
	public void browse_to_user_login_page() {
		loginStep.launchApplication();
		System.out.println("Open http://exams-hrm.v8.vn/");
	}

	@When("^enter Email as \"([^\"]*)\" and  password as \"([^\"]*)\"$")
	public void enter_Email_as_and_password_as(String email, String pwd) throws InterruptedException {
		System.out.println(email);
		loginStep.enterEmail(email);
		Thread.sleep(1000);
		System.out.println(pwd);
		loginStep.enterPassword(pwd);
		Thread.sleep(1000);
	}

	@When("^click Login button$")
	public void click_Login_button() {
		loginStep.clickToLoginBtn();
//		System.out.println("Click login btn");
	}

	@Then("^my login is \"([^\"]*)\" and check \"([^\"]*)\" or \"([^\"]*)\"$")
	public void my_login_is_and_check_or(String expectedResult, String messagesError, String title)
			throws InterruptedException {
		// TH successful

		if (expectedResult.equals("successful")) {
			System.out.println("successful");
			Thread.sleep(8000);
			String currentUrl = loginStep.getTitleAfterLoginSuccessful();
			System.out.println("url" + currentUrl);
			assertThat(currentUrl, equalTo(title));
		} else {
			System.out.println("Failed");

			if (loginStep.exist_login_error_message()) {
				System.out.println("tc4");
				String actualMsg = loginStep.getMessageError();
				assertThat(messagesError, equalTo(actualMsg));
			} else {
				System.out.println("1 " + loginStep.exist_validation_Message_email() + " " + loginStep.validationMessageEmail());
				System.out.println("2 " + loginStep.exist_validation_Message_Password() + " " + loginStep.validationMessagePassword());
				if(loginStep.exist_validation_Message_email() == true) {
					System.out.println("TC2 + " + loginStep.validationMessageEmail());
					String actualValidationMessageEmail = loginStep.validationMessageEmail();
					assertThat(messagesError, equalTo(actualValidationMessageEmail));
				}else {
					System.out.println("TC3 + " + loginStep.validationMessagePassword());
					String actualValidationMessagePassword = loginStep.validationMessagePassword();;
					assertThat(messagesError, equalTo(actualValidationMessagePassword));
				}

			}

		}
	}

}
