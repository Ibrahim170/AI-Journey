package com.company;

public class Character {



    public static boolean isString(String str) {

        return (!str.equals("") &&  str != null && str.chars().allMatch(java.lang.Character:: isLetter));
    }




}
