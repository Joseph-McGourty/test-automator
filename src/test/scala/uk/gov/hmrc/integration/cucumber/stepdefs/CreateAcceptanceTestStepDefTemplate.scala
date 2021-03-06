package uk.gov.hmrc.integration.cucumber.stepdefs

import cucumber.api.Scenario
import java.io.{File, FileWriter}

import cucumber.api.scala.{EN, ScalaDsl}
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate

import scala.reflect.io.Directory
import sys.process._
import scala.math._
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._

import scala.util.control.Breaks._

//******************************************************************************
//********** LIST OF POTENTIAL CODE CHANGES / ADDITIONS ************************
//******************************************************************************

// Extend Background to have a BACKGROUND END option (for more than 1 line to be in the background)
// Settings to create each feature event as a single row
// Configure writer for all BasePage commands & WebDriver commands
// Front Page to run convertors

//**** Browserstacks

// Good wording for feature files - way of dividing things into scenarios etc

//** Background error reporting - code lexing errors into controller

// Widgets for drive & write so spreadsheet, drive & write to Mongo etc.
// Regex and english language the create test functions
// Option to put all Features / Steps in one file
// Redirect the output locations of files (repository directory or base or APPEND (params & features & steps)
// Make the filepath dynamic
// Parameters to buffer so can order before printing
// Before / After hook toggle
// driver.close in feature
// Have a report cleaner which reads the original and creates a new one with only the desired rows in (replace DETAILS with "" etc)

//** if input/output directories don't exist, make them

// Option for non write based commands to use parameters or the real field names

//** Single page of test parameters
//** Parameterise file paths

// put all the "click" fields into getParams on parameter sheet

//** Mike wants auto text for Auth Login etc

//** Replace local host in URL parameters with $path - assume all are basepage & create 2 URLs toggled by boolean - real or path (parameterise this)
//** Parameterise the localBaseUrl type variables as these will differ from project to project
//** Put basePageUrl variable conversion stuff into parameters so users can set their own variable names.

// When changing StepsOn boolean - need to delete the other definitions or there will be a DUPLICATE STEPS error
// What if the input file is blank
//** Store Basepage / Base StepDef / Create AT, PT etc in case of accidental deletion
//** Delete existing steps / features option (if there are lexing errors etc they can cause a failure during the create command.
//** If there's a lex the stepdefinition after hooks don't print it because the step definitions aren't called > error handle this from the BasePage?
// Auto update runners like runner WIP with new features?
//** Add widget to put Before / After Hooks in all stepdefs (as existing locally created ones won't have these).

// MIKE'S IDEAS
// Wants collective stepdef DONE
//** How do we append to existing?
//** Can we parameterise output file - and auto output to new repository (as an append rather than an overwrite?????)
//** Can append pull in existing and not duplicate.
//** Can we draw in existing items via comments in the input file.
//**** HAVE AN OBJECT FOR EACH PAGE?
//** Have a base page template so it writes one automatically.
//** Auto write Base Step Def items (if not there)

// KRUTIKA IDEAS
//**** Does it work for popups
//** Can we make the errors clearer

// MORGAN'S IDEAS
// Also check popups
//** Wants to use his own generic continue buttons

// Ensure ALL LOOP VARIABLES NULLED before next loop
//**** If CLICK sentences match then the first one in that format sets the name of the step def variable - even though the item being clicked may change from use to use. Change to generic?

// SIMON'S IDEAS
// Code for conditional processing in Selenium IDE
// Automatically code so can append new pages etc to existing scripts.
//** Read in step definition sentences before start so re-use / lex process as if all were being created simultaneously.

class CreateAcceptanceTestStepDefTemplate extends ScalaDsl with EN {

  Before() { scenario: Scenario =>
    scenarioName = scenario.getName
    SCENARIO_PRINTED = false
  }

