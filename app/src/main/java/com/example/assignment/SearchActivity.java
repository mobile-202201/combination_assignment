package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment.DataAdapter;
import com.example.assignment.DataList;
import com.example.assignment.emergence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText dataEditText;
    RecyclerView recyclerView;
    DataAdapter adapter;
    DataList dl;
    ArrayList<DataList> data = new ArrayList<>();
    ArrayList<emergence> emergences = new ArrayList<emergence>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        dataEditText = findViewById(R.id.gson_edittext);
        recyclerView = findViewById(R.id.recyclerview);
        Button button = findViewById(R.id.gson_button);
        Parser();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Parser();

            }
        });
    }

    private void Parser() {
        String json = "";
        String searchdata = "sudu";
        try {
            searchdata = dataEditText.getText().toString();
            if (searchdata.equals("sudu")) {
                searchdata = "Varicella";
            } else if (searchdata.equals("A형간염")) {
                searchdata = "Hepatitis_A";
            }
            InputStream is = getAssets().open("json/Datas.json"); // json파일 이름
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            //json파일명을 가져와서 String 변수에 담음
            json = new String(buffer, "UTF-8");
//            Toast.makeText(this,"json : "+ json, Toast.LENGTH_SHORT).show();
//            textView1.setText(String.format(Locale.KOREAN,"json: "+json));


            JSONObject jsonObject = new JSONObject(json);
            JSONArray Array = jsonObject.getJSONArray(searchdata);
            for (int i = 0; i < Array.length(); i++) {

                JSONObject jsonObject1 = (JSONObject) Array.get(i);

                String name = jsonObject1.getString("name");
                String rate = jsonObject1.getString("rate");
                String def = jsonObject1.getString("def");
                String emergence = jsonObject.getString("emergence");


                dl = new DataList(name, rate, def, emergences);
                data.add(dl);

            }
//            adapter.setItems(dl.emdata);
//            adapter.notifyDataSetChanged();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }
}