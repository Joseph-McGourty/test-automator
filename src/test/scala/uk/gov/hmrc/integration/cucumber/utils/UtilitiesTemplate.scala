package uk.gov.hmrc.integration.cucumber.utils

import java.io.{File, FileWriter}

import org.apache.commons.io.FileUtils
import org.joda.time.DateTime
import org.openqa.selenium._
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._

import sys.process._


object UtilitiesTemplate {

  // Contains most recent "action" screen's name - actions are "read", "write", "click" etc.
  var actionScreen  = ""
  val currentDir    = new java.io.File(".").getCanonicalPath
  val logPath       = currentDir + "/test-execution-log"


  /////////////////////////////////////////
  // GENERIC NON-PAGE SPECIFIC FUNCTIONS //
  /////////////////////////////////////////


  //////////////////////////////////////////////////////////////
  // PRINT DEBUG MESSAGES - CONFIGURED IN PARAMETERS TEMPLATE //
  //////////////////////////////////////////////////////////////

  def print(text: String, level: String) {

    //    val printLevel = "OFF" // OFF : ALL : Debug : Error : Info
    if (PRINT_LEVEL != "OFF") {
      if (PRINT_LEVEL == "ALL" || level == "Debug" || level == "Error" || level == "Info") {
        if (level == PRINT_LEVEL || level == "ALL") {
          println(text.toString)
        }
      }
    }

  }

  def refresh() = driver.navigate.refresh()


  //////////////////////////////////////////////////
  // CREATE & SAVE SCREEN PRINTS TO LOG DIRECTORY //
  //////////////////////////////////////////////////

  def screenPrint(replace: Boolean, scenario: String, screenName: String, error: Boolean) {

    if (environmentProperty == "local") {

      val displayError = if (error) "ERROR_" else ""

      if (SCREEN_PRINTS_ON || error) {
        // Remove characters that shouldn't print in a screen print's file name and check if the file already exists
        val outputScreenName    = screenName.replace("?", "").replace("'", "")
        val scenarioName        = scenario.replace("?", "").replace("'", "")
        val fileExists: Boolean = new java.io.File(logPath + File.separator + scenario + "_" + outputScreenName + "".endsWith(".png")).exists

        if (fileExists && !replace && !error) {
          // Don't replace existing file
        } else {
          // fileExists && replace or !fileExists (replace irrelevant) or error
          val now                 = dateTime()
          val screenshotInMemory  = driver.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.FILE)
          val fileToSaveTo        = new File(logPath + File.separator + displayError + scenario + "_" + outputScreenName + "_" + now + ".png")
          FileUtils.copyFile(screenshotInMemory, fileToSaveTo)
        }

      }

    }

  }


  ///////////////////////////////////////////////////
  // LOAD MOST RECENT AFTER HOOK ERROR INTO MEMORY //
  ///////////////////////////////////////////////////

  def incrementErrorDetails() {

    // Record screen name and scenario step number for use in error reporting
    actionScreen = driver.getTitle

    if (lastScenarioName != scenarioName) {
      featureScreenCount = 1
    } else {
      if (lastScreen != actionScreen) {
        featureScreenCount += 1
      }
    }

    lastScreen        = actionScreen
    lastScenarioName  = scenarioName

  }


  ///////////////////////////////////////////////////////////////////////
  // LOC SCENARIO SUCCESSES/ERRORS - CONFIGURED IN PARAMETERS TEMPLATE //
  ///////////////////////////////////////////////////////////////////////

  // writeTotals should only be set the final time the log is to be written to
  // ONLY RECORD ERRORS OPTION
  def logResult(logType: String, scenario: String, throwException: String) {

    if (environmentProperty == "local" && !SWITCH_LOGGING_OFF) {

      logCounter       += 1
      val numField      = 4
      val nameField     = 30
      val dividingLine  = 125
      val blank         = ""
      var screenName    = driver.getTitle

      // Remove totals before each write so only the final totals will remain
      if (logCounter >= 2) {
        for (a <- 1 to 4) {
          Seq("sed", "-i", "$ d", logPath + "/" + fileName) !
        }
      }

      val fw = new FileWriter(logPath + File.separator + fileName, true)

      if (testSuccessCount + testFailCount == 0) {
        fw.write("ITSA Test Execution Log - " + logFileTime + "\n")
        if (LOG_ERRORS || LOG_SUCCESS) {
          fw.write("\n" + "No  | " + "Scenario Name".padTo(nameField, " ").mkString + " | Scenario Result   (Fail Details)" + "\n" + "".padTo(dividingLine, "_").mkString + "\n\n")
        }
      }

      // featureScreenCount prevents report from printing if automater failes with a compilation error before executing
      if (logType == "Error" | featureScreenCount == 0) {

        // Get latest error details as test controller has to fire before find BY for Type reasons and doesn't hold the current screen
        incrementErrorDetails()
        testFailCount += 1

        if (LOG_ERRORS) {
          fw.append(logCounter.toString.padTo(numField, " ").mkString + "| " + scenario.padTo(nameField, " ").mkString.substring(0, nameField) + " | FAILED WITH: " + throwException.filter(_ >= ' ').padTo(MAX_PADDING, " ").mkString.substring(0, MAX_EXCEPTION_CHARS) + " TRIMMED TO " + MAX_EXCEPTION_CHARS + " CHARS")
        }

        if (LOG_ERROR_DETAILS) {
          //} && parameterMap.size > 1) { // a map of "" -> "" has a size of 1
          fw.append("\n" + blank.padTo(numField, " ").mkString + "| " + "DETAILS:".padTo(nameField, " ").mkString + " |      Screen: " + actionScreen + "     Field: " + errorElement + "     Field Value: " + errorValue + "     Scenario Screen Changes at Fail: " + featureScreenCount)
        }


        screenPrint(REPLACE_EXISTING_SCREEN_PRINT, scenario, screenName, true)

        if (testFailCount == QUIT_AFTER_X_ERRORS) {
          // Abort execution after parameterised number of errors
          fw.append("\n\n" + blank.padTo(numField, " ").mkString + "  ABORTED WHEN QUIT_AFTER_X_ERRORS PARAMETER REACHED " + QUIT_AFTER_X_ERRORS)
          println("*****************************************************************")
          println("SCRIPT ABORTED WHEN QUIT_AFTER_X_ERRORS PARAMETER OF " + QUIT_AFTER_X_ERRORS + " REACHED")
          println("*****************************************************************")
          SWITCH_LOGGING_OFF = true
          ShutdownTest()
        }

      } else if (logType == "Success") {

        testSuccessCount += 1

        if (LOG_SUCCESS) {
          fw.append(logCounter.toString.padTo(numField, " ").mkString + "| " + scenario.padTo(nameField, " ").mkString.substring(0, nameField) + " | SUCCESS")
        }

      } else if (logType == "Totals") {

        if (LOG_TOTALS) {
          fw.append("\n" + "".padTo(dividingLine, "_").mkString + "\n\nTOTALS\nSCENARIOS: " + (testSuccessCount + testFailCount) + "   PASS: " + testSuccessCount + "    FAIL: " + testFailCount + "\n")
        }

      }

      if (LOG_TOTALS && !(logType == "Totals")) {
        fw.append("\n" + "".padTo(dividingLine, "_").mkString + "\n\nTOTALS\nSCENARIOS: " + (testSuccessCount + testFailCount) + "   PASS: " + testSuccessCount + "    FAIL: " + testFailCount + "\n")
      }

      fw.close()
    }
  }

  def dateTime(): String = {
    val now = DateTime.now.toString("dd-MM-yyyy_HH:mm:ss:SSS")
    now
  }

}