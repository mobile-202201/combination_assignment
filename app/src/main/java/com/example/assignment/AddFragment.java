package com.example.assignment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.assignment.AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static com.example.assignment.AddFragment newInstance(String param1, String param2) {
        com.example.assignment.AddFragment fragment = new com.example.assignment.AddFragment();
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


    }

    //spinner??????
    private Spinner spinner;
    private String[] items = {"??????", "A?????????", "?????????????????????", "C?????????", "??????????????????"};

    private String spinnerSelected = ""; //??????????????? ????????? ?????? ??????

    private String region = ""; //??????
    private int patients = 0; //????????????
    private String hospital_name = ""; //?????????
    private String hospital_address = ""; //????????????
    private double latitude = 0;//??????
    private double longitude = 0;//??????
    private String oH = "";//????????????



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);



        EditText editArea = (EditText) v.findViewById(R.id.area);

        EditText editPersonnel = (EditText) v.findViewById(R.id.personnel);

        EditText editHospitalname = (EditText) v.findViewById(R.id.hospitalname);

        EditText editHospitallocation = (EditText) v.findViewById(R.id.hospitallocation);

        EditText editlatitude = (EditText) v.findViewById(R.id.latitude);

        EditText editlongitude = (EditText) v.findViewById(R.id.longitude);

        EditText editopentime = (EditText) v.findViewById(R.id.opentime);




        //????????? ?????????
        spinner = v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button mybutton = v.findViewById(R.id.Fragment_add_Button); //??????????????? ????????? ????????? ????????? ??? onClick()???????????? ??????????????? ????????? ???.

        mybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //????????? ?????? ????????? ??????
                int pos1 = spinner.getSelectedItemPosition();
                //????????? ?????? ????????? ??????
                spinnerSelected = items[pos1];



                region = editArea.getText().toString();
                patients = Integer.parseInt(editPersonnel.getText().toString());
                hospital_name = editHospitalname.getText().toString();
                hospital_address = editHospitallocation.getText().toString();
                latitude = Double.parseDouble(editlatitude.getText().toString());
                longitude = Double.parseDouble(editlongitude.getText().toString());
                oH = editopentime.getText().toString();

                String rs = String.format("{\"region\":%s,\"patients\":%d,\"hospital_name\":%s,\"hospital_address\":%s,\"latitude\":%f,\"longitude\":%f,\"OH\":%s}", region, patients, hospital_name, hospital_address, latitude, longitude, oH);

                Activity activity = getActivity();
                ((DataSend)activity).onDataSend(rs, spinnerSelected);    //?????????????????? ?????? ??????????????? ??????.






                //getActivity()??? ?????????????????? ??????????????? ???.
                Toast.makeText(getActivity(), "????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
            }
        });


        return v;

    }

    public interface DataSend{
        void onDataSend(String data1, String data2);
    }

    private DataSend mDataSend;



    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(getActivity() != null && getActivity() instanceof DataSend){
            mDataSend = (DataSend) getActivity();
        }
    }



}