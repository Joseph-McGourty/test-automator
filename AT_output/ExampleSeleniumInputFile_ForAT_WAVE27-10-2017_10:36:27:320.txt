@FeatureNameExampleSeleniumInputFile_ForAT_WAVE
@WAVE

Feature: Feature Number 2 ExampleSeleniumInputFile_ForAT_WAVE

  Scenario: Scenario Number 1

    Given we start on URL 'http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client/test-only/stub-client'
    When User stubbing service where select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And User stubbing service where then navigate to 'http://localhost:9949/auth-login-stub/gg-sign-in'
    And Authority Wizard where 'fieldRedirectionUrl' is 'http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client' and 'fieldEnrolment0Name' is 'HMRC-AS-AGENT' and 'fieldInput00Name' is 'AgentReferenceNumber' and 'fieldInput00Value' is 'a' and select 'clickInputButton'
    And click the WAVE button for a HTML Report
    And Sign up to report your client's income and expenses quarterly where select 'clickStartButton'
    And click the WAVE button for a HTML Report
    And Enter your client's details where 'fieldClientFirstName' is 'Test' and 'fieldClientLastName' is 'User' and 'fieldClientNino' is 'AA111111A' and 'fieldClientDateOfBirthDateDay' is '01' and 'fieldClientDateOfBirthDateMonth' is '01' and 'fieldClientDateOfBirthDateYear' is '1980' and select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Confirm your client where select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Select your client's income type where select 'clickIncomeSourceBusiness' and select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Does your client have any other sources of income where select 'clickChoiceNo' and select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Business accounting period where select 'clickAccountingPeriodPriorNo' and select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Business accounting period where 'fieldStartDateDateDay' is '01' and 'fieldStartDateDateMonth' is '01' and 'fieldStartDateDateYear' is '2022' and 'fieldEndDateDateDay' is '01' and 'fieldEndDateDateMonth' is '01' and 'fieldEndDateDateYear' is '2023' and select 'clickContinueButton'
    And Business name where 'fieldBusinessName' is 'The Business' and select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Accounting method where select 'clickAccountingMethodCash' and select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Terms of participation where select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Check your answers where select 'clickIncomeSourceEdit'
    And click the WAVE button for a HTML Report
    And Select your client's income type where select 'clickIncomeSourceProperty' and select 'clickContinueButton'
    And Does your client have any other sources of income where select 'clickChoiceYes' and select 'clickContinueButton'
    And You can only send quarterly reports on part of your client's income where select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Terms of participation where select 'clickContinueButton'
    And Check your answers where select 'clickIncomeSourceEdit'
    And Select your client's income type where select 'clickIncomeSourceBoth' and select 'clickContinueButton'
    And Does your client have any other sources of income where select 'clickChoiceNo' and select 'clickContinueButton'
    And Business accounting period where select 'clickAccountingPeriodPriorYes' and select 'clickContinueButton'
    And You can't send quarterly reports yet where select 'clickContinueButton'
    And click the WAVE button for a HTML Report
    And Business accounting period choose select 'clickContinueButton'
    And Business name where select 'clickContinueButton'
    And Accounting method where select 'clickAccountingMethodAccruals' and select 'clickContinueButton'
    And Terms of participation where select 'clickContinueButton'
    And Check your answers where select 'clickIncomeSourceEdit'
    And Select your client's income type where select 'clickIncomeSourceOther' and select 'clickContinueButton'
    And You can't sign up your client yet where select 'clickBack'
    And click the WAVE button for a HTML Report
    And Select your client's income type choose select 'clickIncomeSourceBusiness' and select 'clickIncomeSourceProperty' and select 'clickContinueButton'
    And Does your client have any other sources of income choose select 'clickContinueButton'
    Then Terms of participation where select 'clickContinueButton'

  Scenario: Save Accessibility Report

    And save the WAVE Accessibility Test Report