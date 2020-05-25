package com.example.earthquakeusgs;

public class word {
    private Double magnitude;
    private String location;
    private Long mTimeInMilliseconds;
    private String link;
    public word(Double a,String b,Long c,String d){
        magnitude=a;
        location=b;
        mTimeInMilliseconds=c;
        link=d;
    }

    public Double getMagnitude(){
        return magnitude;
    }

    public String getLocation(){
        return location;
    }

    public Long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getLink() {
        return link;
    }


    public void setMagnitude(Double name) {
        this.magnitude = name;
    }

    public void setLocation(String name) {
        this.location = name;
    }

    public void setmTimeInMilliseconds(Long name) {
        this.mTimeInMilliseconds = name;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
