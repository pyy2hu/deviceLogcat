package com.pyy2hu.devicelogcat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by pyy on 2015/5/6.
 */
public class logcatShow extends Activity {
    static final String Tag = "[deviceLogcat]";
    static boolean isGrep = false;
    //public ArrayList<devLogInfo> mKwords = new ArrayList<devLogInfo>() ;
    private TextView mTextView;
    private Button mButton1;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logcat_show);

        mTextView = (TextView)findViewById(R.id.logText);
        mButton1 = (Button)findViewById(R.id.clearButton);
        mButton2 = (Button)findViewById(R.id.saveButton);
        Bundle bundle = this.getIntent().getExtras();
        String grepStr = bundle.getString("grep");
        if (!grepStr.isEmpty() && grepStr.compareTo(" ") != 0) {
            isGrep = true;
        }
        try {
            Process process = Runtime.getRuntime().exec("logcat -dv threadtime");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            StringBuilder log=new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isGrep) {
                    if (line.indexOf(grepStr) != -1) {
                        log.append(line);
                        log.append("\n");
                    }
                } else {
                    log.append(line);
                    log.append("\n");
                }
            }
            mTextView.setText(log.toString());
        } catch (IOException e) {
            Log.e(Tag, "IO Exception");
        }

        mButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Runtime.getRuntime().exec("logcat -c");
                } catch(Exception e) {
                    Log.e(Tag, "Find Exception");
                }
                Intent intent = new Intent();
                intent.setClass(logcatShow.this, keySet.class);
                startActivity(intent);
                logcatShow.this.finish();
            }
        });

    }


}
