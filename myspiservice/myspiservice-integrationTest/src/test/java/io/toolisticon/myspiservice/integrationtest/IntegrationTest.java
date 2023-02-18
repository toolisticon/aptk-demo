package io.toolisticon.myspiservice.integrationtest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ServiceLoader;

public class IntegrationTest {

    @Test
    public void testValidUsage() {

        MatcherAssert.assertThat(ServiceLoader.load(TestService.class).iterator().next().doSomething(), Matchers.equalTo("DONE"));

    }

}
