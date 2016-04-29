package com.example.petarstojanovski.myapplication;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity{


    ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v){


                EditText ordercount = (EditText)findViewById(R.id.content);
                EditText cost = (EditText)findViewById(R.id.cost);
                assert ordercount != null;
                assert cost != null;
                Order order = new Order(ordercount.getText().toString(),Integer.parseInt(cost.getText().toString()));

                orders.add(order);

                ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(MainActivity.this,android.R.layout.simple_list_item_1,orders);

                android.widget.ListView listView = (android.widget.ListView)findViewById(R.id.myorders);

                assert listView != null;
                listView.setAdapter(adapter);
                listView.setContentDescription(adapter.toString());

            }
        });

    }
}
