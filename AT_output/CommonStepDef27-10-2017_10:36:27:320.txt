package uk.gov.hmrc.integration.cucumber.stepdefs

import cucumber.api.Scenario
import cucumber.api.scala.{EN, ScalaDsl}
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._
import org.openqa.selenium.By

class CommonStepDef extends ScalaDsl with EN {

  Before() { scenario: Scenario =>
    scenarioName     = scenario.getName
    SCENARIO_PRINTED = false
  }

  Given("""^we start on URL '(.*)'$""") { (urlStubClient: String) =>
    navigate(urlStubClient)
  }

  And("""^User stubbing service where select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Select your client's income type where select '(.*)' and select '(.*)'$""") { (clickIncomeSourceProperty: String, clickContinueButton: String) =>
    clickRadio(clickIncomeSourceProperty)
    click(clickContinueButton)
  }

  And("""^Does your client have any other sources of income where select '(.*)' and select '(.*)'$""") { (clickChoiceYes: String, clickContinueButton: String) =>
    clickRadio(clickChoiceYes)
    click(clickContinueButton)
  }

  And("""^Terms of participation where select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Check your answers where select '(.*)'$""") { (clickIncomeSourceEdit: String) =>
    click(clickIncomeSourceEdit)
  }

  And("""^Business accounting period where select '(.*)' and select '(.*)'$""") { (clickAccountingPeriodPriorYes: String, clickContinueButton: String) =>
    clickRadio(clickAccountingPeriodPriorYes)
    click(clickContinueButton)
  }

  And("""^Accounting method where select '(.*)' and select '(.*)'$""") { (clickAccountingMethodAccruals: String, clickContinueButton: String) =>
    clickRadio(clickAccountingMethodAccruals)
    click(clickContinueButton)
  }

  And("""^User stubbing service where then navigate to '(.*)'$""") { (urlGgSignIn: String) =>
    navigate(urlGgSignIn)
  }

  And("""^Authority Wizard where 'fieldRedirectionUrl' is '(.*)' and 'fieldEnrolment0Name' is '(.*)' and 'fieldInput00Name' is '(.*)' and 'fieldInput00Value' is '(.*)' and select '(.*)'$""") { (redirectionUrl: String, enrolment0name: String, input00name: String, input00value: String, clickInputButton: String) =>
    write(fieldRedirectionUrl, redirectionUrl, idOverride = "name")
    write(fieldEnrolment0Name, enrolment0name, idOverride = "name")
    write(fieldInput00Name, input00name)
    write(fieldInput00Value, input00value)
    click(clickInputButton, idOverride = "cssSelector")
  }

  And("""^Sign up to report your client's income and expenses quarterly where select '(.*)'$""") { (clickStartButton: String) =>
    click(clickStartButton)
  }

  And("""^Enter your client's details where 'fieldClientFirstName' is '(.*)' and 'fieldClientLastName' is '(.*)' and 'fieldClientNino' is '(.*)' and 'fieldClientDateOfBirthDateDay' is '(.*)' and 'fieldClientDateOfBirthDateMonth' is '(.*)' and 'fieldClientDateOfBirthDateYear' is '(.*)' and select '(.*)'$""") { (clientFirstName: String, clientLastName: String, clientNino: String, clientDateOfBirthdateDay: String, clientDateOfBirthdateMonth: String, clientDateOfBirthdateYear: String, clickContinueButton: String) =>
    write(fieldClientFirstName, clientFirstName)
    write(fieldClientLastName, clientLastName)
    write(fieldClientNino, clientNino)
    write(fieldClientDateOfBirthDateDay, clientDateOfBirthdateDay)
    write(fieldClientDateOfBirthDateMonth, clientDateOfBirthdateMonth)
    write(fieldClientDateOfBirthDateYear, clientDateOfBirthdateYear)
    click(clickContinueButton)
  }

  And("""^Confirm your client where select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Business accounting period where 'fieldStartDateDateDay' is '(.*)' and 'fieldStartDateDateMonth' is '(.*)' and 'fieldStartDateDateYear' is '(.*)' and 'fieldEndDateDateDay' is '(.*)' and 'fieldEndDateDateMonth' is '(.*)' and 'fieldEndDateDateYear' is '(.*)' and select '(.*)'$""") { (startDatedateDay: String, startDatedateMonth: String, startDatedateYear: String, endDatedateDay: String, endDatedateMonth: String, endDatedateYear: String, clickContinueButton: String) =>
    write(fieldStartDateDateDay, startDatedateDay)
    write(fieldStartDateDateMonth, startDatedateMonth)
    write(fieldStartDateDateYear, startDatedateYear)
    write(fieldEndDateDateDay, endDatedateDay)
    write(fieldEndDateDateMonth, endDatedateMonth)
    write(fieldEndDateDateYear, endDatedateYear)
    click(clickContinueButton)
  }

  And("""^Business name where 'fieldBusinessName' is '(.*)' and select '(.*)'$""") { (businessName: String, clickContinueButton: String) =>
    write(fieldBusinessName, businessName)
    click(clickContinueButton)
  }

  And("""^Business name where select '(.*)'$""") { (clickBack: String) =>
    click(clickBack)
  }

  And("""^You can only send quarterly reports on part of your client's income where select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Accounting method choose select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Business accounting period with 'fieldStartDateDateDay' is '(.*)' and 'fieldStartDateDateMonth' is '(.*)' and 'fieldEndDateDateDay' is '(.*)' and 'fieldEndDateDateMonth' is '(.*)' and select '(.*)'$""") { (startDatedateDay: String, startDatedateMonth: String, endDatedateDay: String, endDatedateMonth: String, clickContinueButton: String) =>
    write(fieldStartDateDateDay, startDatedateDay)
    write(fieldStartDateDateMonth, startDatedateMonth)
    write(fieldEndDateDateDay, endDatedateDay)
    write(fieldEndDateDateMonth, endDatedateMonth)
    click(clickContinueButton)
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
