package uk.gov.hmrc.integration.cucumber.stepdefs

import cucumber.api.Scenario
import cucumber.api.scala.{EN, ScalaDsl}
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._

class BaseStepDefTemplate extends ScalaDsl with EN {

  Before() { scenario: Scenario =>
    SCENARIO_PRINTED    = false
    val scenarioName = scenario.getName
  }

  And("""^PAUSE$""") { () =>
    TEST_PROMPT_CONTINUE = true
  }

  And("""^TEST_PAUSE = (.*)$""") { (time: Int) =>
    TEST_PAUSE = Thread.sleep(time)
  }

  After() {
    scenario: Scenario =>
    val scenarioStatus  = scenario.getStatus
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