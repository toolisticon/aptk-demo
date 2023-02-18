package io.toolisticon.myspiservice.example;

import io.toolisticon.myspiservice.api.MySpiService;

@MySpiService(TestService.class)
public class TestBean implements TestService{

    @Override
    public String doSomething() {
        return "It worked !!!";
    }
}
