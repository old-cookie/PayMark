package vtc.oldcookie.paymark.forum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.forum.myHelper.MyDBAdapter;


public class Bell extends AppCompatActivity {

    private MyDBAdapter myDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bell);

        Toast.makeText(Bell.this, "Alert! Reset\nEvery Login", Toast.LENGTH_LONG).show();

        String nickname = getIntent().getStringExtra("nickname");

        ImageView add = findViewById(R.id.add);

        ImageView home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bell.this, Forum.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bell.this, recommand.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        ImageView settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bell.this, Settings.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        Cursor retCursor = null;
        String[] from = new String[] {"nickname"};
        int[] to = new int[]{R.id.like};

        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this, R.layout.bell_row, retCursor, from, to); //this,layout,record, column, row

        ListView listview = (ListView) findViewById(R.id.listView1);

        listview.setAdapter(cursorAdapter);

        myDBAdapter = new MyDBAdapter(this);

        ImageView delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBAdapter.deleteAllRecords();
                Toast.makeText(Bell.this, "Deleted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Bell.this, Bell.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

        retCursor = myDBAdapter.selectAll(); //set record and log
        cursorAdapter.swapCursor(retCursor); //refresh the record

    }
}