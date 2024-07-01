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

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.R;

public class createac extends AppCompatActivity {

    private EditText nickname, email, password, confirmpassword;
    private Button register, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createac);

        nickname = (EditText) findViewById(R.id.nickname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        register = (Button) findViewById(R.id.register);
        back = (Button) findViewById(R.id.back);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nickname.getText().toString().isEmpty()&&!email.getText().toString().isEmpty() &&
                        !password.getText().toString().isEmpty()&&!confirmpassword.getText().toString().isEmpty()
                        &&password.getText().toString().equals(confirmpassword.getText().toString())) {
                    final String url = "http://100.64.50.2:8080/osmad/myPhp/createac.php";
                    new MyAsyncTask().execute(url);
                    Toast.makeText(getApplicationContext(), "Created AC", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(createac.this, login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "The condition is incorrect so cannot create AC", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createac.this, login.class);
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
            //print result
        }
    }

    public String executeHttpPost(String url) {
        String result = "";

        HttpClient client = new DefaultHttpClient(); //Create HttpClient object

        HttpPost request = new HttpPost(url); //Create HttpPost object with url

        //add data to the php file
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

        nameValuePairs.add(new BasicNameValuePair("nickname", nickname.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("myemail", email.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("passwordd", password.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("conpassword", confirmpassword.getText().toString()));

        //定義response from php file
        HttpResponse response;

        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs)); //set the data實體(Entity) from php file

            response = client.execute(request); //execute the request and get the response data

            result = EntityUtils.toString(response.getEntity()); //and set to the String

        } catch (Exception e) {

            result = "[ERROR] " + e.toString();

            Log.v("myLog", "result: " + result);
        }
        return result;
    }
}