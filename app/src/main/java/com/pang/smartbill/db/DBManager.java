package com.pang.smartbill.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pang.smartbill.ui.FloatUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * The class responsible for managing the database
 * main operate the context of table, add, delete, edit and view
 * */
public class DBManager {

    private static SQLiteDatabase db;
    /* Initializes the database object*/
    public static void initDB(Context context){
        DBOpenHelper helper = new DBOpenHelper(context);  //Get the helper class object
        db = helper.getWritableDatabase();      //Get the database object
    }

    /**
     * Read data from the database and write it to the memory collection
     *   kind :Represents income or expense
     * */
    public static List<TypeBean>getTypeList(int kind){
        List<TypeBean>list = new ArrayList<>();
        //read typetb
        String sql = "select * from typetb where kind = "+kind;
        Cursor cursor = db.rawQuery(sql, null);
        //The contents of the cursor are read and stored in the object
        while (cursor.moveToNext()) {
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            int imageId = cursor.getInt(cursor.getColumnIndexOrThrow("imageId"));
            int sImageId = cursor.getInt(cursor.getColumnIndexOrThrow("sImageId"));
            int kind1 = cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            TypeBean typeBean = new TypeBean(id, typename, imageId, sImageId, kind);
            list.add(typeBean);
        }




        return list;
    }

    /*
     * Inserts an element into the billing table
     * */
    public static void insertItemToAccounttb(AccountBean bean){
        ContentValues values = new ContentValues();
        values.put("typename",bean.getTypename());
        values.put("sImageId",bean.getsImageId());
        values.put("note",bean.getNote());
        values.put("money",bean.getMoney());
        values.put("time",bean.getTime());
        values.put("year",bean.getYear());
        values.put("month",bean.getMonth());
        values.put("day",bean.getDay());
        values.put("kind",bean.getKind());
        db.insert("accounttb",null,values);

    }

    // insert new group info
    public static void insertInfoToGrouptb(GroupBean bean){
        ContentValues values = new ContentValues();
        values.put("grouptitle",bean.getGrouptitle());
        values.put("description",bean.getDescription());
        values.put("currency",bean.getCurrency());
        values.put("category",bean.getCategory());
        db.insert("grouptb",null,values);
    }

    public static void updateInfoToGrouptb(GroupBean bean){
        ContentValues values = new ContentValues();
        values.put("grouptitle",bean.getGrouptitle());
        values.put("description",bean.getDescription());
        values.put("currency",bean.getCurrency());
        values.put("category",bean.getCategory());
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(bean.getId())};

