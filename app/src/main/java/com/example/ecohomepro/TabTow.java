package com.example.ecohomepro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class TabTow extends Fragment {

    Ex_adapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2, container, false);
        expListView = view.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();


        listAdapter = new Ex_adapter(getContext(), listDataHeader, listDataChild);
//
//        // setting list adapter
        expListView.setAdapter(listAdapter);
        return view;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        SQLiteDatabase db = new DataBase(getContext()).getReadableDatabase();
        Cursor cursor = db.rawQuery("select mobile,  GROUP_CONCAT(service) GroupedName,  GROUP_CONCAT(date) GroupedDate  from request group by mobile", new String[]{});
        cursor.moveToFirst();
        List<String> info ;


        while (cursor.isAfterLast() == false) {
            listDataHeader.add(cursor.getString(cursor.getColumnIndex("mobile")));
            info=new ArrayList<>();
            List<String> services = new ArrayList<String>();
            List<String> dates = new ArrayList<String>();
            StringTokenizer tokenizer = new StringTokenizer(cursor.getString(cursor.getColumnIndex("GroupedName")),",");

            while (tokenizer.hasMoreTokens()) {
                services.add(tokenizer.nextToken());

            }
          tokenizer = new StringTokenizer(cursor.getString(cursor.getColumnIndex("GroupedDate")),",");

            while (tokenizer.hasMoreTokens()) {
                dates.add(tokenizer.nextToken());

            }
            for (int i = 0; i < services.size(); i++) {
                Log.d("test", "prepareListData: "+"ser"+services.get(i)+" date"+dates.get(i));
                info.add(services.get(i)+" on "+dates.get(i));

            }

            listDataChild.put(cursor.getString(cursor.getColumnIndex("mobile")), info);

            cursor.moveToNext();
        }

    }

}
