package com.example.ecohomepro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class requsetInfo extends AppCompatActivity {
    SQLiteDatabase db;
    TextView note,time,phone,address,date,service,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requset_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i=getIntent();

        db = new DataBase(getApplicationContext()).getReadableDatabase();

        note=   findViewById(R.id.note);
        service=   findViewById(R.id.service);
        phone=   findViewById(R.id.phone);
        time=   findViewById(R.id.time);
        date=   findViewById(R.id.date);
        address=   findViewById(R.id.address);
        info=findViewById(R.id.info);

       Long id= i.getLongExtra("id",0);
        Cursor cursor = db.query("Request", null, "id=?", new String[]{""+id}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){


            note.setText(cursor.getString(cursor.getColumnIndex("note")));
            info.setText(cursor.getString(cursor.getColumnIndex("info")));
            time.setText(cursor.getString(cursor.getColumnIndex("time")));
            phone.setText(cursor.getString(cursor.getColumnIndex("mobile")));
            service.setText(cursor.getString(cursor.getColumnIndex("service")));
            address.setText(cursor.getString(cursor.getColumnIndex("address")));
            date.setText(cursor.getString(cursor.getColumnIndex("date")));


            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri u = Uri.parse("tel:" + phone.getText().toString());
                    Intent i = new Intent(Intent.ACTION_DIAL, u);
                    startActivity(i);
                }
            });
        }

    }

}
