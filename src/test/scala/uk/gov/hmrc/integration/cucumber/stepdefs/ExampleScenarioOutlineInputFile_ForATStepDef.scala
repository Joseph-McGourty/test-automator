package uk.gov.hmrc.integration.cucumber.stepdefs

import cucumber.api.Scenario
import cucumber.api.scala.{EN, ScalaDsl}
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._
import org.openqa.selenium.By

class ExampleScenarioOutlineInputFile_ForATStepDef extends ScalaDsl with EN {

  Before() { scenario: Scenario =>
    scenarioName     = scenario.getName
    SCENARIO_PRINTED = false
  }

  And("""^Select your client's income type press select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Business accounting period press select '(.*)'$""") { (clickContinueButton: String) =>
    click(clickContinueButton)
  }

  And("""^Business accounting period push select '(.*)'$""") { (clickContinueButton: String) =>
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
