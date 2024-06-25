package vtc.oldcookie.paymark.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.utils.FloatUtils;

/**
 * Class for managing database operations.
 */
public class DBManager {
    private static SQLiteDatabase db;

    /**
     * Initializes the database.
     *
     * @param context the context in which the database is being used
     */
    public static void initDB(Context context) {
        DBOpenHelper helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * Retrieves a list of types based on the kind.
     *
     * @param kind the kind of types to retrieve
     * @return a list of TypeBean objects
     */
    public static List<TypeBean> getTypeList(int kind) {
        List<TypeBean> list = new ArrayList<>();
        String sql = "select * from typetb where kind = " + kind;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int typenameIndex = cursor.getColumnIndex("typename");
            String typename = typenameIndex != -1 ? cursor.getString(typenameIndex) : null;
            int imageIdIndex = cursor.getColumnIndex("imageId");
            int imageId = imageIdIndex != -1 ? cursor.getInt(imageIdIndex) : -1;
            int sImageIdIndex = cursor.getColumnIndex("sImageId");
            int sImageId = sImageIdIndex != -1 ? cursor.getInt(sImageIdIndex) : -1;
            int idIndex = cursor.getColumnIndex("id");
            int id = idIndex != -1 ? cursor.getInt(idIndex) : -1;
            TypeBean typeBean = new TypeBean(id, typename, imageId, sImageId, kind);
            list.add(typeBean);
        }
        return list;
    }

    /**
     * Inserts an account item into the database.
     *
     * @param bean the AccountBean object to insert
     */
    public static void insertItemToAccounttb(AccountBean bean) {
        ContentValues values = new ContentValues();
        values.put("typename", bean.getTypename());
        values.put("sImageId", bean.getsImageId());
        values.put("comment", bean.getComment());
        values.put("money", bean.getMoney());
        values.put("time", bean.getTime());
        values.put("year", bean.getYear());
        values.put("month", bean.getMonth());
        values.put("day", bean.getDay());
        values.put("kind", bean.getKind());
        db.insert("accounttb", null, values);
    }

