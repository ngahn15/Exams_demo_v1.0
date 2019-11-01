package vnpt.Exams;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Predicate;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;

public class MyPageObject extends PageObject {
	private static final TemporalUnit SECONDS = ChronoUnit.SECONDS;

	protected MyPageObject() {
		super();
	}

	protected MyPageObject(final WebDriver driver, Predicate<? super PageObject> callback) {
		super(driver, callback);
	}

	public MyPageObject(final WebDriver driver, final int ajaxTimeout) {
		super(driver, ajaxTimeout);
	}

	public MyPageObject(final WebDriver driver) {
		super(driver);
	}

	public MyPageObject(final WebDriver driver, final EnvironmentVariables environmentVariables) {
		super(driver, environmentVariables);
	}

	public void waitUntilHTMLReady(int _timeoutInSeconds) {
		new FluentWait<WebDriver>(getDriver()).withTimeout(_timeoutInSeconds, TimeUnit.SECONDS)
				.withMessage("***** INFO ***** HTML STILL LOADING FOR OVER" + _timeoutInSeconds + "SECONDS.")
				.pollingEvery(50, TimeUnit.MILLISECONDS).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						try {
							JavascriptExecutor jsExec = (JavascriptExecutor) d;
							return (Boolean) jsExec.executeScript("return document.readyState").toString()
									.equals("complete");
						} catch (Exception e) {
							return true;
						}
					}
				});
	}

	public void waitUntiljQueryRequestCompletes(int _timeoutInSeconds) {
		try {
			new FluentWait<WebDriver>(getDriver()).withTimeout(_timeoutInSeconds, TimeUnit.SECONDS)
					.withMessage("***** INFO ***** JQUERY STILL LOADING FOR OVER" + _timeoutInSeconds + "SECONDS.")
					.pollingEvery(50, TimeUnit.MILLISECONDS).until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							try {
								JavascriptExecutor jsExec = (JavascriptExecutor) d;
								return (Boolean) jsExec.executeScript("return jQuery.active == 0");
							} catch (Exception e) {
								return true;
							}
						}
					});
		} catch (Exception e) {
		}
	}

	public void waitUntilAjaxCompletes(int _timeoutInSeconds) {
		new FluentWait<WebDriver>(getDriver()).withTimeout(_timeoutInSeconds, TimeUnit.SECONDS)
				.withMessage("***** INFO ***** AJAX STILL ACTIVE FOR OVER" + _timeoutInSeconds + "SECONDS.")
				.pollingEvery(50, TimeUnit.MILLISECONDS).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						try {
							JavascriptExecutor jsExec = (JavascriptExecutor) d;
							return (Boolean) jsExec.executeScript("return $.active == 0");
						} catch (Exception e) {
							return true;
						}
					}
				});
	}

	public void waitForEverythingComplete(int _timeoutInSeconds) {
		waitABit(30);
		waitUntilHTMLReady(_timeoutInSeconds);
		waitUntiljQueryRequestCompletes(_timeoutInSeconds);
		waitUntilAjaxCompletes(_timeoutInSeconds);

	}

	public void waitForEverythingComplete() {
		waitForEverythingComplete(60);
	}

	public void scrollIntoElementView(String _xPath) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", findBy(_xPath));
		waitABit(15);
	}

	public void highlightElement(String _xPath) {
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='2px solid red'",
					findBy(_xPath));
		} catch (Exception e) {
			// Do nothing
		}
	}

	public WebElementFacade waitElementToBePresentThenScrollIntoViewAndHighlight(String _xPath) {
		waitForEverythingComplete();
		waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(_xPath)));
		scrollIntoElementView(_xPath);
		highlightElement(_xPath);
		return findBy(_xPath);
	}

	public WebElementFacade XH(String _xPath) {
		return waitElementToBePresentThenScrollIntoViewAndHighlight(_xPath);
	}

	public void waitUntilElementVisible(String xPath, int _timeoutInSeconds) {
		try {
			withTimeoutOf(_timeoutInSeconds, TimeUnit.SECONDS)
					.waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		} catch (Exception e) {
			System.out.println("***** INFO ***** ELEMENT NOT PRESENCE FOR OVER " + _timeoutInSeconds + "SECONDS.");
		}
	}

	/**
	 * Description Check element exist or not immediately
	 * 
	 * @param xPath
	 * @return
	 */
	public boolean isElementExistNow(String xPath) {
		waitForEverythingComplete();
		waitForEverythingComplete();
		try {
			// withTimeoutOf(500,
			// TimeUnit.MILLISECONDS).waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			List<WebElementFacade> subDataCellResults = withTimeoutOf(500, TimeUnit.MILLISECONDS).findAll(xPath);
			if (subDataCellResults.size() != 0) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public boolean isElementExistNowBasic(String xPath) {
		try {
			List<WebElementFacade> subDataCellResults = withTimeoutOf(120, TimeUnit.MILLISECONDS).findAll(xPath);
			if (subDataCellResults.size() != 0) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Description Check element exist or not immediately
	 * 
	 * @param xPath
	 * @return
	 */
	public boolean isElementExist(String _xPath, int _TimeOutInSecond) {
		try {
			setImplicitTimeout(_TimeOutInSecond, SECONDS);
			XH(_xPath);
			resetImplicitTimeout();
			return true;
		} catch (Exception e) {
			resetImplicitTimeout();
		}
		return false;
	}
}
