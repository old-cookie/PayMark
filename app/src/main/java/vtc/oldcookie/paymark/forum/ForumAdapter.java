package vtc.oldcookie.paymark.forum;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.forum.myHelper.MyDBAdapter;

import java.util.HashSet;
import java.util.Set;

public class ForumAdapter extends ArrayAdapter<ForumHelper> {
    private Context context;
    private int layoutResourceId;
    private ForumHelper[] data;
    private String getnickname = ""; //定義

    private MyDBAdapter myDBAdapter;

    public ForumAdapter(Context context, int layoutResourceId, ForumHelper[] data, String getnickname) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data != null ? data : new ForumHelper[0];
        this.getnickname = getnickname; //初始化
    }

    //set the forum row of listview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ForumHolder holder; //定義ForumHolder

        if (row == null) {
            //get the LayoutInflater from the context in forum_row.xml to display the view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            //create the holder to get the view by id in forum_row.xml(the most under)
            holder = new ForumHolder();
            holder.textarea = row.findViewById(R.id.text);
            holder.dtime = row.findViewById(R.id.date);
            holder.nickname = row.findViewById(R.id.nickname);



            LinearLayout input = row.findViewById(R.id.input);
            input.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ForumInput.class);
                    intent.putExtra("nickname", getnickname);
                    context.startActivity(intent);
                }
            });

            myDBAdapter = new MyDBAdapter(context);

            Set<String> contains = new HashSet<>(); //sem2 thing, cannot repeat by .contains after .add when haven't deleteAll() in MyDBAdapter

            ImageView like = row.findViewById(R.id.like);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nicknamee = holder.nickname.getText().toString();
                    if (!contains.contains(nicknamee)) {
                        contains.add(nicknamee);
                        myDBAdapter.insert("You liked " + nicknamee + " post!");
                        Toast.makeText(context, "Liked!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            ImageView share = row.findViewById(R.id.share);
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("https://mail.google.com");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(i);
                    Toast.makeText(context, "Sending!", Toast.LENGTH_LONG).show();
                }
            });



            row.setTag(holder); //use setTag to add holder to the forum row of listview
        } else {
            holder = (ForumHolder) row.getTag(); //get back the holder if the forum row of listview is repeated
        }

        //get the ForumHelper data in this position(top)
        ForumHelper forumHelper = data[position];
        if (forumHelper != null) {
            holder.textarea.setText(forumHelper.textarea);
            holder.dtime.setText(forumHelper.dtime);
            holder.nickname.setText(forumHelper.nickname);
        }
        return row; //return View row = convertView;
    }

    //靜態定義the holder items' name
    static class ForumHolder {
        TextView textarea;
        TextView dtime;
        TextView nickname;
    }
}