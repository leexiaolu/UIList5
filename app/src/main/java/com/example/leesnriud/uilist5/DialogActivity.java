package com.example.leesnriud.uilist5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * android dialog 对话框
 *
 * 使用流程
 * Step 1：创建AlertDialog.Builder对象；
 * Step 2：调用setIcon()设置图标，setTitle()或setCustomTitle()设置标题；
 * Step 3：设置对话框的内容：setMessage()还有其他方法来指定显示的内容；
 * Step 4：调用setPositive/Negative/NeutralButton()设置：确定，取消，中立按钮；
 * Step 5：调用create()方法创建这个对象，再调用show()方法将对话框显示出来；
 *
 */
public class DialogActivity extends AppCompatActivity {

    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_dialog1, R.id.bt_dialog2, R.id.bt_dialog3, R.id.bt_dialog4, R.id.bt_dialog5, R.id.bt_dialog6, R.id.bt_dialog7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_dialog1:
                showOrdinaryDialog();
                break;
            case R.id.bt_dialog2:
                commonListDialog();
                break;
            case R.id.bt_dialog3:
                radiobuttonListDialog();
                break;
            case R.id.bt_dialog4:
                checkboxListDialog();
                break;
            case R.id.bt_dialog5:

                break;
            case R.id.bt_dialog6:

                break;
            case R.id.bt_dialog7:

                break;
        }
    }
    //普通的dialog
    public void showOrdinaryDialog(){
        alert = null;
        builder = new AlertDialog.Builder(DialogActivity.this);
        alert = builder.setIcon(R.mipmap.ic_bug)
                .setTitle("我是标题")
                .setMessage("我是提示信息")
                .setNegativeButton("取消",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"点击了取消",Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"点击了确定",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("中立",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"点击了中立",Toast.LENGTH_SHORT).show();
                    }
                }).create();
        alert.show();
    }

    //普通列表对话框
    public void commonListDialog(){
        final String[] person = new String[] {"唐三藏","孙悟空","猪八戒","沙和尚"};
        alert = null;
        builder = new AlertDialog.Builder(DialogActivity.this);
        alert = builder.setIcon(R.mipmap.ic_bug)
                .setTitle("选择你喜欢的人物")
                .setItems(person, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"你选择的是"+person[i],Toast.LENGTH_SHORT).show();
                    }
                }).create();
        alert.show();
    }

    //单选列表对话框
    public void radiobuttonListDialog(){
        final String[] num = new String[]{"1","2","3","4","5"};
        alert = null;
        builder = new AlertDialog.Builder(DialogActivity.this);
        alert = builder.setIcon(R.mipmap.ic_bug)
                .setTitle("选择你喜欢的一个数字")
                .setSingleChoiceItems(num, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"你喜欢的数字是"+num[i],Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alert.show();
    }

    //多选列表对话框
    public void checkboxListDialog(){
        final String[] menu = new String[]{"111","222","333","444"};
        checkItems = new boolean[]{false,false,false,false};
        alert = null;
        builder = new AlertDialog.Builder(DialogActivity.this);
        alert = builder.setIcon(R.mipmap.ic_bug)
                .setMultiChoiceItems(menu, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        checkItems[i] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String result = "";
                        for (int i = 0; i < checkItems.length; i++) {
                            if (checkItems[i])
                                result += menu[i] + " ";
                        }
                        Toast.makeText(DialogActivity.this, "你点了:" + result, Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alert.show();
    }

    //进度条对话框
    public void progressbarDialog(){

    }

    //日期选择对话框
    public void timeDialog(){

    }

    //自定义对话框
    public void customDialog(){

    }

}
