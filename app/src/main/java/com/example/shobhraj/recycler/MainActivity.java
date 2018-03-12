package com.example.shobhraj.recycler;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static  final String URL_DATA="https://github.com/snowden141/demo/blob/master/db.json";
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    Context mContext;
    private List<listItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyAdapter(new ArrayList<listItem>(),mContext);
        recyclerView.setAdapter(adapter);
        listItems = new ArrayList<>();

        /*for(int i=0;i<=10;i++)
        {
            listItem listItem = new listItem(
                    "heading" + i,
                    "my input dumy text"
                    //"nothing much !!"
            );
            listItems.add(listItem);
        }

        adapter=new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);*/
       loadRecyclerViewData();
    }

    public String ConvertStreamToString(InputStream is){

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = reader.readLine())!=null){
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

    public void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data...");
        progressDialog.show();

//        InputStream is = null;
        String json = "";
        try {
            InputStream is = getAssets().open("JSON_file.json");
            json = ConvertStreamToString(is);
        } catch (IOException e1) {
            Log.d("Hello", e1.toString());
//            Log.d("Hello", "Error here");
            e1.printStackTrace();

        }
        progressDialog.dismiss();
//            adapter = new MyAdapter(listItems,mContext);
//            recyclerView.setAdapter(adapter);



        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            Log.d("Hello",json);
            JSONArray jsonArray = jsonObject.getJSONArray("phoneNumber");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                listItem item = new listItem(
                        o.getString("type"),
                        o.getString("num")
                );
                listItems.add(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.add(listItems);


    }
}
