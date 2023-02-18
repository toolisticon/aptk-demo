package io.toolisticon.myspiservice.processor;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit test for {@link MySpiServiceProcessorMessages}.
 */
public class MySpiServiceProcessorMessagesTest {

    @Test
    public void test_enum() {

        MatcherAssert.assertThat(MySpiServiceProcessorMessages.ERROR_COULD_NOT_CREATE_CONFIG.getCode(), Matchers.startsWith("MySpiService"));
        MatcherAssert.assertThat(MySpiServiceProcessorMessages.ERROR_COULD_NOT_CREATE_CONFIG.getMessage(), Matchers.containsString("create config"));

    }


}
