package io.toolisticon.myspiservice.processor.tests;

import io.toolisticon.myspiservice.api.MySpiService;
import io.toolisticon.myspiservice.processor.TestService;

@MySpiService(TestService.class)
public enum TestcaseInvalidUsageOnEnum implements TestService{

    A_VALUE;

    public String doSomething(){
        return "DONE";
    }

}