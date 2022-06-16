package com.mytech.smmpanel.model;

public class UserCampaign {
    String catname,servicename,description,channellink,quantity,charge,userid,starttime;

    public UserCampaign(String catname, String servicename, String description, String channellink, String quantity, String charge, String userid, String starttime) {
        this.catname = catname;
        this.servicename = servicename;
        this.description = description;
        this.channellink = channellink;
        this.quantity = quantity;
        this.charge = charge;
        this.userid = userid;
        this.starttime = starttime;
    }

    public UserCampaign() {
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannellink() {
        return channellink;
    }

    public void setChannellink(String channellink) {
        this.channellink = channellink;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
