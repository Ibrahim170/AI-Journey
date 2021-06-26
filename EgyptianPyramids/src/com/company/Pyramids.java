package com.company;

public class Pyramids {
    String pharaoh ;
    String Moder_Name ;
    String site ;
    Double Height ;

    public Pyramids(String pharaoh, String moder_Name, String site, Double height) {
        this.pharaoh = pharaoh;
        Moder_Name = moder_Name;
        this.site = site;
        Height = height;
    }

    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }

    public void setModer_Name(String moder_Name) {
        Moder_Name = moder_Name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setHeight(Double height) {
        Height = height;
    }

    public String getPharaoh() {
        return pharaoh;
    }

    public String getModer_Name() {
        return Moder_Name;
    }

    public String getSite() {
        return site;
    }

    public Double getHeight() {
        return Height;
    }

    @Override
    public String toString() {
        return "Pyramids{" +
                "pharaoh='" + pharaoh + '\'' +
                ", Moder_Name='" + Moder_Name + '\'' +
                ", site='" + site + '\'' +
                ", Height=" + Height +
                '}';
    }
}

