package vtc.oldcookie.paymark.forum;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.R;

public class ForumInput extends AppCompatActivity {
    private ListView listView;
    private ForumAdapter adapter;
    private ImageView send;
    private EditText typein;
    private String choice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_input);

        send = findViewById(R.id.send);
        typein = findViewById(R.id.typein);

        String nickname = getIntent().getStringExtra("nickname");

        listView = findViewById(R.id.listView1);

        final String url = "http://100.64.50.2:8080/osmad/myPhp/forum.php";

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "http://100.64.50.2:8080/osmad/myPhp/recommend.php";
                new MyAsyncTask().execute(url);
                Intent intent = new Intent(ForumInput.this, Forum.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
                Toast.makeText(ForumInput.this, "Successful", Toast.LENGTH_LONG).show();
            }
        });

        ImageView add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumInput.this, recommand.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        Spinner spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
                new MyAsyncTask().execute(url + "?category=" + choice);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ImageView home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumInput.this, Forum.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        ImageView bell = findViewById(R.id.bell);

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumInput.this, Bell.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        ImageView settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumInput.this, Settings.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        ImageView orderby = findViewById(R.id.orderby);
        orderby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute(url + "?category=" + choice + "&orderby=" + "desc"); //GET request receive data using ? (send cannot have space)
            }
        });

        ImageView enter = findViewById(R.id.enter);
        EditText search = findViewById(R.id.search);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = search.getText().toString();
                if (!searchText.contains(" ")){
                    new MyAsyncTask().execute(url + "?category=" + choice + "&search=" + searchText); //GET request receive data using ? (send cannot have space)
                    search.setText("");
                }
            }
        });

    }

    private class MyAsyncTask extends AsyncTask<String, Void, ForumHelper[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Connecting to the server", Toast.LENGTH_LONG).show();
        }

        @Override
        protected ForumHelper[] doInBackground(String... strings) {
            return executeHttpGet(strings[0]);
        }

        @Override
        protected void onPostExecute(ForumHelper[] result) {
            super.onPostExecute(result);
            String nickname = getIntent().getStringExtra("nickname");
            adapter = new ForumAdapter(ForumInput.this, R.layout.forum_row, result, nickname);
            listView.setAdapter(adapter);
        }
    }

    private ForumHelper[] executeHttpGet(String url) {
        String result = "";
        String nickname = getIntent().getStringExtra("nickname");

        HttpClient client = new DefaultHttpClient();

        HttpPost request = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);

        nameValuePairs.add(new BasicNameValuePair("category", choice));
        nameValuePairs.add(new BasicNameValuePair("textarea", typein.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("nickname", nickname));

        HttpResponse response;

        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            response = client.execute(request);

            result = EntityUtils.toString(response.getEntity());

            JSONArray JSONArray = new JSONArray(result);
            ForumHelper[] recommend = new ForumHelper[JSONArray.length()];

            for (int i = 0; i < JSONArray.length(); i++) {
                JSONObject JSONObject = JSONArray.getJSONObject(i);
                recommend[i] = new ForumHelper(JSONObject.getString("textarea"), JSONObject.getString("dtime"), JSONObject.getString("nickname"));
            }
            return recommend;
        } catch (Exception e) {
            result = "[ERROR] " + e.toString();

            Log.v("myLog", "result: " + result);

            return new ForumHelper[0];
        }
    }
}