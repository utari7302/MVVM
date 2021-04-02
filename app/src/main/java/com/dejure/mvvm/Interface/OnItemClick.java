package com.dejure.mvvm.Interface;

public class OnItemClick {
    boolean isClick;
    String country;
    String status;

    public OnItemClick(boolean isClick, String country, String status) {
        this.isClick = isClick;
        this.country = country;
        this.status = status;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
