package com.pang.smartbill.db;
/** Describes record the content group of data*/
public class GroupBean {
    int id;
    String grouptitle;   //type
//    int sImageId;   //Selected type image
    String description;

    String currency ;
    String category;



    String groupmember;



    public int getId() {
        return id;
    }

    public void setId(int id) {
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


//    public String getGroupmember() {
//        return groupmember;
//    }
//
//        public void setGroupmember(String groupmember) {
//        this.groupmember= groupmember;
//    }




    public GroupBean() {
    }

    public GroupBean(int id, String grouptitle, String description,String currency, String category) {
        this.id = id;
        this.grouptitle = grouptitle;

        this.description= description;
        this.currency= currency;
        this.category= category;

     //   this.groupmember = groupmember;

    }
}
