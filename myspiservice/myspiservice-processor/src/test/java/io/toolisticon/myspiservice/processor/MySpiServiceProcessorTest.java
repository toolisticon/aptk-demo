package io.toolisticon.myspiservice.processor;

import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.corematcher.CoreMatcherValidationMessages;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import io.toolisticon.cute.TestAnnotation;
import io.toolisticon.cute.matchers.CoreGeneratedFileObjectMatchers;
import org.junit.Before;
import org.junit.Test;

import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;


/**
 * Tests of {@link io.toolisticon.myspiservice.api.MySpiService}.
 *
 * TODO: replace the example testcases with your own testcases
 */

public class MySpiServiceProcessorTest {


    CompileTestBuilder.CompilationTestBuilder compileTestBuilder;

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

        compileTestBuilder = CompileTestBuilder
                .compilationTest()
                .addProcessors(MySpiServiceProcessor.class);
    }


    @Test
    public void test_valid_usage() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/TestcaseValidUsage.java"))
                .compilationShouldSucceed()
                .expectThatFileObjectExists(StandardLocation.CLASS_OUTPUT,"", "META-INF/services/"+ TestService.class.getCanonicalName(), CoreGeneratedFileObjectMatchers.createContainsSubstringsMatcher("io.toolisticon.myspiservice.processor.tests.TestcaseValidUsage"))
                .executeTest();
    }


    @Test
    public void test_invalid_usage_on_enum() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/TestcaseInvalidUsageOnEnum.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_invalid_usage_on_interface() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/TestcaseInvalidUsageOnInterface.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_invalid_usage_must_implement_service() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/TestcaseInvalidUsageMustImplementService.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(CoreMatcherValidationMessages.IS_ASSIGNABLE_TO.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_invalid_usage_attribute_must_be_interface() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/TestcaseInvalidUsageAttributeMustBeInterface.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(MySpiServiceProcessorCompilerMessages.ERROR_ATTRIBUTE_VALUE_MUST_BE_INTERFACE.getCode())
                .executeTest();
    }
}