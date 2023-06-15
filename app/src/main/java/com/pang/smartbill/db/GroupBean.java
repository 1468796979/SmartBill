package com.pang.smartbill.db;


import java.util.List;

/** Describes record the content group of data*/
public class GroupBean {
    long id;
    String grouptitle;   //type
//    int sImageId;   //Selected type image
    String description;

    String currency ;
    String category;



    List<MemberBean> members;



    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrouptitle() {
        return grouptitle;
    }

    public void setGrouptitle(String grouptitle) {
        this.grouptitle = grouptitle;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.category= currency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category= category;
    }







    public GroupBean() {
    }

    public GroupBean(long id, String grouptitle, String description,String currency, String category) {
        //,List<MemberBean> members
        this.id = id;
        this.grouptitle = grouptitle;

        this.description= description;
        this.currency= currency;
        this.category= category;
//        this.members = members;

     //   this.groupmember = groupmember;

    }
}
