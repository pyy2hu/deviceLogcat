package com.pyy2hu.devicelogcat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class keySet extends Activity {
    static final String Tag = "[deviceLogcat]";
    public ArrayList<keyWord> mKwords = new ArrayList<keyWord>() ;
    private TextView mTextView;
    private EditText mEditText;
    private Button mButton1;
    private Button mButton2;
    String textViewStr = new String("");
    static boolean reGetKeyWord = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_set);

        //取得TextView, EditText
        mTextView = (TextView)findViewById(R.id.myTextView);
        mEditText = (EditText)findViewById(R.id.myEditText);
        mButton1 = (Button)findViewById(R.id.myButton1);
        mButton2 = (Button)findViewById(R.id.myButton2);


        mButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reGetKeyWord) {
                    mTextView.setText("已输入的关键字");
                    reGetKeyWord = false;
                    mButton2.setEnabled(true);
                }
                keyWord tempWord = new keyWord();
                tempWord.keywords = mEditText.getText().toString();
                mKwords.add(tempWord);
                tempWord.print();
                mEditText.setText("");
            }
        });

        mButton2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStr = "关键字有: \n";
                Iterator it = mKwords.iterator();
                while(it.hasNext()) {
                    keyWord temp = (keyWord)it.next();
                    textViewStr = textViewStr.concat(temp.keywords);
                    textViewStr = textViewStr.concat("\n");
                }
                //Log.i(Tag, "textViewStr " + textViewStr);
                mTextView.setText(textViewStr);

                textViewStr = "";
                mKwords.clear();
                reGetKeyWord = true;
                mButton2.setEnabled(false);
            }
        });

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //if (reGetKeyWord) {
                //    mTextView.setText("已输入的关键字");
                //    reGetKeyWord = false;
               //}
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_key_set, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.all_log) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
