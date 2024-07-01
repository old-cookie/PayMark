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
import android.widget.TextView;
import android.widget.Toast;

import vtc.oldcookie.paymark.forum.myHelper.MyDBAdapter;

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

public class Settings extends AppCompatActivity {

    private MyDBAdapter myDBAdapter;

    private EditText rename;
    private Button enter, logout, delete;
    private TextView nickname, ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        rename = findViewById(R.id.rename);
        enter = findViewById(R.id.enter);
        nickname = findViewById(R.id.nickname);
        logout = findViewById(R.id.logout);
        delete = findViewById(R.id.delete);
        ratings = findViewById(R.id.ratings);

        String nicknamee = getIntent().getStringExtra("nickname"); //get nickname
        nickname.setText(nicknamee);

        final String url = "http://100.64.50.2:8080/osmad/myPhp/rating.php";
        new MyAsyncTask().execute(url);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "http://100.64.50.2:8080/osmad/myPhp/rename.php";
                new MyAsyncTask().execute(url);
                Toast.makeText(getApplicationContext(), "Renamed!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.this, login.class);
                startActivity(intent);
            }
        });

        myDBAdapter = new MyDBAdapter(this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBAdapter.deleteAllRecords(); //delete like data
                Intent intent = new Intent(Settings.this, login.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBAdapter.deleteAllRecords(); //delete like data
                final String url = "http://100.64.50.2:8080/osmad/myPhp/delete.php";
                new MyAsyncTask().execute(url);
                Toast.makeText(getApplicationContext(), "Account Deleted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.this, login.class);
                startActivity(intent);
            }
        });

        ImageView home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Forum.class);
                intent.putExtra("nickname", nicknamee);
                startActivity(intent);
            }
        });

        ImageView bell = findViewById(R.id.bell);

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Bell.class);
                intent.putExtra("nickname", nicknamee);
                startActivity(intent);
            }
        });

        ImageView add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, recommand.class);
                intent.putExtra("nickname", nicknamee);
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
            //print result if it's working
            try {
                int count = (int)(Double.parseDouble(result));
                count = count * 500;
                ratings.setText("Ratings:\n" + count);
                ImageView face = findViewById(R.id.face);
                if (count >= 5000) {
                    face.setImageResource(R.drawable.king);
                }
                if (count >= 10000) {
                    face.setImageResource(R.drawable.king2);
                }
            } catch (Exception e) {
                result = "[ERROR] " + e.toString();
                Log.v("myLog", "result: " + result);
            }
        }
    }

    public String executeHttpPost(String url) {
        String result = "";
        String nicknamee = getIntent().getStringExtra("nickname"); //get nickname

        HttpClient client = new DefaultHttpClient();

        HttpPost request = new HttpPost(url);

        //add data to the php file
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        nameValuePairs.add(new BasicNameValuePair("oldname", nicknamee)); //old name
        nameValuePairs.add(new BasicNameValuePair("rename", rename.getText().toString())); //new name

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