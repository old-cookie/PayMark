package vtc.oldcookie.paymark.forum.myHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE good (_id INTEGER PRIMARY KEY, nickname TEXT)";
        Log.v("myLog", "sql: " + sql);
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql =
                "DROP TABLE IF EXISTS good";
        Log.v("myLog", "sql: " + sql);
        db.execSQL(sql);
        onCreate(db);

    }

}
