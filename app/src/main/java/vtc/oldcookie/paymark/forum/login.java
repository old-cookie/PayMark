package vtc.oldcookie.paymark.forum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.MainActivity;
import vtc.oldcookie.paymark.R;

public class login extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton, signup,goback;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        goback = findViewById(R.id.go_back);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "http://100.64.50.2:8080/osmad/myPhp/login.php";
                new MyAsyncTask().execute(url);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, createac.class);
                startActivity(intent);
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Connecting to the server", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return executeLogin(strings[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            //print result(true/false)
            if (result) {
                Intent intent = new Intent(login.this, Forum.class);
                intent.putExtra("nickname", nickname); //here nickname
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean executeLogin(String url) {
        String result = "";

        HttpClient client = new DefaultHttpClient(); //Create HttpClient object

        HttpPost request = new HttpPost(url); //Create HttpPost object with url

        //add data to the php file
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        nameValuePairs.add(new BasicNameValuePair("myemail", username.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("passwordd", password.getText().toString()));

        //定義response from php file
        HttpResponse response;

        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs)); //set the data實體(Entity) from php file

            response = client.execute(request); //execute the request and get the response data

            String Check = EntityUtils.toString(response.getEntity()); //and set to the String

            //Check with startwith
            if (Check.startsWith("success")) {
                nickname = Check.split("\n")[1];//分割 the second row 0 (1)
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            result = "[ERROR] " + e.toString();
            Log.v("myLog", "result: " + result);
        }
        return false;
    }
}