package com.example.json1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {
    public static  String rate;
    public static  String def;
    public static  String region;
    public static  String patients;
    public static  String hospital_name;
    public static  String hospital_address;
    public static  String latitude;
    public static  String longitude;
    public static  String OH;
    public TextView textTitle,textDetail,GPSlocation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentA.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentA newInstance(String param1, String param2) {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        String json = "";
        try {
            InputStream is = getContext().getAssets().open("json/Data.json"); // json파일 이름
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            //json파일명을 가져와서 String 변수에 담음
            json = new String(buffer, "UTF-8");
            JSONObject jsonObjectA = new JSONObject(json);
            JSONArray Array2 = jsonObjectA.getJSONArray("A형간염");//배열의 이름
            JSONObject ObjectAG = Array2.getJSONObject(0);
            rate = ObjectAG.getString("rate");
            def =ObjectAG.getString("def");
            region = ObjectAG.getString("region");
            patients = ObjectAG.getString("patients");
            hospital_name= ObjectAG.getString("patients");
            hospital_address= ObjectAG.getString("hospital_address");
            latitude= ObjectAG.getString("latitude");
            longitude= ObjectAG.getString("longitude");
            OH= ObjectAG.getString("OH");



        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        textTitle = v.findViewById(R.id.textTitle1);
        textTitle.setText("A형간염" + rate);
        textDetail = v.findViewById(R.id.textDetail1);
        textDetail.setText("[정의]" + def);
        GPSlocation = v.findViewById(R.id.textDetail1);
        GPSlocation.setText("[위도경도]" + longitude + latitude);
        container.addView(v);

        return v;

    }}
