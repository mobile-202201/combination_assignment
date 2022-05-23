package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


    }

    public void addOnClick(View v){
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new com.example.assignment.AddFragment());
        ft.commit();
    }

    public void editOnClick(View v){
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new com.example.assignment.EditFragment());
        ft.commit();
    }
}