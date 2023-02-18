package io.toolisticon.myspiservice.integrationtest;

import io.toolisticon.myspiservice.api.MySpiService;

@MySpiService(TestService.class)
public class TestcaseValidUsage implements TestService{
    @Override
    public String doSomething() {
        return "DONE";
    }
}