package com.example.paymark;
public class Record {
    private String type;
    private double price;
    private String info;
    private long timestamp;
    public Record(String type, double price, String info, long timestamp) {
        this.type = type;
        this.price = price;
        this.info = info;
        this.timestamp = timestamp;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}