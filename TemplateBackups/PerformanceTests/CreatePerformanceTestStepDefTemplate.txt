package uk.gov.hmrc.integration.cucumber.stepdefs

import java.io.{File, FileWriter}

import cucumber.api.Scenario
import cucumber.api.scala.{EN, ScalaDsl}
import org.joda.time.DateTime
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._

import scala.io.Source._
import scala.reflect.io.Directory
import sys.process._

class CreatePerformanceTestStepDefTemplate extends ScalaDsl with EN {

  Before() { scenario: Scenario =>
    scenarioName      = scenario.getName
    SCENARIO_PRINTED  = false
  }

  //*************************************************************************************************************************************************//
  //********** USER CONFIGURABLE OUTPUT FILE DIRECTORIES (OPTIONAL) *********************************************************************************//
  //*************************************************************************************************************************************************//
  val userPTpagesAndJourniesDir = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/vat-flat-rate-calculator-performance-tests" + "/src/test/scala/uk/gov/hmrc/perftests/vfr/"
  val userPTjourneyConfDir      = "/home/joseph" + "/Applications/hmrc-development-environment/hmrc/vat-flat-rate-calculator-performance-tests" + "/src/test/resources/"
//   val userPTpagesAndJourniesDir = ""
//   val userPTjourneyConfDir      = ""
  //*************************************************************************************************************************************************//
  //********** END OF USER CONFIGURABLE OUTPUT FILE DIRECTORIES (OPTIONAL) **************************************************************************//
  //*************************************************************************************************************************************************//

  //*************************************************************************************************************************************************//
  //********** USER CONFIGURABLE PARAMETERS *********************************************************************************************************//
  //*************************************************************************************************************************************************//
  val PTfileProjectName     = "vfr" // the project name the files will be given so that "Simulations.scala" becomes vfrSimulations.scala (for PTfileProjectName of "vfr")
  //*************************************************************************************************************************************************//
  //********** END OF USER CONFIGURABLE PARAMETERS **************************************************************************************************//
  //*************************************************************************************************************************************************//

  // Variables for storing the output file names ready to be written to their destination folders in the active Performance Test repository.
  var PTpages           = ""
  var PTjournies        = ""
  var PTjourneyConf     = ""
  var PToutputFilePath  = ""
  val PTjourniesClass   = "Requests"
  val PTpagesClass      = "Simulations"


  //******************************************************************************
  //********** CUCUMBER EXECUTES PERFORMANCE TEST CREATION ***********************
  //******************************************************************************

