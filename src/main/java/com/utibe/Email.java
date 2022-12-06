package com.utibe;

public class Email {
    private String userID;
    private String dns;
    private String address;

    public Email(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress() {
        if ( (this.address != null)  && (this.dns != null))
            this.address = this.userID + '@' + this.dns;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String toString(){
        return (this.userID + '@' + this.dns);
    }
}
