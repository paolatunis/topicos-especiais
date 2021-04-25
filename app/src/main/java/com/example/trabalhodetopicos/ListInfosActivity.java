package com.example.trabalhodetopicos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListInfosActivity extends ListActivity {

    private Bundle bundle;
    private ArrayList<Usuario> list;
    private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initializeComponents();
        ArrayAdapter<Usuario> adapterUserList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        super.setListAdapter(adapterUserList);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("USER_OBJECT", list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initializeComponents() {
        this.bundle = getIntent().getExtras();
        this.list = (ArrayList<Usuario>) bundle.getSerializable("USER_LIST");
        this.lvList = findViewById(android.R.id.list);
    }
}
