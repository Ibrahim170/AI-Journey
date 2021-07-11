public class Persons {

    String name;
    String country;
    int salary;

    public Persons(String name, String country, int salary) {
        this.name = name;
        this.country = country;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

