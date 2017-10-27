package uk.gov.hmrc.integration.cucumber.stepdefs.Wave

import java.awt.Robot
import java.awt.event.KeyEvent
import java.io.File

import cucumber.api.scala.{EN, ScalaDsl}
import org.apache.commons.io.FileUtils
import org.openqa.selenium._
import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.Matchers
import org.scalatest.selenium.WebBrowser
import uk.gov.hmrc.integration.cucumber.utils.WaveReport

class WaveStepDef extends ScalaDsl with EN with Matchers with WebBrowser with TemplateSteps{

  var waveReportFlag = "N"
  var waveFailFlag = "N"

  var totalAlerts = 0
  var totalErrors = 0

  And( """^click the WAVE button for a HTML Report$""") {

    val robot = new Robot()
    robot.keyPress(KeyEvent.VK_CONTROL)
    robot.keyPress(KeyEvent.VK_SHIFT)
    robot.keyPress(KeyEvent.VK_7)
    robot.delay(100)
    robot.keyRelease(KeyEvent.VK_CONTROL)
    robot.keyRelease(KeyEvent.VK_SHIFT)
    robot.keyRelease(KeyEvent.VK_7)
    val pageUrl = driver.getCurrentUrl
    val pageTitle = driver.getTitle
    val dir = new File("target/wave-reports")
    dir.mkdir()

    val errorElements = driver.findElements(By.xpath("//main//img[starts-with(@alt,'ERRORS:')]"))
    val errorsCount = errorElements.size()
    totalErrors += errorsCount

    val alertElements = driver.findElements(By.xpath("//main//img[starts-with(@alt,'ALERTS:')]"))
    val alertCount = alertElements.size()
    totalAlerts += alertCount

    var testStatus = "Passed"
    if (errorsCount>0) {
      testStatus = "Failed"
      waveFailFlag="Y"
    }
    WaveReport.buildWaveHTMLReport(testStatus,pageTitle,alertCount,errorsCount,waveReportFlag,pageUrl )
    waveReportFlag = "Y"

    robot.keyPress(KeyEvent.VK_CONTROL)
    robot.keyPress(KeyEvent.VK_SHIFT)
    robot.keyPress(KeyEvent.VK_7)
    robot.delay(100)
    robot.keyRelease(KeyEvent.VK_CONTROL)
    robot.keyRelease(KeyEvent.VK_SHIFT)
    robot.keyRelease(KeyEvent.VK_7)

    if (ExpectedConditions.alertIsPresent() != null)
      try {
        driver.navigate().refresh()
      } catch {
        case uaEx: UnhandledAlertException => {}
        case e: Exception => {
          throw e
        }
      }
    {
      try {
        val alert = driver.switchTo().alert()
        alert.accept()
      }
      catch {
        case napEx: NoAlertPresentException => {}
        case e: Exception => {
          throw e
        }
      }
    }
  }

  Then( """^I click on the Wave extension for HTML Report. With errors=(.*)$""") { (withErrors: Boolean) =>

    val robot = new Robot()
    robot.keyPress(KeyEvent.VK_CONTROL)
    robot.keyPress(KeyEvent.VK_SHIFT)
    robot.keyPress(KeyEvent.VK_7)
    robot.delay(100)
    robot.keyRelease(KeyEvent.VK_CONTROL)
    robot.keyRelease(KeyEvent.VK_SHIFT)
    robot.keyRelease(KeyEvent.VK_7)
    val pageUrl = driver.getCurrentUrl
    val pageTitle = driver.getTitle.concat(withErrors match {
      case true => " - Shown With Error Messages"
      case _ => " - Shown Without Error Messages"
    })
    val dir = new File("target/wave-reports")
    dir.mkdir()


    val errorElements = driver.findElements(By.xpath("//main//img[starts-with(@alt,'ERRORS:')]"))
    val errorsCount = errorElements.size()
    totalErrors += errorsCount
    //println(s"Errors in current page (${pageUrl}) = ${errorsCount}")


    val alertElements = driver.findElements(By.xpath("//main//img[starts-with(@alt,'ALERTS:')]"))
    val alertCount = alertElements.size()
    totalAlerts += alertCount
    //println(s"Alerts in current page (${pageUrl}) = ${alertCount}")

    var testStatus = "Passed"
    if (errorsCount>0)
    {
      testStatus = "Failed"
      waveFailFlag="Y"
    }
    WaveReport.buildWaveHTMLReport(testStatus,pageTitle,alertCount,errorsCount,waveReportFlag,pageUrl )
    waveReportFlag = "Y"

    robot.keyPress(KeyEvent.VK_CONTROL)
    robot.keyPress(KeyEvent.VK_SHIFT)
    robot.keyPress(KeyEvent.VK_7)
    robot.delay(100)
    robot.keyRelease(KeyEvent.VK_CONTROL)
    robot.keyRelease(KeyEvent.VK_SHIFT)
    robot.keyRelease(KeyEvent.VK_7)

  }

  Then( """^I save the Wave Accessibility Test Report$""") { () =>
    val source = new File("src/test/Images/wavelogo.png")
    val dest = new File("target/wave-reports/wavelogo.png")
    try {
      FileUtils.copyFile(source, dest)
    } catch {
      case e: Throwable => ()//println(s"non-clickable object = e = ${e.getMessage}")
    }
    WaveReport.createAccessiblityReport()
    if(totalErrors > 0) {
      driver.quit()
      assert(false, "this site has accessibility errors and/or alerts")
    }

  }

  Then( """^save the WAVE Accessibility Test Report$""") { () =>
    val source = new File("src/test/Images/wavelogo.png")
    val dest = new File("target/wave-reports/wavelogo.png")
    try {
      FileUtils.copyFile(source, dest)
    } catch {
      case e: Throwable => ()//println(s"non-clickable object = e = ${e.getMessage}")
    }
    WaveReport.createAccessiblityReport()
    if(totalErrors > 0) {
      driver.quit()
      assert(false, "this site has accessibility errors and/or alerts")
    }

  }

}