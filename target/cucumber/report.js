$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CreateTestScriptsAUTOMATOR.feature");
formatter.feature({
  "line": 3,
  "name": "Create Test Scripts",
  "description": "",
  "id": "create-test-scripts",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@CreateTests"
    }
  ]
});
formatter.before({
  "duration": 51696341,
  "status": "passed"
});
formatter.before({
  "duration": 23107,
  "status": "passed"
});
formatter.before({
  "duration": 16665,
  "status": "passed"
});
formatter.before({
  "duration": 16319,
  "status": "passed"
});
formatter.before({
  "duration": 15274,
  "status": "passed"
});
formatter.before({
  "duration": 14900,
  "status": "passed"
});
formatter.before({
  "duration": 19462,
  "status": "passed"
});
formatter.before({
  "duration": 23156,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 5,
      "value": "# INSTRUCTIONS"
    },
    {
      "line": 7,
      "value": "# 1) Record Acceptance Tests, Performance Tests \u0026 Accessibility Tests in FireFox Selenium (add Scenario / Scenario Outline instructions whilst or after recording, or not at all)."
    },
    {
      "line": 8,
      "value": "#    // SCENARIO START additional text becomes scenario name (blank becomes a scenario number)"
    },
    {
      "line": 9,
      "value": "#    // SCENARIO END"
    },
    {
      "line": 10,
      "value": "#    // SCENARIO OUTLINE START"
    },
    {
      "line": 11,
      "value": "#    // SCENARIO OUTLINE END"
    },
    {
      "line": 12,
      "value": "#    Test automator currently only recognises sendKeys and click commands (no text or URL validation)."
    },
    {
      "line": 14,
      "value": "# 2) Export them to WebDriver (Scenario instructions can still be added)."
    },
    {
      "line": 15,
      "value": "# 3) Load acceptance test and accesibility files into AT_input \u003e load performance test files into PT_input (all extensions must be .txt)."
    },
    {
      "line": 16,
      "value": "# 4) Configure output directories in AT \u0026 PT Step Defs (existing Step Defs in output directories will be read into memory to prevent LEXING repetition)."
    },
    {
      "line": 17,
      "value": "# 5) Configure general parameters in ParameterTemplate."
    },
    {
      "line": 19,
      "value": "# 6) Execute CreateTestScriptsAUTOMATOR (OVERWRITES EXISTING Feature / Step Definition etc FILES in destination repository)."
    },
    {
      "line": 20,
      "value": "# 7) Manually add Performance Test URLs or run PT Scraper scenario to add them automatically (buy running the input files to scrape them)."
    },
    {
      "line": 22,
      "value": "# Feature files / step definitions / performance files etc are created in the AT_output / PT_output folders \u003e and ready to run in the parameterised destination folder."
    },
    {
      "line": 23,
      "value": "# 8) The \"simplified\" centralised Acceptance / Accessibility commands require the centralised templates to run (COPY AND PASTE THESE FROM THE test-automator REPOSITORY)."
    },
    {
      "line": 24,
      "value": "# Centralised Templates:- BasePageTemplate \u0026 BaseStepDefTemplate \u0026 ParametersTemplate \u0026 UtilitiesTemplate (Wave needs standard Wave/TemplateStepDef \u0026 WaveStepDef \u0026 WaveReport)."
    },
    {
      "line": 25,
      "value": "# Other templates specific to the test-automator: features/CreateTestScriptsAUTOMATOR \u0026 CreateAcceptanceTestStepDefTempate \u0026 CreatePerformanceTestStepDefTemplate \u0026 GeneralUtilities \u0026 UrlScraper \u0026 UrlScraper.sh"
    },
    {
      "line": 26,
      "value": "# NOTE - There is no facility to automatically add additional tests to existing features. These would have to be recorded separately then pasted into the existing Features manually."
    },
    {
      "line": 28,
      "value": "#####################################################################################################################################################################################"
    },
    {
      "line": 30,
      "value": "#  Scenario: Create Acceptance Tests"
    },
    {
      "line": 31,
      "value": "#"
    },
    {
      "line": 32,
      "value": "#    When Scala files are read and performance test URLs are false ACCEPTANCE TESTS ARE CREATED"
    }
  ],
  "line": 34,
  "name": "Create Performance Test Without URLs",
  "description": "",
  "id": "create-test-scripts;create-performance-test-without-urls",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 36,
  "name": "Scala files are read and a performance test is created",
  "keyword": "When "
});
formatter.match({
  "location": "CreatePerformanceTestStepDefTemplate.scala:56"
});
formatter.result({
  "duration": 197460636,
  "status": "passed"
});
formatter.after({
  "duration": 286800548,
  "status": "passed"
});
formatter.after({
  "duration": 22424,
  "status": "passed"
});
formatter.after({
  "duration": 17444,
  "status": "passed"
});
formatter.after({
  "duration": 17312,
  "status": "passed"
});
formatter.after({
  "duration": 16898,
  "status": "passed"
});
formatter.after({
  "duration": 24356,
  "status": "passed"
});
formatter.after({
  "duration": 28147,
  "status": "passed"
});
formatter.after({
  "duration": 59667,
  "status": "passed"
});
});