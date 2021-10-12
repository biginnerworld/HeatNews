package com.karpovskiy.newssite.Generator;

import java.util.UUID;


//STRING ID GENERATOR
public class IDGenerator {
    public static String generateID(){
        return UUID.randomUUID().toString();
    }
}
