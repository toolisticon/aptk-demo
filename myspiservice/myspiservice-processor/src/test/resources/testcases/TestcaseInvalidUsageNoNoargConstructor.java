package io.toolisticon.myspiservice.processor.tests;

import io.toolisticon.myspiservice.api.MySpiService;
import io.toolisticon.myspiservice.processor.TestService;

@MySpiService("Xyz")
public class TestcaseInvalidUsageNoNoargConstructor implements TestService {

    private String field;

    String doSomething(){
        return "DONE!!!";
    }
}