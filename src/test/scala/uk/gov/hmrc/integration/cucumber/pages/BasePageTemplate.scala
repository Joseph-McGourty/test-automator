package uk.gov.hmrc.integration.cucumber.pages

import java.util.concurrent.TimeUnit
import java.io.FileWriter

import org.openqa.selenium._
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import org.scalatest.Matchers
import uk.gov.hmrc.integration.cucumber.utils.SingletonDriver
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._

import scala.util.{Failure, Success, Try}
import scala.io.StdIn.readLine


object BasePageTemplate extends BasePageTemplate


trait BasePageTemplate extends Matchers {

  val driver    = SingletonDriver.getInstance()
  var storedUrl = ""
  var urlList   = scala.collection.mutable.Set[String]()

  val longFluentWait: FluentWait[WebDriver] = new FluentWait[WebDriver](BasePageTemplate.driver)
    .withTimeout(LONG_WAIT_TIME, TimeUnit.SECONDS)
    .pollingEvery(POLLING_INTERVAL_LONG_WAIT_TIME, TimeUnit.SECONDS)

  val shortFluentWait: FluentWait[WebDriver] = new FluentWait[WebDriver](BasePageTemplate.driver)
    .withTimeout(SHORT_WAIT_TIME, TimeUnit.SECONDS)
    .pollingEvery(POLLING_INTERVAL_SHORT_WAIT_TIME, TimeUnit.MILLISECONDS)

  def currentUrl      = driver.getCurrentUrl

  def currentElement  = driver.switchTo().activeElement()

  def getUrlParamName = driver.getTitle.split(' ').map(_.capitalize).mkString(" ").replaceAll("'", "").replaceAll("-", "").replaceAll(" ", "")

  //////////////////////////////////////////////////////
  // FUNDAMENTAL WEBDRIVER COMMANDS - BUILDING BLOCKS //
  //////////////////////////////////////////////////////

  var tryEx: Try[Unit] = Try(assert(1 == 1))

  //  click           (element)
  //  link            (element)
  //  clickRadio      (element, choice)
  //  verifyValue     (element, text)
  //  verifyText      (element, text)
  //  read            (element)
  //  clear           (element)
  //  write           (element, value)
  //  append          (element, text)
  //  displayed       (element)
  //  writeAndVerify  (element, text)
  //  navigate        (url)
  //  contains        (item, value)
  //  verify          (item, value)

  def click(element: String, idOverride: String = "id") {
    tryEx = Try {
      find(getParam(element), idOverride).click()
    }
    catchErr(tryEx, element, getParam(element))
  }

  def link(element: String, idOverride: String = "id") {
    click(element, idOverride)
  }

  def clickRadio(element: String, choice: String = "", idOverride: String = "id") {
    click(element + choice, idOverride)
  }

  def verifyValue(element: String, inputText: String = "", idOverride: String = "id") {
    tryEx = Try {
      assert(find(element, idOverride).getAttribute("value") == inputText)
    }
    catchErr(tryEx, element, inputText)
  }

  def verifyText(element: String, inputText: String = "", idOverride: String = "id"): Unit = {
    tryEx = Try {
      assert(find(element, idOverride).getText == inputText)
    }
    catchErr(tryEx, element, inputText)
  }

  def read(element: String, idOverride: String = "id"): String = {
    var returnVal = ""
    tryEx = Try {
      val returnVal = find(element, idOverride).getAttribute("value")
    }
    catchErr(tryEx, element, getParam(element))
    returnVal
  }

  def clear(element: String, idOverride: String = "id") {
    tryEx = Try {
      find(element, idOverride).clear()
    }
    catchErr(tryEx, element, getParam(element))
  }

  def write(element: String, inputValue: String = "", idOverride: String = "id") {
    tryEx = Try {
      find(element, idOverride).clear()
      find(element, idOverride).sendKeys(inputValue)
    }
    catchErr(tryEx, element, inputValue)
  }

  def append(element: String, inputValue: String, idOverride: String = "id") {
    tryEx = Try {
      find(element, idOverride).sendKeys(inputValue)
    }
    catchErr(tryEx, element, inputValue)
  }

  def displayed(element: String, idOverride: String = "id") {
    tryEx = Try {
      find(element, idOverride).isDisplayed
    }
    catchErr(tryEx, element)
  }

  def writeAndVerify(element: String, inputValue: String = "", idOverride: String = "id") {
    tryEx = Try {
      write(element, inputValue)
      verifyValue(element, inputValue)
    }
    catchErr(tryEx, element, inputValue)
  }

  def navigate(url: String) {
    tryEx = Try {
      driver.navigate().to(getParam(url))
    }
    catchErr(tryEx, url, getParam(url))
  }

  def contains(itemType: String, inputValue: String): Boolean = {
    var returnBoolean: Boolean = false
    itemType match {
      case "URL" => if (driver.getCurrentUrl.contains(inputValue)) {
        returnBoolean = true
      }
      case "TEXT" => if (driver.getPageSource.contains(inputValue)) {
        returnBoolean = true
      }
    }
    returnBoolean
  }

