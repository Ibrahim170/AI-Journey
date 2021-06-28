package com.company;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        DAO pop = new DAO();
        List<Country> countries = pop.readcountry("Countries.csv");
        List<City> cities = pop.readcity("Cities.csv");

        for (Country country : countries) {
            System.out.println(country);
        }
        for (City city : cities) {
            System.out.println(city);


        }

        List<String> cityList = cities.stream().map((city -> city.getName())).sorted().collect(Collectors.toList());
        List<Integer> countryPop = countries.stream().map(country -> country.getPopulation()).collect(Collectors.toList());
        System.out.println(countryPop);
        OptionalDouble avgPop = countries.stream().map(country -> country.getPopulation()).mapToInt(Integer:: intValue).average();
        System.out.println("Average poulation is : "+avgPop);

        OptionalDouble avgArea = countries.stream().map(country -> country.getSurfaceArea()).mapToDouble(Double:: doubleValue).average();
        System.out.println(avgArea);
        OptionalInt maxPop = countries.stream().map(country -> country.getPopulation()).mapToInt(Integer:: intValue).max();
        System.out.println("Maximum Population : " +maxPop);







    }}



