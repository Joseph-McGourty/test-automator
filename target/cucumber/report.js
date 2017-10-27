$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("ExampleScenarioOutlineInputFile_ForAT.feature");
formatter.feature({
  "line": 3,
  "name": "Feature Number 3 ExampleScenarioOutlineInputFile_ForAT",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@FeatureNameExampleScenarioOutlineInputFile_ForAT"
    }
  ]
});
formatter.before({
  "duration": 52740465,
  "status": "passed"
});
formatter.before({
  "duration": 17409,
  "status": "passed"
});
formatter.before({
  "duration": 13768,
  "status": "passed"
});
formatter.before({
  "duration": 13298,
  "status": "passed"
});
formatter.before({
  "duration": 13290,
  "status": "passed"
});
formatter.before({
  "duration": 12769,
  "status": "passed"
});
formatter.before({
  "duration": 15318,
  "status": "passed"
});
formatter.before({
  "duration": 13715,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "Scenario Number 1",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat;scenario-number-1",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "we start on URL \u0027http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client/test-only/stub-client\u0027",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "User stubbing service where select \u0027clickContinueButton\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "TEST_DELAY \u003d 100",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "User stubbing service where then navigate to \u0027http://localhost:9949/auth-login-stub/gg-sign-in\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Authority Wizard where \u0027fieldRedirectionUrl\u0027 is \u0027http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client\u0027 and \u0027fieldEnrolment0Name\u0027 is \u0027HMRC-AS-AGENT\u0027 and \u0027fieldInput00Name\u0027 is \u0027AgentReferenceNumber\u0027 and \u0027fieldInput00Value\u0027 is \u0027a\u0027 and select \u0027clickInputButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "TEST_DELAY \u003d 0",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "Sign up to report your client\u0027s income and expenses quarterly where select \u0027clickStartButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "PAUSE",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "Enter your client\u0027s details where \u0027fieldClientFirstName\u0027 is \u0027Test\u0027 and \u0027fieldClientLastName\u0027 is \u0027User\u0027 and \u0027fieldClientNino\u0027 is \u0027AA111111A\u0027 and \u0027fieldClientDateOfBirthDateDay\u0027 is \u002701\u0027 and \u0027fieldClientDateOfBirthDateMonth\u0027 is \u002701\u0027 and \u0027fieldClientDateOfBirthDateYear\u0027 is \u00271980\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Confirm your client where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "Select your client\u0027s income type where select \u0027clickIncomeSourceBoth\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Does your client have any other sources of income where select \u0027clickChoiceNo\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Business accounting period where select \u0027clickAccountingPeriodPriorNo\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client/test-only/stub-client",
      "offset": 17
    }
  ],
  "location": "CommonStepDef.scala:18"
});
formatter.result({
  "duration": 609174215,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 36
    }
  ],
  "location": "CommonStepDef.scala:22"
});
formatter.result({
  "duration": 313050671,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "100",
      "offset": 13
    }
  ],
  "location": "BaseStepDefTemplate.scala:20"
});
formatter.result({
  "duration": 100813442,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "http://localhost:9949/auth-login-stub/gg-sign-in",
      "offset": 46
    }
  ],
  "location": "CommonStepDef.scala:54"
});
formatter.result({
  "duration": 269476783,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "http://localhost:9563/report-quarterly/income-and-expenses/sign-up/client",
      "offset": 49
    },
    {
      "val": "HMRC-AS-AGENT",
      "offset": 154
    },
    {
      "val": "AgentReferenceNumber",
      "offset": 196
    },
    {
      "val": "a",
      "offset": 246
    },
    {
      "val": "clickInputButton",
      "offset": 261
    }
  ],
  "location": "CommonStepDef.scala:58"
});
formatter.result({
  "duration": 803130646,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 13
    }
  ],
  "location": "BaseStepDefTemplate.scala:20"
});
formatter.result({
  "duration": 62860,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickStartButton",
      "offset": 76
    }
  ],
  "location": "CommonStepDef.scala:66"
});
formatter.result({
  "duration": 221791032,
  "status": "passed"
});
formatter.match({
  "location": "BaseStepDefTemplate.scala:16"
});
formatter.result({
  "duration": 76992,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test",
      "offset": 61
    },
    {
      "val": "User",
      "offset": 97
    },
    {
      "val": "AA111111A",
      "offset": 129
    },
    {
      "val": "01",
      "offset": 180
    },
    {
      "val": "01",
      "offset": 226
    },
    {
      "val": "1980",
      "offset": 271
    },
    {
      "val": "clickContinueButton",
      "offset": 289
    }
  ],
  "location": "CommonStepDef.scala:70"
});
formatter.result({
  "duration": 45421515053,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 34
    }
  ],
  "location": "CommonStepDef.scala:80"
});
formatter.result({
  "duration": 270155636,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickIncomeSourceBoth",
      "offset": 47
    },
    {
      "val": "clickContinueButton",
      "offset": 82
    }
  ],
  "location": "CommonStepDef.scala:26"
});
formatter.result({
  "duration": 93749789,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickChoiceNo",
      "offset": 64
    },
    {
      "val": "clickContinueButton",
      "offset": 91
    }
  ],
  "location": "CommonStepDef.scala:31"
});
formatter.result({
  "duration": 259748368,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingPeriodPriorNo",
      "offset": 41
    },
    {
      "val": "clickContinueButton",
      "offset": 83
    }
  ],
  "location": "CommonStepDef.scala:44"
});
formatter.result({
  "duration": 250782325,
  "status": "passed"
});
formatter.after({
  "duration": 128693118,
  "status": "passed"
});
formatter.after({
  "duration": 33358,
  "status": "passed"
});
formatter.after({
  "duration": 29106,
  "status": "passed"
});
formatter.after({
  "duration": 68423,
  "status": "passed"
});
formatter.after({
  "duration": 29228,
  "status": "passed"
});
formatter.after({
  "duration": 30201,
  "status": "passed"
});
formatter.after({
  "duration": 42137,
  "status": "passed"
});
formatter.after({
  "duration": 33219,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 21,
  "name": "Acc Period Repeat",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 23,
  "name": "Business accounting period where \u0027fieldStartDateDateDay\u0027 is \u0027\u003cfieldStartDateDateDay\u003e\u0027 and \u0027fieldStartDateDateMonth\u0027 is \u0027\u003cfieldStartDateDateMonth\u003e\u0027 and \u0027fieldStartDateDateYear\u0027 is \u0027\u003cfieldStartDateDateYear\u003e\u0027 and \u0027fieldEndDateDateDay\u0027 is \u0027\u003cfieldEndDateDateDay\u003e\u0027 and \u0027fieldEndDateDateMonth\u0027 is \u0027\u003cfieldEndDateDateMonth\u003e\u0027 and \u0027fieldEndDateDateYear\u0027 is \u0027\u003cfieldEndDateDateYear\u003e\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "Business name where \u0027fieldBusinessName\u0027 is \u0027\u003cfieldBusinessName\u003e\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Accounting method where select \u0027clickAccountingMethodAccruals\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "Terms of participation where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "Check your answers where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "Terms of participation where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "Accounting method choose select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "Business name where select \u0027clickBack\u0027",
  "keyword": "Then "
});
formatter.examples({
  "line": 32,
  "name": "",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;",
  "rows": [
    {
      "cells": [
        "fieldStartDateDateDay",
        "fieldStartDateDateMonth",
        "fieldStartDateDateYear",
        "fieldEndDateDateDay",
        "fieldEndDateDateMonth",
        "fieldEndDateDateYear",
        "fieldBusinessName"
      ],
      "line": 33,
      "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;;1"
    },
    {
      "cells": [
        "01",
        "01",
        "2018",
        "01",
        "01",
        "2019",
        "Test Business"
      ],
      "line": 34,
      "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;;2"
    },
    {
      "cells": [
        "01",
        "01",
        "2018",
        "01",
        "01",
        "2019",
        "Test Business"
      ],
      "line": 35,
      "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;;3"
    },
    {
      "cells": [
        "01",
        "01",
        "2018",
        "01",
        "01",
        "2019",
        "Test Business"
      ],
      "line": 36,
      "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 2510,
  "status": "passed"
});
formatter.before({
  "duration": 2034,
  "status": "passed"
});
formatter.before({
  "duration": 1774,
  "status": "passed"
});
formatter.before({
  "duration": 1814,
  "status": "passed"
});
formatter.before({
  "duration": 1755,
  "status": "passed"
});
formatter.before({
  "duration": 1628,
  "status": "passed"
});
formatter.before({
  "duration": 2105,
  "status": "passed"
});
formatter.before({
  "duration": 1883,
  "status": "passed"
});
formatter.scenario({
  "line": 34,
  "name": "Acc Period Repeat",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@FeatureNameExampleScenarioOutlineInputFile_ForAT"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "Business accounting period where \u0027fieldStartDateDateDay\u0027 is \u002701\u0027 and \u0027fieldStartDateDateMonth\u0027 is \u002701\u0027 and \u0027fieldStartDateDateYear\u0027 is \u00272018\u0027 and \u0027fieldEndDateDateDay\u0027 is \u002701\u0027 and \u0027fieldEndDateDateMonth\u0027 is \u002701\u0027 and \u0027fieldEndDateDateYear\u0027 is \u00272019\u0027 and select \u0027clickContinueButton\u0027",
  "matchedColumns": [
    0,
    1,
    2,
    3,
    4,
    5
  ],
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "Business name where \u0027fieldBusinessName\u0027 is \u0027Test Business\u0027 and select \u0027clickContinueButton\u0027",
  "matchedColumns": [
    6
  ],
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Accounting method where select \u0027clickAccountingMethodAccruals\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "Terms of participation where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "Check your answers where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "Terms of participation where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "Accounting method choose select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "Business name where select \u0027clickBack\u0027",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "01",
      "offset": 61
    },
    {
      "val": "01",
      "offset": 99
    },
    {
      "val": "2018",
      "offset": 136
    },
    {
      "val": "01",
      "offset": 172
    },
    {
      "val": "01",
      "offset": 208
    },
    {
      "val": "2019",
      "offset": 243
    },
    {
      "val": "clickContinueButton",
      "offset": 261
    }
  ],
  "location": "CommonStepDef.scala:84"
});
formatter.result({
  "duration": 659290106,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test Business",
      "offset": 44
    },
    {
      "val": "clickContinueButton",
      "offset": 71
    }
  ],
  "location": "CommonStepDef.scala:94"
});
formatter.result({
  "duration": 266384758,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingMethodAccruals",
      "offset": 32
    },
    {
      "val": "clickContinueButton",
      "offset": 75
    }
  ],
  "location": "CommonStepDef.scala:49"
});
formatter.result({
  "duration": 97958457,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 201822352,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 317670533,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 148961346,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:107"
});
formatter.result({
  "duration": 146042038,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 28
    }
  ],
  "location": "CommonStepDef.scala:99"
});
formatter.result({
  "duration": 138556485,
  "status": "passed"
});
formatter.after({
  "duration": 27926090,
  "status": "passed"
});
formatter.after({
  "duration": 9137,
  "status": "passed"
});
formatter.after({
  "duration": 17477,
  "status": "passed"
});
formatter.after({
  "duration": 6326,
  "status": "passed"
});
formatter.after({
  "duration": 6830,
  "status": "passed"
});
formatter.after({
  "duration": 7042,
  "status": "passed"
});
formatter.after({
  "duration": 7013,
  "status": "passed"
});
formatter.after({
  "duration": 7067,
  "status": "passed"
});
formatter.before({
  "duration": 2609,
  "status": "passed"
});
formatter.before({
  "duration": 5901,
  "status": "passed"
});
formatter.before({
  "duration": 2100,
  "status": "passed"
});
formatter.before({
  "duration": 2112,
  "status": "passed"
});
formatter.before({
  "duration": 1905,
  "status": "passed"
});
formatter.before({
  "duration": 2066,
  "status": "passed"
});
formatter.before({
  "duration": 2274,
  "status": "passed"
});
formatter.before({
  "duration": 2077,
  "status": "passed"
});
formatter.scenario({
  "line": 35,
  "name": "Acc Period Repeat",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@FeatureNameExampleScenarioOutlineInputFile_ForAT"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "Business accounting period where \u0027fieldStartDateDateDay\u0027 is \u002701\u0027 and \u0027fieldStartDateDateMonth\u0027 is \u002701\u0027 and \u0027fieldStartDateDateYear\u0027 is \u00272018\u0027 and \u0027fieldEndDateDateDay\u0027 is \u002701\u0027 and \u0027fieldEndDateDateMonth\u0027 is \u002701\u0027 and \u0027fieldEndDateDateYear\u0027 is \u00272019\u0027 and select \u0027clickContinueButton\u0027",
  "matchedColumns": [
    0,
    1,
    2,
    3,
    4,
    5
  ],
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "Business name where \u0027fieldBusinessName\u0027 is \u0027Test Business\u0027 and select \u0027clickContinueButton\u0027",
  "matchedColumns": [
    6
  ],
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Accounting method where select \u0027clickAccountingMethodAccruals\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "Terms of participation where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "Check your answers where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "Terms of participation where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "Accounting method choose select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "Business name where select \u0027clickBack\u0027",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "01",
      "offset": 61
    },
    {
      "val": "01",
      "offset": 99
    },
    {
      "val": "2018",
      "offset": 136
    },
    {
      "val": "01",
      "offset": 172
    },
    {
      "val": "01",
      "offset": 208
    },
    {
      "val": "2019",
      "offset": 243
    },
    {
      "val": "clickContinueButton",
      "offset": 261
    }
  ],
  "location": "CommonStepDef.scala:84"
});
formatter.result({
  "duration": 638865144,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test Business",
      "offset": 44
    },
    {
      "val": "clickContinueButton",
      "offset": 71
    }
  ],
  "location": "CommonStepDef.scala:94"
});
formatter.result({
  "duration": 251178579,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingMethodAccruals",
      "offset": 32
    },
    {
      "val": "clickContinueButton",
      "offset": 75
    }
  ],
  "location": "CommonStepDef.scala:49"
});
formatter.result({
  "duration": 85332783,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 294708526,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 51093724,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 223795673,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:107"
});
formatter.result({
  "duration": 139111108,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 28
    }
  ],
  "location": "CommonStepDef.scala:99"
});
formatter.result({
  "duration": 159680385,
  "status": "passed"
});
formatter.after({
  "duration": 26392484,
  "status": "passed"
});
formatter.after({
  "duration": 5465,
  "status": "passed"
});
formatter.after({
  "duration": 3882,
  "status": "passed"
});
formatter.after({
  "duration": 3808,
  "status": "passed"
});
formatter.after({
  "duration": 3842,
  "status": "passed"
});
formatter.after({
  "duration": 4932,
  "status": "passed"
});
formatter.after({
  "duration": 3955,
  "status": "passed"
});
formatter.after({
  "duration": 3921,
  "status": "passed"
});
formatter.before({
  "duration": 2174,
  "status": "passed"
});
formatter.before({
  "duration": 1585,
  "status": "passed"
});
formatter.before({
  "duration": 1464,
  "status": "passed"
});
formatter.before({
  "duration": 1461,
  "status": "passed"
});
formatter.before({
  "duration": 1585,
  "status": "passed"
});
formatter.before({
  "duration": 1415,
  "status": "passed"
});
formatter.before({
  "duration": 2094,
  "status": "passed"
});
formatter.before({
  "duration": 1966,
  "status": "passed"
});
formatter.scenario({
  "line": 36,
  "name": "Acc Period Repeat",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat;acc-period-repeat;;4",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@FeatureNameExampleScenarioOutlineInputFile_ForAT"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "Business accounting period where \u0027fieldStartDateDateDay\u0027 is \u002701\u0027 and \u0027fieldStartDateDateMonth\u0027 is \u002701\u0027 and \u0027fieldStartDateDateYear\u0027 is \u00272018\u0027 and \u0027fieldEndDateDateDay\u0027 is \u002701\u0027 and \u0027fieldEndDateDateMonth\u0027 is \u002701\u0027 and \u0027fieldEndDateDateYear\u0027 is \u00272019\u0027 and select \u0027clickContinueButton\u0027",
  "matchedColumns": [
    0,
    1,
    2,
    3,
    4,
    5
  ],
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "Business name where \u0027fieldBusinessName\u0027 is \u0027Test Business\u0027 and select \u0027clickContinueButton\u0027",
  "matchedColumns": [
    6
  ],
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Accounting method where select \u0027clickAccountingMethodAccruals\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "Terms of participation where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "Check your answers where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "Terms of participation where select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "Accounting method choose select \u0027clickBack\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "Business name where select \u0027clickBack\u0027",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "01",
      "offset": 61
    },
    {
      "val": "01",
      "offset": 99
    },
    {
      "val": "2018",
      "offset": 136
    },
    {
      "val": "01",
      "offset": 172
    },
    {
      "val": "01",
      "offset": 208
    },
    {
      "val": "2019",
      "offset": 243
    },
    {
      "val": "clickContinueButton",
      "offset": 261
    }
  ],
  "location": "CommonStepDef.scala:84"
});
formatter.result({
  "duration": 616949495,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test Business",
      "offset": 44
    },
    {
      "val": "clickContinueButton",
      "offset": 71
    }
  ],
  "location": "CommonStepDef.scala:94"
});
formatter.result({
  "duration": 228098892,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingMethodAccruals",
      "offset": 32
    },
    {
      "val": "clickContinueButton",
      "offset": 75
    }
  ],
  "location": "CommonStepDef.scala:49"
});
formatter.result({
  "duration": 81451145,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 184273743,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 284373383,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 145862534,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:107"
});
formatter.result({
  "duration": 118797258,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBack",
      "offset": 28
    }
  ],
  "location": "CommonStepDef.scala:99"
});
formatter.result({
  "duration": 151973185,
  "status": "passed"
});
formatter.after({
  "duration": 19501048,
  "status": "passed"
});
formatter.after({
  "duration": 7446,
  "status": "passed"
});
formatter.after({
  "duration": 4751,
  "status": "passed"
});
formatter.after({
  "duration": 7509,
  "status": "passed"
});
formatter.after({
  "duration": 4986,
  "status": "passed"
});
formatter.after({
  "duration": 6313,
  "status": "passed"
});
formatter.after({
  "duration": 5256,
  "status": "passed"
});
formatter.after({
  "duration": 5070,
  "status": "passed"
});
formatter.before({
  "duration": 2133,
  "status": "passed"
});
formatter.before({
  "duration": 2095,
  "status": "passed"
});
formatter.before({
  "duration": 2398,
  "status": "passed"
});
formatter.before({
  "duration": 1713,
  "status": "passed"
});
formatter.before({
  "duration": 1817,
  "status": "passed"
});
formatter.before({
  "duration": 1578,
  "status": "passed"
});
formatter.before({
  "duration": 1762,
  "status": "passed"
});
formatter.before({
  "duration": 1709,
  "status": "passed"
});
formatter.scenario({
  "line": 38,
  "name": "Change User Inputs",
  "description": "",
  "id": "feature-number-3-examplescenariooutlineinputfile-forat;change-user-inputs",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 40,
  "name": "Business accounting period with \u0027fieldStartDateDateDay\u0027 is \u002712\u0027 and \u0027fieldStartDateDateMonth\u0027 is \u002712\u0027 and \u0027fieldEndDateDateDay\u0027 is \u002712\u0027 and \u0027fieldEndDateDateMonth\u0027 is \u002712\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 41,
  "name": "Business name where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 42,
  "name": "Accounting method where select \u0027clickAccountingMethodCash\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 43,
  "name": "Terms of participation where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 44,
  "name": "Check your answers where select \u0027clickIncomeSourceEdit\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 45,
  "name": "Select your client\u0027s income type press select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 46,
  "name": "Check your answers where select \u0027clickOtherIncomeEdit\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 47,
  "name": "Does your client have any other sources of income where select \u0027clickChoiceYes\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 48,
  "name": "You can only send quarterly reports on part of your client\u0027s income where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 49,
  "name": "Business accounting period press select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 50,
  "name": "Business accounting period push select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 51,
  "name": "Business name where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 52,
  "name": "Accounting method choose select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 53,
  "name": "Terms of participation where select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 54,
  "name": "Check your answers where select \u0027clickAccountingPeriodDateEdit\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 55,
  "name": "Business accounting period with \u0027fieldStartDateDateDay\u0027 is \u002711\u0027 and \u0027fieldStartDateDateMonth\u0027 is \u002711\u0027 and \u0027fieldEndDateDateDay\u0027 is \u002711\u0027 and \u0027fieldEndDateDateMonth\u0027 is \u002711\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 56,
  "name": "Check your answers where select \u0027clickBusinessNameEdit\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 57,
  "name": "Business name where \u0027fieldBusinessName\u0027 is \u0027Test Businesses\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 58,
  "name": "Check your answers where select \u0027clickAccountingMethodEdit\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 59,
  "name": "Accounting method where select \u0027clickAccountingMethodAccruals\u0027 and select \u0027clickContinueButton\u0027",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "12",
      "offset": 60
    },
    {
      "val": "12",
      "offset": 98
    },
    {
      "val": "12",
      "offset": 132
    },
    {
      "val": "12",
      "offset": 168
    },
    {
      "val": "clickContinueButton",
      "offset": 184
    }
  ],
  "location": "CommonStepDef.scala:111"
});
formatter.result({
  "duration": 485020995,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 28
    }
  ],
  "location": "CommonStepDef.scala:99"
});
formatter.result({
  "duration": 157250986,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingMethodCash",
      "offset": 32
    },
    {
      "val": "clickContinueButton",
      "offset": 71
    }
  ],
  "location": "CommonStepDef.scala:49"
});
formatter.result({
  "duration": 89596562,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 291132660,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickIncomeSourceEdit",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 62601081,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 47
    }
  ],
  "location": "ExampleScenarioOutlineInputFile_ForATStepDef.scala:18"
});
formatter.result({
  "duration": 279363794,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickOtherIncomeEdit",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 149230520,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickChoiceYes",
      "offset": 64
    },
    {
      "val": "clickContinueButton",
      "offset": 92
    }
  ],
  "location": "CommonStepDef.scala:31"
});
formatter.result({
  "duration": 103653865,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 82
    }
  ],
  "location": "CommonStepDef.scala:103"
});
formatter.result({
  "duration": 259113017,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 41
    }
  ],
  "location": "ExampleScenarioOutlineInputFile_ForATStepDef.scala:22"
});
formatter.result({
  "duration": 183953150,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 40
    }
  ],
  "location": "ExampleScenarioOutlineInputFile_ForATStepDef.scala:26"
});
formatter.result({
  "duration": 176279157,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 28
    }
  ],
  "location": "CommonStepDef.scala:99"
});
formatter.result({
  "duration": 166324599,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:107"
});
formatter.result({
  "duration": 162272073,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickContinueButton",
      "offset": 37
    }
  ],
  "location": "CommonStepDef.scala:36"
});
formatter.result({
  "duration": 172346482,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingPeriodDateEdit",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 220800705,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "11",
      "offset": 60
    },
    {
      "val": "11",
      "offset": 98
    },
    {
      "val": "11",
      "offset": 132
    },
    {
      "val": "11",
      "offset": 168
    },
    {
      "val": "clickContinueButton",
      "offset": 184
    }
  ],
  "location": "CommonStepDef.scala:111"
});
formatter.result({
  "duration": 495754808,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickBusinessNameEdit",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 157428480,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test Businesses",
      "offset": 44
    },
    {
      "val": "clickContinueButton",
      "offset": 73
    }
  ],
  "location": "CommonStepDef.scala:94"
});
formatter.result({
  "duration": 252176602,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingMethodEdit",
      "offset": 33
    }
  ],
  "location": "CommonStepDef.scala:40"
});
formatter.result({
  "duration": 168786460,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "clickAccountingMethodAccruals",
      "offset": 32
    },
    {
      "val": "clickContinueButton",
      "offset": 75
    }
  ],
  "location": "CommonStepDef.scala:49"
});
formatter.result({
  "duration": 92953861,
  "status": "passed"
});
formatter.after({
  "duration": 135260667,
  "status": "passed"
});
formatter.after({
  "duration": 8208,
  "status": "passed"
});
formatter.after({
  "duration": 4658,
  "status": "passed"
});
formatter.after({
  "duration": 4124,
  "status": "passed"
});
formatter.after({
  "duration": 4079,
  "status": "passed"
});
formatter.after({
  "duration": 4327,
  "status": "passed"
});
formatter.after({
  "duration": 4399,
  "status": "passed"
});
formatter.after({
  "duration": 4243,
  "status": "passed"
});
});