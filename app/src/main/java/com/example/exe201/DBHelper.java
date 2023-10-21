package com.example.exe201;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname= "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    public void queryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table User (userID Integer Primary Key Autoincrement," +
                "userName nvarchar(20), password nvarchar(20), fullName nvarchar(50), role nvarchar(20), phone nvarchar(20)" +
                ", dateOfBirth nvarchar(20), gender nvarchar(20), address nvarchar(20), point Integer)");

//        myDB.execSQL("create Table Orders (orderID Integer Primary Key Autoincrement," +
 //               "foreign key (userName) references User(userName), createDate nvarchar(20), getDate nvarchar(20), getTime nvarchar(20)," +
 //               "status nvarchar(20), getAddress nvarchar(20), materialAmount Integer, orderPoint Integer)");

        myDB.execSQL("create Table Voucher (voucherID Integer Primary Key Autoincrement," +
                "voucherName nvarchar(20), expiredDate nvarchar(20), voucherPrice Integer, description nvarchar(50))");

        myDB.execSQL("create Table UserVoucher (userVoucherID Integer Primary Key Autoincrement," +
                "userName nvarchar(20) references User,voucherName nvarchar(20) references Voucher,voucherCode nvarchar(20), exchangeDate nvarchar(20))");

        myDB.execSQL("create table Orders (orderID Integer Primary Key Autoincrement,orderCode nvarchar(20)," +
                " userName nvarchar(20) references User, materialAmount decimal,createDate nvarchar(20), status nvarchar(20), getAddress nvarchar(50)," +
                " getDate nvarchar(20), getTime nvarchar(20), orderPoint Integer)");
        myDB.execSQL("Create Table if not exists Material (materialID Integer Primary Key Autoincrement," +
                "materialName nvarchar(20), unitPrice Integer)");
        myDB.execSQL("create table if not exists OrderDetail (orderCode nvarchar(20) references Orders," +
                " materialName nvarchar(20) references Material, materialAmount decimal, Primary key (orderCode,materialName))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists User");
        myDB.execSQL("drop Table if exists Orders");
        myDB.execSQL("drop Table if exists Voucher");
        myDB.execSQL("drop Table if exists UserVoucher");
        myDB.execSQL("drop Table if exists Material");
        myDB.execSQL("drop Table if exists OrderDetail");
        onCreate(myDB);
    }

    public void queryData(){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL("Insert into Voucher values (1, 'Shopee 15k', '12/12/2023',15000, 'Giảm 15k cho đơn hàng từ 60k trở lên')");
        database.execSQL("Insert into Voucher values (2, 'Lazada 20k', '01/12/2023',20000, 'Giảm 20k cho đơn hàng từ 100k trở lên')");
        database.execSQL("Insert into Voucher values (3, 'KFC 20%', '20/22/2023',20000, 'Giảm 20% cho đơn hàng từ 100k trở lên')");
        database.execSQL("Insert into Voucher values (4, 'Shopee Food FS', '10/11/2023',10000, 'Miễn phí giao hàng cho đơn hàng 50k trở lên')");
        database.execSQL("Insert into Voucher values (5, 'Fahasa FS', '12/11/2023',10000, 'Miễn phí giao hàng cho đơn hàng 100k trở lên')");
    }

    public void queryDataMaterial(){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL("Insert into Material values (1, 'Báo', 3500)");
        database.execSQL("Insert into Material values (2, 'Giấy vụn', 1000)");
        database.execSQL("Insert into Material values (3, 'Bìa các-tông', 2000)");
        database.execSQL("Insert into Material values (4, 'Sắt vụn', 3000)");
        database.execSQL("Insert into Material values (5, 'Sắt gia công', 3000)");
        database.execSQL("Insert into Material values (6, 'Giấy bìa cứng', 1800)");
        database.execSQL("Insert into Material values (7, 'Mủ nguyên chất', 4000)");
        database.execSQL("Insert into Material values (8, 'Mủ màu', 2500)");
        database.execSQL("Insert into Material values (9, 'Chai nhựa', 2000)");
        database.execSQL("Insert into Material values (10, 'Nhôm', 50000)");
        database.execSQL("Insert into Material values (11, 'Đồng thau', 55000)");
    }

    public boolean insert(String username, String password, String role, String phone, String address){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("userID", (Integer) 2);
        contentValues.put("userName",username);
        contentValues.put("password",password);
        contentValues.put("fullName"," ");
        contentValues.put("role",role);
        contentValues.put("phone",phone);
        contentValues.put("dateOfBirth"," ");
        contentValues.put("gender"," ");
        contentValues.put("address",address);
        contentValues.put("point",20000);
        long result= myDB.insert("User", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public boolean checkUser(String username){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from User where userName= ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkUserPass(String username, String password){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from User where userName= ? and password= ?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

//    public void updateUser(String username, String fullName, String phone, String birth, String gen, String add){
//        SQLiteDatabase myDB= this.getWritableDatabase();
//        myDB.execSQL("Update User SET fullName= '"+fullName+"' , phone= '"+phone+"', dateOfBirth= '"+birth+"'" +
//                ", gender= '"+gen+"', address= '"+add+"' WHERE username= '"+username+"'");
//        return;
//    }
    public boolean update(String username, String fullName, String phone ,String birth, String gen, String address){
    SQLiteDatabase myDB= this.getWritableDatabase();
    ContentValues contentValues= new ContentValues();
    contentValues.put("fullName",fullName);
    contentValues.put("phone",phone);
    contentValues.put("dateOfBirth",birth);
    contentValues.put("gender",gen);
    contentValues.put("address",address);
    contentValues.put("point",20000);
    long result= myDB.update("User", contentValues, "userName=?", new String[]{username});
    if (result==-1)
        return false;
    else
        return true;
}
    public boolean insertMyVoucher(String username, String vouchername, String vouchercode){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("Select * from UserVoucher",null);
        int tem= cursor.getCount();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        ContentValues contentValues= new ContentValues();
        contentValues.put("userVoucherID", tem);
        contentValues.put("userName", username);
        contentValues.put("voucherName", vouchername);
        contentValues.put("voucherCode", vouchercode);
        contentValues.put("exchangeDate", day+"/"+month+"/"+year);
        long result= myDB.insert("UserVoucher",null, contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getData(String username){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from User WHERE userName=?", new String[]{username});
        return cursor;
    }

    public Cursor getVoucher(){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from Voucher", null);
        return cursor;
    }
    public Cursor getVMaterial(){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from Material", null);
        return cursor;
    }

    public Cursor getMyVoucher(String username){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select UserVoucher.voucherName, voucherCode, description from UserVoucher INNER join Voucher on UserVoucher.voucherName= Voucher.voucherName where userName=?", new String[]{username});
        return cursor;
    }

    public boolean insertOrder(String userName, String address, String orderTime, String orderDate){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("Select * from Orders",null);
        int tem= cursor.getCount();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        ContentValues contentValues= new ContentValues();
        contentValues.put("orderID",tem);
        contentValues.put("userName",userName);
        contentValues.put("orderCode",getRanString(10));
        contentValues.put("createDate",day+"/"+month+"/"+year);
        contentValues.put("status","waiting");
        contentValues.put("getAddress", address);
        contentValues.put("getDate", orderDate);
        contentValues.put("getTime", orderTime);
        contentValues.put("orderPoint", 0);
        long result= myDB.insert("Orders",null, contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public boolean insertOrderDetail(String orderCode, String materialName, double materialAmount){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("orderCode",orderCode);
        contentValues.put("materialName",materialName);
        contentValues.put("materialAmount",materialAmount);
        long result= myDB.insert("Orders",null, contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public boolean updateOrderDetail(String orderCode, String materialName, double materialAmount){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("materialAmount",materialAmount);
        long result= myDB.update("Orders",contentValues,"orderCode=? AND materialName = ?", new String[]{orderCode,materialName});
        if (result==-1)
            return false;
        else
            return true;
    }
    public boolean updateOrderStatus(String orderCode, String status){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("status", status);
        long result= myDB.update("Orders",contentValues,"orderCode=?", new String[]{orderCode});
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getOrder(){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from Orders order by getDate",null);
        return cursor;
    }
    public Cursor getMyOrder(String username){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from Orders where userName=? order by getDate", new String[]{username});
        return cursor;
    }
    public Cursor getOrderDetail(String orderCode){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from OrderDetail where userCode=? order by getDate", new String[]{orderCode});
        return cursor;
    }

    public void updatePoint(String username, int newPoint){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("point", newPoint);
        myDB.update("User",contentValues,"userName=?",new String[]{username});
    }
    private static String getRanString(int a){
        final String character= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result= new StringBuilder();
        while (a>0){
            Random r= new Random();
            result.append(character.charAt(r.nextInt(character.length())));
            a--;
        }
        return result.toString();
    }
}
