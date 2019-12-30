package com.example.ecohomepro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TabThree extends Fragment {
    View view;
    MyRequest req;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab3, container, false);
        List<MyRequest> list = new ArrayList<>();
        SQLiteDatabase db = new DataBase(getContext()).getReadableDatabase();


        Cursor cursor = db.query("request", null, null, null, null, null, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            req = new MyRequest(
                    Long.parseLong(cursor.getString(cursor.getColumnIndex("id"))),
                    cursor.getString(cursor.getColumnIndex("note")),
                    cursor.getString(cursor.getColumnIndex("info")),
                    cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("mobile")),
                    cursor.getString(cursor.getColumnIndex("service")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("served"))

            );
            list.add(req);
            cursor.moveToNext();
        }
        ListView listView = view.findViewById(R.id.listv);
        MyAdapter adapter = new MyAdapter(getContext(), (ArrayList<MyRequest>) list);
        listView.setAdapter(adapter);
        return view;
    }


}
