package vtc.oldcookie.paymark.forum;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.forum.myHelper.MyDBAdapter;
import com.google.android.material.navigation.NavigationBarView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Forum extends AppCompatActivity {
    private ListView listView;
    private ForumAdapter adapter;
    private String choice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);

        String nickname = getIntent().getStringExtra("nickname"); //get nickname

        listView = findViewById(R.id.listView1);

        final String url = "http://100.64.50.2:8080/osmad/myPhp/forum.php";

        ImageView add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forum.this, recommand.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        Spinner spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
                new MyAsyncTask().execute(url + "?category=" + choice); //GET request receive data using ?
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ImageView bell = findViewById(R.id.bell);

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forum.this, Bell.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        ImageView settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forum.this, Settings.class);
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
                String nickname = getIntent().getStringExtra("nickname"); //get nickname
                adapter = new ForumAdapter(Forum.this, R.layout.forum_row, result, nickname); //create & call ForumAdapter java main name method
                listView.setAdapter(adapter); // Set adapter for the listview about the forum content in ForumAdapter java
        }
    }

    private ForumHelper[] executeHttpGet(String url) {
        String result = "";

        HttpClient client = new DefaultHttpClient();

        HttpGet request = new HttpGet(url);

        HttpResponse response;

        try {
            response = client.execute(request);

            result = EntityUtils.toString(response.getEntity()); //result String is json format

            JSONArray JSONArray = new JSONArray(result); //將result轉換為JSONArray
            ForumHelper[] recommend = new ForumHelper[JSONArray.length()]; // 使用JSONArray的size初始化ForumHelper的array

            //JSONArray的size for loop
            for (int i = 0; i < JSONArray.length(); i++) {
                JSONObject JSONObject = JSONArray.getJSONObject(i); //get JSONObject from JSONArray(一齊用)
                recommend[i] = new ForumHelper(JSONObject.getString("textarea"), JSONObject.getString("dtime"), JSONObject.getString("nickname"));
                //create & call ForumHelper的array java main name method by using JSONObject and set to the String
            }
            return recommend; //return the recommend array to the onPostExecute result in ForumAdapter java
        } catch (Exception e) {
            result = "[ERROR] " + e.toString();

            Log.v("myLog", "result: " + result);

            return new ForumHelper[0]; //return the null array
        }
    }
}