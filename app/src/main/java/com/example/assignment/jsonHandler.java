package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class jsonHandler extends AppCompatActivity {

    ArrayList<String> KeyList = new ArrayList<>(); //질병명 이름이 담긴 배열
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String json = "";

        try {
            InputStream is = getAssets().open("json/data_pre.json"); // json파일 이름
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            //json파일명을 가져와서 String 변수에 담음
            json = new String(buffer, "UTF-8");

            jsonObject = new JSONObject(json);
            Iterator it = jsonObject.keys(); //질병명이 담긴 배열 추출
            while (it.hasNext()) {
                String b = it.next().toString();
                KeyList.add(b); //KeyList라는 리스트에 질병명을 담음
            }

            //배열로된 자료를 가져올때
            //지정한 인덱스의 맞는 배열을 가져옴
            //JSONArray Array = jsonObject.getJSONArray(KeyList[0].toString());//배열의 이름
//            JSONObject Object = Array.getJSONObject(0);
//            textView1.setText(String.format(Locale.KOREAN,"name is: "+ Object.getString(("title"))));
            //**********************
            //검출할 질병의 인덱스명 지정
//            JSONObject Object = Array.getJSONObject(0);
//            //**********************
//            String name_D = Object.get("name").toString();
//            String rate_D = Object.get("name").toString();
//            String def_d = Object.get("name").toString();
//
//            String reg_d = Object.getJSONArray("emergence").get(0).toString(); // 지역
//            String parent_d = Object.getJSONArray("emergence").get(1).toString(); // 환자수
//            String hosp_d = Object.getJSONArray("emergence").get(0).toString(); // 병원명
//            String add_d = Object.getJSONArray("emergence").get(3).toString(); // 주소
//            String lat_d = Object.getJSONArray("emergence").get(2).toString(); // 위도
//            String lng_d = Object.getJSONArray("emergence").get(4).toString(); // 경도
//            String OH_d = Object.getJSONArray("emergence").get(5).toString(); // 운영시간
//
//
//            for(int i=0; i<Array.length(); i++)
//            {
//                //Object는 varicella 내부의 정보(정의 및 병원 정보)
//                JSONObject Object = Array.getJSONObject(i);
//                JSONArray Array2 = Object.getJSONArray("emergence");
//                for(int j=0;j<Array2.length();j++){
//                    //Object2는 각 질병의 병원 정보 값
//                    JSONObject Object2 = Array2.getJSONObject(j);
//                    String hospital_name = Object2.getString("hospital_name");
//                }
//            }


        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }

    }

    public String[] getDisease(String Disease_n) throws JSONException {
        int num = KeyList.indexOf(Disease_n);

        //배열로된 자료를 가져올때
        //지정한 인덱스의 맞는 배열을 가져옴
        JSONArray Array = jsonObject.getJSONArray(KeyList.get(num).toString());//배열의 이름
//            JSONObject Object = Array.getJSONObject(0);
//            textView1.setText(String.format(Locale.KOREAN,"name is: "+ Object.getString(("title"))));
        //**********************
        //검출할 질병의 인덱스명 지정
        JSONObject Object = Array.getJSONObject(0);
        //**********************
        String name_D = Object.get("name").toString();
        String rate_D = Object.get("name").toString();
        String def_d = Object.get("name").toString();

        String reg_d = Object.getJSONArray("emergence").get(0).toString(); // 지역
        String parent_d = Object.getJSONArray("emergence").get(1).toString(); // 환자수
        String hosp_d = Object.getJSONArray("emergence").get(0).toString(); // 병원명
        String add_d = Object.getJSONArray("emergence").get(3).toString(); // 주소
        String lat_d = Object.getJSONArray("emergence").get(2).toString(); // 위도
        String lng_d = Object.getJSONArray("emergence").get(4).toString(); // 경도
        String OH_d = Object.getJSONArray("emergence").get(5).toString(); // 운영시간

        String disease_info[]= new String[]{name_D, rate_D, def_d, reg_d, parent_d, hosp_d, add_d, lat_d, lng_d, OH_d};
        return disease_info;
    }
}

