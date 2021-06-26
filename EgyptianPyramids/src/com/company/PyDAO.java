package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PyDAO {
    List<Pyramids> pyramids;


    public PyDAO() {
        pyramids = new ArrayList<Pyramids>();
    }

    public List<Pyramids> readcsv(String file) {


        BufferedReader br = null;

        {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String line;
        try {
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {
            try {
                line = br.readLine();

                while (line != null) {
                    String attributes[] = line.split(",");


                    Double height = (attributes[7].equals("")  ? 0.0 : Double.parseDouble(attributes[7]));
                        Pyramids info = new Pyramids(attributes[0], attributes[2], attributes[4], height);
                        pyramids.add(info);
                        line = br.readLine();
                    }

                } catch (IOException e) {
                e.printStackTrace();
            }


        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pyramids;
    }

    public List<Pyramids> sortPyramids(List<Pyramids>list){

            Collections.sort(list , new Sort());
            return list;
        }

    public Map<String,Integer> countSite(){
        Map<String,Integer> sites = new HashMap<>();
        Iterator<Pyramids> iter = this.pyramids.iterator();
        while (iter.hasNext()){
            Pyramids t = iter.next();
            String key = t.getSite();
            if(sites.containsKey(key)){
                int count = sites.get(key);
                sites.put(key,count+1);
            }
            else {
                sites.put(key,1);




            }
        }
        return sites;


    }
    }







