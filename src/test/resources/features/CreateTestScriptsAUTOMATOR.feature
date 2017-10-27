@CreateTests

Feature: Create Test Scripts

# INSTRUCTIONS

# 1) Record Acceptance Tests, Performance Tests & Accessibility Tests in FireFox Selenium (add Scenario / Scenario Outline instructions whilst or after recording, or not at all).
#    // SCENARIO START additional text becomes scenario name (blank becomes a scenario number)
#    // SCENARIO END
#    // SCENARIO OUTLINE START
#    // SCENARIO OUTLINE END
#    Test automator currently only recognises sendKeys and click commands (no text or URL validation).

# 2) Export them to WebDriver (Scenario instructions can still be added).
# 3) Load acceptance test and accesibility files into AT_input > load performance test files into PT_input (all extensions must be .txt).
# 4) Configure output directories in AT & PT Step Defs (existing Step Defs in output directories will be read into memory to prevent LEXING repetition).
# 5) Configure general parameters in ParameterTemplate.

# 6) Execute CreateTestScriptsAUTOMATOR (OVERWRITES EXISTING Feature / Step Definition etc FILES in destination repository).
# 7) Manually add Performance Test URLs or run PT Scraper scenario to add them automatically (buy running the input files to scrape them).

# Feature files / step definitions / performance files etc are created in the AT_output / PT_output folders > and ready to run in the parameterised destination folder.
# 8) The "simplified" centralised Acceptance / Accessibility commands require the centralised templates to run (COPY AND PASTE THESE FROM THE test-automator REPOSITORY).
# Centralised Templates:- BasePageTemplate & BaseStepDefTemplate & ParametersTemplate & UtilitiesTemplate (Wave needs standard Wave/TemplateStepDef & WaveStepDef & WaveReport).
# Other templates specific to the test-automator: features/CreateTestScriptsAUTOMATOR & CreateAcceptanceTestStepDefTempate & CreatePerformanceTestStepDefTemplate & GeneralUtilities & UrlScraper & UrlScraper.sh
# NOTE - There is no facility to automatically add additional tests to existing features. These would have to be recorded separately then pasted into the existing Features manually.

#####################################################################################################################################################################################

#  Scenario: Create Acceptance Tests
#
#    When Scala files are read and performance test URLs are false ACCEPTANCE TESTS ARE CREATED

  Scenario: Create Performance Test Without URLs

    When Scala files are read and a performance test is created

#  Scenario: Create Performance Tests With URLs
#
#    When Scala files are read and performance test URLs are true ACCEPTANCE TESTS ARE CREATED
#    And a URL_SCRAPE_LIST is created by running the performance test input files
#    Then clear unnecessary files generated when URL_SCRAPE_LIST was created
#    And Scala files are read and a performance test is created
#    Then the URL_SCRAPE_LIST is read and the performance test Url variables are updated
