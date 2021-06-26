package com.company;

import java.util.Comparator;

public class Sort implements Comparator<Pyramids> {
    @Override
    public int compare(Pyramids o1, Pyramids o2) {
        double v =  o1.getHeight() - o2.getHeight();
        return (int) v;
    }


}
