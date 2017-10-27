package uk.gov.hmrc.integration.cucumber.stepdefs

import cucumber.api.Scenario
import cucumber.api.scala.{EN, ScalaDsl}
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._
import org.openqa.selenium.By

class ExampleSeleniumInputFile_ForAT_WAVEStepDef extends ScalaDsl with EN {

  Before() { scenario: Scenario =>
    scenarioName     = scenario.getName
    SCENARIO_PRINTED = false
  }

  And("""^You can't send quarterly reports yet where select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Business accounting period choose select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^You can't sign up your client yet where select '(.*)'$""") { (clickBack: String) =>
    click(clickBack)
  }

  And("""^Select your client's income type choose select '(.*)' and select '(.*)' and select '(.*)'$""") { (clickIncomeSourceBusiness: String, clickIncomeSourceProperty: String, clickContinueButton: String) =>
    clickRadio(clickIncomeSourceBusiness)
    clickRadio(clickIncomeSourceProperty)
    click(clickContinueButton)
  }

  And("""^Does your client have any other sources of income choose select '(.*)'$""") { (clickContinueButton: String) =>
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
