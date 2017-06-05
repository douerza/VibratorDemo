# Android Vibrator使用
Vibrator是Android提供的一个手机振动服务。<br>
手机振动是很多场合需要用到的功能，如消息提示，游戏等。<br>
Vibrator主要提供了以下几种常用方法：<br>

 - abstract void cancel()
 - abstract boolean hasVibrator()
 - void vibrate(long milliseconds)
 - void vibrate(long[] pattern,int repeat)
 
第一个cancel()的方法一目了然，是取消振动。<br>
第二个hasVibrator()是用来判断手机是否存在振动器。<br>
第三个和第四个vibrate()用来控制振动。<br>

- vibrate(long milliseconds)的参数milliseconds是表示手机振动milliseconds毫秒。<br>
- vibrate(long[] pattern,int repeat)中的long[] pattern表示振动模式，比如，pattern为new int[100,200,300,400]，就是表示以100ms，200ms，300ms，400ms交替启动和关闭振动。<br>
repeat虽然中文翻译为重复，但表示的不仅是重复，还表示从pattern数组开始循环的位置。如果repeat为-1，则表示按数组表示的频率振动一次，不重复。repeat为0，则表示从pattern[0]开始，以100ms振动，停止200ms，振动300ms，停止400ms的模式来循环振动。repeat为1，则表示pattern[1]开始振动，即200ms振动，300ms停止以此类推。<br>

接下来就在程序中应用这些方法。先预想下要用vibrate实现什么样的效果。<br>

- 点击振动：vibrate(long milliseconds)，milliseconds可自行定义，这里设置为100，即点击振动100毫秒。
- 短振动一次：vibrate(long[] pattern,int repeat)，振动一次，则repeat设置为-1，pattern可自行定义，这里设置为new long[]{100, 200, 100, 200}，即振动100ms，停止200毫秒，振动100ms，停止200ms。
- 循环短振动：vibrate(new long[]{100, 200, 100, 200},0)，除了repeat设为0表示循环振动，振动节奏与上一致。
- 长振动一次：vibrate(new long[]{100, 100, 100, 1000}, -1)，将振动时间调长些。
- 循环长振动：vibrate(new long[]{100, 100, 100, 1000}, 0);
- 其他节奏振动：vibrate(new long[]{500, 100, 500, 100, 500, 100}, 0)。

开始布局：<br>
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.example.douzaer.vibratordemo.MainActivity">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:orientation="vertical">
        <Button
            android:id="@+id/buttonOnce"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="点击振动"
            android:onClick="OnceVibration">
        </Button>
        <Button
            android:id="@+id/buttonShort1"
            android:layout_height="wrap_content"
            android:layout_width="match_parent"
            android:text="短振动"
            android:onClick="ShortVibration1">
        </Button>
        <Button
            android:id="@+id/buttonShort2"
            android:layout_height="wrap_content"
            android:layout_width="match_parent"
            android:text="循环短振动"
            android:onClick="ShortVibration2">
        </Button>
        <Button
            android:id="@+id/buttonLong1"
            android:layout_height="wrap_content"
            android:layout_width="match_parent"
            android:text="长振动"
            android:onClick="LongVibration1">
        </Button>
        <Button
            android:id="@+id/buttonLong2"
            android:layout_height="wrap_content"
            android:layout_width="match_parent"
            android:text="循环长振动"
            android:onClick="LongVibration2">
        </Button>
        <Button
            android:id="@+id/buttonRhythm"
            android:layout_height="wrap_content"
            android:layout_width="match_parent"
            android:text="节奏振动"
            android:onClick="RhythmVibration">
        </Button>
    </LinearLayout>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
        <Button
            android:id="@+id/buttonIs"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:layout_weight="1"
            android:text="是否存在振动器"
            android:onClick="IsVibration">
        </Button>
        <Button
            android:id="@+id/buttonCancel"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:layout_weight="1"
            android:text="取消振动"
            android:onClick="CancelVibration">
        </Button>
    </LinearLayout>
</LinearLayout>

```
布局效果：<br>
![layout](https://raw.githubusercontent.com/douerza/picture/master/VibratorDemo/layout.png)<br>
首先要在AndroidManifest中添加手机振动的权限：<br>
```
<uses-permission android:name="android.permission.VIBRATE"/>
```
MainActivity.java
```
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

```
由于振动要在真机才能感受到，这里就不截图表示了。<br>
Demo代码：[VibratorDemo](https://github.com/douerza/VibratorDemo)<br>
