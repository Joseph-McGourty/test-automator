package uk.gov.hmrc.integration.cucumber.utils

import org.openqa.selenium.{By, JavascriptExecutor, WebElement}
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._


object GeneralUtilities {

  var globalScreenMap     = scala.collection.mutable.Map[String, String]()

  //******************************************************************************
  //********** ADDITIONAL SCREEN FUNCTIONS READY FOR USE *************************
  //******************************************************************************

  // Check screen links by supplying either ("Text" / "Class") and then ("Link Text" / "Class Name", Screen Header Text, Screen Contains Text, Screen URL, Close tab if opened). Nulls are ignored.
  // I.E. (1 -> ("Class type", "copyright link name", "Crown copyright header", "body text", urlConstant, "closeNewTabYES"))
  def checkPageLinks(linkMap: scala.collection.mutable.Map[Int, (String, String, String, String, String, String)], page: String) {
    val mapLoop = linkMap
    print("Checking links on \"" + page + "\" screen.", "Info")
    mapLoop.foreach((record) =>
      if (record._2._1 == "Text") {
        checkTextLink(record._2._2, record._2._3, record._2._4, record._2._5, record._2._6)
      } else if (record._2._1 == "Class") {
        checkClassLink(record._2._2, record._2._3, record._2._4, record._2._5, record._2._6)
      }
    )
  }

  def checkTextLink(linkText: String, header: String, body: String, url: String, closeNewTab: String) {
    clickByLinkText(linkText)
    verifyLink(linkText, header, body, url, closeNewTab)
  }

  def checkClassLink(className: String, header: String, body: String, url: String, closeNewTab: String) {
    clickByClassLink(className)
    verifyLink(className, header, body, url, closeNewTab)
  }

  def clickByLinkText(linkText: String)   = driver.findElement(By.linkText(linkText)).click()

  def clickByClassLink(className: String) = driver.findElement(By.className(className)).click()

  def verifyLink(linkText: String, header: String, body: String, url: String, closeNewTab: String) {
    print("The following link or Tab was tested: " + linkText, "Info")
    if (closeNewTab == "closeNewTabYES") {
      val origWindow = driver.getWindowHandle
      var allWindows = driver.getWindowHandles
      val newWindow: Array[AnyRef] = allWindows.toArray()
      // Pause to try and avoid Array Out of Bounds Error
      Thread.sleep(1000)
      driver.switchTo().window(newWindow(1).toString)
      verifyPageText(header, body, url)
      driver.close()
      driver.switchTo().window(origWindow)
    } else {
      verifyPageText(header, body, url)
      tab("back", 1)
    }
    // In case of navigation delays - sleep a little
    Thread.sleep(100)
  }

  def verifyPageText(header: String, body: String, url: String) {
    if (!header.isEmpty) {
      verifyHeaderText(header)
    }
    if (!body.isEmpty) {
      pageContainsText(body)
    }
    if (!url.isEmpty) {
      verifyUrl(url)
    }
    print("Link with header \"" + header + "\" and Body \"" + body + "\" and URL \"" + url + "\" checked", "Info")
  }

  def verifyHeaderText(text: String) = driver.findElement(By.cssSelector("h1")).getText shouldBe text

  def pageContainsText(text: String) = assert(driver.getPageSource.contains(text))

  //******************************************************************************
  //********** DEFINITIONS TO VERIFY SCREEN LINKS ********************************
  //******************************************************************************

  // TO CALL THE CHECKLINKS FUNCTION:
  //def checkLinks() {
  //  var linkMap = scala.collection.mutable.Map[Int, (String, String, String, String, String, String)]()
  //  linkMap += (1 -> ("Text", "Overview", "VAT Flat Rate Scheme", "1. Overview", urlOverview, ""))
  //  linkMap += (2 -> ("Text", "Join or leave the scheme", "VAT Flat Rate Scheme", "2. Join or leave the scheme", urlJoinOrLeaveTheScheme, ""))
  //  linkMap += (3 -> ("Text", "Eligibility", "VAT Flat Rate Scheme", "3. Eligibility", urlEligibility, ""))
  //  linkMap += (4 -> ("Text", "Businesses and charging VAT", "", "", urlBusiness, ""))
  //  linkMap += (5 -> ("Text", "VAT Annual Accounting Scheme", "", "", urlAnnualAccountingScheme, ""))
  //  linkMap += (6 -> ("Text", "VAT Cash Accounting Scheme", "", "", urlCashAccountingScheme, ""))
  //  linkMap += (7 -> ("Text", "VAT Returns", "", "", urlReturns, ""))
  //  linkMap += (8 -> ("Text", "More", "", "", urlMore, ""))
  //  linkMap += (9 -> ("Text", "Home", "How often do you do your VAT returns?", "", urlReturnPeriod, ""))
  //  checkSwitchUrl(vfrHowMuchYouPayUrl)
  //  checkPageLinks(linkMap, "Limited Costs")
  //}

