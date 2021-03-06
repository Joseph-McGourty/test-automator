package uk.gov.hmrc.integration.cucumber.stepdefs

import cucumber.api.Scenario
import cucumber.api.scala.{EN, ScalaDsl}
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate._
import uk.gov.hmrc.integration.cucumber.pages.BasePageTemplate
import uk.gov.hmrc.integration.cucumber.utils.ParametersTemplate._
import uk.gov.hmrc.integration.cucumber.utils.UtilitiesTemplate._
import org.openqa.selenium.By

class StubUserStepDef extends ScalaDsl with EN {

  Before() { scenario: Scenario =>
    scenarioName     = scenario.getName
    SCENARIO_PRINTED = false
  }

  // No Unique Steps In This Step Definition - Common Steps Are Located In the "CommonStepDef" Definition

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
