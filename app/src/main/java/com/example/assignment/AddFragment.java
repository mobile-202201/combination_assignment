package com.example.assignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

    //spinner사용
    private Spinner spinner;
    private String[] items = {"수두", "A형 간염", "유행성 이하선염", "C형 간염", "쯔쯔가무시병"};

    private String spinnerSelected = ""; //스피너에서 선택된 질병 종류

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);



        //스피너 초기화
        spinner = v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button mybutton = v.findViewById(R.id.Fragment_add_Button); //프레그먼트 내에서 버튼을 사용할 땐 onClick()메서드를 오버라이드 해줘야 함.

        mybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //선택된 항목 인덱스 얻기
                int pos1 = spinner.getSelectedItemPosition();
                //선택된 항목 데이터 얻기
                spinnerSelected = items[pos1];

                //getActivity()는 액티비티에서 보여준다는 뜻.
                Toast.makeText(getActivity(), "질병이 추가되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        return v;

    }


}