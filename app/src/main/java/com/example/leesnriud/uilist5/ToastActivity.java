package com.example.leesnriud.uilist5;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * android toast 吐司
 *
 */
public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_toast1, R.id.bt_toast2, R.id.bt_toast3,R.id.bt_toast4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_toast1:
                Toast.makeText(ToastActivity.this,"普通的toast",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_toast2:
                midToast(ToastActivity.this,"我的颜色位置变了",1);
                break;
            case R.id.bt_toast3:
                imageToast(ToastActivity.this,"快看有图",1);
                break;
            case R.id.bt_toast4:
                customToast(ToastActivity.this,"我是自定义的",1);
                break;
        }
    }

    //修改toast效果
    public void midToast(Context context,String str, int showTime){
        Toast toast = Toast.makeText(context, str, showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL , 0, 0);  //设置显示位置
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.YELLOW);     //设置字体颜色
        toast.show();
    }
    //带图片的toast效果
    public void imageToast(Context context,String str,int showTime){
        Toast toast = Toast.makeText(context, str, showTime);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM , 0, 0);  //设置显示位置
        LinearLayout layout = (LinearLayout) toast.getView();
        layout.setBackgroundColor(Color.BLUE);
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.ic_bug);
        layout.addView(image, 0);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);     //设置字体颜色
        toast.show();
    }
    //完全自定义的toast效果
    public void customToast(Context context,String str,int showTime){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.view_toast_custom,
                (ViewGroup) findViewById(R.id.lly_toast));
        ImageView img_logo = (ImageView) view.findViewById(R.id.img_logo);    //可以动态设置图片
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        tv_msg.setText(str);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(showTime);
        toast.setView(view);
        toast.show();
    }
}
