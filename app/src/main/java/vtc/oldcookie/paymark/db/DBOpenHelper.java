package vtc.oldcookie.paymark.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import vtc.oldcookie.paymark.R;

/**
 * Class for managing the SQLite database.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private final Context context;

    /**
     * Constructor for DBOpenHelper.
     *
     * @param context the context in which the database is being used
     */
    public DBOpenHelper(@Nullable Context context) {
        super(context, "paymark.db", null, 1);
        this.context = context;
    }

    /**
     * Called when the database is created for the first time.
     *
     * @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table typetb(id integer primary key autoincrement,typename varchar(10),imageId integer,sImageId integer,kind integer)";
        db.execSQL(sql);
        insertType(db);
        sql = "create table accounttb(id integer primary key autoincrement,typename varchar(10),sImageId integer,comment varchar(80),money float," +
                "time varchar(60),year integer,month integer,day integer,kind integer)";
        db.execSQL(sql);
    }

    private void insertType(SQLiteDatabase db) {
        String sql = "insert into typetb (typename,imageId,sImageId,kind) values (?,?,?,?)";
        db.execSQL(sql, new Object[]{context.getString(R.string.type_other), R.mipmap.ic_other, R.mipmap.ic_other_fs, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_food), R.mipmap.ic_food, R.mipmap.ic_food_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_transport), R.mipmap.ic_transport, R.mipmap.ic_transport_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_shopping), R.mipmap.ic_shopping, R.mipmap.ic_shopping_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_clothing), R.mipmap.ic_clothing, R.mipmap.ic_clothing_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_daily), R.mipmap.ic_daily, R.mipmap.ic_daily_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_entertainment), R.mipmap.ic_entertainment, R.mipmap.ic_entertainment_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_education), R.mipmap.ic_education, R.mipmap.ic_education_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_medical), R.mipmap.ic_medical, R.mipmap.ic_medical_chose, 0});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_tenement), R.mipmap.ic_tenement, R.mipmap.ic_tenement_chose, 0});

        db.execSQL(sql, new Object[]{context.getString(R.string.type_other), R.mipmap.in_other, R.mipmap.in_other_chose, 1});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_salary), R.mipmap.in_salary, R.mipmap.in_salary_chose, 1});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_interest), R.mipmap.in_interest, R.mipmap.in_interest_choose, 1});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_invest), R.mipmap.in_invest, R.mipmap.in_invest_chose, 1});
        db.execSQL(sql, new Object[]{context.getString(R.string.type_trade), R.mipmap.in_trade, R.mipmap.in_trade_chose, 1});
    }

    /**
     * Called when the database needs to be upgraded.
     *
     * @param db         the database
     * @param oldVersion the old database version
     * @param newVersion the new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
