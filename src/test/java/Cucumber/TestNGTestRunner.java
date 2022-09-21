package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="StepDefinition", monochrome=true, tags= "@ErrorValidation", plugin={"html:target/cucumber.html"})

/*@CucumberOptions(features="feature files path", glue="stepDefinition package name or path", monochrome=true(it will make output readable),
tags = "profiles you want to run just like groups in testNG", plugin={"what type of output it will create as result: path where it will be saved"})*/

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
