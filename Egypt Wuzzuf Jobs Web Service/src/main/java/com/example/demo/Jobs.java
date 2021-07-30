package com.example.demo;

public class Jobs {

    String Title ;
    String Comany ;
    String Location ;
    String Type ;
    String Level ;
    String YearsExp ;
    String Country ;
    String Skills ;



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getComany() {
        return Comany;
    }

    public void setComany(String comany) {
        Comany = comany;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getYearsExp() {
        return YearsExp;
    }

    public void setYearsExp(String yearsExp) {
        YearsExp = yearsExp;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }






    @Override
    public String toString() {
        return "Jobs{" +
                "Title='" + Title + '\'' +
                ", Comany='" + Comany + '\'' +
                ", Location='" + Location + '\'' +
                ", Type='" + Type + '\'' +
                ", Level='" + Level + '\'' +
                ", YearsExp='" + YearsExp + '\'' +
                ", Country='" + Country + '\'' +
                ", Skills='" + Skills + '\'' +
                '}';
    }
}
