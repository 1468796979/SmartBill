package com.pang.smartbill.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pang.smartbill.R;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(@Nullable Context context) {
        super(context,"SmartBill.db" , null, 1);
    }

    //create the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table
        String sql = "create table typetb(id integer primary key autoincrement,typename varchar(10),imageId integer,sImageId integer,kind integer)";
        db.execSQL(sql);
        insertType(db);
        //Create a bookkeeping table
        sql = "create table accounttb(id integer primary key autoincrement,typename varchar(10),sImageId integer,note varchar(80),money float," +
                "time varchar(60),year integer,month integer,day integer,kind integer)";
        db.execSQL(sql);

        sql = "create table groupinfo(id integer primary key autoincrement,grouptitle varchar(80),sImageId integer,groupdescription varchar(100),currency varchar(80)," +
                "category varchar(50),groupmember varchar(50))";
        db.execSQL(sql);
    }

    private void insertType(SQLiteDatabase db) {
//      typetb
        String sql = "insert into typetb (typename,imageId,sImageId,kind) values (?,?,?,?)";
        db.execSQL(sql,new Object[]{"others", R.mipmap.ic_qita,R.mipmap.ic_qita_fs,0});
        db.execSQL(sql,new Object[]{"catering", R.mipmap.ic_canyin,R.mipmap.ic_canyin_fs,0});
        db.execSQL(sql,new Object[]{"traffic", R.mipmap.ic_jiaotong,R.mipmap.ic_jiaotong_fs,0});
        db.execSQL(sql,new Object[]{"shopping", R.mipmap.ic_gouwu,R.mipmap.ic_gouwu_fs,0});
        db.execSQL(sql,new Object[]{"costume", R.mipmap.ic_fushi,R.mipmap.ic_fushi_fs,0});
        db.execSQL(sql,new Object[]{"necessities", R.mipmap.ic_riyongpin,R.mipmap.ic_riyongpin_fs,0});
        db.execSQL(sql,new Object[]{"entertainment", R.mipmap.ic_yule,R.mipmap.ic_yule_fs,0});
        db.execSQL(sql,new Object[]{"snacks", R.mipmap.ic_lingshi,R.mipmap.ic_lingshi_fs,0});
        db.execSQL(sql,new Object[]{"drink", R.mipmap.ic_yanjiu,R.mipmap.ic_yanjiu_fs,0});
        db.execSQL(sql,new Object[]{"study", R.mipmap.ic_xuexi,R.mipmap.ic_xuexi_fs,0});
        db.execSQL(sql,new Object[]{"medical", R.mipmap.ic_yiliao,R.mipmap.ic_yiliao_fs,0});
        db.execSQL(sql,new Object[]{"residence", R.mipmap.ic_zhufang,R.mipmap.ic_zhufang_fs,0});
        db.execSQL(sql,new Object[]{"water&gas", R.mipmap.ic_shuidianfei,R.mipmap.ic_shuidianfei_fs,0});
        db.execSQL(sql,new Object[]{"network", R.mipmap.ic_tongxun,R.mipmap.ic_tongxun_fs,0});
        db.execSQL(sql,new Object[]{"relations", R.mipmap.ic_renqingwanglai,R.mipmap.ic_renqingwanglai_fs,0});

        db.execSQL(sql,new Object[]{"others", R.mipmap.in_qt,R.mipmap.in_qt_fs,1});
        db.execSQL(sql,new Object[]{"salary", R.mipmap.in_xinzi,R.mipmap.in_xinzi_fs,1});
        db.execSQL(sql,new Object[]{"bonus", R.mipmap.in_jiangjin,R.mipmap.in_jiangjin_fs,1});
        db.execSQL(sql,new Object[]{"borrow", R.mipmap.in_jieru,R.mipmap.in_jieru_fs,1});
        db.execSQL(sql,new Object[]{"debt collection", R.mipmap.in_shouzhai,R.mipmap.in_shouzhai_fs,1});
        db.execSQL(sql,new Object[]{"interest", R.mipmap.in_lixifuji,R.mipmap.in_lixifuji_fs,1});
        db.execSQL(sql,new Object[]{"investment", R.mipmap.in_touzi,R.mipmap.in_touzi_fs,1});
        db.execSQL(sql,new Object[]{"transaction", R.mipmap.in_ershoushebei,R.mipmap.in_ershoushebei_fs,1});
        db.execSQL(sql,new Object[]{"Windfall", R.mipmap.in_yiwai,R.mipmap.in_yiwai_fs,1});
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
