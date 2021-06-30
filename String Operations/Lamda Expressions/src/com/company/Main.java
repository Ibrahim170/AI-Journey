package com.company;

import java.util.function.BiPredicate;

public class Main {

    public static class Better {
        public static String betterString (String s1 , String s2 , BiPredicate<String,String> p){
        if (p.test(s1,s2)){
            return s1;
        }
        else {
            return s2;
        }
        }
    }

    public static void main(String[] args) {
	// write your code here
        String string1 = "Hi";
        String string2= "Hello";

        String longer = Better.betterString(string1,string2,(s1,s2) -> s1.length() > s2.length());
        String first = Better.betterString(string1,string2,(s1,s2) -> true);

        System.out.println(longer);
        System.out.println(first);


    }
}
