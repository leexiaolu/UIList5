package com.example.leesnriud.uilist5;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

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
    private View custom_dialog;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private ProgressDialog pd2;
    private final static int MAXVALUE = 100;
    private int progressStart = 0;
    private int add = 0;
    private String result;


    //定义一个用于更新进度的Handler,因为只能由主线程更新界面,所以要用Handler传递信息
    final Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            //这里的话如果接受到信息码是123
            if(msg.what == 123)
            {
                //设置进度条的当前值
                pd2.setProgress(progressStart);
            }
            //如果当前大于或等于进度条的最大值,调用dismiss()方法关闭对话框
            if(progressStart >= MAXVALUE)
            {
                pd2.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_dialog1, R.id.bt_dialog2, R.id.bt_dialog3, R.id.bt_dialog4, R.id.bt_dialog5, R.id.bt_dialog6, R.id.bt_dialog7,R.id.bt_dialog8})
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
                progressbarDialog();
                break;
            case R.id.bt_dialog6:
                datepickerDialog();
                break;
            case R.id.bt_dialog7:
                timepickerDialog();
                break;
            case R.id.bt_dialog8:
                customDialog();
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
        //初始化
        progressStart = 0;
        add = 0;
        //设置一些属性
        pd2 = new ProgressDialog(DialogActivity.this);
        pd2.setMax(MAXVALUE);
        pd2.setTitle("下载中");
        pd2.setMessage("文件下载中,请稍后...");
        //设置为不可以通过按取消按钮关闭进度条
        pd2.setCancelable(false);
        pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置的是是否显示进度,设为false才是显示的！！！
        pd2.setIndeterminate(false);
        pd2.show();
        //新建一个线程,重写run()方法
        new Thread()
        {
            public void run()
            {
                while(progressStart < MAXVALUE)
                {
                    //这里是决定进度条变化
                    progressStart = 2 * usetime() ;
                    //把信息码发送给handle让更新界面
                    handler.sendEmptyMessage(123);
                }
            }
        }.start();
    }

    //日期选择对话框
    public void datepickerDialog(){
        Calendar cale1 = Calendar.getInstance();
        new DatePickerDialog(DialogActivity.this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                //这里获取到的月份需要加上1
                result += "你选择的是"+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日";
                Toast.makeText(DialogActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
                ,cale1.get(Calendar.YEAR)
                ,cale1.get(Calendar.MONTH)
                ,cale1.get(Calendar.DAY_OF_MONTH)).show();
    }

    //时间选择对话框
    public void timepickerDialog(){
        Calendar cale2 = Calendar.getInstance();
        new TimePickerDialog(DialogActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                result = "";
                result += "您选择的时间是:"+hourOfDay+"点"+minute+"分";
                Toast.makeText(DialogActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
    }

    //自定义对话框
    public void customDialog(){
        alert = null;
        builder = new AlertDialog.Builder(DialogActivity.this);
        final LayoutInflater inflater = getLayoutInflater();
        custom_dialog = inflater.inflate(R.layout.view_dialog_custom,null,false);
        builder.setView(custom_dialog);
        builder.setCancelable(false);
        alert = builder.create();
        alert.show();

        custom_dialog.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        custom_dialog.findViewById(R.id.btn_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
            }
        });

        custom_dialog.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                alert.dismiss();
            }
        });
    }

    //设置一个耗时的方法:
    private int usetime() {
        add++;
        try{
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return add;
    }
}
