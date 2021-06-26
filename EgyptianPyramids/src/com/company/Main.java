package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        PyDAO pDAO = new PyDAO();
        List<Pyramids> pyramids = pDAO.readcsv("pyramids.csv");
        List<Pyramids> sorted = pDAO.sortPyramids(pyramids);


        for (int i = 0; i < pyramids.size(); i++) {

            System.out.println((pyramids.get(i)));

        }
        for (Pyramids p : sorted) {
            System.out.println(p);
        }
        Map<String , Integer> sites ;
        sites = pDAO.countSite();
        sites.forEach((k,v) -> System.out.println("Site name :"+ k+ ", Count="+ v));

    }
}


