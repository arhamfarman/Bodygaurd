package com.example.bodygaurd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent i = new Intent(getApplicationContext(), MainFragment.class);
//        startActivity(i);

        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }


}


//    TextView textView =(TextView)findViewById(R.id.textView);
//textView.setClickable(true);
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//        String text = "<a href='http://www.google.com'> Google </a>";
//        textView.setText(Html.fromHtml(text));