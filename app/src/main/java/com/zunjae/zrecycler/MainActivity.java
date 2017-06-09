package com.zunjae.zrecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;


/**
 * Created by zunjae on 6/9/2017.
 */

public final class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }
}
