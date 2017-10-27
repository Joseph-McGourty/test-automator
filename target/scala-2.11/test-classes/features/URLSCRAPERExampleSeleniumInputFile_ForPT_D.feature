@URLSCRAPER

Feature: Feature Number 3 ExampleSeleniumInputFile_ForPT_D

  Scenario: Scenario Number 1

    Given we start on URL 'http://localhost:9949/auth-login-stub/gg-sign-in'
    When Authority Wizard where 'fieldRedirectionUrl' is 'http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client' and 'fieldEnrolment0Name' is 'HMRC-AS-AGENT' and 'fieldInput00Name' is 'AgentReferenceNumber' and 'fieldInput00Value' is 'a' and select 'clickInputButton'
    And Sign up to report your client's income and expenses quarterly where select 'clickStartButton'
    And Enter your client's details with 'fieldClientFirstName' is 'Test' and 'fieldClientLastName' is 'User' and 'fieldClientNino' is 'AA111111A' and 'fieldClientDateOfBirthDateDay' is '01' and 'fieldClientDateOfBirthDateMonth' is '01' and 'fieldClientDateOfBirthDateYear' is '1980' and select 'clickContinueButton'
    And Confirm your client where select 'clickContinueButton'
    Then Select your client's income type where select 'clickIncomeSourceOther' and select 'clickContinueButton'
