package com.example.douzaer.vibratordemo;

import android.app.Service;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //声明一个Vibrator实例
    private Vibrator myVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取系统Vibrator
        myVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
    }

    //点击振动100毫秒
    public void OnceVibration(View view){
        myVibrator.cancel();
        myVibrator.vibrate(100);
    }

    //点击循环短振动
    public void ShortVibration1(View view){
        myVibrator.cancel();
        myVibrator.vibrate(new long[]{100, 200, 100, 200}, -1);
        Toast.makeText(MainActivity.this,"Short",Toast.LENGTH_SHORT).show();
    }
    //点击循环短振动
    public void ShortVibration2(View view){
        myVibrator.cancel();
        myVibrator.vibrate(new long[]{100, 200, 100, 200}, 0);
        Toast.makeText(MainActivity.this,"Short",Toast.LENGTH_SHORT).show();
    }

    //点击长振动
    public void LongVibration1(View view){
        myVibrator.cancel();
        myVibrator.vibrate(new long[]{100, 100, 100, 1000}, -1);
        Toast.makeText(MainActivity.this,"Long",Toast.LENGTH_SHORT).show();
    }
    //点击循环长振动
    public void LongVibration2(View view){
        myVibrator.cancel();
        myVibrator.vibrate(new long[]{100, 100, 100, 1000}, 0);
        Toast.makeText(MainActivity.this,"Long",Toast.LENGTH_SHORT).show();
    }

    //节奏振动
    public void RhythmVibration(View view){
        myVibrator.cancel();
        myVibrator.vibrate(new long[]{500, 100, 500, 100, 500, 100}, 0);
        Toast.makeText(MainActivity.this,"Rhythm",Toast.LENGTH_SHORT).show();
    }

    //是否有振动器
    public void IsVibration(View view){
        Toast.makeText(MainActivity.this, myVibrator.hasVibrator() ? "存在" : "不存在",
                Toast.LENGTH_SHORT).show();
    }

    //取消振动
    public void CancelVibration(View view){
        myVibrator.cancel();
        Toast.makeText(MainActivity.this,"CancelVibration",Toast.LENGTH_SHORT).show();
    }

}
