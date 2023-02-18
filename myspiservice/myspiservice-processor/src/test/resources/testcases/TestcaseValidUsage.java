package io.toolisticon.myspiservice.processor.tests;

import io.toolisticon.myspiservice.api.MySpiService;
import io.toolisticon.myspiservice.processor.TestService;

@MySpiService(TestService.class)
public class TestcaseValidUsage implements TestService{

    public String doSomething(){
        return "Done!!!";
    }
}