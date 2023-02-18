package io.toolisticon.myspiservice.example;

import java.util.ServiceLoader;

public class TestUsageClass {

    public static void main(String[] args) {

        System.out.println(ServiceLoader.load(TestService.class).iterator().next().doSomething());


    }


}
