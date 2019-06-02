package com.example.windowdialogdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    SetWallpaperPopup mSetWallpaperPopup = new SetWallpaperPopup();
    public void clickme(View view){
        mSetWallpaperPopup.showPopupWindow(this);
        mSetWallpaperPopup.setOnItemClicListener(new SetWallpaperPopup.OnItemClicListener() {
            @Override
            public void onItemClick(int state) {
                Toast.makeText(MainActivity.this,"state="+state,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
