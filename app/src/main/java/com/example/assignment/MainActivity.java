package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (savedInstanceState == null) { //매번 앱을 처음 실행할 때 마다 다시 로그인 하기
            SharedPreferences prefs = getSharedPreferences("person_info", 0);
            SharedPreferences.Editor editor = prefs.edit();

            editor.clear();
            editor.commit();
        }


        File files = new File("/data/data/com.example.assignment/files/test1.txt");
        if (files.exists() == false) {
            try {
                FileOutputStream fos = openFileOutput("test1.txt", Context.MODE_PRIVATE);
                String Rtext = getString(R.string.data);
                fos.write(Rtext.getBytes());
                fos.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // 변경 예정
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //마커
        LatLng position = new LatLng(37.4889432, 127.0876424);
        mMap.addMarker(new MarkerOptions().position(position).title("서울삼성병원")
                .snippet("평일 09:00~16:00/토요일 09:~12:00/일요일 휴무"));

        position = new LatLng(37.291819, 126.996309);
        mMap.addMarker(new MarkerOptions().position(position).title("경기도의료원수원병원")
                .snippet("평일 08:30~17:30/주말&공휴일 휴무(응급실 24시간 진료)"));

        position = new LatLng(35.1990707, 128.5677163);
        mMap.addMarker(new MarkerOptions().position(position).title("창원제일종합병원")
                .snippet("평일 09:00~18:00/토요일 09:00~14:00(응급실 24시간 진료)/일요일 휴무"));

        position = new LatLng(37.3191333, 126.8252651);
        mMap.addMarker(new MarkerOptions().position(position).title("고려대학교 안산병원")
                .snippet("평일 08:30~17:30/토요일 08:30~12:00/일요일 휴무"));

        position = new LatLng(37.336864, 126.7280003);
        mMap.addMarker(new MarkerOptions().position(position).title("시흥 센트럴병원")
                .snippet("평일 08:30~17:30/토요일 08:30~12:30/일요일 휴무"));

        position = new LatLng(37.5795427, 126.9990602);
        mMap.addMarker(new MarkerOptions().position(position).title("서울대학교병원")
                .snippet("매일 08:00~18:00"));

        position = new LatLng(37.4983977, 126.7628536);
        mMap.addMarker(new MarkerOptions().position(position).title("창원제일종합병원")
                .snippet("평일 09:00~17:00/토요일 09:00~13:00/일요일 휴무"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 7));

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        switch (loginChecked()) {
            case 0://로그인 안 된 상태
                inflater.inflate(R.menu.menu_main, menu);
                break;
            case 1://로그인 된 상태(사용자)
                inflater.inflate(R.menu.menu_login, menu);
                break;
            case 2://로그인 된 상태(관리자)
                inflater.inflate(R.menu.menu_admin, menu);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.loginMenu:    //로그인 액티비티 이동
//                if(loginChecked()==1||loginChecked()==2){
//                    SharedPreferences prefs = getSharedPreferences("person_info", 0);
//                    SharedPreferences.Editor editor = prefs.edit();
//
//                    editor.clear();
//                    editor.commit();
//
//                    Toast alreadyLogin = Toast.makeText(this.getApplicationContext(), "로그아웃 성공", Toast.LENGTH_SHORT);
//                    alreadyLogin.show();
//                }else{
//                    intent = new Intent(this, LoginActivity.class);
//                    startActivity(intent);
//                }
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.logoutMenu:
                SharedPreferences prefs = getSharedPreferences("person_info", 0);
                SharedPreferences.Editor editor = prefs.edit();

                editor.clear();
                editor.commit();

                Toast alreadyLogin = Toast.makeText(this.getApplicationContext(), "로그아웃 성공", Toast.LENGTH_SHORT);
                alreadyLogin.show();
                return true;
            case R.id.edit: //질병 정보 추가 편집 액티비티 이동
                intent = new Intent(this, AddActivity.class);
                startActivity(intent);

//                if(loginChecked()){ //로그인 정보가 프리퍼런스에 저장되어 있을 때에만 실행
//                    intent = new Intent(this, AddActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast loginToast = Toast.makeText(this.getApplicationContext(),"로그인을 해주세요.",Toast.LENGTH_SHORT);
//                    loginToast.show();
//                }
                return true;

            case R.id.search: //질병 검색
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public int loginChecked() { //로그인 확인 - 로그인 정보가 프리퍼런스에 저장되어 있을 때
        SharedPreferences prefs = getSharedPreferences("person_info", 0);

        String id = prefs.getString("id", "");
        String password = prefs.getString("password", "");
        if (id.equals("admin") && password.equals("admin")) { // admin 계정이면
            Toast.makeText(this.getApplicationContext(), "id : " + id + "pw: " + password, Toast.LENGTH_SHORT);
            return 2;
        } else if (id != "" && password != "") { //admin이 아닌 계정으로 로그인이 되면
            return 1;
        }
        return 0; //로그인이 안 되면
    }

}