package com.example.shobhraj.recycler;

/**
 * Created by shobhraj on 12/3/18.
 */

public class listItem {

    private String head;
    private String desc;
    //private String imageurl;

    public listItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
        //this.imageurl=imageurl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    //public String getImageurl() {
     //   return imageurl;
    //}
}
