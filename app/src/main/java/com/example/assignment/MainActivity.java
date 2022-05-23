package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(savedInstanceState == null){ //매번 앱을 처음 실행할 때 마다 다시 로그인 하기
            SharedPreferences prefs = getSharedPreferences("person_info", 0);
            SharedPreferences.Editor editor = prefs.edit();

            editor.clear();
            editor.commit();
        }
    }

    @Override
    protected void onStart() {  //로그인 되면 메뉴바에 로그인 텍스트 로그아웃으로 바꾸기 + 질병 검색 띄우기
        if(loginChecked()){
//            MenuItem login_menu = menu.findItem(R.id.login);
//            login_menu.setTitle("로그아웃");

        }
        super.onStart();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        if (loginChecked()){
            menu.add(Menu.NONE,3,Menu.NONE,"질병 검색");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.login:    //로그인 액티비티 이동
                if(loginChecked()){ //로그인 되어있으면 로그아웃 버튼 사용. 로그아웃 되면 프리퍼런스 값 사라지고 로그인 버튼으로 바뀜.
                    SharedPreferences prefs = getSharedPreferences("person_info", 0);
                    SharedPreferences.Editor editor = prefs.edit();

                    editor.clear();
                    editor.commit();

                    Toast alreadyLogin = Toast.makeText(this.getApplicationContext(), "로그아웃 성공", Toast.LENGTH_SHORT);
                    alreadyLogin.show();

                    menu.findItem(R.id.login).setTitle("로그인");  //메뉴바에 로그아웃 텍스트 로그인으로 다시 바꾸기

                }
                else{
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);

                }
                break;

            case R.id.edit: //질병 정보 추가 편집 액티비티 이동

                if(loginChecked()){ //로그인 정보가 프리퍼런스에 저장되어 있을 때에만 실행
                    intent = new Intent(this, AddActivity.class);
                    startActivity(intent);
                }else{
                    Toast loginToast = Toast.makeText(this.getApplicationContext(),"로그인을 해주세요.",Toast.LENGTH_SHORT);
                    loginToast.show();
                }
                break;

            case 3: //질병 검색
                intent = new Intent(this,SearchActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean loginChecked(){ //로그인 확인 - 로그인 정보가 프리퍼런스에 저장되어 있을 때
        SharedPreferences prefs = getSharedPreferences("person_info", 0);

        String id = prefs.getString("id", "");
        String password = prefs.getString("password", "");
        boolean logincheck = ((id != "" && password != "") ? true : false);
        return logincheck;
    }
}