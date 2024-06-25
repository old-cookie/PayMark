package vtc.oldcookie.paymark;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * This class represents a web activity in the application.
 * It extends AppCompatActivity, which is a base class for activities that use the support library action bar features.
 */
public class webActivity extends AppCompatActivity {

    /**
     * This method is called when the activity is starting.
     * It is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        // Set the user interface layout for this activity
        setContentView(R.layout.activity_web);
        // Set a listener to receive callbacks when the insets for this view have changed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Get the system window insets
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Set the padding of the view
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the WebView by its ID
        WebView webView = findViewById(R.id.web);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        // Set a WebViewClient for the WebView
        webView.setWebViewClient(new WebViewClient());
        // Load a web page into the WebView
        webView.setInitialScale(150);
        webView.loadUrl("https://old-cookie.github.io/KnowingFish.html");
    }
}