    /**
     * Retrieves a list of account items for a specific day.
     *
     * @param year  the year of the items to retrieve
     * @param month the month of the items to retrieve
     * @param day   the day of the items to retrieve
     * @return a list of AccountBean objects
     */
    public static List<AccountBean> getAccountListOneDayFromAccounttb(int year, int month, int day) {
        List<AccountBean> list = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? and day=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + ""});
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("id");
            int id = idIndex != -1 ? cursor.getInt(idIndex) : -1;
            int typenameIndex = cursor.getColumnIndex("typename");
            String typename = typenameIndex != -1 ? cursor.getString(typenameIndex) : null;
            int commentIndex = cursor.getColumnIndex("comment");
            String comment = commentIndex != -1 ? cursor.getString(commentIndex) : null;
            int timeIndex = cursor.getColumnIndex("time");
            String time = timeIndex != -1 ? cursor.getString(timeIndex) : null;
            int sImageIdIndex = cursor.getColumnIndex("sImageId");
            int sImageId = sImageIdIndex != -1 ? cursor.getInt(sImageIdIndex) : -1;
            int kindIndex = cursor.getColumnIndex("kind");
            int kind = kindIndex != -1 ? cursor.getInt(kindIndex) : -1;
            int moneyIndex = cursor.getColumnIndex("money");
            float money = moneyIndex != -1 ? cursor.getFloat(moneyIndex) : -1;
            AccountBean accountBean = new AccountBean(id, typename, sImageId, comment, money, time, year, month, day, kind);
            list.add(accountBean);
        }
        return list;
    }

    /**
     * Retrieves a list of account items for a specific month.
     *
     * @param year  the year of the items to retrieve
     * @param month the month of the items to retrieve
     * @return a list of AccountBean objects
     */
    public static List<AccountBean> getAccountListOneMonthFromAccounttb(int year, int month) {
        List<AccountBean> list = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + ""});
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("id");
            int id = idIndex != -1 ? cursor.getInt(idIndex) : -1;
            int typenameIndex = cursor.getColumnIndex("typename");
            String typename = typenameIndex != -1 ? cursor.getString(typenameIndex) : null;
            int commentIndex = cursor.getColumnIndex("comment");
            String comment = commentIndex != -1 ? cursor.getString(commentIndex) : null;
            int timeIndex = cursor.getColumnIndex("time");
            String time = timeIndex != -1 ? cursor.getString(timeIndex) : null;
            int sImageIdIndex = cursor.getColumnIndex("sImageId");
            int sImageId = sImageIdIndex != -1 ? cursor.getInt(sImageIdIndex) : -1;
            int kindIndex = cursor.getColumnIndex("kind");
            int kind = kindIndex != -1 ? cursor.getInt(kindIndex) : -1;
            int moneyIndex = cursor.getColumnIndex("money");
            float money = moneyIndex != -1 ? cursor.getFloat(moneyIndex) : -1;
            int dayIndex = cursor.getColumnIndex("day");
            int day = dayIndex != -1 ? cursor.getInt(dayIndex) : -1;
            AccountBean accountBean = new AccountBean(id, typename, sImageId, comment, money, time, year, month, day, kind);
            list.add(accountBean);
        }
        return list;
    }

    /**
     * Retrieves the total sum of money for a specific day.
     *
     * @param year  the year of the items to sum
     * @param month the month of the items to sum
     * @param day   the day of the items to sum
     * @param kind  the kind of items to sum
     * @return the total sum of money
     */
    public static float getSumMoneyOneDay(int year, int month, int day, int kind) {
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and month=? and day=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + "", kind + ""});
        if (cursor.moveToFirst()) {
            int moneyIndex = cursor.getColumnIndex("sum(money)");
            total = moneyIndex != -1 ? cursor.getFloat(moneyIndex) : 0.0f;
        }
        return total;
    }

    /**
     * Retrieves the total sum of money for a specific month.
     *
     * @param year  the year of the items to sum
     * @param month the month of the items to sum
     * @param kind  the kind of items to sum
     * @return the total sum of money
     */
    public static float getSumMoneyOneMonth(int year, int month, int kind) {
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        if (cursor.moveToFirst()) {
            int moneyIndex = cursor.getColumnIndex("sum(money)");
            total = moneyIndex != -1 ? cursor.getFloat(moneyIndex) : 0.0f;
        }
        return total;
    }

    /**
     * Retrieves the total count of items for a specific month.
     *
     * @param year  the year of the items to count
     * @param month the month of the items to count
     * @param kind  the kind of items to count
     * @return the total count of items
     */
    public static int getCountItemOneMonth(int year, int month, int kind) {
        int total = 0;
        String sql = "select count(money) from accounttb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        if (cursor.moveToFirst()) {
            int countIndex = cursor.getColumnIndex("count(money)");
            total = countIndex != -1 ? cursor.getInt(countIndex) : 0;
        }
        return total;
    }

    /**
     * Deletes an item from the database by its id.
     *
     * @param id the id of the item to delete
     */
    public static void deleteItemFromAccounttbById(int id) {
        db.delete("accounttb", "id=?", new String[]{id + ""});
    }

    /**
     * Retrieves a list of account items based on a remark.
     *
     * @param comment the remark to search for
     * @return a list of AccountBean objects
     */
    public static List<AccountBean> getAccountListByRemarkFromAccounttb(String comment) {
        List<AccountBean> list = new ArrayList<>();
        String sql = "select * from accounttb where comment like '%" + comment + "%'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("id");
            int id = idIndex != -1 ? cursor.getInt(idIndex) : -1;
            int typenameIndex = cursor.getColumnIndex("typename");
            String typename = typenameIndex != -1 ? cursor.getString(typenameIndex) : null;
            int commentIndex = cursor.getColumnIndex("comment");
            String commenti = commentIndex != -1 ? cursor.getString(commentIndex) : null;
            int timeIndex = cursor.getColumnIndex("time");
            String time = timeIndex != -1 ? cursor.getString(timeIndex) : null;
            int sImageIdIndex = cursor.getColumnIndex("sImageId");
            int sImageId = sImageIdIndex != -1 ? cursor.getInt(sImageIdIndex) : -1;
            int kindIndex = cursor.getColumnIndex("kind");
            int kind = kindIndex != -1 ? cursor.getInt(kindIndex) : -1;
            int moneyIndex = cursor.getColumnIndex("money");
            float money = moneyIndex != -1 ? cursor.getFloat(moneyIndex) : -1;
            int yearIndex = cursor.getColumnIndex("year");
            int year = yearIndex != -1 ? cursor.getInt(yearIndex) : -1;
            int monthIndex = cursor.getColumnIndex("month");
            int month = monthIndex != -1 ? cursor.getInt(monthIndex) : -1;
            int dayIndex = cursor.getColumnIndex("day");
            int day = dayIndex != -1 ? cursor.getInt(dayIndex) : -1;
            AccountBean accountBean = new AccountBean(id, typename, sImageId, commenti, money, time, year, month, day, kind);
            list.add(accountBean);
        }
        return list;
    }

    /**
     * Retrieves a list of distinct years from the database.
     *
     * @return a list of years
     */
    public static List<Integer> getYearListFromAccounttb() {
        List<Integer> list = new ArrayList<>();
        String sql = "select distinct(year) from accounttb order by year asc";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int yearIndex = cursor.getColumnIndex("year");
            int year = yearIndex != -1 ? cursor.getInt(yearIndex) : -1;
            list.add(year);
        }
        return list;
    }

    /**
     * Deletes all accounts from the database.
     */
    public static void deleteAllAccount() {
        String sql = "delete from accounttb";
        db.execSQL(sql);
    }

    /**
     * Retrieves a list of chart items for a specific month.
     *
     * @param year  the year of the items to retrieve
     * @param month the month of the items to retrieve
     * @param kind  the kind of items to retrieve
     * @return a list of ChartItemBean objects
     */
    public static List<ChartItemBean> getChartListFromAccounttb(int year, int month, int kind) {
        List<ChartItemBean> list = new ArrayList<>();
        float sumMoneyOneMonth = getSumMoneyOneMonth(year, month, kind);
        String sql = "select typename,sImageId,sum(money)as total from accounttb where year=? and month=? and kind=? group by typename " + "order by total desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        while (cursor.moveToNext()) {
            int sImageIdIndex = cursor.getColumnIndex("sImageId");
            int sImageId = sImageIdIndex != -1 ? cursor.getInt(sImageIdIndex) : -1;
            int typenameIndex = cursor.getColumnIndex("typename");
            String typename = typenameIndex != -1 ? cursor.getString(typenameIndex) : null;
            int totalIndex = cursor.getColumnIndex("total");
            float total = totalIndex != -1 ? cursor.getFloat(totalIndex) : 0.0f;
            float ratio = FloatUtils.div(total, sumMoneyOneMonth);
            ChartItemBean bean = new ChartItemBean(sImageId, typename, ratio, total);
            list.add(bean);
        }
        return list;
    }

    /**
     * Retrieves the maximum sum of money for a specific day in a month.
     *
     * @param year  the year of the items to sum
     * @param month the month of the items to sum
     * @param kind  the kind of items to sum
     * @return the maximum sum of money
     */
    public static float getMaxMoneyOneDayInMonth(int year, int month, int kind) {
        String sql = "select sum(money) from accounttb where year=? and month=? and kind=? group by day order by sum(money) desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        if (cursor.moveToFirst()) {
            int moneyIndex = cursor.getColumnIndex("sum(money)");
            return moneyIndex != -1 ? cursor.getFloat(moneyIndex) : 0.0f;
        }
        return 0;
    }

    /**
     * Retrieves a list of the sum of money for each day in a specific month.
     *
     * @param year  the year of the items to sum
     * @param month the month of the items to sum
     * @param kind  the kind of items to sum
     * @return a list of BarChartItemBean objects
     */
    public static List<BarChartItemBean> getSumMoneyOneDayInMonth(int year, int month, int kind) {
        String sql = "select day,sum(money) from accounttb where year=? and month=? and kind=? group by day";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        List<BarChartItemBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int dayIndex = cursor.getColumnIndex("day");
            int day = dayIndex != -1 ? cursor.getInt(dayIndex) : -1;
            int smoneyIndex = cursor.getColumnIndex("sum(money)");
            float smoney = smoneyIndex != -1 ? cursor.getFloat(smoneyIndex) : 0.0f;
            BarChartItemBean itemBean = new BarChartItemBean(year, month, day, smoney);
            list.add(itemBean);
        }
        return list;
    }
}