        db.update("grouptb", values, whereClause, whereArgs);

    }

    public static void insertInfoToMembertb(MemberBean bean){
        ContentValues values = new ContentValues();
        values.put("member_name",bean.getMemberName());
        values.put("group_id",bean.getMemberGroupId());
        db.insert("membertb",null,values);
    }
    //get group info
    public static List<GroupBean>getInfoFromGrouptb(){
        List<GroupBean>list = new ArrayList<>();
        List<MemberBean>memberBeanList = new ArrayList<>();

        String sql = "select * from grouptb order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{});
        //traverse data
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String grouptitle = cursor.getString(cursor.getColumnIndexOrThrow("grouptitle"));
            String description= cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String currency = cursor.getString(cursor.getColumnIndexOrThrow("currency"));
            String category= cursor.getString(cursor.getColumnIndexOrThrow("category"));


            GroupBean groupBean = new GroupBean(id, grouptitle, description,currency,category);//,memberBeanList
            list.add(groupBean);

        }

        return list;

    }

    public static List<MemberBean>getInfoFromMembertb(){
        List<GroupBean>list = new ArrayList<>();
        List<MemberBean>memberBeanList = new ArrayList<>();

        String sql = "select * from membertb order by member_id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{});
        //traverse data
        while (cursor.moveToNext()) {
            int member_id = cursor.getInt(cursor.getColumnIndexOrThrow("member_id"));
            String member_name = cursor.getString(cursor.getColumnIndexOrThrow("member_name"));
            int group_id= cursor.getInt(cursor.getColumnIndexOrThrow("group_id"));

            MemberBean memberBean = new MemberBean(member_id, member_name, group_id);//,memberBeanList
            memberBeanList.add(memberBean);

        }


        return memberBeanList;
    }





    /*
     *  get income/expense information from one day
     * */
    public static List<AccountBean>getAccountListOneDayFromAccounttb(int year,int month,int day){
        List<AccountBean>list = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? and day=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + ""});
        //traverse data
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            String note= cursor.getString(cursor.getColumnIndexOrThrow("note"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            int sImageId = cursor.getInt(cursor.getColumnIndexOrThrow("sImageId"));
            int kind = cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("money"));
            AccountBean accountBean = new AccountBean(id, typename, sImageId, note, money, time, year, month, day, kind);
            list.add(accountBean);
        }

        return list;
    }

    /*
     * Obtain all expenditures or income for a month
     * */
    public static List<AccountBean>getAccountListOneMonthFromAccounttb(int year,int month){
        List<AccountBean>list = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + ""});
        //Iterate over each row of data that meets the requirements
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            String note= cursor.getString(cursor.getColumnIndexOrThrow("note"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            int sImageId = cursor.getInt(cursor.getColumnIndexOrThrow("sImageId"));
            int kind = cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("money"));
            int day = cursor.getInt(cursor.getColumnIndexOrThrow("day"));
            AccountBean accountBean = new AccountBean(id, typename, sImageId, note, money, time, year, month, day, kind);
            list.add(accountBean);
        }
        return list;
    }
    /**
     *  day :expense==0    income===1
     * */
    public static float getSumMoneyOneDay(int year,int month,int day,int kind){
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and month=? and day=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + "", kind + ""});

        if (cursor.moveToFirst()) {
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("sum(money)"));
            total = money;
        }
        return total;
    }
    /**
     * month
     * */
    public static float getSumMoneyOneMonth(int year,int month,int kind){
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        // Iterate
        if (cursor.moveToFirst()) {
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("sum(money)"));
            total = money;
        }
        return total;
    }
    /** statistics  income -1   expense-0*/
    public static int getCountItemOneMonth(int year,int month,int kind){
        int total = 0;
        String sql = "select count(money) from accounttb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(cursor.getColumnIndexOrThrow("count(money)"));
            total = count;
        }
        return total;
    }

    public static float getSumMoneyOneYear(int year,int kind){
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", kind + ""});

        if (cursor.moveToFirst()) {
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("sum(money)"));
            total = money;
        }
        return total;
    }

    /*
     * delete accounttb
     * */
    public static int deleteItemFromAccounttbById(int id){
        int i = db.delete("accounttb", "id=?", new String[]{id + ""});
        return i;
    }

    public static Long deleteItemFromGrouptbById(Long id){
        long i = db.delete("grouptb", "id=?", new String[]{id + ""});
        return i;
    }

    public static int deleteItemFromMembertbById(int id){
        int i = db.delete("membertb", "member_id=?", new String[]{id + ""});
        return i;
    }
    /**
     * search income or expense
     * */
    public static List<AccountBean>getAccountListByRemarkFromAccounttb(String note){
        List<AccountBean>list = new ArrayList<>();
        String sql = "select * from accounttb where note like '%"+note+"%'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            String bz = cursor.getString(cursor.getColumnIndexOrThrow("note"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            int sImageId = cursor.getInt(cursor.getColumnIndexOrThrow("sImageId"));
            int kind = cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("money"));
            int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));
            int month = cursor.getInt(cursor.getColumnIndexOrThrow("month"));
            int day = cursor.getInt(cursor.getColumnIndexOrThrow("day"));
            AccountBean accountBean = new AccountBean(id, typename, sImageId, bz, money, time, year, month, day, kind);
            list.add(accountBean);
        }
        return list;
    }

    /**
     * Check how many years are in the account table
     * */
    public static List<Integer>getYearListFromAccounttb(){
        List<Integer>list = new ArrayList<>();
        String sql = "select distinct(year) from accounttb order by year asc";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));
            list.add(year);
        }
        return list;
    }

    /*
     * delete all data of accounttb
     * */
    public static void deleteAllAccount(){
        String sql = "delete from accounttb";
        db.execSQL(sql);
    }



    public static List<ChartItemBean>getChartListFromAccounttb(int year,int month,int kind){
        List<ChartItemBean>list = new ArrayList<>();
        float sumMoneyOneMonth = getSumMoneyOneMonth(year, month, kind);
        String sql = "select typename,sImageId,sum(money)as total from accounttb where year=? and month=? and kind=? group by typename " +
                "order by total desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        while (cursor.moveToNext()) {
            int sImageId = cursor.getInt(cursor.getColumnIndexOrThrow("sImageId"));
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            float total = cursor.getFloat(cursor.getColumnIndexOrThrow("total"));

            float ratio = FloatUtils.div(total,sumMoneyOneMonth);
            ChartItemBean bean = new ChartItemBean(sImageId, typename, ratio, total);
            list.add(bean);
        }
        return list;
    }


    public static float getMaxMoneyOneDayInMonth(int year,int month,int kind){
        String sql = "select sum(money) from accounttb where year=? and month=? and kind=? group by day order by sum(money) desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        if (cursor.moveToFirst()) {
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("sum(money)"));
            return money;
        }
        return 0;
    }


    public static List<BarChartItemBean>getSumMoneyOneDayInMonth(int year,int month,int kind){
        String sql = "select day,sum(money) from accounttb where year=? and month=? and kind=? group by day";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        List<BarChartItemBean>list = new ArrayList<>();
        while (cursor.moveToNext()) {

            int day = cursor.getInt( cursor.getColumnIndexOrThrow("day"));
            float smoney = cursor.getFloat(cursor.getColumnIndexOrThrow("sum(money)"));
            BarChartItemBean itemBean = new BarChartItemBean(year, month, day, smoney);
            list.add(itemBean);
        }
        return list;
    }

}
