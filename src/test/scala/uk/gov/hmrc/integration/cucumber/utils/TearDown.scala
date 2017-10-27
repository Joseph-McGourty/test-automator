package uk.gov.hmrc.integration.cucumber.utils

import cucumber.api.Scenario
import cucumber.api.java.{After, Before}
import org.openqa.selenium.{WebDriverException, OutputType, TakesScreenshot}


trait TearDown {

  val driver: Driver = SingletonDriver

  @Before
  def initialize() {
    driver.getInstance().manage().deleteAllCookies()
  }

  @After
  def tearDown(result: Scenario) {
    if (result.isFailed) {
      if (driver.getInstance().isInstanceOf[TakesScreenshot]) {
        try {
          val screenshot = driver.getInstance().asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
          result.embed(screenshot, "image/png")
        } catch {
          case somePlatformsDontSupportScreenshots: WebDriverException => System.err.println(somePlatformsDontSupportScreenshots.getMessage)
        }
      }
    }
  }

}
