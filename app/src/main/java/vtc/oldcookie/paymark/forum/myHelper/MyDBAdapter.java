package vtc.oldcookie.paymark.forum.myHelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class MyDBAdapter {
    private static String DB_NAME = "good_db";
    private static int DB_VERSION = 1;

    private Context context;
    private SQLiteOpenHelper myDBHelper;

    public MyDBAdapter(Context context) {
        this.context = context;
        this.myDBHelper = new MyDBHelper(this.context, DB_NAME, null, DB_VERSION);
    }

    public long insert(String nickname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nickname", nickname);

        SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();

        long numRows = sqlDB.insert("good", null, contentValues);
        sqlDB.close();

        return numRows;
    }


    public Cursor selectAll() {
        SQLiteDatabase sqlDB = myDBHelper.getReadableDatabase();

        Cursor cursor = sqlDB.query("good", new String[]{"_id", "nickname"},
                null, null, null, null, null);

        displayCursor(cursor);
        sqlDB.close();
        return cursor;
    }

    private void displayCursor(Cursor cursor) {
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String id = cursor.getString(0);
            String nickname = cursor.getString(1);


            Log.v("nickname", "Id: " + id + "Nickname: " + nickname);
            cursor.moveToNext();
        }
    }

    public void deleteAllRecords(){
        SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();

        sqlDB.delete("good", null,
                null);
        sqlDB.close();

    }


}





