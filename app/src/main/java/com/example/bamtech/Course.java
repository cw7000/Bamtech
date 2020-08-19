package com.example.bamtech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.example.bamtech.ItemAdapter.SPAN_COUNT_ONE;
import static com.example.bamtech.ItemAdapter.SPAN_COUNT_THREE;


public class Course extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Items> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_page);

        // getSupportActionBar().hide();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initializeItemData();

        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_THREE);
        itemAdapter = new ItemAdapter(items , gridLayoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    private void initializeItemData(){
        items = new ArrayList<>(4 );
        items.add( new Items (R.drawable.bam2,"Bam1",20,33) );
        items.add( new Items (R.drawable.bam4,"Bam2",10,69) );
        items.add( new Items (R.drawable.bam7,"Bam3",30,77) );
        items.add( new Items (R.drawable.bam8,"Bam4",40,12) );
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.menu_switch_layout ){
            switchLayout();
            switchIcon(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchLayout() {
        if (gridLayoutManager.getSpanCount() == SPAN_COUNT_ONE ){
            gridLayoutManager.setSpanCount(SPAN_COUNT_THREE);
        }else{
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
        }
        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
    }

    private void switchIcon(MenuItem item) {
        if (gridLayoutManager.getSpanCount() == SPAN_COUNT_THREE ){
            item.setIcon(getResources().getDrawable(R.drawable.list));
        }else{
            item.setIcon(getResources().getDrawable(R.drawable.grid));
        }
    }
}