  //  def checkSwitchUrl(Url: String) {
  //    val curUrl: String = driver.getCurrentUrl
  //    if (curUrl != basePageUrl + Url) {
  //      navigateTo(basePageUrl + Url)
  //    }
  //  }

  def back() = driver.navigate().back()

  def forward() = driver.navigate().forward()

  def tab(direction: String, times: Int) {
    val url = currentUrl
    (1 to times).foreach { _ =>
      if (direction == "back") back()
      if (direction == "forward") forward()
    }
    if (url == currentUrl) Thread.sleep(5000)
  }

  //******************************************************************************
  //********** CHECK FIELD VALIDATION WITH NEGATIVE AND POSITIVE VECTOR ATTACKS **
  //******************************************************************************

  def fieldValidationNegativeCheck(field: String, value: Vector[String], pageText: String, urlToken: String, action: String) {
    var errorCounter = 0 // ERROR means the field ACCEPTED INVALID INPUT without erroring)
    for (value <- value) {
      write("id", field, value)
      action match {
        case "continueButton" => click("condinut-button")
      }
      if (!pageContainsTextBoolean(pageText)) {
        errorCounter += 1
        print("Field \"" + field + "\" accepted the invalid input value \"" + value + "\".", "Debug")
        // Return to previous page ready to insert more invalid values.
        print(basePageUrl + urlToken, "Info")
        navigateTo(basePageUrl + urlToken)
      }
    }
    assert(errorCounter == 0, "Field validation for field \"" + field + "\" has failed. " + errorCounter + " invalid inputs were accepted from the expected fail catalogue.")
  }

  def pageContainsTextBoolean(text: String): Boolean  = driver.getPageSource.contains(text)

  def fieldValidationPositiveCheck(field: String, value: Vector[String], pageText: String, urlToken: String, action: String) {
    var errorCounter = 0 // ERROR means the field REJECTED VALID INPUT (with an error))
    for (value <- value) {
      write("id", field, value)
      action match {
        case "continueButton" => click("continue-button")
      }
      if (pageContainsTextBoolean(pageText)) {
        errorCounter += 1
        print("Field \"" + field + "\" rejected the valid input value \"" + value + "\".", "Debug")
      } else {
        // Return to previous page ready to insert more invalid values
        print(basePageUrl + urlToken, "Info")
        navigateTo(basePageUrl + urlToken)
      }
    }
    assert(errorCounter == 0, "Field validation for field \"" + field + "\" has failed. " + errorCounter + " valid inputs were rejected from the expected success catalogue.")
  }

  //******************************************************************************
  //********** READ A SCREEN TABLE INTO MEMORY ***********************************
  //******************************************************************************

  def setGlobalScreenMap(): scala.collection.mutable.Map[String, String] = {

    var rowCount: Int         = driver.findElements(By.xpath("//table/tbody/tr")).size()
    val baseTable: WebElement = driver.findElement(By.tagName("table"))
    var loopCount             = 0
    var elementCount          = 1
    var index: String         = ""
    var value: String         = ""

    print("TABLE ROW COUNT IS: " + rowCount, "Debug")

    for (loopCount <- 0 until rowCount - 1) {

      var trPath1               = "//tr[" + elementCount + "]/td[1]"
      var trPath2               = "//tr[" + elementCount + "]/td[2]"
      var tableRow: WebElement  = baseTable.findElement(By.xpath(trPath1))

      index = tableRow.findElement(By.xpath(trPath1)).getText
      value = tableRow.findElement(By.xpath(trPath2)).getText + "%"

      elementCount    += 1
      globalScreenMap += (index -> value)

    }

    globalScreenMap

  }

  def findOffScreenField(by: By): WebElement = {

    val element: WebElement = driver.findElement(by)
    // jump to the element cos otherwise selenium may not function properly if the element is not visible on screen
    driver.asInstanceOf[JavascriptExecutor].executeScript("arguments[0].scrollIntoView(true);", element)
    shortFluentWait.until(ExpectedConditions.presenceOfElementLocated(by))
    driver.findElement(by)

  }

}