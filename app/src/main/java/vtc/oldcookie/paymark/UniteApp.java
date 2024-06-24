package vtc.oldcookie.paymark;

import android.app.Application;

import vtc.oldcookie.paymark.db.DBManager;

/**
 * Application class for the PayMarkV2 application.
 * This class is used to initialize the database when the application starts.
 * LAU Cho Cheuk
 */
public class UniteApp extends Application {
    /**
     * Called when the application is starting, before any other application objects have been created.
     * This is where most initialization should go.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the database
        DBManager.initDB(getApplicationContext());
    }
}