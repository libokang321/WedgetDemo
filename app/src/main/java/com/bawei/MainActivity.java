package com.bawei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button clear, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        clear = findViewById(R.id.clear);
        //添加
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView dotRondum = findViewById(R.id.dotRondum);
                dotRondum.addDot();
            }
        });
        //清除
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView dotRondum = findViewById(R.id.dotRondum);
                dotRondum.clearDot();
            }
        });
    }
}
