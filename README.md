income-tax-subscription-agent-acceptance-tests
======================

This repo contain the acceptance tests for the income tax subscription service for Agents.

It is built using:

cucumber 1.1.8

java 1.8

Scala 2.11.7

SBT to build 0.13.8
    
### Getting started

Ensure that you have a working linux environment.

###  Execution
In /src/test/scala/uk/gov/hmrc/integration/cucumber there are scala classes which control what is run according to the tests tagged with the below tags. The main class is Runner which selects and runs tests which are tagged. You can run ./run_integration_local.sh to run tests against a local version of the application


################################################################################################################################################
INSTRUCTIONS
################################################################################################################################################


CREATE TEST SCRIPTS:
################################################################################################################################################

RECORD SCREEN FLOWS:

Record Acceptance Tests, Performance Tests & Accessibility Tests in FireFox Selenium (add Scenario / Scenario Outline instructions whilst or after recording or not at all)
// SCENARIO START additional text becomes scenario name (blank becomes a scenario number)
// SCENARIO END
// SCENARIO OUTLINE START
// SCENARIO OUTLINE END

Test automator currently only recognises write and click commands (no text or URL validation)
Export them to WebDriver (Scenario instructions can still be added)
Load acceptance test and accesibility files into AT_input (extension must be .txt)
Load performa nce test files into PT_input (extension must be .txt)

NOTE CreateTestScriptsAUTOMATOR creates Acceptance Tests, Performance Tests without URLs and Performance Tests with URLs

CONFIGURE AUTOMATOR:

Configure output directories in AT & PT Step Defs
Configure general parameters in ParameterTeplate

REMEMBER:

If Feature / Step Definition files (etc) already exist in the destination repository, they will be overwritten.

CREATE TEST SCRIPTS:

Execute CreateTestScriptsAUTOMATOR

Note - FireFox Selenium doesn't record URLs.
As these are required for Performance Tests they can either be added to the variables manually afterwards, or added automatically by running the scenario which runs the  Performance Journey's as features in order to scrape their URLs and update the URL variables manually.

TEST SCRIPTS OUTPUT TO CONFIGURED DIRECTORIES:

Feature files / step definitions / performance files etc are created in the AT_output / PT_output folders appropriate.
They are also created in OUT OF THE BOX WORKING ORDER in either the test-automator Features and Step Definition folders (or the mirror directories in a chosen parameterised repository)
If "simplified" commands were chosen (centralised default commands), the Acceptance and Wave tests will need the centralised templates in the test-automator repository to function.

BEFORE GENERATING ADDITIONAL TEST SCRIPTS:

To add new tests to a repository, set the directory parameters so that the appropriate existing step definitions are taken into account when new Step Definitions are being created.
There is no facility to automatically add additional tests to existing features. These would have to be recorded separately then pasted into the existing Features manually.

REMEMBER:

If Feature / Step Definition files (etc) already exist in the destination repository, they will be overwritten.



AUTOMATOR CONNCEPTS:
################################################################################################################################################

