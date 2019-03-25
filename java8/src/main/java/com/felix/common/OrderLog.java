package com.felix.common;

public class OrderLog {
    private int year;

    private double account;

    private String city;

    private String changeName;

    public OrderLog(int year, double account, String city, String changeName) {
        this.year = year;
        this.account = account;
        this.city = city;
        this.changeName = changeName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }
}
