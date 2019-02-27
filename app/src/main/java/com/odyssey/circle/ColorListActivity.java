package com.odyssey.circle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ColorListActivity extends AppCompatActivity {

    private List<Colors> colorsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ColorsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);

        recyclerView = findViewById(R.id.colorRecycler);

        adapter = new ColorsAdapter(ColorListActivity.this,colorsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getColorData();
    }

    private void getColorData(){
        Colors colors = new Colors(Color.YELLOW,Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY,Color.MAGENTA, Color.DKGRAY);
        colorsList.add(colors);

        colors = new Colors(Color.LTGRAY,Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.BLUE,Color.BLACK, Color.YELLOW);
        colorsList.add(colors);

        adapter.notifyDataSetChanged();
    }
}
