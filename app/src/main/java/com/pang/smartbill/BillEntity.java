package com.pang.smartbill;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.db.MemberBean;

// Column "MemberId" has a foreign key reference to column "Id" of MemberEntity.
// Column "GroupName" has a foreign key reference to column "GroupName" of GroupEntity
@Entity(foreignKeys = {
        @ForeignKey(entity = MemberBean.class,
            parentColumns = "Id",
            childColumns = "MemberId",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(entity = GroupBean.class,
            parentColumns = "GroupName",
            childColumns = "GroupName",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )},
        indices = {
                @Index(name="MemberIdIndex",value = {"MemberId"}),
                @Index(name="GroupNameIndexBill",value = {"GroupName"})
        })
public class BillEntity {
    BillEntity(int mid, String item, String cost, String gName, String paidBy) {
        this.mid = mid;
        this.item = item;
        this.cost = cost;
        this.gName = gName;
        this.paidBy = paidBy;
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    public int id;

    @ColumnInfo(name = "MemberId") // id of the member who paid for the bill
    public int mid;

    @ColumnInfo(name = "Item")
    public String item;

    @ColumnInfo(name = "PaidBy") // name of the member who paid for the bill
    String paidBy;

    @ColumnInfo(name = "Cost")
    String cost;

    @ColumnInfo(name = "GroupName")
    String gName;

    public void setId(int id) {
        this.id = id;
    }
}