  def verify(itemType: String, inputValue: String): Boolean = {
    var returnBoolean: Boolean = false
    itemType match {
      case "URL" => if (currentUrl == inputValue) {
        returnBoolean = true
      }
      case "TEXT" => if (read(itemType) == inputValue) {
        returnBoolean = true
      }
    }
    returnBoolean
  }


  ////////////////////////////////////
  // UNIVERSAL FIND ELEMENT COMMAND //
  ////////////////////////////////////

  def find(itemIdentifier: String, idOverride: String = "id") = idOverride match {
    case "name"         => findBy(By.name(itemIdentifier))
    case "cssSelector"  => findBy(By.cssSelector(itemIdentifier))
    case "xpath"        => findBy(By.xpath(itemIdentifier))
    case "linktext"     => findBy(By.linkText(itemIdentifier))
    case "partlinktext" => findBy(By.partialLinkText(itemIdentifier))
    case "classname"    => findBy(By.className(itemIdentifier))
    case "tagname"      => findBy(By.tagName(itemIdentifier))
    case "id" | _       => findBy(By.id(itemIdentifier))
  }

  def findBy(by: By) = {
    testController()
    driver.findElement(by)
  }


  ///////////////////////////////////////////////////////////////
  // TEST CONTROLLER EXECUTES DURING EVERY FUNDAMENTAL COMMAND //
  ///////////////////////////////////////////////////////////////

  def testController() {

    // Save error details for generic error reporting
    incrementErrorDetails()

    // Terminate test run if STOP condition met
    if (TEST_STOP) {
      ShutdownTest()
    }

    // Delay next command for duration of TEST_DELAY (change the speed of WebDriver playback
    TEST_DELAY

    // Scrape screen URLs into a file (principally for use in the Performance Tests)
    if (System.getProperty("testDevice") == "URLSCRAPER") {
      if (storedUrl != currentUrl) {
        if (!urlList.contains(getUrlParamName)) {
          var fwUrlList  = new FileWriter(outputFilePath + "URL_SCRAPE_LIST.txt", true)
          fwUrlList.write(getUrlParamName + " (" + currentUrl + ")\n")
          urlList       += getUrlParamName
          fwUrlList.close()
        }
      }
    }

    // Stop WebDriver playback and wait for user input if Feature Step PAUSE command encountered
    if (TEST_PROMPT_CONTINUE) {
      val continue = readLine("<Enter> to continue, \"x\" and <Enter> to resume full speed  ")
      if (continue == "x") {
        TEST_PROMPT_CONTINUE = false
      }
    }

  }


  ///////////////////////////////////////
  // HANDLE FUNDAMENTAL COMMAND ERRORS //
  ///////////////////////////////////////

  def catchErr(tryResult: Try[Unit], element: String = "no element", value: String = "no value") {
    tryResult match {
      case Success(s) =>
      case Failure(e) => thrownException = e.getMessage
        errorElement = element
        errorValue = value
        // Trigger a scala failure and a print of the scala stack to Cucumber
        assert(e.getMessage == "PRINT ERR")
    }
  }


  ////////////////////////////////////////////////
  // OLD NAVIGATION COMMANDS - NEED REFACTORING //
  ////////////////////////////////////////////////

  def waitFor(itemType: String, itemToWaitFor: String) {
    var i = 0
    while (i <= 10) {
      if (containsBoolean("URL", itemToWaitFor)) {
        i = 11
      } else {
        i += 1
        Thread.sleep(100)
        if (i == 10) {
          // Impossible assert to force unmatch failure in Cucumber
          assert("Expected " + itemType + ": \"" + itemToWaitFor == currentUrl)
        }
      }
    }
  }

  def containsBoolean(containsType: String, input: String): Boolean = {
    var returnBoolean: Boolean = false
    containsType match {
      // currentUrl
      case "URL" => if (driver.getCurrentUrl.contains(input)) {
        returnBoolean = true
      }
      //case "URL" => if (currentUrl.contains(input)) {returnBoolean = true}
      case "TEXT" => if (driver.getPageSource.contains(input)) {
        returnBoolean = true
      }
    }
    returnBoolean
  }

  def verifyUrl(urlText: String) {
    longFluentWait.until(ExpectedConditions.urlContains(urlText))
    driver.getCurrentUrl should include(urlText)
  }

  def assertUrl(url: String) {
    tryEx = Try {
      waitFor("URL", getParam(url))
    }
    catchErr(tryEx, url)
  }

  def navigateTo(url: String) {
    navigate(url)
    waitFor("URL", getParam(url))
  }

  def assertUrlNot(url: String) = driver.getCurrentUrl shouldNot be(basePageUrl + url)

  def WaitForUrlChange(inputUrl: String) {
    var i = 0
    while (i <= 10) {
      if (inputUrl != currentUrl) {
        i = 11
      } else {
        i += 1
        Thread.sleep(100)
        if (i == 10) {
          // Impossible assert to force unmatch failure in Cucumber
          assert(inputUrl == currentUrl)
        }
      }
    }
  }

  def ShutdownTest() = driver.quit()

}