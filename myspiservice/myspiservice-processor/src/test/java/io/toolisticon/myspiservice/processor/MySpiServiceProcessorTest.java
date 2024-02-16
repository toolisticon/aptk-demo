package io.toolisticon.myspiservice.processor;

import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.corematcher.CoreMatcherValidationMessages;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import io.toolisticon.cute.matchers.CoreGeneratedFileObjectMatchers;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests of {@link io.toolisticon.myspiservice.api.MySpiService}.
 * <p>
 * TODO: replace the example testcases with your own testcases
 */

public class MySpiServiceProcessorTest {


    CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder;

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

        compileTestBuilder = Cute
                .blackBoxTest()
                .given().processors(MySpiServiceProcessor.class);
    }


    @Test
    public void test_valid_usage() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseValidUsage.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .andThat().generatedResourceFile("", "META-INF/services/"+ TestService.class.getCanonicalName())
                .matches(CoreGeneratedFileObjectMatchers.createContainsSubstringsMatcher("io.toolisticon.myspiservice.processor.tests.TestcaseValidUsage"))
                .executeTest();
    }


    @Test
    public void test_invalid_usage_on_enum() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageOnEnum.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_invalid_usage_on_interface() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageOnInterface.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_invalid_usage_must_implement_service() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageMustImplementService.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(CoreMatcherValidationMessages.IS_ASSIGNABLE_TO.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_invalid_usage_attribute_must_be_interface() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageAttributeMustBeInterface.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(MySpiServiceProcessorCompilerMessages.ERROR_ATTRIBUTE_VALUE_MUST_BE_INTERFACE.getCode())
                .executeTest();
    }
}