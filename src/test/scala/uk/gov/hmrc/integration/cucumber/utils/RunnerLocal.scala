package uk.gov.hmrc.integration.cucumber.utils

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("src/test/resources/features"),
  glue = Array("uk.gov.hmrc.integration.cucumber.stepdefs"),
  format = Array ("pretty", "html:target/cucumber", "json:target/cucumber.json"),
  tags = Array("@Auth,@Back&ForwardNav,@Business&Property,@Business,@ClientMatch,@ClientSummary,@DESFailures,@OtherIncome,@Property,@Relationships,@SignOut,@Summary,@Shutdown")

)
class RunnerLocal {
}