@FeatureNameExampleFile

Feature: Feature Number 4 ExampleFile

  Scenario: Scenario Number 1

    Given we start on URL 'http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client/test-only/stub-client'
    When User stubbing service where select 'clickContinueButton'
    And User stubbing service where then navigate to 'http://localhost:9949/auth-login-stub/gg-sign-in'
    And Authority Wizard where 'fieldRedirectionUrl' is 'http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client' and 'fieldEnrolment0Name' is 'HMRC-AS-AGENT' and 'fieldInput00Name' is 'AgentReferenceNumber' and 'fieldInput00Value' is 'a' and select 'clickInputButton'
    And Sign up to report your client's income and expenses quarterly where select 'clickStartButton'
    And Enter your client's details where 'fieldClientFirstName' is 'Test' and 'fieldClientLastName' is 'User' and 'fieldClientNino' is 'AA111111A' and 'fieldClientDateOfBirthDateDay' is '01' and 'fieldClientDateOfBirthDateMonth' is '01' and 'fieldClientDateOfBirthDateYear' is '1980' and select 'clickContinueButton'
    And Confirm your client where select 'clickContinueButton'
    And Select your client's income type where select 'clickIncomeSourceBusiness' and select 'clickContinueButton'
    And Does your client have any other sources of income where select 'clickChoiceNo' and select 'clickContinueButton'
    And Business accounting period where select 'clickAccountingPeriodPriorNo' and select 'clickContinueButton'
    And Business accounting period where 'fieldStartDateDateDay' is '01' and 'fieldStartDateDateMonth' is '01' and 'fieldStartDateDateYear' is '2018' and 'fieldEndDateDateDay' is '01' and 'fieldEndDateDateMonth' is '01' and 'fieldEndDateDateYear' is '2019' and select 'clickContinueButton'
    And Business name where 'fieldBusinessName' is 'Test Business' and select 'clickContinueButton'
    And Accounting method where select 'clickAccountingMethodCash' and select 'clickContinueButton'
    Then Terms of participation where select 'clickContinueButton'
