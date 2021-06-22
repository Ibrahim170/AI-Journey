package com.company;

public class SimpleThread extends Thread{
    String name ;

    public SimpleThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i =0 ; i < 10 ; i++){
            System.out.println(i + " " + name);
            try {
                sleep((int)(Math.random()*1000));
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        }
        System.out.println("Done!"+ name);
}}