  When("""^Scala files are read and a performance test is created$""") {


    //******************************************************************************
    //********** LIST OF POTENTIAL CODE CHANGES / ADDITIONS ************************
    //******************************************************************************

    // Capitalise parameter names
    // Create variables to set URL values
    // Turn strings into named Defs
    // Create Resources folder files (including generic WITH journeys)
    // NEED TO REMOVE navigateToAuthorityWizard & navigateToEnteryourclientdetails and navigateToConfirmyourclient etc if using ITSA BEFORE text - and add "navigateToIndex"
    // Could automate commented out ITS code currently at the bottom of this code
    // Name the journies after their input files
    // Refactor string chopping into definitions for clarity
    // Create application.conf automatically


    //******************************************************************************
    //********** SET DATETIME FOR THE UNIQUE OUTPUT FILENAMES **********************
    //******************************************************************************

    var logFileTime = ""

    def dateTime(): String = {
      val now = DateTime.now.toString("dd-MM-yyyy_HH:mm:ss:SSS")
      now
    }

    if (logFileTime.isEmpty) {
      logFileTime = dateTime()
    }


    //******************************************************************************
    //********** CONFIGURE FILE DIRECTORIES ****************************************
    //******************************************************************************

    val currentDir            = new java.io.File(".").getCanonicalPath
    val outputFilePath        = currentDir + "/PT_output/"
    val inputFilePath         = currentDir + "/PT_input/"
    PToutputFilePath          = outputFilePath
    val PTpagesAndJourniesDir = if (userPTpagesAndJourniesDir.length > 0) userPTpagesAndJourniesDir else currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/"
    val PTjourneyConfDir      = if (userPTjourneyConfDir.length > 0) userPTjourneyConfDir else currentDir + "/src/test/resources/"

    // Store the file names and output file path so the performance files can be copied to the active performance repository after the create files loop closes.
    PTpages           = "PT_Pages_" + logFileTime + ".txt"
    PTjournies        = "PT_Journeys_" + logFileTime + ".txt"
    PTjourneyConf     = "PT_JourneysConf_" + logFileTime + ".txt"
    // Create the output files to write during the input file loop
    val fwPages       = new FileWriter(outputFilePath + PTpages, true)
    val fwJournies    = new FileWriter(outputFilePath + PTjournies, true)
    val fwJourneyConf = new FileWriter(outputFilePath + PTjourneyConf, true)


    //******************************************************************************
    //********** SET GLOBAL VARIABLES **********************************************
    //******************************************************************************

    var navigateString    = ""
    var submitString      = ""
    var journeyString     = ""
    var redirectString    = ""
    var nextPage: Boolean = false
    var journeyNumber     = 1
    var pageNumber        = 1
    var firstRun          = true
    var pageList          = scala.collection.mutable.Set[String]()
    var urlList           = scala.collection.mutable.Set[String]()

    // SPECIFIC ITSA PARAMETERS - Their PTs are specialised, normal PTs will not require their additional test commands.
    val includeITSABeforeStub = false
    val ITSAJourney           = false


    //******************************************************************************
    //********** LOOP THROUGH EACH FILE IN THE "PT" INPUT DIRECTORY ****************
    //******************************************************************************

    var inputFileCount: Int = 0
    val fileDir: Directory  = Directory(new File(inputFilePath))
    // Count the files for journeys.conf loading (divided evenly for now)
    for (file <- fileDir.list if file.name endsWith ".txt" ) {inputFileCount += 1}
    for (file <- fileDir.list if file.name endsWith ".txt") {

      // Loop variables
      navigateString  = ""
      submitString    = ""
      journeyString   = ""
      redirectString  = ""
      nextPage        = false

      //    ********** OLD CODE TO PRINT JOURNEY FILES IN SEPARATE PAGES *****************
      //    val fwPages = new FileWriter(outputFilePath + journeyNumber + "PT_Pages_" + file.name.replace(".txt", "") + logFileTime + ".txt", true)
      //    val fwJournies = new FileWriter(outputFilePath + journeyNumber + "PT_Journeys_" + file.name.replace(".txt", "") + logFileTime + ".txt", true)


      //******************************************************************************
      //********** WRITE FILE HEADERS (Pages, Journies, Conf) ************************
      //******************************************************************************

      // Print package headers for the first file being processed in a loop (applies to collective journeys or individual journeys
      if (firstRun) {

        fwJournies.write("package uk.gov.hmrc.perftests." + PTfileProjectName + "\n\n" +
          "import uk.gov.hmrc.performance.simulation.PerformanceTestRunner\n")
        fwJournies.write("import uk.gov.hmrc.perftests." + PTfileProjectName + "." + PTfileProjectName + PTjourniesClass + "._\n")

        if (includeITSABeforeStub) { // Extra includes if printing ITSA journey BEFORE text
          fwJournies.write("import java.net.{HttpCookie, HttpURLConnection, URL, URLEncoder}\n" +
            "import org.jsoup.Jsoup\n")
        }

        fwJournies.write("\nclass " + PTfileProjectName + PTpagesClass + " extends PerformanceTestRunner {")
        //        fwPages.write("package uk.gov.hmrc.perftests.PACKAGE_NAME\n\nimport io.gatling.core.Predef._\nimport io.gatling.http.Predef._\n\nobject pageSteps" + pageNumber + " extends PerformanceTestRunner {" + "\n\n")
        fwPages.write("package uk.gov.hmrc.perftests." + PTfileProjectName + "\n\n" +
          "import uk.gov.hmrc.performance.conf.ServicesConfiguration\n" +
          "import io.gatling.core.Predef._\n" +
          "import io.gatling.http.Predef._\n\n")
        fwPages.write("object " + PTfileProjectName + PTjourniesClass + " extends ServicesConfiguration {" + "\n\n")
        fwPages.write("  val csrfPattern      = \"\"\"<input type=\"hidden\" name=\"csrfToken\" value=\"([^\"]+)\"\"\"\"\n" +
          "  def saveCsrfToken()  = regex(_ => csrfPattern).saveAs(\"csrfToken\")\n\n")

        if (includeITSABeforeStub) {
          ITSABeforeStub()
        }

        // Print journeys.conf file header
        ITSAJourneysConfStart()
        firstRun = false

      } else {

        // Close previous journey
        fwJournies.write("\n" + "  )")

      }


      //******************************************************************************
      //********** WRITE JOURNEY START TEXT AND JOURNEY CONF ENTRY *******************
      //******************************************************************************

      // Write the start of each journey
      fwJournies.write("\n\n  setup(\"" + file.name.replace(".txt","") + "_journey" + journeyNumber + "\", \"Journey " + journeyNumber + " Description\") withRequests(" + "\n")

      //******************************************************************************
      //********** WRITE JOURNEYS.CONF FILE ******************************************
      //******************************************************************************

      // Write the journeys.conf record for each journey
      fwJourneyConf.write("  " + file.name.replace(".txt","") + " = {\n\n")
      fwJourneyConf.write("    description = \"" + file.name.replace(".txt","") + "_journey" + journeyNumber + " Description\"\n\n")
      // Calculate journey load as a fraction of 1 (evenly divided between journeys for now - can't divide 1, so dividing 100 and multiplying by 0.01).
      fwJourneyConf.write("    load = " + (100 / inputFileCount * 0.01).toString + "\n")
      fwJourneyConf.write("    parts = [")
      // Only print setups start, login and matchedClient for ITSA - other projects may/will have different setups
      if (ITSAJourney) {
        fwJourneyConf.write("start, login, matchedClient, ")
      }
      fwJourneyConf.write(file.name.replace(".txt","") + "_journey" + journeyNumber + "]\n  }\n\n")


      //******************************************************************************
      //********** LOOP THROUGH THE INPUT FILE AND WRITE THE OUTPUT FILE CONTENTS ****
      //******************************************************************************

      try {

        io.Source.fromFile(inputFilePath + file.name).getLines.foreach { x =>

          //******************************************************************************
          //********** NAVIGATE COMMANDS *************************************************
          //******************************************************************************

          x match {

            case x if x.contains("getTitle") =>

              //********************************************************
              // Store redirectString & navigateString for use in SUBMIT
              //********************************************************

              if (nextPage) {
                redirectString =  "      .check(status.is(303))" + "\n" +
                  "//      .check(redirectionLocationIs())" + "\n\n"
              } else {
                nextPage = true
              }

              navigateString = redirectString +
                //                "  lazy val navigateTo" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + " = \n" +
                "  lazy val navigateTo" + concatScreenName + " = \n" +
                //                "    http(\"Navigate to " + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + " page\")\n" +
                "    http(\"Navigate to " + screenTitleSentence + " page\")\n" +
                "      .get(" + urlVariable + ")\n" +
                "      .check(status.in(200))" + "\n" +
                "      .check(saveCsrfToken())" + "\n"

              //*******************
              // Write Journey File
              //*******************

              if (journeyString != "") fwJournies.write("," + "\n")
              //              journeyString = "    navigateTo" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "")
              journeyString = "    navigateTo" + concatScreenName
              fwJournies.write(journeyString)

              // Add each new URL variable to the url list for printing at the bottom of the Simulations File
              if (!urlList.contains(urlVariable)) {
                urlList += urlVariable
              }

            //***********************************************************
            // Ignore all commands except for getTitle in the input files
            //***********************************************************

            case _ => navigateString = ""

          }

          //******************************************************
          // Write a Page command if a Journey command was created
          //******************************************************

          if (!navigateString.isEmpty) {

            //            val commandName = "navigateTo" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "")
            val commandName = "navigateTo" + concatScreenName

            // only create a navigateTo page entry if there isn't one already.
            if (pageList.contains(commandName)) {
              // Only create NEW navigate commands in the Page File(s)
            } else {
              pageList += commandName
              fwPages.write(navigateString + "\n")
            }

          }


          //******************************************************************************
          //********** SUBMIT COMMANDS ***************************************************
          //******************************************************************************

          x match {

            //*************************************
            // Store submitString for use in SUBMIT
            //*************************************

            case x if x.contains("getTitle") => submitString =
              //              "  lazy val submit" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + " = \n" +
              "  lazy val submit" + concatScreenName + " = \n" +
                //              "    http(\"Submit to " + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + " page\")\n" +
                "    http(\"Submit to " + screenTitleSentence + " page\")\n" +
                //                "      .post(" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + "UrlFull)\n" +
                "      .post(" + urlVariable + ")\n" +
                "      .formParam(\"csrfToken\", \"${csrfToken}\")"

              //*******************
              // Write Journey File
              //*******************

              if (journeyString != "") fwJournies.write("," + "\n")
              //              journeyString = "    submit" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "")
              journeyString = "    submit" + concatScreenName
              fwJournies.write(journeyString)

            //**********************************************
            // Store any Field Entries into the submitString
            //**********************************************

            case x if x.contains("sendKeys") => submitString =
              //              "      .formParam(\"" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)) + "\", \"" + x.substring(x.indexOf("Keys(\"") + 6, x.indexOf("\");")) + "\")"
              "      .formParam(\"" + formElement + "\", \"" + formValue + "\")"

            //**********************************************************************
            // Ignore all commands except for getTitle & sendKeys in the input files
            //**********************************************************************

            case _ => submitString = ""

          }

          //******************************************************
          // Write a Page command if a Journey command was created
          //******************************************************

          if (!submitString.isEmpty) {

            //            val commandName = "submit" + x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "")
            val commandName = "submit" + concatScreenName

            // only create a navigateTo page entry if there isn't one already.
            if (pageList.contains(commandName)) {
              // Only create NEW navigate commands in the Page File(s)
            } else {
              pageList += commandName
              fwPages.write(submitString + "\n")
            }

          }


          //******************************************************************************
          //********** SELENIUM TO WEBDRIVER STRING DEFINITIONS **************************
          //******************************************************************************

          def urlVariable         = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).split(' ').map(_.capitalize).mkString(" ").replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "") + "UrlFull"
          def concatScreenName    = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replaceAll(" ", "").replaceAll("'", "").replace("^exact:", "").replace("[\\\\s\\\\S]$", "")
          def screenTitleSentence = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1)).replace("^exact:", "").replace("[\\\\s\\\\S]$", "")
          def formElement         = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", x.indexOf("\"") + 1))
          def formValue           = x.substring(x.indexOf("Keys(\"") + 6, x.indexOf("\");"))

        }

      } catch {

        case e: Exception =>

          //********************************************************************
          // Close the files in a (hopefully) working state if there's a failure
          //********************************************************************

          fwJournies.write("\n" + "  )" + "\n\n  runSimulation()\n\n}")
          fwPages.write("\n}")

          fwPages.close()
          fwJournies.close()
          fwJourneyConf.close()

          assert(e.getMessage == "PRINT ERR")

      }

      journeyNumber += 1
      pageNumber    += 1

    }


    //******************************************************************************
    //********** COMPLETE THE SUCCESSFUL JOURNEY AND PAGES FILES *******************
    //******************************************************************************

    fwJournies.write("\n" + "  )" + "\n\n  runSimulation()\n\n}")
    fwPages.write("      .check(status.is(303))\n//      .check(redirectionLocationIs())\n")


    //******************************************************************************
    //********** PRINT URL NAVIGATION VARIABLES IN THE PAGES FILE ******************
    //******************************************************************************

    // If URL variables were generated, print them at the bottom of the Simulations (fwPages) file
    if (!urlList.isEmpty) {
      val padding: Int = urlList.maxBy(_.length).length + 9 // The 8 accounts for the "  val url"
      fwPages.write("\n")
      urlList.foreach {url =>
        fwPages.write(("  val " + url).padTo(padding, ' ') + " = " + "\"\"\n")
      }
    }


    //******************************************************************************
    //********** CLOSE THE JOURNEY, PAGES AND CONFIG FILES *************************
    //******************************************************************************

    fwPages.write("\n}")
    fwPages.close()
    fwJournies.close()
    // Populate end of journeys.conf before closing it
    ITSAJourneysConfEnd()
    fwJourneyConf.close()


    //******************************************************************************
    //********** SPECIFIC ITSA DEFINITIONS (EXTRA LOCAL FUNCTIONALITY) *************
    //******************************************************************************

    def ITSABeforeStub() {
      fwJournies.write("\n\n  before {\n\n")
      fwJournies.write("    val url = ClientStub.stubUrl\n\n")
      fwJournies.write("    println(\"stubbing the test client\")\n\n")
      fwJournies.write("    def getCsrfAndCookie(): (String, List[HttpCookie]) = {\n")
      fwJournies.write("      import collection.JavaConversions._\n")
      fwJournies.write("      val connection = new URL(url).openConnection.asInstanceOf[HttpURLConnection]\n")
      fwJournies.write("      connection.setRequestMethod(\"GET\")\n\n")
      fwJournies.write("      val responseCode = connection.getResponseCode\n")
      fwJournies.write("      println(\"" + "\\" + "n" + "Sending 'GET' request to URL : \" + url)\n")
      fwJournies.write("      println(\"Response Code : \" + responseCode)\n")
      fwJournies.write("      assert(responseCode == 200)\n\n")
      fwJournies.write("      val inputStream = connection.getInputStream\n")
      fwJournies.write("      val content = scala.io.Source.fromInputStream(inputStream).mkString\n")
      fwJournies.write("      if (inputStream != null) inputStream.close()\n\n")
      fwJournies.write("      val cookie: List[HttpCookie] = connection.getHeaderFields.get(\"Set-Cookie\")\n")
      fwJournies.write("      .map(HttpCookie.parse).reduce(_ ++ _).toList\n\n")
      fwJournies.write("      val csrf = Jsoup.parse(content).select(\"input[name=csrfToken]\").attr(\"value\")\n\n")
      fwJournies.write("      (csrf, cookie)\n")
      fwJournies.write("    }\n\n")
      fwJournies.write("    val (csrfToken: String, cookies: List[HttpCookie]) = getCsrfAndCookie()\n\n")
      fwJournies.write("    // stub client\n")
      fwJournies.write("    def stubClient() = {\n\n")
      fwJournies.write("      val request = Map(\n")
      fwJournies.write("        \"csrfToken\" -> csrfToken,\n")
      fwJournies.write("        \"clientFirstName\" -> \"Test\",\n")
      fwJournies.write("        \"clientLastName\" -> \"User\",\n")
      fwJournies.write("        \"clientNino\" -> \"AA 11 11 11 A\",\n")
      fwJournies.write("        \"clientSautr\" -> \"1234567890\",\n")
      fwJournies.write("        \"clientDateOfBirth.dateDay\" -> \"01\",\n")
      fwJournies.write("        \"clientDateOfBirth.dateMonth\" -> \"01\",\n")
      fwJournies.write("        \"clientDateOfBirth.dateYear\" -> \"1980\"\n")
      fwJournies.write("      )\n\n")
      fwJournies.write("      val urlParameters = request.map(entry =>\n")
      fwJournies.write("        URLEncoder.encode(entry._1, \"UTF-8\") + \"=\" + URLEncoder.encode(entry._2, \"UTF-8\")\n")
      fwJournies.write("      ).mkString(\"&\")\n\n")
      fwJournies.write("      val connection = new URL(url).openConnection.asInstanceOf[HttpURLConnection]\n")
      fwJournies.write("      connection.setRequestMethod(\"POST\")\n")
      fwJournies.write("      val cookieValues = cookies.:+(\"seen_cookie_message=yes\").mkString(\"; \")\n")
      fwJournies.write("      connection.setRequestProperty(\"Cookie\", cookieValues)\n")
      fwJournies.write("      connection.setRequestProperty(\"Content-Type\", \"application/x-www-form-urlencoded\")\n")
      fwJournies.write("      connection.setRequestProperty(\"Content-Length\", urlParameters.getBytes.length.toString)\n")
      fwJournies.write("      connection.setDoOutput(true)\n")
      fwJournies.write("      connection.getOutputStream.write(urlParameters.getBytes(\"UTF-8\"))\n\n")
      fwJournies.write("      val responseCode = connection.getResponseCode\n")
      fwJournies.write("      println(\"" + "\\" + "n" + "Sending 'POST' request to URL : \" + url)\n")
      fwJournies.write("      println(\"Response Code : \" + responseCode)\n")
      fwJournies.write("      assert(responseCode == 200)\n\n")
      fwJournies.write("    }\n\n")
      fwJournies.write("    stubClient()\n")
      fwJournies.write("  }\n\n")
      fwJournies.write("  setup(\"start\", \"Start new application\") withRequests(navigateToGuidance, beginApplication)\n\n")
      fwJournies.write("  setup(\"login\", \"Login\") withRequests(navigateToAuth, logInWithUser)")
    }

    def ITSAJourneysConfStart() {
      fwJourneyConf.write("# Configure here your journeys. A journey is a sequence of requests at a certain load.\n\n")
      fwJourneyConf.write("journeys {\n\n")
      fwJourneyConf.write("//  # Example\n")
      fwJourneyConf.write("//  # Give a name to the journey.\n")
      fwJourneyConf.write("//  hello-world = {\n")
      fwJourneyConf.write("//\n")
      fwJourneyConf.write("//    # The description will appear in the test report. Use something meaningful\n")
      fwJourneyConf.write("//    description = \"Hello world journey\"\n")
      fwJourneyConf.write("//\n")
      fwJourneyConf.write("//    # The load is in journeys per second. Put here the load you are going to have at the peak.\n")
      fwJourneyConf.write("//    # There is no need to put a higher value at this point. Use prerftest.loadPercentage in application.conf instead\n")
      fwJourneyConf.write("//    load = 9.1\n")
      fwJourneyConf.write("//\n")
      fwJourneyConf.write("//    # This points to a csv file with the data you need to inject in the journey. [More here](https://github.com/hmrc/performance-test-runner#step-4-configure-the-user-feeder)\n")
      fwJourneyConf.write("//    feeder = data/helloworld.csv\n")
      fwJourneyConf.write("//\n")
      fwJourneyConf.write("//    # The parts your journey is made of. A part is made one or more requests.\n")
      fwJourneyConf.write("//    parts = [\n")
      fwJourneyConf.write("//      login,\n")
      fwJourneyConf.write("//      home\n")
      fwJourneyConf.write("//    ]\n")
      fwJourneyConf.write("//  }\n\n")
    }

    def ITSAJourneysConfEnd() {
      fwJourneyConf.write("}\n\n")
      fwJourneyConf.write("#Default behaviour is to run all journeys. If that is not what you need you can specify the list of journeys to run\n")
      fwJourneyConf.write("#journeysToRun = [\n")
      fwJourneyConf.write("#  property,\n")
      fwJourneyConf.write("#  business,\n")
      fwJourneyConf.write("#  business-and-property\n")
      fwJourneyConf.write("#]\n\n")
      fwJourneyConf.write("# You can specify the same list of journeys via environment variables:\n")
      fwJourneyConf.write("# journeysToRun.0 = hello-world-1\n")
      fwJourneyConf.write("# journeysToRun.1 = hello-world-3\n")
    }


    //******************************************************************************
    //********** COPY OUTPUT FILES TO THE USER ENTERED PERFORMANCE REPOSITORIES ****
    //******************************************************************************

    // Send copies of the output files to the User Entered Performance Test repository directories
    copyFileToFile(PToutputFilePath, PTjournies, PTpagesAndJourniesDir, PTfileProjectName + "Simulations.scala")
    copyFileToFile(PToutputFilePath, PTpages, PTpagesAndJourniesDir, PTfileProjectName + "Requests.scala")
    copyFileToFile(PToutputFilePath, PTjourneyConf, PTjourneyConfDir, "journeys.conf")

  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // GENERIC ITS JOURNEYS TO SET UP THE OTHER JOURNEYS - just commented out, not coded into the above //////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  setup("matchedClient", "Match against the stubbed client") withRequests(
  //    navigateToIndex,
  //    navigateToClientDetails,
  //    submitMatchedClientDetails,
  //    navigateToConfirmClient,
  //    submitConfirmClient
  //    )
  //
  //  setup("unmatchedClient", "DO NOT match against the stubbed client") withRequests(
  //    navigateToIndex,
  //    navigateToClientDetails,
  //    submitUnmatchedClientDetails,
  //    navigateToClientDetailsError,
  //    submitClientDetailsError,
  //    navigateToClientDetails
  //    )
  //  GENERIC STEPS
  //  val indexUrl = baseUrl + "/" + "index"
  //  lazy val navigateToIndex =
  //
  //    http("Navigate to the Home page")
  //      .get(indexUrl)
  //      .check(status.in(303))
  //
  //  val clientDetailsUrl = "/client-details"
  //  val clientDetailsUrlFull = baseUrl + clientDetailsUrl
  //
  //  lazy val navigateToClientDetails =
  //    http("Navigate to Client Details page")
  //      .get(clientDetailsUrlFull)
  //      .check(status.is(200))
  //      .check(saveCsrfToken())
  //
  //  lazy val submitMatchedClientDetails =
  //    http("Submit Matched Client Details")
  //      .post(clientDetailsUrlFull)
  //      .formParam("csrfToken", "${csrfToken}")
  //      .formParam("clientFirstName", "Test")
  //      .formParam("clientLastName", "User")
  //      .formParam("clientNino", "AA111111A")
  //      .formParam("clientDateOfBirth.dateDay", "01")
  //      .formParam("clientDateOfBirth.dateMonth", "01")
  //      .formParam("clientDateOfBirth.dateYear", "1980")
  //      .check(status.is(303))
  //      .check(redirectionLocationIs(ConfirmClientRequest.confirmClientUrl))
  //
  //  lazy val submitUnmatchedClientDetails =
  //    http("Submit Invalid Client Details")
  //      .post(clientDetailsUrlFull)
  //      .formParam("csrfToken", "${csrfToken}")
  //      .formParam("clientFirstName", "Test")
  //      .formParam("clientLastName", "User")
  //      .formParam("clientNino", "BB111111B")
  //      .formParam("clientDateOfBirth.dateDay", "01")
  //      .formParam("clientDateOfBirth.dateMonth", "01")
  //      .formParam("clientDateOfBirth.dateYear", "1980")
  //      .check(status.is(303))
  //      .check(redirectionLocationIs(ConfirmClientRequest.confirmClientUrl))
  //
  //  val confirmClientUrl = "/confirm-client"
  //  val confirmClientUrlFull = baseUrl + confirmClientUrl
  //
  //  lazy val navigateToConfirmClient =
  //    http("Navigate to Confirm Client page")
  //      .get(confirmClientUrlFull)
  //      .check(status.is(200))
  //      .check(saveCsrfToken())
  //
  //  lazy val submitConfirmClient =
  //    http("Submit with Confirmed Client details")
  //      .post(confirmClientUrlFull)
  //      .formParam("csrfToken", "${csrfToken}")
  //      .check(status.is(303))
  //      .check(redirectionLocationIs(IncomeSourceRequest.incomeSourceUrl))
  //
  //  val clientDetailsErrorUrl = "/error/client-details"
  //  val clientDetailsErrorUrlFull = baseUrl + clientDetailsErrorUrl
  //
  //  lazy val navigateToClientDetailsError =
  //    http("Navigate to Confirm Client Details Error page")
  //      .get(clientDetailsErrorUrlFull)
  //      .check(status.is(200))
  //      .check(saveCsrfToken())
  //
  //  lazy val submitClientDetailsError =
  //    http("Submit with Go Back button to Client details")
  //      .post(clientDetailsErrorUrlFull)
  //      .formParam("csrfToken", "${csrfToken}")
  //      .check(status.is(303))
  //      .check(redirectionLocationIs(ClientDetailsRequest.clientDetailsUrl))
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // ABOVE - GENERIC ITS JOURNEYS TO SET UP THE OTHER JOURNEYS - just commented out, not coded into the above //////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  //  BasePageTemplate.driver.close


  //******************************************************************************
  //********** UNIX FILE COLPY COMMAND *******************************************
  //******************************************************************************

  // Unix file copy command to load the user defined performance test repository directories
  def copyFileToFile(outFilePath: String, outFile: String, inFilePath: String, inFile: String): Unit = {
    Seq("cp", outFilePath + outFile, inFilePath + inFile).!!
  }

  When("""^a URL_SCRAPE_LIST is created by running the performance test input files$""") {

    // TRY CATCH allows PT creation to complete even if PT input files won't run.
    try {
      "./UrlScraper.sh local chrome".!!
      assert(1==1)
    } catch {
      case e: Exception =>
        assert(1==1)
    }

  }

  Then("""^clear unnecessary files generated when URL_SCRAPE_LIST was created$""") { () =>

    val currentDir          = new java.io.File(".").getCanonicalPath
    val outputFilePath      = currentDir + "/PT_output/"
    val fileDir: Directory  = Directory(new File(outputFilePath))

    for (file <- fileDir.list) {
      if (!file.name.contains("PT_") && !file.name.contains("SCRAPE")) {
        Seq("rm", outputFilePath + file.name).!!
      }
    }

  }

  When("""^the URL_SCRAPE_LIST is read and the performance test Url variables are updated$""") {

    try {

      val currentDir            = new java.io.File(".").getCanonicalPath
      val outputFilePath        = currentDir + "/PT_output/"
      val PTpagesAndJourniesDir = if (userPTpagesAndJourniesDir.length > 0) userPTpagesAndJourniesDir else currentDir + "/src/test/scala/uk/gov/hmrc/integration/cucumber/"
      val fileDir: Directory    = Directory(new File(outputFilePath))
      var fileCounter           = 0

      for (file <- fileDir.list if file.name contains "PT_Pages") {

        fileCounter += 1

        if (fileCounter == 1) {

          val ptPagesLines          = io.Source.fromFile(outputFilePath + file.name).getLines.toList
          val ptPageraNumberOfLines = io.Source.fromFile(outputFilePath + file.name).getLines.size
          val urlScrapeListLines    = io.Source.fromFile(outputFilePath + "URL_SCRAPE_LIST.txt").getLines.toList
          var urlScrapeMap          = scala.collection.mutable.Map[String, String]()

          for (i <- urlScrapeListLines) {
            urlScrapeMap += i.substring(0, i.indexOf(" (")) + " " -> i.substring(i.indexOf("(") + 1, i.indexOf(")"))
          }

          // Transfer ALL required template files for AT/PT to target repository without overwriting existing.
          // import java.nio.file.{Paths, Files}
          // Files.exists(Paths.get("/tmp"))
          // long length = FileSystem.getFileStatus(PATH).getLen();

          val newParameterFile  = "PT_Pages_WithURLs_" + logFileTime + ".txt"
          var fwParameterNew    = new FileWriter(outputFilePath + newParameterFile, true)

          for (k <- ptPagesLines) {

            if (k.contains("UrlFull") && k.contains("= \"\"")) {

              val urlVariableName = k.substring(k.indexOf("val ") + 4, k.indexOf("UrlFull")).capitalize + " "

              if (urlScrapeMap.contains(urlVariableName)) {
                // Add a space to the match to ensure "contains" matches a full variable
                k.replace("= \"\"", "= \"" + urlScrapeMap(urlVariableName))
                fwParameterNew.write(k.replace("= \"\"", "= \"" + urlScrapeMap(urlVariableName)) + "\"\n")
              } else {
                fwParameterNew.write(k + "\n")
              }

            } else {

              fwParameterNew.write(k + "\n")

            }

          }

          fwParameterNew.close()
          copyFileToFile(outputFilePath, newParameterFile, PTpagesAndJourniesDir, PTfileProjectName + "Requests.scala")

        }
      }
    }

    catch {
      case e: Exception =>
        assert(1 == 1)
    }

  }

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