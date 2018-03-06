package com.example.leesnriud.uilist5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * android 部分UI
 * toast 吐司
 * dialog 对话框
 * popupwindow 悬浮框
 */
public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_toast, R.id.bt_dialog, R.id.bt_popupwindow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_toast:
                intent = new Intent(MainActivity.this,ToastActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_dialog:
                intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_popupwindow:
                break;
        }
    }
}
