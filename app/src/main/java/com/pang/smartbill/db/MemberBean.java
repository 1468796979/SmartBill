package com.pang.smartbill.db;


import java.util.List;

public class MemberBean {
    private int member_Id;
    private String member_name;

    private int group_id;
    private double amountOwed;



    public int getMemberId() {
        return member_Id;
    }

    public void setMemberId(int member_Id) {
        this.member_Id = member_Id;
    }

    public String getMemberName() {
        return member_name;
    }

    public void setMemberName(String member_name) {
        this.member_name = member_name;
    }


    public int getMemberGroupId() {
        return group_id;
    }

    public void setMemberGroupId(int group_id) {
        this.group_id = group_id;
    }


    public MemberBean() {
    }

    public MemberBean(int member_Id, String member_name, int group_id) {
        this.member_Id = member_Id;
        this.member_name = member_name;

        this.group_id= group_id;
        }
}


