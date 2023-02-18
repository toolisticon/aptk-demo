package io.toolisticon.myspiservice.processor.tests;

import io.toolisticon.myspiservice.api.MySpiService;
import io.toolisticon.myspiservice.processor.TestService;
import io.toolisticon.myspiservice.processor.MySpiServiceProcessorTest;

@MySpiService(MySpiServiceProcessorTest.class)
public class TestcaseInvalidUsageAttributeMustBeInterface{

    public String doSomething(){
        return "Done!!!";
    }
}