Concepts - the automated scripts require the centralised tests framework in this test repository to work.
If Feature Files & Step Definitions are being sent to other repositories automatically, the framework will have to be added to that repository.
(BasePageTemplate / BaseStepDefTemplate / ParameterTemplate / Utilities Template / test-execution-log directory - various standard WAVE files are required for accessibility tests).
Eath AT_input file generates a Feature File and an Associated Step Definition.
If there are multiple AT_input files any common Step Definitions will be grouped into an additional Common Step Definition File.
If the word WAVE is used in the AT_input file's name, a WAVE accessibility feature file will be created.
One Cucumber Feature Line is created for each webpage visited.
There is a parameter to add a Step Number to every feature row (if desired)
There is a parameter to turn the first row of each Feature file into a BACKGROUND (normally "given that URL")
The acceptance test creator prints a feature row each time a getTitle (new page) command is read from the input file, unless a scenario outline is indicated, in which case the scenario feature is printed at the end of the scenario, start of the next scenario, or end of the file.
All files ending in .txt in the AT_input directory are converted into Feature files (and overwrite existing feature files of the same name).
All files ending in .txt in the PT_input directory are converted into individual journies within the single performance test output file (creates a journey file, a pages file and an application.conf file)
If supplied with repository directories, or if running locally, the Acceptance Test creator reads any existing Step Definitions and takes these into account when generating Unique NON-LEXING steps and a common Step Definitions File.
The Common Step Definition file is the last definition created (matching steps are deleted from the individual files).
AT, PT and WAVE files can be generated into the test-automator repository, or a destination repository, and should RUN OUT OF THE BOX if the framework files are present.
For security - the TemplateBackups directory holds copies of all the Framework Files and other automation generating files held within the SRC directory.
The test-execution-log directory will record the outcome / error messages for every Scenario executed during a test run (based on centralised Afterhook success/error reporting)
The reporting, and many other things are paraterised in the ParametersTemplate (screen print on error etc).
AT_output and PT_output files continue to accumulate until deleted.
In the Acceptance and WAVE tests, all of the Field Names, Button Names (clickable items) and URLs are parameterised in the central file "ParametersTemplate" - allowing for universal changes to be applied in a single place
The ParametersTemplate file is automaticaly updated with new Field, Button and URL variables generated by the Test Creator (variables are never deleted automatically).
GeneralUtilities provides functions for manual inclusion into test scripts (none of these are added automatically).
UrlScraper is only used to create a file of URLs. One of the CreateTestScriptsAUTOMATOR scenarios reads and writes these into Performance Tests. By changing the feature sentences and putting the relevant input files in the PT_input directory, and Feature can be URL scraped into a URL file like this.
Bother the Acceptance and Performance Test creator StepDefinitions contain a list of "yet to be made" changes / additions.
Many things can be parameterised such as including a Background at the start of each Feature and how many empty/full Scenario Example lines should be created for each Outline.
The automator ONLY CREATES ONE ROW OF SCENARIO OUTLINE DATA. The additional rows should be filled in manually after the Feature Files are created.
To create a scenario outline the comment marking an OUTLINE needs to go before the getTitle command for the starting page of the outline.
To take advantage of the centralisationn and simplification of this automator, "webDriverCommandType" should be set to "simplified". A setting of "originalWebDriver" will product native uncentralised WebDriver commands.
The centralised WebDriver commands allow for Play Back to be PAUSED, advanced one test at a time, to be run at variable speed, and to produce execution/error reports. The non-centralised commands do not allow these features.
PAUSE / ADVANCE A STEP AT A TIME / SET PLAY BACK SPEED by applying the BaseStepDefTemplate commands to the Feature files (only works with "simplified" "webDriverCcommantType" commands).
Accepted SCENARIO comments in AT_input files:
// SCENARIO START
// SCENARIO END
// SCENARIO OUTLINE START
// SCENARIO OUTLINE END
Any text after these commands becomes becomes the name of the Scenario - otherwise they are assigned numbers.
STARTED scenarios will automatically end if another is STARTED.
The last Scenario command between any pair of getTitle commands is the one that will be applied.
If there are no Scenario commands, the whole feature will be a single SCENARIO.
END commands are not required at the end of the AT_input file
The automator will only interpret the commands WebDriver and SCENARIO commands it's designed to read. ALL OTHER TEXT IN THE INTPUT FILES WILL BE IGNORED
There was no time to break the code into a master program that calls multiple definitions in a separate utilities file thus :-
Every section of the Automator code contains NOTES to explain what it's doing - meaninng the text output to screen can be changed with ease.
The NON-LEXING code (preventing multiple stepDefinitions from overlapping) uses lists of alternative text to replace words in matching sentences. Large repositories may need more words. Note the "breakable" "breaks" in these text loops have not been tested (time constraint) and the breaks should be removed if they don't work.
When running BrowsersStacks scripts using the automator's Framework will run certain PHONES and PADS dynamically as if they were browsers (note that the browserstackdata recource has to be updated manually - [development time connstraint]
ITS specific sections of the CreatePerformanceTestStepDefTemplate have been commented out (uncomment includes them in the output).
A single Performance Test PAGES and JOURNIES file is created conntainins all of the performance journeys (as this is the way most current PTs are written).
GeneralUtilities contains a dynamic page link checker, a field validation checker (enter vectors of valid / invalid inputs), a screen table reader.
URL SCRAPE process created URLSCRAPE Feature and StepDefinition files in the test-automator repository.

When the utils.Runner has been set to the appropriate target (@CreateTests), it is executed with the Linux command './run_local.sh local chrome' (local environment using Chrome driver).
When executing tests, the template allows for automatic execution against multiple environments by substituting "local" for "qa" or "staging" - see available environments in ParametersTemplate.


ISSUES:
################################################################################################################################################

The AT and PT output directories will clog up until manually cleared.
Orphaned Step Definitions and Feature Files can cause the Automators to fail (definition not found etc) - the solution is to delete them before generating more files.
If templates like BaseStepTemplate are accidentally deleted / changed, there are backups in the TemplateBackups directory (rename them to .scala).
It's easy to put SCENARIO commands in the wrong place and create illogical loops. Generally moving the one getTitle forwards or backwards will solve this.
Illogical errors (definition nont found after Features / Definitions have been deleted etc) can usually be solved by applying an 'sbt clean compile' at the Linux command prompt.
Scala tends to prevent 2 unix commands being run in sequence. The solution is to divide the with an irrelevant command (a = "b" or assert(1==1), or to put the second command in a definition and call it after the first command.
There are no screen text validator commands (RAN OUT OF TIME). Recommend using TEXT commands as others conflice with the "contains" values the automator is checking.
FireFox Selenium files have to be manually exported into WebDriver commands (suggest "Java Junit 4 WebDriver"). Ran out of time to automate this.
The Framework Template files are not automatically transferred to target repositories (ran out of time). They would have to check any existing files were exactly the same size to avoid overwriting local code in the target repository.
Afterhooks have a flaw whereby if there's a compilation issue in the StepDef code they don't register this as a scenario failure and report Scenario Success.
Afterhook error reporting ISN'T 100% RELIABLE. It turns out multiple hooks fire during each error, so without hardcoding the string of each feature step into each feature for error reporting, isn't not always possible to pin point errors using the hook messages.


BE AWARE
THIS TOOL HASN'T AND WON'T BE FULLY TESTED
