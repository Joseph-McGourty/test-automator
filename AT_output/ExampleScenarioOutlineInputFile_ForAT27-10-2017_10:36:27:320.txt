@FeatureNameExampleScenarioOutlineInputFile_ForAT

Feature: Feature Number 3 ExampleScenarioOutlineInputFile_ForAT

  Scenario: Scenario Number 1

    Given we start on URL 'http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client/test-only/stub-client'
    When User stubbing service where select 'clickContinueButton'
    And User stubbing service where then navigate to 'http://localhost:9949/auth-login-stub/gg-sign-in'
    And Authority Wizard where 'fieldRedirectionUrl' is 'http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client' and 'fieldEnrolment0Name' is 'HMRC-AS-AGENT' and 'fieldInput00Name' is 'AgentReferenceNumber' and 'fieldInput00Value' is 'a' and select 'clickInputButton'
    And Sign up to report your client's income and expenses quarterly where select 'clickStartButton'
    And Enter your client's details where 'fieldClientFirstName' is 'Test' and 'fieldClientLastName' is 'User' and 'fieldClientNino' is 'AA111111A' and 'fieldClientDateOfBirthDateDay' is '01' and 'fieldClientDateOfBirthDateMonth' is '01' and 'fieldClientDateOfBirthDateYear' is '1980' and select 'clickContinueButton'
    And Confirm your client where select 'clickContinueButton'
    And Select your client's income type where select 'clickIncomeSourceBoth' and select 'clickContinueButton'
    And Does your client have any other sources of income where select 'clickChoiceNo' and select 'clickContinueButton'
    Then Business accounting period where select 'clickAccountingPeriodPriorNo' and select 'clickContinueButton'

  Scenario Outline: Acc Period Repeat 

    When Business accounting period where 'fieldStartDateDateDay' is '<fieldStartDateDateDay>' and 'fieldStartDateDateMonth' is '<fieldStartDateDateMonth>' and 'fieldStartDateDateYear' is '<fieldStartDateDateYear>' and 'fieldEndDateDateDay' is '<fieldEndDateDateDay>' and 'fieldEndDateDateMonth' is '<fieldEndDateDateMonth>' and 'fieldEndDateDateYear' is '<fieldEndDateDateYear>' and select 'clickContinueButton'
    And Business name where 'fieldBusinessName' is '<fieldBusinessName>' and select 'clickContinueButton'
    And Accounting method where select 'clickAccountingMethodAccruals' and select 'clickContinueButton'
    And Terms of participation where select 'clickContinueButton'
    And Check your answers where select 'clickBack'
    And Terms of participation where select 'clickBack'
    And Accounting method choose select 'clickBack'
    Then Business name where select 'clickBack'

    Examples:
      | fieldStartDateDateDay | fieldStartDateDateMonth | fieldStartDateDateYear | fieldEndDateDateDay | fieldEndDateDateMonth | fieldEndDateDateYear | fieldBusinessName |
      | 01                    | 01                      | 2018                   | 01                  | 01                    | 2019                 | Test Business     |
      | 01                    | 01                      | 2018                   | 01                  | 01                    | 2019                 | Test Business     |
      | 01                    | 01                      | 2018                   | 01                  | 01                    | 2019                 | Test Business     |

  Scenario: Change User Inputs

    When Business accounting period with 'fieldStartDateDateDay' is '12' and 'fieldStartDateDateMonth' is '12' and 'fieldEndDateDateDay' is '12' and 'fieldEndDateDateMonth' is '12' and select 'clickContinueButton'
    And Business name where select 'clickContinueButton'
    And Accounting method where select 'clickAccountingMethodCash' and select 'clickContinueButton'
    And Terms of participation where select 'clickContinueButton'
    And Check your answers where select 'clickIncomeSourceEdit'
    And Select your client's income type press select 'clickContinueButton'
    And Check your answers where select 'clickOtherIncomeEdit'
    And Does your client have any other sources of income where select 'clickChoiceYes' and select 'clickContinueButton'
    And You can only send quarterly reports on part of your client's income where select 'clickContinueButton'
    And Business accounting period press select 'clickContinueButton'
    And Business accounting period push select 'clickContinueButton'
    And Business name where select 'clickContinueButton'
    And Accounting method choose select 'clickContinueButton'
    And Terms of participation where select 'clickContinueButton'
    And Check your answers where select 'clickAccountingPeriodDateEdit'
    And Business accounting period with 'fieldStartDateDateDay' is '11' and 'fieldStartDateDateMonth' is '11' and 'fieldEndDateDateDay' is '11' and 'fieldEndDateDateMonth' is '11' and select 'clickContinueButton'
    And Check your answers where select 'clickBusinessNameEdit'
    And Business name where 'fieldBusinessName' is 'Test Businesses' and select 'clickContinueButton'
    And Check your answers where select 'clickAccountingMethodEdit'
    Then Accounting method where select 'clickAccountingMethodAccruals' and select 'clickContinueButton'
