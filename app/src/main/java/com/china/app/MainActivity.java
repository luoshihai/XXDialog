package com.china.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.luoshihai.xxdialog.DialogViewHolder;
import com.luoshihai.xxdialog.XXDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        XXDialog xxDialog = new XXDialog(this, R.layout.dialog) {
            @Override
            public void convert(DialogViewHolder holder) {
                holder.setOnClick(R.id.tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点了", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.fromRightToMiddle().fullHeight().showDialog().setCanceledOnTouchOutside(true);

    }
}
