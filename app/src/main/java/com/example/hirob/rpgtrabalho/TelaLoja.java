package com.example.hirob.rpgtrabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TelaLoja extends AppCompatActivity {
    ListView lv;
    List<String> initialList;
    ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_loja);

        lv = (ListView) findViewById(R.id.listViewLoja);

        initialList = new ArrayList<>();
        initialList.add("Poção - 5 Gold - 10 cura");
        mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                initialList);
        lv.setAdapter(mAdapter);
    }
}
