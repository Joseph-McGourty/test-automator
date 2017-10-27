package uk.gov.hmrc.integration.cucumber.utils

import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._
import sys.process._


object ParametersTemplate {

  //***************************************************************************************//
  //******************************* CONFIGURABLE PARAMETERS *******************************//
  //***************************************************************************************//

  // SCREEN PRINTS - ON/OFF
  val SCREEN_PRINTS_ON = false
  // Enable BasePage.screenPrint function Globally - each individual call to screenPrint filters what will be printed

  // REPLACE EXISTING SCREEN PRINT of the same name - ON/OFF
  val REPLACE_EXISTING_SCREEN_PRINT = true

  // DEBUG PRINTING LEVEL - OFF/ALL/Debug/Error/Info
  val PRINT_LEVEL = "OFF"
  // OFF = no printing : ALL = output every type of print : Debug = print debug messages : Error = print error messages : Info = print information messages

  // URL map lives in the parameter file to prevent it being reset when the BasePage opens/closes
  val currentDir      = new java.io.File(".").getCanonicalPath
  val outputFilePath  = currentDir + "/PT_output/"
  val inputFilePath   = currentDir + "/PT_input/"

  // Fluent Wait settings
  val LONG_WAIT_TIME                    = 15
  // seconds
  val POLLING_INTERVAL_LONG_WAIT_TIME   = 3
  // seconds
  val SHORT_WAIT_TIME                   = 1
  // seconds
  val POLLING_INTERVAL_SHORT_WAIT_TIME  = 250 // milliseconds

  // LOGGING OPTIONS (switch each log message type on/off individually)
  var SWITCH_LOGGING_OFF                = false // OVERRIDES ALL OTHER LOGGING SETTINGS
  val LOG_ERRORS                        = true
  val LOG_ERROR_DETAILS                 = true
  val LOG_SUCCESS                       = true
  val LOG_TOTALS                        = true
  val QUIT_AFTER_X_ERRORS               = 3000
  // Maximum number of Chars to print from the Scala Exception message (some can be thousands of chars long)
  val MAX_EXCEPTION_CHARS, MAX_PADDING  = 200

  // Global reporting variables
  var testSuccessCount      = 0
  var testFailCount         = 0
  val logFileTime           = dateTime()
  var logCounter            = 0
  var currentTest           = ""
  var SCENARIO_PRINTED      = false
  var featureScreenCount    = 0
  var lastURL               = ""
  var lastScreen            = ""
  // File name needs to be in a string for sed to access it
  val fileName              = "ITS_Test_Execution_Log_" + logFileTime + ".txt"
  var thrownException       = ""
  var errorElement: String  = ""
  var errorValue:   String  = ""

  // Scenario Variables
  var scenarioName      = ""
  var scenarioTag       = ""
  var scenarioStatus    = ""
  var lastScenarioName  = ""

  // Test Playback variables
  var TEST_PAUSE            = Thread.sleep(0)
  var TEST_STOP             = false
  var TEST_PROMPT_CONTINUE  = false

  // BROWSERSTACK - Switch Base URLs to use IP address rather than host URL for Browserstack testing of Phones and Pads (all other testing is done via URL but Phones and Pads only work with IPs).
  var testDevice    = System.getProperty("testDevice")
  // Detect Phones / Pads that work on Browserstack.com website. Note - most don't, which is why they aren't included here.
  var PHONE_OR_PAD  = testDevice match {
    case "BS_iOS_iPhone5S_v7" => true
    case "iPad_Air_v8_3" | "iPad_Mini_v7" => true
    case _ => false
  }

  //**************************************************************************************//
  //*********************************** URL PARAMETERS ***********************************//
  //**************************************************************************************//

  final lazy val  basePageUrl: String     = setEnvVariables("basePageUrl")
  final lazy val  ggSignInUrl: String     = setEnvVariables("ggSignInUrl")
  final lazy val  authUrl: String         = setEnvVariables("authUrl")
  final lazy val  redirectionUrl: String  = setEnvVariables("redirectionUrl")
  final lazy val  preferenceUrl: String   = setEnvVariables("preferenceUrl")
  val             environmentProperty     = System.getProperty("environment", "local").toLowerCase

  def getHostIP = "hostname -I".!!.takeWhile(_ != ' ')

  def setEnvVariables(setUrl: String): String = {

    var returnVar = ""
    var path      = ""
    var env       = environmentProperty

    environmentProperty match {
      case "dev" =>
        path = "https://www-dev.tax.service.gov.uk"
      case "qa" =>
        path = "https://www-qa.tax.service.gov.uk"
      case "staging" =>
        path = "https://www-staging.tax.service.gov.uk"
//      case "preview" =>
//        path = "https://preview.tax.service.gov.uk"
      case "local" =>
        // Localhost paths are not generic
        if (PHONE_OR_PAD) path = "http://" + getHostIP + ":" else path = "http://" + "localhost:"
    }

    if (setUrl == "basePageUrl" | setUrl == "redirectionUrl") returnVar = path + {if (env == "local") "9563" else ""} + "/report-quarterly/income-and-expenses/sign-up/client"
    if (setUrl == "ggSignInUrl")                              returnVar = path + {if (env == "local") "9025" else ""} + "/gg-sign-in"
    if (setUrl == "authUrl")                                  returnVar = path + {if (env == "local") "9949" else ""}
    if (setUrl == "preferenceUrl")                            returnVar = path + {if (env == "local") "9024" else ""}

    returnVar

  }

  // NOTE - in Chrome the "basePageUrl" in the redirection is filled with auto text that can't be predicted or matched to
  val GG_REDIRECTION_URL_FULL   = "?continue=" + basePageUrl + "/index&origin=income-tax-subscription-agent-frontend"
  val GG_REDIRECTION_URL_START  = "?continue="
  val GG_REDIRECTION_URL_END    = "/index&origin=income-tax-subscription-agent-frontend"

  // Set tokens for URLs, Buttons and Links
  def getParam(paramName: String): String = {
    val returnUrl: String = paramName match {

      case _ => paramName

    }

    returnUrl

  }

}