  When("""^Scala files are read and performance test URLs are (false|true) ACCEPTANCE TESTS ARE CREATED$""") { (urlScraperActive: Boolean) =>

    //*************************************************************************************************************************************************//
    //********** USER CONFIGURABLE PARAMETERS *********************************************************************************************************//
    //*************************************************************************************************************************************************//
    // Set output command type for Step Files                                                                               //*************************//
    val webDriverCommandType              = "simplified"  // originalWebDriver // simplified // other team's stuff          //*************************//
    val commonStepDefFileOn               = true                                                                            //*************************//
    val printBackground                   = false                                                                           //*************************//
    val stepsOn                           = false                                                                           //*************************//
    val extraScenarioExampleRows          = 2     // number of additional rows printed for each Scenario Outline            //*************************//
    val blankExamples                     = false // Either make the additional rows blank, or repeat the first row's value //*************************//
    val loadFeatureAndStepDefDirs         = true  // Copy AT_output files to the Feature and StepDefs folders               //*************************//
    val includeFeatureFirstRowInWaveTest  = false // As first row of Feature is usually Given URL - exclue from WAVE tests  //*************************//
    val waveTestLastRowOfEachScenario     = true  // Add wave test after final row of each scenario for WAVE files          //*************************//
    //*************************************************************************************************************************************************//
    //********** USER CONFIGURABLE OUTPUT FILE DIRECTORIES (OPTIONAL). Local repository = "" **********************************************************//
    //*************************************************************************************************************************************************//
    // USER CONFIGURABLE OUTPUT DIRECTORIES FOR FEATURE FILES / STEP DEFINITION FILES / PARAMETER FILES (paths default to current repository if empty) //
    //        val userStepDefFilePath   = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/income-tax-subscription-agent-acceptance-tests-REFACTOR" + "/src/test/scala/uk/gov/hmrc/integration/cucumber/stepdefs/"
    //        val userFeatureFilePath   = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/income-tax-subscription-agent-acceptance-tests-REFACTOR" + "/src/test/resources/features/"
    //        val userParameterFilePath = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/income-tax-subscription-agent-acceptance-tests-REFACTOR" + "/src/test/scala/uk/gov/hmrc/integration/cucumber/utils/"
    //        val userStepDefFilePath   = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/vat-flat-rate-calculator-acceptance-tests" + "/src/test/scala/uk/gov/hmrc/integration/cucumber/stepdefs/"
    //        val userFeatureFilePath   = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/vat-flat-rate-calculator-acceptance-tests" + "/src/test/resources/features/"
    //        val userParameterFilePath = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/vat-flat-rate-calculator-acceptance-tests" + "/src/test/scala/uk/gov/hmrc/integration/cucumber/utils/"
    val userStepDefFilePath               = ""    // File location (repository) to send step definition files to            //*************************//
    val userFeatureFilePath               = ""    // File location (repository) to send feature files to                    //*************************//
    val userParameterFilePath             = ""    // File location (repository) to send parameter file entries to           //*************************//
    //*************************************************************************************************************************************************//
    //********** END OF USER CONFIGURABLE PARAMETERS **************************************************************************************************//
    //*************************************************************************************************************************************************//

    var logFileTime         = dateTime()
    var loopRow             = ""
    var previousLoopRow     = ""
    var paramClickFieldName = ""
    var waveTest            = false // NOT a user confirgurable boolean


    //******************************************************************************
    //********** FEATURE / STEP SENTENCE BUILDING DEFINITIONS **********************
    //******************************************************************************

    // First "" quoted element of input string
    def firstStringInRow      = loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1)) + "'\n"
    // Parameterise field names
    def xAllCaps              = loopRow.split('-').map(_.capitalize).mkString("-").split('.').map(_.capitalize).mkString(".").split('"').map(_.capitalize).mkString("\"").split(' ').map(_.capitalize).mkString(" ")
    def fieldName             = "field" + xAllCaps.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1)).replaceAll("-", "").replaceAll("'", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\.", "")
    // Parameterise background url name - reverse is used to cut the penultimate uri as this is auth-login-stub
    def bgUrlReverse          = loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1)).reverse
    def bgUrlParam            = "      case url" + bgUrlReverse.substring(bgUrlReverse.indexOf("/") + 1, bgUrlReverse.indexOf("/", loopRow.indexOf("/") + 1)).reverse.split('-').map(_.capitalize).mkString("-").replaceAll("-", "") + " = \"" + loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1))
    def bgUrl                 = "url" + bgUrlReverse.substring(0, bgUrlReverse.indexOf("/")).reverse.split('-').map(_.capitalize).mkString("-").replaceAll("-", "")
    def bgUrlStepPrint        = ("      case \"url" + bgUrlReverse.substring(bgUrlReverse.indexOf("/") + 1, bgUrlReverse.indexOf("/", loopRow.indexOf("/") + 1)).reverse.split('-').map(_.capitalize).mkString("-").replaceAll("-", "") + "\"").padTo(100, ' ') + webDriverElementName.replace(" =", "=>")
    def bgFeature             = "    Given we start on URL '" + firstStringInRow
    def bgUrlValue            = loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1))
    // Store field input values
    def fieldValue            = loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1)).replaceAll("-", "").replaceAll("'", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\.", "")
    def webDriverElementName  = " = \"" + loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1)) + "\"\n"
    // Parameterise URL names
    def urlReverse            = loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1)).reverse
    def urlParam              = "url" + urlReverse.substring(0, urlReverse.indexOf("/")).reverse.split('-').map(_.capitalize).mkString("-").replaceAll("-", "")
    def urlValue              = loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1))

    //******************************************************************************
    //********** WEBDRIVER COMMAND DEFINITIONS (SIMPLE & ORIGINAL) *****************
    //******************************************************************************

    def navigate = webDriverCommandType match {
      //      case "simplified"         => "    navigate(\"" + loopRow.substring(loopRow.indexOf("\"") + 1, loopRow.indexOf("\"", loopRow.indexOf("\"") + 1)) + "\")\n"
      case "simplified"         => "    navigate(" + urlParam + ")\n"
      case "originalWebDriver"  => loopRow.replace(";", "") + "\n"
    }

    def write = webDriverCommandType match {
      case "simplified"         => "    write(" + fieldName + ", " + fieldValue + byOverride(loopRow) + ")\n"
      case "originalWebDriver"  => previousLoopRow.replace(";", "") + "\n" + loopRow.replace(";", "") + "\n"
    }

    def click = webDriverCommandType match {
      case "simplified"         => "    click(" + paramClickFieldName + byOverride(loopRow) + ")\n"
      case "originalWebDriver"  => loopRow.replace(";", "") + "\n"
    }

    def clickRadio = webDriverCommandType match {
      case "simplified"         => "    clickRadio(" + paramClickFieldName + byOverride(loopRow) + ")\n"
      case "originalWebDriver"  => loopRow.replace(";", "") + "\n"
    }

    //******************************************************************************
    //********** GENERAL DEFINITIONS - HEADERS / STEP NUMBER PRINTS ****************
    //******************************************************************************

    def getFeatureHeader(fileName: String, featureNo: Int): String = {
      val background  = if (printBackground) "\n  Background: Starting URL\n\n" else ""
      val waveFeature = if (waveTest) "\n@WAVE" else ""
      if (urlScraperActive) {
        "@URLSCRAPER" +
          "\n\nFeature: Feature Number " + featureNo.toString + " " + fileName.replace(".txt", "") + "\n" +
          background
      } else {
        "@FeatureName" + fileName.replace(".txt", "") + waveFeature +
          "\n\nFeature: Feature Number " + featureNo.toString + " " + fileName.replace(".txt", "") + "\n" +
          background
      }
    }

    def getStepHeader(stepNo: String, fileName: String): String = {
      val urlScraper = if (urlScraperActive) "URLSCRAPER" else ""
      "package uk.gov.hmrc.integration.cucumber.stepdefs\n\nimport cucumber.api.Scenario\nimport cucumber.api.scala.{EN, ScalaDsl}\nimport uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._\nimport uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate\nimport uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._\nimport uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._\nimport org.openqa.selenium.By\n\n" +
        "class " + stepNo + urlScraper + fileName.replace(".txt", "").replace("StepDef", "") + "StepDef extends ScalaDsl with EN {\n\n" +
        "  Before() { scenario: Scenario =>\n" +
        "    scenarioName     = scenario.getName\n" +
        "    SCENARIO_PRINTED = false\n" +
        "  }\n\n"
    }

    var stepWithoutSpace  = ""
    var stepWithSpace     = ""
    var stepNumber        = 1

    def setStepPrints(fileNumber: Int, StepNumber: Int) {
      stepWithoutSpace  = "^"
      stepWithSpace     = " "
      if (stepsOn) {
        stepWithoutSpace  = "^" + fileNumber + "." + stepNumber + " - "
        stepWithSpace     = " " + fileNumber + "." + stepNumber + " - "
      }
    }

    //******************************************************************************
    //********** CLASS LEVEL MATCHING LISTS AND VARIABLES **************************
    //******************************************************************************

    // Class level vars
    var featureNumber   = 1
    var paramList       = scala.collection.mutable.Set[String]()
    var stepList        = scala.collection.mutable.Set[String]()
    var stepListStrings = scala.collection.mutable.Set[String]()
    var commonStepList  = scala.collection.mutable.Set[String]()
    var stepFilesList   = scala.collection.mutable.Set[String]()
    var waveList        = scala.collection.mutable.Set[String]()
    var stepPhrase      = ""

    // Loop level vars
    var stepString                = ""
    var stepParams                = ""
    var stepComma                 = ""
    var featureAnd                = ""
    var featureSentence           = ""
    var stepSentence              = ""
    var newTitle                  = ""
    var featureWhen               = ""
    var unprintedBgFeature        = ""
    var bgStep                    = ""
    var stepRadio                 = false
    var nextPage                  = false
    var scenarioNumber            = 1
    var scenarioStepNumber        = 1
    var firstGetTitle             = true
    var oldTitle                  = ""
    var scenarioOutlineSentence   = ""
    var finalOutline              = ""
    var outlineExampleTitle       = ""
    var outlineExampleValue       = ""
    var outlineExampleEmpty       = ""
    var scenarioName              = ""
    var scenarioEnd               = false
    var finalFeature              = ""
    var outlineAlreadyPrinted     = false
    var featureProcessed          = false
    var printScenarioOutline      = false
    var scenarioOutlineTitle      = ""
    var scenarioContentPrinted    = false
    var fileNumber                = 0
    var backgroundSet             = false
    var waveSentence              = ""
    var screenTitle               = ""
    var waveTestPrinted           = false

    val currentDir        = new java.io.File(".").getCanonicalPath
    val localStepDefPath  = currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/stepdefs/"
    val localFeaturePath  = currentDir + "/src/test/resources/features/"
    val outputFilePath    = if (urlScraperActive) currentDir + "/PT_output/" else currentDir + "/AT_output/"
    val routeFilePath     = currentDir + "/"
    val inputFilePath     = if (urlScraperActive) currentDir + "/PT_input/" else currentDir + "/AT_input/"
    val stepDefFilePath   = if (urlScraperActive) localStepDefPath else if (userStepDefFilePath.length > 0) userStepDefFilePath else localStepDefPath
    val featureFilePath   = if (urlScraperActive) localFeaturePath else if (userFeatureFilePath.length > 0) userFeatureFilePath else localFeaturePath
    val parameterFilePath = if (urlScraperActive) currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/utils/" else if (userParameterFilePath.length > 0) userParameterFilePath else currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/utils/"
    //    val stepDefFilePath   = if (userStepDefFilePath.length > 0) userStepDefFilePath else currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/stepdefs/"
    //    val featureFilePath   = if (userFeatureFilePath.length > 0) userFeatureFilePath else currentDir + "/src/test/resources/features/"
    //    val parameterFilePath = if (userParameterFilePath.length > 0) userParameterFilePath else currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/utils/"
    //    val stepDefFilePath   = currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/stepdefs/"
    //    val featureFilePath   = currentDir + "/src/test/resources/features/"
    //    val parameterFilePath = currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/utils/"
    val caseParameterFile = "AT_CaseParameters" + logFileTime + ".txt"
    val valParameterFile  = "AT_ValParameters" + logFileTime + ".txt"
    val commonStepsFile   = "CommonStepDef" + logFileTime + ".txt"

    var scenarioOutline   = "" // "start" or "accumulate" or ""
  var scenarioStart     = "" // "normal" or "outline" or ""


    //******************************************************************************
    //********** CREATE GENERIC OUTPUT FILES ***************************************
    //******************************************************************************

    // Generic (non feature specific) output files
    var fwCaseParameter   = new FileWriter(outputFilePath + caseParameterFile, true)
    var fwValParameter    = new FileWriter(outputFilePath + valParameterFile, true)
    var fwCommonSteps     = new FileWriter(outputFilePath + commonStepsFile, true)

    // The fwCommonSteps file must be created whether it's used or not (otherwise subsequent references to it dont' compile - but it's deleted if commonStepDefFileOn is false.
    if (commonStepDefFileOn) {
      fwCommonSteps.write(getStepHeader("", commonStepsFile.replace(logFileTime, "")))
    }


    //******************************************************************************
    //********** LOAD INITIAL STEPDEFS INTO STEP LISTS *****************************
    //******************************************************************************

    // Prevent new Step Definition files lexing with pre-existing Step Definition files (load existing stepdefs into step and common step lists before processing starts)
    val stepDefFileDir: Directory = Directory(new File(stepDefFilePath))

    for (file <- stepDefFileDir.list if file.name endsWith ".scala") {

      val inputFileContent = io.Source.fromFile(stepDefFilePath + file.name).getLines.toList

      for (i <- inputFileContent) {

        // Load all existing stepdefs into stepList
        if (i.contains("\"\"\"^")) {
          stepList += i.substring(i.indexOf("\"\"\"^") + 3, i.indexOf("$\"\"\""))
        }

        // Load only existing common stepdefs into commonStepList
        if ((file.name == "CommonStepDef" && i.contains("\"\"\"^")) | (file.name == "BaseStepDef" && i.contains("\"\"\"^"))) {
          commonStepList += i.substring(i.indexOf("\"\"\"^") + 3, i.indexOf("$\"\"\""))
        }
      }
    }


    //************************************************************************************************************************************************************
    //********** LOOP THROUGH EACH FILE IN THE "AT" INPUT DIRECTORY **********************************************************************************************
    //************************************************************************************************************************************************************

    val fileDir: Directory = Directory(new File(inputFilePath))

    for (file <- fileDir.list if file.name endsWith ".txt") {

      fileNumber += 1

      // Mark whether this file is a WAVE accessibility test or not
      waveList.clear()
      waveTestPrinted = false

      if (file.name.contains("WAVE")) {
        waveTest      = true
        waveSentence  = "    And click the Wave button for a HTML Report\n"
      } else {
        waveTest      = false
        waveSentence  = ""
      }

      def checkAndWriteParam(paramName: String, paramPrint: String) {

        if (paramList.contains(paramName)) {
          // Only create NEW parameters
        } else {

          paramList += paramName

          if (paramName.contains("val ")) {
            fwValParameter.write(paramPrint)
          } else if (paramName.contains("case ")) {
            fwCaseParameter.write(paramPrint)
          }
        }
      }

      def setBgStep(): String = {
        // Can't remember why there are 2 options here - trusting the 2 for now
        if (bgUrl.contains("driver.get")) {
          // refactor to use urlParam - historically bgUrl meaning can't print navigate automatically for different command types.
          bgStep = "  Given(\"\"\"^we start on URL '(.*)'$\"\"\") { (" + bgUrl + ": String) =>\n" + navigate + "  }\n\n"
        } else {
          bgStep = "  Given(\"\"\"^we start on URL '(.*)'$\"\"\") { (" + bgUrl + ": String) =>\n    navigate(" + bgUrl + ")\n  }\n\n"
        }
        bgStep
      }

      def setStepCommaSetFeatureAnd() {
        if (stepComma == "") {
          stepComma   = ", "
          featureAnd  = " and"
        }
      }

      //******************************************************************************
      //********** CREATE THE FEATURE AND STEP FILES *********************************
      //******************************************************************************

      var urlScraper  = if (urlScraperActive) "URLSCRAPER" else ""
      var featureNo   = if (stepsOn) "ATF" + featureNumber + "_" else ""
      var featureFile = featureNo + urlScraper + file.name.replace(".txt", "") + logFileTime + ".txt"
      var step        = if (stepsOn) "Step" + featureNumber else ""
      var stepFile    = step + urlScraper + file.name.replace(".txt", "") + "StepDef" + logFileTime + ".txt"
      var fwStep      = new FileWriter(outputFilePath + stepFile, true)
      var fwFeature   = new FileWriter(outputFilePath + featureFile, true)
      // Record each step file created so common steps can OPTIONALLY be removed from the individual step files after the file loop has closed
      stepFilesList   += stepFile

      //******************************************************************************
      //********** FILE LEVEL DEFINITIONS ********************************************
      //******************************************************************************

      def checkAndWriteStep(phrase: String, step: String) {

        if (stepList.contains(stepPhrase)) {
          // POTENTIALLY DO NOTHING. Only create NEW steps. Duplicated existing steps get OPTIONALLY written to the AT_CommonSteps.txt file.

          if (commonStepDefFileOn) {

            if (commonStepList.contains(stepPhrase)) {
              // DO NOTHING. Step already in common steps file.
            } else {
              // Only create NEW steps. Duplicated existing steps get written to the AT_CommonSteps.txt file.
              fwCommonSteps.write(bgStep)
              commonStepList += stepPhrase
            }
          }

        } else {

          stepList += stepPhrase
          fwStep.write(bgStep)

        }
      }

      def incrementScenarioWithUrl() {

        val padding         = max(urlParam.length, urlValue.length)
        //featureSentence = featureSentence + featureAnd + " '" + fieldName + "' is '<" + x.substring(x.indexOf("s(\"") + 3, x.indexOf("\");")) + ">'"
        featureSentence     = featureSentence + " then navigate to '<url>'" // urlParam
        outlineExampleTitle = outlineExampleTitle + " " + "url".padTo(padding, " ").mkString + " |"
        outlineExampleValue = outlineExampleValue + " " + urlValue.padTo(padding, " ").mkString + " |"
        outlineExampleEmpty = outlineExampleEmpty + " " + "".padTo(padding, " ").mkString + " |"

      }

      //******************************************************************************
      //********** FILE LEVEL LOOP VARIABLE SETTINGS & OUTPUT FILE CREATION **********
      //******************************************************************************

      stepString              = ""
      stepParams              = ""
      stepComma               = ""
      featureAnd              = ""
      featureSentence         = ""
      stepSentence            = ""
      newTitle                = ""
      featureWhen             = ""
      bgStep                  = ""
      stepRadio               = false
      nextPage                = false
      scenarioNumber          = 1
      stepNumber              = 1
      scenarioStepNumber      = 1
      firstGetTitle           = true
      oldTitle                = ""
      scenarioEnd             = false
      backgroundSet           = false
      scenarioStart           = "normal"
      scenarioOutline         = ""
      scenarioOutlineSentence = ""
      finalOutline            = ""
      printScenarioOutline    = false
      unprintedBgFeature      = ""

      // Write Feature and Step Headers
      fwFeature.write(getFeatureHeader(file.name, featureNumber))
      fwStep.write(getStepHeader(step, file.name))

      //************************************************************************************************************************************************************
      //********** LOOP THROUGH EACH LINE IN THE INPUT FILE ********************************************************************************************************
      //************************************************************************************************************************************************************

      // If catch triggers, close feature, step and parameter files to retain output till the point of failure.
      try {
        // Loop through as many files as there are (expecting one per Feature)
        io.Source.fromFile(inputFilePath + file.name).getLines.foreach { x =>
          // Store the fileRow for printing purposes
          loopRow = x

          var scenarioOutlineName   = s"\n  Scenario Outline: $scenarioName \n\n"
          var scenarioOutlineNumber = "\n  Scenario Outline: Scenario " + scenarioNumber + "\n\n"
          var scenarioWithName      = "\n  Scenario: " + scenarioName + "\n\n"
          var scenarioWithNumber    = "\n  Scenario: Scenario Number " + scenarioNumber + "\n\n"

          //******************************************************************************
          //********** PROCESS SCENARIO START AND END COMMANDS  **************************
          //******************************************************************************

          x match {

            case x if x.contains("SCENARIO") =>

              if (x.replaceAll(" ", "").capitalize.contains("SCENARIOOUTLINESTART")) {

                scenarioName                                                                            = x.substring(x.indexOf("START") + 5, x.length).trim
                if (scenarioOutlineSentence != "") printScenarioOutline                                 = true else false
                scenarioOutline                                                                         = "start"
                scenarioStart                                                                           = "outline"
                scenarioStepNumber                                                                      = 1
                if (featureSentence != "" | scenarioOutlineSentence != "" | stepNumber > 1) scenarioEnd = true // print Scenario end first if there are outstanding features or the Scenario Start is directly after a getTitle (so no pending features)

              } else if (x.replaceAll(" ", "").capitalize.contains("SCENARIOSTART")) {

                scenarioName                                                                            = x.substring(x.indexOf("START") + 5, x.length).trim
                if (scenarioOutlineSentence != "") printScenarioOutline                                 = true else false
                scenarioStart                                                                           = "normal"
                scenarioStepNumber                                                                      = 1
                if (featureSentence != "" | scenarioOutlineSentence != "" | stepNumber > 1) scenarioEnd = true // print Scenario end first if there are outstanding features or the Scenario Start is directly after a getTitle (so no pending features)

                //TODO we are the same
              } else if (x.replaceAll(" ", "").capitalize.contains("SCENARIOEND")) {

                if (scenarioOutlineSentence != "") printScenarioOutline                                 = true else false
                scenarioEnd                                                                             = true
                scenarioStart                                                                           = "normal"

              } else if (x.replaceAll(" ", "").capitalize.contains("SCENARIOOUTLINEEND")) {

                if (scenarioOutlineSentence != "") printScenarioOutline                                 = true else false
                scenarioEnd                                                                             = true
                scenarioStart                                                                           = "normal"

              }

            //******************************************************************************
            //********** PROCESS GET TITLE ROWS ********************************************
            //******************************************************************************
            //********** STEPS AND FEATURES ARE ONLY WRITTEN DURING GET TITLE ROWS *********
            //******************************************************************************

            // Get title is used to write a feature and step sentence per page
            case x if x.contains("getTitle") =>

              if (firstGetTitle) {
                // Each getTitle triggers the print of an accumulated setp/feature sentence for the previous getTitle. First one skipped as background already printed.
                firstGetTitle = false
                // Store current title to be printed when the next title ends the step/feature sentence that's about to be accumulated.
                oldTitle      = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + " where"

              // All rows except first row
              } else {

                //******************************************************************************
                //********** SWITCH PARTIAL MATCHING FEATURE/STEP SENTENCES THAT WOULD LEX *****
                //******************************************************************************

                // If Feature file sentences start with the same phrase and field name, even though the rest of the sentence differs, Cucumber will fail due to ambiguity (lexing error).
                // This is prevented by checking for exact matches in the structure of the step definition step sentence and not creating a new one ...
                // any by finding partial matches based on the three sentence identifiers of ' and ' & ' and select ' & ' where select ' and changing the wording of the first phrase.

                oldTitle = manageFeatureStepDuplication(oldTitle, stepSentence, stepWithoutSpace)


                //******************************************************************************
                //******************************************************************************
                //********** PROCESS STEPS *****************************************************
                //******************************************************************************
                //******************************************************************************


                // Set whether and which step numbers should be printed when writing the steps to file
                setStepPrints(fileNumber, stepNumber)

                // After each subsequent title write the accumulated steps in a single feature / step sentence (assumes every journey ends with a getTitle)
                if (stepString != "") {
                  // List of parms for the step are encased in a single set of brackets
                  if (stepParams != "") {
                    stepParams = " (" + stepParams + ")"
                  }

                  // Store current title
                  newTitle = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + " where"

                  // Write accumulated step sentence
                  stepPhrase = stepWithoutSpace + oldTitle + stepSentence
                  val stepExists: Boolean = stepList.contains(stepPhrase)
                  if (stepExists) {

                    // POTENTIALLY DO NOTHING. Only create NEW steps. Duplicated existing steps get OPTIONALLY written to the AT_CommonSteps.txt file.
                    if (commonStepDefFileOn) {
                      if (commonStepList.contains(stepPhrase)) {
                        // DO NOTHING. Step already in common steps file.
                      } else {
                        // Write new common step to commonStepList
                        fwCommonSteps.write("  And(\"\"\"" + stepWithoutSpace + oldTitle + stepSentence + "$\"\"\") {" + stepParams + " =>\n")
                        // Write accumulated steps
                        fwCommonSteps.write(stepString)
                        fwCommonSteps.write("  }\n\n")
                        commonStepList += stepPhrase
                      }
                    }

                  } else {

                    stepList        += stepPhrase
                    fwStep.write("  And(\"\"\"" + stepWithoutSpace + oldTitle + stepSentence + "$\"\"\") {" + stepParams + " =>\n")
                    // Write accumulated steps
                    fwStep.write(stepString)
                    fwStep.write("  }\n\n")

                  }

                  // Increment the Step Strings List for use in step command level matching
                  stepListStrings += (stepWithoutSpace + oldTitle + stepSentence + stepString).replaceAll(" ", "").replaceAll("(\\(.*?)[^= \"]\\)", "()").replaceAll("(\\(.*?)\\=", "(").replaceAll("\n", "").replaceAll("\\'\\(\\)\\'", "'(.*)'")
                  stepString      = ""
                  stepParams      = ""
                  stepComma       = ""
                  stepSentence    = ""
                  stepNumber      += 1


                  //******************************************************************************
                  //******************************************************************************
                  //********** PROCESS FEATURES **************************************************
                  //******************************************************************************
                  //******************************************************************************


                  // Set Feature When or And depending on whether this is the first row of a scenario
                  if (featureSentence != "") {
                    if (scenarioStepNumber == 1) {
                      // First feature starts with When
                      featureWhen = "    When"
                    } else {
                      // All subsequent features start with And
                      featureWhen = "    And"
                    }
                  }

                  //******************************************************************************
                  //********** SET WAVE SENTENCE FOR FEATURES AND OUTLINES ***********************
                  //******************************************************************************

                  // Either don't print wave tests or print wave tests excluding the first feature row sentence (usually Given URL) or always print wave tests
                  if (waveTest) {
                    screenTitle = oldTitle.reverse.dropWhile(_ != ' ').drop(1).reverse
                    if (!waveList.contains(screenTitle)) {
                      if (!includeFeatureFirstRowInWaveTest && stepNumber < 2) {
                        waveSentence = "" // Based on boolean, don't print wave sentence after the Given URL sentence (first sentence).
                      } else {
                        waveSentence    = "    And click the WAVE button for a HTML Report\n"
                        waveList        += screenTitle
                        waveTestPrinted = true // Any setting of waveSentence is presumed to have created a wave HTML report - requiring a Report Save at the end of the file loop
                      }
                    } else {
                      waveSentence = ""
                    }
                  }

                  //******************************************************************************
                  //********** FEATURE - ACCUMULATE SCENARIO OUTLINE *****************************
                  //******************************************************************************

                  if (scenarioOutline == "accumulate" | printScenarioOutline) {
                    if (!printScenarioOutline) {
                      // Add AND feature row to outline (store a Final Outline THEN in case a SCENARIO COMMAND happens after the final getTitle in the file and the ELSE below is never triggered
                      finalOutline = scenarioOutlineSentence + "    Then" + stepWithSpace + oldTitle + featureSentence + "\n"
                      scenarioOutlineSentence = scenarioOutlineSentence + featureWhen + stepWithSpace + oldTitle + featureSentence + "\n"
                    } else {
                      // Add final THEN command in case this is the EOF and the And needs to be replaced with a Then
                      scenarioOutlineSentence = scenarioOutlineSentence + "    Then" + stepWithSpace + oldTitle + featureSentence + "\n"
                      finalOutline = scenarioOutlineSentence
                    }
                  }

                  //******************************************************************************
                  //********** FEATURE - PRINT STANDARD SCENARIO ROW *****************************
                  //******************************************************************************

                  // If an outline is starting then the previous feature prints as normal
                  if (scenarioOutline == "" | scenarioOutline == "start" && !printScenarioOutline) {
                    if (!scenarioEnd) {
                      fwFeature.write(featureWhen + stepWithSpace + oldTitle + featureSentence + "\n" + waveSentence)
                      // Store in case this is the EOF and an And needs to be replaced with a Then
                      finalFeature = "    Then" + stepWithSpace + oldTitle + featureSentence + "\n" + waveSentence
                    } else {
                      // Print a THEN for the last feature row unless it's also the first feature row
                      if (stepNumber == 1) {
                        fwFeature.write(featureWhen + stepWithSpace + oldTitle + featureSentence + "\n" + waveSentence)
                      } else {
                        fwFeature.write("    Then" + stepWithSpace + oldTitle + featureSentence + "\n" + waveSentence)
                      }
                    }
                    // If outline is starting, set it to accumulate now the pre-outline feature row has printed
                    if (scenarioOutline == "start") scenarioOutline = "accumulate"
                    scenarioContentPrinted                          = true
                  }
                }

                //******************************************************************************
                //********** FEATURE - PRINT SCENARIO OUTLINE **********************************
                //******************************************************************************

                if (printScenarioOutline) {
                  // If this is an invalid Scenario Outline because there are no elements in the Examples section - make the heading "Scenario:" rather than "Scenario Outline:"
                  if (outlineExampleTitle == "") {
                    fwFeature.write(scenarioOutlineTitle.replace(" Outline", ""))
                  } else {
                    fwFeature.write(scenarioOutlineTitle)
                  }
                  // Print the accumulated Scenario Outline rows (valid whether there are, or are not, any outline examples)
                  fwFeature.write(scenarioOutlineSentence)
                  // Print Examples block below Scenario Outline - including x empty lines
                  if (outlineExampleTitle != "") {
                    // Any scenario end sets scenarioOutline to "print" so only print Examples if they are populated (meaning there is an outline)
                    fwFeature.write("\n    Examples:\n" +
                                    "      |" + outlineExampleTitle + "\n" +
                                    "      |" + outlineExampleValue + "\n")
                    for (a <- 1 to extraScenarioExampleRows) {
                      if (blankExamples) {
                        fwFeature.write("      |" + outlineExampleEmpty + "\n")
                      } else {
                        fwFeature.write("      |" + outlineExampleValue + "\n")
                      }
                    }
                  }
                  if (scenarioOutline == "start") {
                    scenarioOutline = "accumulate" // Because the next Scenario Outline is starting when the next row of the input file is read (two outlines back to back)
                  } else {
                    scenarioOutline = ""
                  }
                  outlineExampleTitle     = ""
                  outlineExampleValue     = ""
                  outlineExampleEmpty     = ""
                  scenarioOutlineSentence = ""
                  printScenarioOutline    = false
                  scenarioContentPrinted  = true
                }

                // Assign title for next step/feature line
                oldTitle = newTitle


                //******************************************************************************
                //******************************************************************************
                //********** SCENARIOS AND PARAMETERS AND BACKGROUNDS **************************
                //******************************************************************************
                //******************************************************************************


                //******************************************************************************
                //********** CREATE URL PARAMETER **********************************************
                //******************************************************************************

                // Add current page title to URL parameter object (if not already present)
                val urlParam = xAllCaps.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replace("^exact:", "").replace("[\\\\s\\\\S]$", "").replace(" ", "").replace("'", "").replace(".", "").replace("-", "")
                val paramExists: Boolean = paramList.contains(urlParam)
                if (paramExists) {
                  // Only create NEW parameters
                } else {
                  paramList += urlParam
                  fwCaseParameter.write(("      case \"url" + urlParam + "\"").padTo(100, ' ') + "=> \"\"\n")
                }

                // Reset incremental values for FEATURE and STEP sentences
                featureSentence     = ""
                featureAnd          = ""
                newTitle            = ""
                scenarioStepNumber += 1
              }


              //******************************************************************************
              //********** PRINT SCENARIO TITLES *********************************************
              //******************************************************************************

              if (scenarioStart == "normal") {
                if (scenarioName != "") {
                  fwFeature.write(scenarioWithName)
                  scenarioName = ""
                } else {
                  fwFeature.write(scenarioWithNumber)
                }
              } else if (scenarioStart == "outline") {
                if (scenarioName != "") {
                  scenarioOutlineTitle = scenarioOutlineName
                  scenarioName = ""
                } else {
                  scenarioOutlineTitle = scenarioOutlineNumber
                }
              }

              //******************************************************************************
              //********** PRINT BACKGROUND ROW IF BACKGROUND PARAM NOT SET ******************
              //******************************************************************************

              // If Background wasn't printed in the Feature File - print the URL start commands as the first line in the first Feature Scenario.
              if (scenarioNumber == 1 && unprintedBgFeature != "") {
                fwFeature.write(unprintedBgFeature)
              }

              if (scenarioStart == "normal" | scenarioStart == "outline") {
                scenarioStart           = ""
                scenarioStepNumber      = 1 // Begin counting scenario steps again for the new scenario - this determines whether a When or And is printed at the start of each Feature line.
                scenarioNumber          += 1
                scenarioEnd             = false
                scenarioContentPrinted  = false
              }


            //******************************************************************************
            //******************************************************************************
            //********** READ INDIVIDUAL COMMANDS TO BUILD FEATURE & STEP SENTENCES ********
            //******************************************************************************
            //******************************************************************************


            //******************************************************************************
            //********** PROCESS BACKGROUND PRINTS AND NAVIGATE (TO URL) COMMANDS***********
            //******************************************************************************

            case x if x.contains("driver.get(") =>
              // Open starting URL
              if (!backgroundSet) {

                //******************************************************************************
                //********** PROCESS BACKGROUND ROW ********************************************
                //******************************************************************************
                // Process background row - either pring background, or add to scenario outline, or store for printing as part of standard scenario
                if (printBackground) {
                  // Print background row
                  fwFeature.write(bgFeature) // Print an extra new line character if the start URL command being printed as part of a background.
                } else {
                  // Accumulate scenario outline row
                  if (scenarioOutline == "start") {
                    val padding = bgUrlValue.length
                    scenarioOutlineSentence = "    Given we start on URL '<url>'\n"
                    outlineExampleTitle     = " " + "url".padTo(padding, " ").mkString + " |"
                    outlineExampleValue     = " " + bgUrlValue.padTo(padding, " ").mkString + " |"
                    outlineExampleEmpty     = " " + "".padTo(padding, " ").mkString + " |"
                    scenarioOutline         = "accumulate"
                  } else {
                    // Store unprinted background for standard scenario print
                    unprintedBgFeature = bgFeature // Store what would have been background row for printing in first scenario
                  }
                }

                // Write background URL parameter & background step
                checkAndWriteParam(bgUrlParam, bgUrlStepPrint) // Write new parameters to the param list
                bgStep        = setBgStep() // Starting URL step
                stepPhrase    = "^we start on URL '(.*)'"
                checkAndWriteStep(stepPhrase, bgStep)
                backgroundSet = true

              } else {

                //******************************************************************************
                //********** PROCESS NAVIGATE (TO URL) ROW *************************************
                //******************************************************************************

                // Increment step strings together (until next getTitle triggers string print and clear)
                stepString = stepString + navigate
                // Add each new url to the parameter's object (if not already present)
                checkAndWriteParam(urlParam, ("  val " + urlParam).padTo(50, ' ') + " = \"" + urlValue + "\"\n")
                // Increment strings for Feature and Step matching sentences (printed when getTitle detected)
                stepParams = stepParams + stepComma + urlParam + ": String"
                if (scenarioOutline == "accumulate") {
                  incrementScenarioWithUrl()
                } else {
                  featureSentence = featureSentence + " then navigate to '" + urlValue + "'"
                }
                stepSentence = stepSentence + " then navigate to '(.*)'"
                // Feature comma & and are null for the first step then populated for all others (so strings don't start with comma or and)
                setStepCommaSetFeatureAnd()
                // Indiactes the next click is not a radio button
                stepRadio = false

              }

            //******************************************************************************
            //********** PROCESS SENDKEYS COMMANDS *****************************************
            //******************************************************************************

            case x if x.contains("sendKeys") =>

              // Increment step strings together (until next getTitle triggers string print and clear)
              stepString = stepString + write
              // Add each new field to the parameter's object (if not already present)

              //******************************************************************************
              //********** WRITE FIELD NAME PARAMETER ****************************************
              //******************************************************************************
              val paramExists: Boolean = paramList.contains(fieldName)
              if (paramExists) {
                // Only create NEW parameters
              } else {
                paramList += fieldName
                // PARAMETER
                fwValParameter.write(("  val " + fieldName).padTo(50, ' ') + webDriverElementName) //" = \"" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)) + "\"\n")
              }

              // Increment strings for Feature and Step matching sentences (printed when getTitle detected)
              stepParams = stepParams + stepComma + fieldValue + ": String"

              if (scenarioOutline == "accumulate") {
                var fieldValue = x.substring(x.indexOf("s(\"") + 3, x.indexOf("\");"))
                var padding = max(fieldValue.length, fieldName.length)
                //featureSentence = featureSentence + featureAnd + " '" + fieldName + "' is '<" + x.substring(x.indexOf("s(\"") + 3, x.indexOf("\");")) + ">'"
                featureSentence     = featureSentence + featureAnd + " '" + fieldName + "' is '<" + fieldName + ">'"
                outlineExampleTitle = outlineExampleTitle + " " + fieldName.padTo(padding, " ").mkString + " |"
                outlineExampleValue = outlineExampleValue + " " + fieldValue.padTo(padding, " ").mkString + " |"
                outlineExampleEmpty = outlineExampleEmpty + " " + "".padTo(padding, " ").mkString + " |"
              } else {
                featureSentence = featureSentence + featureAnd + " '" + fieldName + "' is '" + x.substring(x.indexOf("s(\"") + 3, x.indexOf("\");")) + "'"
              }

              stepSentence = stepSentence + featureAnd + " '" + fieldName + "' is '(.*)'"

              // Feature comma & and are null for the first step then populated for all others (so strings don't start with comma or and)
              if (stepComma == "") {
                stepComma   = ", "
                featureAnd  = " and"
              }
              // Indiactes the next click is not a radio button
              stepRadio = false

            //******************************************************************************
            //********** PROCESS CLICK COMMANDS ********************************************
            //******************************************************************************

            case x if x.contains(".click") =>
              // Prepare to add radio or click to the field name for parameter purposes
              paramClickFieldName = fieldName
              // Ignore @id as this unnecessary selects a radio field before clicking an option
              if (!x.contains("@id")) {
                // Print a radio if @id marker was set for the previous line in the txt file ........ and unset the radio flag
                if (stepRadio) {
                  paramClickFieldName = fieldName.replace(fieldName.substring(0, 5), "click")
                  stepString          = stepString + clickRadio // "    clickRadio(\"" + paramClickFieldName + byOverride(x) + "\")\n"
                  stepRadio           = false
                } else {
                  // Print a click (radio flag not set)
                  paramClickFieldName = fieldName.replace(fieldName.substring(0, 5), "click")
                  stepString          = stepString + click
                }

                //******************************************************************************
                //********** WRITE CLICK FIELD NAME PARAMETER **********************************
                //******************************************************************************

                val paramExists: Boolean = paramList.contains(paramClickFieldName)
                if (paramExists) {
                  // Only create NEW parameters
                } else {
                  paramList += paramClickFieldName
                  // PARAMETER
                  fwCaseParameter.write(("      case \"" + paramClickFieldName + "\"").padTo(100, ' ') + "=>" + webDriverElementName.replace("= ", "")) //" = \"" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)) + "\"\n")
                }

                // Concatonate feature parameters, cancatonate feature sentence, concatonate matching step sentence
                stepParams      = stepParams + stepComma + paramClickFieldName + ": String"
                featureSentence = featureSentence + featureAnd + " select '" + paramClickFieldName + "'"
                stepSentence    = stepSentence + featureAnd + " select '(.*)'"

                // If this was the first item in the feature/step sentence, set comma and And for each subsequent sentence
                if (stepComma == "") {
                  stepComma   = ", "
                  featureAnd  = " and"
                }

              } else {

                //******************************************************************************
                //********** RECORD THAT A CLICK RADIO EVENT IS REQUIRED ***********************
                //******************************************************************************
                // Record that if next command is a click (following a click of @id) then "radio" will be written in place of "click".
                stepRadio = true

              }

            // IGNORE ALL COMMANDS NOT LISTED IN THE CASE STATEMENT ABOVE
            case _ =>

          }

          // Record the x.fileRow just processed for use in printing Original WebDriver commands (as sendKeys requires a prior clear command)
          previousLoopRow = x

        }

        //************************************************************************************************************************************************************
        //************************************************************************************************************************************************************
        //********** END OF "WHEN A SCALA FILE IS READ AN ACCEPTANCE TEST IS CREATED" ***** FILE INPUT LOOP CLOSED ***************************************************
        //************************************************************************************************************************************************************
        //************************************************************************************************************************************************************

        //******************************************************************************
        //********** CLEAN UP AFTER INPUT FILE LOOP HAS CLOSED *************************
        //******************************************************************************

        // Clean up required because there is no way of knowing which row is the last in the input file and getTitle is not always the last command

        //******************************************************************************
        //********** PRINT UNWRITTEN SCENARIO OUTLINE **********************************
        //******************************************************************************

        // Print scenario outline if script ended without a scenario end command
        // Start by printing the Title if the scenario currently doesn't have one
        if (scenarioOutline == "accumulate") {
          if (scenarioOutlineTitle != "") {
            if (outlineExampleTitle == "") {
              // If there are no examples then the title changes from a "Scenario Outline:" to a "Scenario:"
              fwFeature.write(scenarioOutlineTitle.replace(" Outline", ""))
            } else {
              fwFeature.write(scenarioOutlineTitle)
            }
          }
          fwFeature.write(finalOutline)
          // Print Examples block below Scenario Outline - including x empty lines
          fwFeature.write("\n    Examples:\n" +
                          "      |" + outlineExampleTitle + "\n" +
                          "      |" + outlineExampleValue + "\n")
          for (a <- 1 to extraScenarioExampleRows) {
            if (blankExamples) {
              fwFeature.write("      |" + outlineExampleEmpty + "\n")
            } else {
              fwFeature.write("      |" + outlineExampleValue + "\n")
            }
          }
        }

        fwStep.write(scenarioAfterStatement())
        featureNumber           += 1
        fwStep.close()
        fwFeature.close()
        outlineExampleTitle     = ""
        outlineExampleValue     = ""
        outlineExampleEmpty     = ""
        scenarioOutlineTitle    = ""
        scenarioContentPrinted  = true

        //******************************************************************************
        //********** DELETE AND REWRITE FINAL FEATURE ROW AS A THEN ********************
        //******************************************************************************

        // AFTER EOF - delete the final Feature Row and re-write it as a THEN (there's no way of recognising the final Feature line other than waiting till it closes (can't delete from open file if case "}" EOF)
        if (scenarioOutline == "accumulate") {
          // Unless this was a scenario outline handled directly after the case loop
        } else {
          if (scenarioStepNumber > 1 && featureSentence == "") {
            // If there's a hangover feature sentence after the last getTitle printed AND, no need to delete and replace as a THEN will be printed below
            // Delete the current final line from the feature file
            Seq("sed", "-i", "$d", outputFilePath + featureFile) !
            // Write the line back to the feature file with a THEN at the start of it
            var fwFeature = new FileWriter(outputFilePath + featureFile, true)
            fwFeature.write(finalFeature)
            fwFeature.close()
          }
        }

        scenarioOutline         = ""
        finalOutline            = ""
        scenarioOutlineSentence = ""

        //******************************************************************************
        //********** REMOVE FINAL SCENARIO TITLE IF NO FEATURES WERE PRINTED ***********
        //******************************************************************************

        // If a new scenario name was printed but there are no feature lines (because and old one was closed at the end of the input file etc), delete it from the output file
        if ((!scenarioContentPrinted && scenarioStart == "") | (scenarioStepNumber < 2 && scenarioStart == "")) {
          // Delete 1 row as input file ended with a scenario end meaning the automatic scenario start has to be deleted
          for (a <- 1 to 3) {
            Seq("sed", "-i", "$d", outputFilePath + featureFile) !
          }
        }

        //******************************************************************************
        //********** PRINT ANY REMAINING FEATURE ROW - IF GETTITLE NOT FINAL COMMAND ***
        //******************************************************************************

        if (featureSentence != "") {

          // Delete After Hook from step definition
          for (a <- 1 to 14) {
            Seq("sed", "-i", "$d", outputFilePath + stepFile) !
          }

          var fwFeature = new FileWriter(outputFilePath + featureFile, true)
          var fwStep    = new FileWriter(outputFilePath + stepFile, true)

          setStepPrints(fileNumber, stepNumber)

          oldTitle = manageFeatureStepDuplication(oldTitle, stepSentence, stepWithoutSpace)

          if (stepParams != "") {
            stepParams = " (" + stepParams + ")"
          }
          // Write accumulated step sentence
          stepPhrase              = stepWithoutSpace + oldTitle + stepSentence
          val stepExists: Boolean = stepList.contains(stepPhrase)
          if (stepExists) {

            // POTENTIALLY DO NOTHING. Only create NEW steps. Duplicated existing steps get written to the AT_CommonSteps.txt file.
            if (commonStepDefFileOn) {
              if (commonStepList.contains(stepPhrase)) {
                // DO NOTHING. Step already in common steps file.
              } else {
                // Only create NEW steps. Duplicated existing steps get written to the AT_CommonSteps.txt file.
                fwCommonSteps.write("  And(\"\"\"" + stepWithoutSpace + oldTitle + stepSentence + "$\"\"\") {" + stepParams + " =>\n")
                // Write accumulated steps
                fwCommonSteps.write(stepString)
                fwCommonSteps.write("  }\n\n")
                commonStepList += stepPhrase
              }
            }

          } else {

            stepList += stepPhrase
            fwStep.write("  And(\"\"\"" + stepWithoutSpace + oldTitle + stepSentence + "$\"\"\") {" + stepParams + " =>\n")
            // Write accumulated steps
            fwStep.write(stepString)
            fwStep.write("  }\n\n")

          }

          // Increment the Step Strings List for use in step command level matching
          stepListStrings += (stepPhrase + stepString).replaceAll(" ", "").replaceAll("(\\(.*?)[^= \"]\\)", "()").replaceAll("(\\(.*?)\\=", "(").replaceAll("\n", "").replaceAll("\\'\\(\\)\\'", "'(.*)'")

          // Rewrite After Hook
          fwStep.write(scenarioAfterStatement())
          fwStep.close()

          fwFeature.write("    Then" + stepWithSpace + oldTitle + featureSentence + "\n")
          fwFeature.close()

          featureSentence = ""
          stepSentence    = ""

        }

        //******************************************************************************
        //********** SAVE THE HTML WAVE FILE IF A WAVE REPORT WAS GENERATED ************
        //******************************************************************************

        if (waveTestPrinted) {
          var fwFeature = new FileWriter(outputFilePath + featureFile, true)
          fwFeature.write("\n  Scenario: Save Accessibility Report")
          fwFeature.write("\n\n    And save the WAVE Accessibility Test Report")
          fwFeature.close()
        }

        //******************************************************************************
        //********** COPY FEATURE AND STEP FILES TO FEATURE AND STEP DIRECTORIES *******
        //******************************************************************************

        // Copy Feature Files and Step Defs to the appropriate Feature and Step Def file locations (in a repository) if parameter true
        if (loadFeatureAndStepDefDirs) {
          Seq("cp", outputFilePath + featureFile, featureFilePath + featureFile.replace(logFileTime, "").replace(".txt", ".feature")) !!
          val dummy = "to allow next Unix command"
          Seq("cp", outputFilePath + stepFile, stepDefFilePath + stepFile.replace(logFileTime, "").replace(".txt", ".scala")) !!
          val dumdum = "keep going"
        }

      } catch {

        case e: Exception =>
          // Always aim to produce a set of current progress files if there's a processing error
          if (featureNumber > 0) {
            fwStep.write(scenarioAfterStatement())
            featureNumber += 1
            fwStep.close()
            fwFeature.close()
            fwCaseParameter.close()
            fwValParameter.close()
            fwCommonSteps.close()
            BasePageTemplate.driver.close()
          }
          assert(e.getMessage == "PRINT ERR")
      }

      //******************************************************************************
      //********** SWITCH PARTIAL MATCHING FEATURE/STEP SENTENCES THAT WOULD LEX *****
      //******************************************************************************

      def manageFeatureStepDuplication(oldTitle: String, stepSentence: String, stepWithoutSpace: String): String = {

        var modifiedOldTitleToReturn = oldTitle

        // If Feature file sentences start with the same phrase and field name, even though the rest of the sentence differs, Cucumber will fail due to ambiguity.

        // Reuse exactly matching step sentences. Modify the language of the first phrase of step sentences that partially match.
        // Is currently set to work for sentences that contain the generic text of ' and ' or ' and select ' or ' where select '.

        // EXAMPLE REPLACE SENTENCE: Business accounting period where 'fieldStartDateDateDay' is '01' and '
        val andList = List("where", "with", "for", "wherein", "using", "where input", "where item", "where field", "where value",
          "with input", "with item", "with field", "with value", "for input", "for item", "for field", "for value",
          "wherein input", "wherein item", "wherein field", "wherein value", "using input", "using item", "using field", "using value")

        // EXAMPLE REPLACE SENTENCE: Accounting method where select 'clickAccountingMethodCash' and select '
        val andSelectList = List("where", "choose", "press", "push", "pick", "action", "use", "call", "click", "then",
          "then chose", "then press", "then push", "then pick", "then action", "then use", "then call", "then click",
          "then chooses", "then presses", "then pushes", "then picks", "then actions", "then uses", "then calls", "then clicks",
          "we choose", "we press", "we push", "we pick", "we action", "we use", "we call", "we click", "we then",
          "user chooses", "user presses", "user pushes", "user picks", "user actions", "user uses", "user calls", "user clicks", "user then",
          "operator chooses", "operator presses", "operator pushes", "operator picks", "operator actions", "operator uses", "operator calls", "operator clicks", "operator then")

        // EXAMPLE REPLACE SENTENCE: Accounting method where select 'clickAccountingMethodCash' and select 'one value only'
        val whereSelectList = List("where", "choose", "press", "push", "pick", "action", "use", "call", "click", "then",
          "then chose", "then press", "then push", "then pick", "then action", "then use", "then call", "then click",
          "then chooses", "then presses", "then pushes", "then picks", "then actions", "then uses", "then calls", "then clicks",
          "with choose", "with press", "with push", "with pick", "with action", "with use", "with call", "with click",
          "with chooses", "with presses", "with pushes", "with picks", "with actions", "with uses", "with calls", "with clicks",
          "for choose", "for press", "for push", "for pick", "for action", "for use", "for call", "for click",
          "for chooses", "for presses", "for pushes", "for picks", "for actions", "for uses", "for calls", "for clicks")

        var stepMatch                 = false
        var finished                  = false
        var checkForMoreExactMatches  = true

        if ((oldTitle + stepSentence).contains("where select \'")) {

          // Prevent this type of sentence from lexing by changing words to prevent overlaps: Accounting method where select 'clickAccountingMethodCash' and select 'one value only'
          var matchWords  = whereSelectList
          stepMatch       = true
          finished        = false

          breakable {
            matchWords.foreach { listElement =>

              if ((stepMatch && !finished) | checkForMoreExactMatches) {

                var newWordTitle  = oldTitle.reverse.dropWhile(_ != ' ').reverse + listElement
                var newStepPhrase = stepWithoutSpace + newWordTitle + stepSentence

                // Check for an exact match by adding the stepList end of sentence characters to the newStepPhrase - we don't modify exact matches
                if (stepList.toString.contains(newStepPhrase + ", ^") | stepList.toString.contains(newStepPhrase + ")")) {
                  // EXACT MATCH so exit loop without changing sentence wording
                  setNewTitle(newWordTitle, newStepPhrase)
                  break

                } else {

                  // Check if this is a unique match and set stepMatch to false if not ("where select '(.*)'  &&  "where select '(.*)' and select '(.*)' may both be present - will cause lexing.
                  if (newStepPhrase.contains("\' and select \'")) {

                    if (stepList.toString.contains(newStepPhrase + ", ^") | stepList.toString.contains(newStepPhrase + ")")) {
                      setNewTitle(newWordTitle, newStepPhrase)
                      break
                    } else {
                      if (stepList.toString.contains(stepWithoutSpace + newWordTitle + " select \'(.*)\'")) {
                        stepMatch = false
                        checkForMoreExactMatches = true
                      }
                    }

                  } else {

                    if (stepList.toString.contains(newStepPhrase + ", ^") | stepList.toString.contains(newStepPhrase + ")")) {
                      setNewTitle(newWordTitle, newStepPhrase)
                      break
                    } else {
                      if (stepList.toString.contains(stepWithoutSpace + newWordTitle + " select \'(.*)\' and select \'")) {
                        stepMatch = false
                        checkForMoreExactMatches = true
                      }
                    }
                  }
                }
              }
            }
          }

          // Check unmatched step sentences for partial matches
          if (!stepMatch) {
            switchAndPreventLexingSentences("where select \'", whereSelectList)
          }

        } else if (stepSentence.contains("\' and \'")) {

          // Prevent this type of sentence from lexing by changing words to prevent overlaps: "Business accounting period where use 'fieldStartDateDateDay' is '01' and '"
          var matchWords = andList

          breakable {
            matchWords.foreach { listElement =>

              if ((!stepMatch && !finished) | checkForMoreExactMatches) {

                var newWordTitle  = oldTitle.reverse.dropWhile(_ != ' ').reverse + listElement
                var newStepPhrase = stepWithoutSpace + newWordTitle + stepSentence

                if (stepList.toString.contains(newStepPhrase + ", ^") | stepList.toString.contains(newStepPhrase + ")")) {
                  setNewTitle(newWordTitle, newStepPhrase)
                  break
                }
              }
            }
          }

          // Check unmatched step sentences for partial matches
          if (!stepMatch) {
            switchAndPreventLexingSentences("\' and \'", andList)
          }

        } else if (stepSentence.contains("\' and select \'")) {

          // Prevent this type of sentence from lexing by changing words to prevent overlaps: "Accounting method WHERE select 'clickAccountingMethodCash' and select '"
          var matchWords  = andSelectList
          stepMatch       = false

          breakable {
            matchWords.foreach { listElement =>

              if (!stepMatch) {

                // THIS SHOULD MATCH WHEN BIGGER SENTENCE IS LIKE SMALLER ONE, AND BIGGER SENTENCE SHOULD CHANGE
                var newWordTitle  = oldTitle.reverse.dropWhile(_ != ' ').reverse + listElement
                var newStepPhrase = stepWithoutSpace + newWordTitle + stepSentence

                if (stepList.toString.contains(newStepPhrase + ", ^") | stepList.toString.contains(newStepPhrase + ")")) {
                  setNewTitle(newWordTitle, newStepPhrase)
                  break
                }
              }
            }
          }

          // Check unmatched step sentences for partial matches
          if (!stepMatch) {
            switchAndPreventLexingSentences("\' and select \'", andSelectList)
          }

        }

        //******************************************************************************
        //********** DETERMINE IF MATCHED SENTENCES STEP STRINGS MATCH *****************
        //******************************************************************************

        // Return unchanged "oldTitle" to file processing loop if step sentence is exactly matched (it will be reused without lexing).
        // If oldTitles match but the step strings differ, return false to trigger partial matching sentence change
        def setNewTitle(newTitle: String, newStepPhrase: String) {

          var stepStringMatch = true
          val counter         = StringUtils.countMatches(stepListStrings.toString, newStepPhrase.replaceAll(" ", ""))
          var stringStart     = stepListStrings.toString.indexOf(newStepPhrase.replaceAll(" ", "")) //, startIndex+newStepPhrase.replaceAll(" ", "").length())
          var stringEnd       = stepListStrings.toString.drop(stringStart).indexOf(", ^")
          var firstMatch      = (newStepPhrase + stepString).replaceAll(" ", "").replaceAll("(\\(.*?)[^= \"]\\)", "()").replaceAll("(\\(.*?)\\=", "(").replaceAll("\n", "").replaceAll("\\'\\(\\)\\'", "'(.*)'")
          var loopVar         = 0

          // Loop through all newStepPhrase matches to see if step strings also match (mismatch requires that the step & feature sentence be altered to prevent unusable single record in Common Stepdefs
          while (loopVar < counter) {

            // Trim the incoming list to the point where the newStepPhrase matches, then extract the full phrase + string
            var choppedList = stepListStrings.toString.drop(stringStart)
            var theEnd      = choppedList.toString.indexOf(", ^")

            // If current loop phrase doesn't match original phrase, set matching to false and trigger step & feature sentence alteration
            if (firstMatch != choppedList.toString.slice(0, theEnd)) {

              stepStringMatch = false
              // Exit the loop
              loopVar += 1000000

            } else {

              stepStringMatch = true

            }

            loopVar += 1

            // Remove current matching phrase before next loop
            choppedList = choppedList.drop(theEnd)
            stringStart = choppedList.indexOf(newStepPhrase.replaceAll(" ", ""))

          }

          // Return false match if step strings differ
          if (stepStringMatch) {
            stepMatch = true
          } else {
            stepMatch = false
          }

          finished                  = true
          checkForMoreExactMatches  = false
          modifiedOldTitleToReturn  = newTitle

        }

        //******************************************************************************
        //********** DETERMINE WHETHER SENTENCES HAVE PARTIALLY MATCHED ****************
        //******************************************************************************

        // Check whether the start of a feature sentence partially matches the start of an existing feature sentence and change the text to prevent lexing if it does
        def switchAndPreventLexingSentences(searchString: String, replaceList: List[String]) {

          var continueChanging  = true
          var partialMatchFound = false

          if (searchString == "where select \'") {

            if ((oldTitle + stepSentence).contains(" where select \'") && !stepSentence.contains("\' and select \'")) {

              var stepString = (oldTitle + stepSentence).substring(0, (oldTitle + stepSentence).indexOf(" where select \'") + 15) + "(.*)\' and select \'"
              // Contains - rather than full match - so needs to change
              if (stepList.toString.contains(stepString)) {
                partialMatchFound = true
              }

            } else if ((oldTitle + stepSentence).contains(" where select \'") && stepSentence.contains("\' and select \'")) {

              var stepString = (oldTitle + stepSentence).substring(0, (oldTitle + stepSentence).indexOf("\' where select \'") + 15)
              // Contains - rather than full match - so needs to change
              if (stepList.toString.contains(stepString)) {
                partialMatchFound = true
              }
            }

          } else if (searchString == "\' and \'" | searchString == "\' and select \'") {

            var oldTitleMatch = oldTitle

            if (stepSentence.contains(searchString)) {

              if (searchString == "\' and \'") {

                // Contains - rather than full match - so needs to change
                if (stepList.toString.contains(oldTitleMatch + stepSentence.substring(0, stepSentence.indexOf("\' is \'") + 6))) {
                  partialMatchFound = true
                }

              } else if (searchString == "\' and select \'") {

                // Contains - rather than full match - so needs to change
                if (stepList.toString.contains(oldTitleMatch + stepSentence.substring(0, stepSentence.indexOf("\' is \'") + 6))) {
                  partialMatchFound = true
                }
              }
            }

          } else {

            continueChanging = false

          }

          //******************************************************************************
          //********** PROCESS WORD SWITCHING OF PARTIALLY MATCHED SENTENCES *************
          //******************************************************************************

          if (continueChanging) {

            if (partialMatchFound) {

              var stop              = false
              var replacementWords  = replaceList

              breakable {
                replacementWords.foreach { listElement =>

                  if (!stop) {

                    var newWordTitle = oldTitle.reverse.dropWhile(_ != ' ').reverse + listElement

                    if (stepList.toString.contains(newWordTitle)) {
                      // DO NOTHING - This sentence is already in use
                    } else {
                      // Use this unique sentence to prevent lexing
                      modifiedOldTitleToReturn  = newWordTitle
                      stop                      = true
                      break
                    }
                  }
                }
              }
            }
          }
        }

        modifiedOldTitleToReturn

      }

    }

    //******************************************************************************
    //********** REMOVE COMMON STEPS FROM LOCAL STEP DEFINITIONS *******************
    //******************************************************************************

    // OPTIONALLY remove any common step definition steps from the individual step files created
    if (commonStepDefFileOn) {

      stepFilesList.foreach { stepFileName =>

        val fwNewStepFile     = new FileWriter(outputFilePath + "NewStepFile.txt", true)
        var copyThisLine      = true
        var countUniqueSteps  = 0

        // Read the file line by line and delete as appropriate
        for (inputLine <- io.Source.fromFile(outputFilePath + stepFileName).getLines) {

          // Check for a matching line in the commonStepList
          if (inputLine.contains("\"\"\"^") && inputLine.contains("$\"\"\"")) {

            if (commonStepList.contains(inputLine.substring(inputLine.indexOf("(\"\"\"^") + 4, inputLine.indexOf("$\"\"\")")))) {

              copyThisLine = false

            } else {

              countUniqueSteps  += 1
              copyThisLine      = true

            }
          }

          // Cancel a copyThisLine false setting if we've reached the end of the individual step file.
          if (inputLine.contains("After() {")) {

            if (countUniqueSteps == 0) {
              // Write a comment if
              fwNewStepFile.write("  // No Unique Steps In This Step Definition - Common Steps Are Located In the \"CommonStepDef\" Definition\n\n")

            }

            copyThisLine = true

          }

          // Build the new individual step file line by line
          if (copyThisLine) {

            fwNewStepFile.write(inputLine + "\n")

          }
        }

        // Copy new file back to old file
        fwNewStepFile.close()
        copyFileToFile(outputFilePath, "NewStepFile.txt", outputFilePath, stepFileName)
        Seq("rm", outputFilePath + "NewStepFile.txt").!!

        if (loadFeatureAndStepDefDirs) {
          Seq("cp", outputFilePath + stepFileName, stepDefFilePath + stepFileName.replace(logFileTime, "").replace(".txt", ".scala")).!!
        }
      }

      // Close the common steps file now that it's had its final use within the routine (following the end of the "after file loop" functions).
      if (commonStepDefFileOn) {

        fwCommonSteps.write(scenarioAfterStatement())
        fwCommonSteps.close()
        Seq("cp", outputFilePath + commonStepsFile, stepDefFilePath + "CommonStepDef.scala").!!

      } else {

        // Delete the empty file that was created at the start of the routine to allow the references to it to compile.
        Seq("rm", outputFilePath + commonStepsFile).!!

      }
    }

    //******************************************************************************
    //********** WRITE THE NEW CASE AND VAL PARMS TO THE PARAMETERS FILE ***********
    //******************************************************************************

    if (paramList.nonEmpty) {

      fwCaseParameter.close()
      fwValParameter.close()

      val paramLines          = io.Source.fromFile(parameterFilePath + "ParametersTemplate.scala").getLines.toList
      val paramNumberOfLines  = io.Source.fromFile(parameterFilePath + "ParametersTemplate.scala").getLines.size
      val caseParamLines      = io.Source.fromFile(outputFilePath + "AT_CaseParameters" + logFileTime + ".txt").getLines.toList
      val valParamLines       = io.Source.fromFile(outputFilePath + "AT_ValParameters" + logFileTime + ".txt").getLines.toList
      var caseParamPrintList  = ""
      var valParamPrintList   = ""

      // Remove the duplicate "case" parameters and update them with basePageUrl etc as appropriate
      for (i <- caseParamLines.sorted) {
        if (paramLines.toString.replace(" ", "").contains(i.replace(" ", "")) |
          paramLines.toString.replace(" ", "").contains(i
            .replace("\"" + basePageUrl, "basePageUrl + \"")
            .replace("\"" + redirectionUrl, "redirectionUrl + \"")
            .replace("\"" + ggSignInUrl, "ggSignInUrl + \"")
            .replace("\"" + authUrl, "authUrl + \"")
            .replace("\"" + preferenceUrl, "preferenceUrl + \"")
            .replace(" ", ""))) {

          // Disguard repeated parameters

        } else {
          caseParamPrintList = caseParamPrintList + i
            .replace("\"" + basePageUrl, "basePageUrl + \"")
            .replace("\"" + redirectionUrl, "redirectionUrl + \"")
            .replace("\"" + ggSignInUrl, "ggSignInUrl + \"")
            .replace("\"" + authUrl, "authUrl + \"")
            .replace("\"" + preferenceUrl, "preferenceUrl + \"")
            .replace(" + \"\"", "") + "\n"
        }
      }

      // Remove the duplicate "val" parameters and update them with basePageUrl etc as appropriate
      var paramLineCounter = 0
      for (j <- valParamLines.sorted) {
        if (paramLines.toString.replace(" ", "").contains(j.replace(" ", ""))) {
          // Disguard repeated parameters
        } else {
          valParamPrintList = valParamPrintList + j + "\n"
        }
      }

      // Write a New Parameter file containing the old parameters and the New Ones
      val newParameterFile  = "AT_NewParameters" + logFileTime + ".txt"
      var fwParameterNew    = new FileWriter(outputFilePath + newParameterFile, true)

      for (k <- paramLines) {
        paramLineCounter += 1
        if (paramLineCounter != paramNumberOfLines) {
          if (k.trim.replace(" ", "") == "valreturnUrl:String=paramNamematch{") {
            // Write the new Case Params at the top of the getParam definition
            fwParameterNew.write(k + "\n" + caseParamPrintList)
          } else {
            fwParameterNew.write(k + "\n")
          }
        } else {
          if (!valParamPrintList.isEmpty) {
            fwParameterNew.write(valParamPrintList + "\n\n}")
          } else {
            // Ensures the closing Parameters brace stays in the same place if there are no new parameters to write
            fwParameterNew.write("}")
          }
        }

      }

      fwParameterNew.close()

      // Check which new Case and Val parameters are to be written to the Parameters file
      // Call separate Unix CP definition ................................. to avoid illogical compilation errors
      copyFileToFile(outputFilePath, newParameterFile, parameterFilePath, "ParametersTemplate.scala")

    }
  }

  def copyFileToFile(outFilePath: String, outFile: String, inFilePath: String, inFile: String): Unit = {
    Seq("cp", outFilePath + outFile, inFilePath + inFile).!!
  }

  // Close the blank browser page that opens when this CreateAcceptanceTest script is executed
  //BasePageTemplate.ShutdownTest()

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////// Definition Section ////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  def dateTime(): String = {
    val now = DateTime.now.toString("dd-MM-yyyy_HH:mm:ss:SSS")
    now
  }

  // Dynamically calculate the text to replace By.id if the input file is using a different findElement method
  def byOverride(x: String): String = {
    val returnVal: String = x match {
      case x if x.contains("By.name")         => ", idOverride = \"name\""
      case x if x.contains("By.cssSelector")  => ", idOverride = \"cssSelector\""
      case x if x.contains("By.xpath")        => ", idOverride = \"xpath\""
      case x if x.contains("By.linktext")     => ", idOverride = \"linkText\""
      case x if x.contains("By.partlinktext") => ", idOverride = \"partialLinkText\""
      case x if x.contains("By.classname")    => ", idOverride = \"className\""
      case x if x.contains("By.tagname")      => ", idOverride = \"tagName\""
      case _                                  => ""
    }
    returnVal
  }

  def scenarioAfterStatement() =
    "  After() {\n" +
      "    scenario: Scenario =>\n" +
      "      val scenarioStatus = scenario.getStatus\n" +
      "      if (!SCENARIO_PRINTED) {\n" +
      "        if (scenarioStatus == \"passed\") {\n" +
      "          logResult(\"Success\", scenarioName, \"\")\n" +
      "        } else if (scenarioStatus == \"failed\") {\n" +
      "          logResult(\"Error\", scenarioName, thrownException)\n" +
      "        }\n" +
      "        SCENARIO_PRINTED = true\n" +
      "      }\n" +
      "  }\n\n" +
      "}\n"


  After() {
    scenario: Scenario =>
      val scenarioStatus = scenario.getStatus
      if (!SCENARIO_PRINTED) {
        if (scenarioStatus == "passed") {
          logResult("Success", scenarioName, "")
        } else if (scenarioStatus == "failed") {
          logResult("Error", scenarioName, thrownException)
        }
        SCENARIO_PRINTED = true
      }
  }

}