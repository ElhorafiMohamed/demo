package com.example.demo.utils;
import java.util.Random;

public class RandomValues {

    private static final Random random = new Random();

    public static Long getRandomLong(){
        return random.nextLong();
    }

}
