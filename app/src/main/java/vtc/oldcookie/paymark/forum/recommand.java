package vtc.oldcookie.paymark.forum;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.R;

public class recommand extends AppCompatActivity {

    private Spinner category;
    private EditText textarea;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommanddd);

        category = findViewById(R.id.spinner);
        textarea = findViewById(R.id.textarea);
        submit = findViewById(R.id.submit);

        String nickname = getIntent().getStringExtra("nickname"); //get nickname

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "http://100.64.50.2:8080/osmad/myPhp/recommend.php";
                new MyAsyncTask().execute(url);
                Intent intent = new Intent(recommand.this, Forum.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
                Toast.makeText(recommand.this , "Successful", Toast.LENGTH_LONG).show();
            }
        });

        ImageView home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recommand.this, Forum.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        ImageView bell = findViewById(R.id.bell);

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recommand.this, Bell.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        ImageView settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recommand.this, Settings.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });
    }
    private class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Connecting to the server", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            return executeHttpPost(strings[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //print result(nothing)
        }
    }

    public String executeHttpPost(String url) {
        String result = "";
        String nickname = getIntent().getStringExtra("nickname");

        HttpClient client = new DefaultHttpClient();

        HttpPost request = new HttpPost(url);

        //add data to the php file
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);

        nameValuePairs.add(new BasicNameValuePair("category", category.getSelectedItem().toString()));
        nameValuePairs.add(new BasicNameValuePair("textarea", textarea.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("nickname", nickname));

        HttpResponse response;

        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            response = client.execute(request);

            result = EntityUtils.toString(response.getEntity());

        } catch (Exception e) {

            result = "[ERROR] " + e.toString();

            Log.v("myLog", "result: " + result);
        }
        return result;
    }
}
