package com.example.ecohomepro;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TabOne extends Fragment {
    SQLiteDatabase db;
    LinearLayout linear;
    View view;

    public void showAdmins(View view) {
        db = new DataBase(getContext()).getWritableDatabase();

        linear = view.findViewById(R.id.linear);
        Cursor res = db.rawQuery("select * from admins", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            String name = res.getString(res.getColumnIndex("name"));
            final String mobile = res.getString(res.getColumnIndex("phone"));
            View card = getLayoutInflater().inflate(R.layout.card, linear, false);
            TextView n = card.findViewById(R.id.service);
            n.setText(name);
            TextView a = card.findViewById(R.id.mobile);
            a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri u = Uri.parse("tel:"+mobile);
                    Intent i = new Intent(Intent.ACTION_DIAL, u);
                    startActivity(i);
                }
            });
            a.setText(mobile);
            linear.addView(card);
            res.moveToNext();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab1, container, false);
        showAdmins(view);
        FloatingActionButton button = view.findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog(getContext());

            }
        });
        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void showAddItemDialog(Context c) {
        final EditText name = new EditText(c);
        final EditText pass = new EditText(c);
        final EditText mobile = new EditText(c);
        name.setHint("Name");
        mobile.setHint("Mobile");
        final TextView res = new TextView(c);
        res.setGravity(Gravity.CENTER);
        res.setTextColor(R.color.colorPrimary);
        res.setText("");
        pass.setHint("password");
        pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mobile.setInputType(InputType.TYPE_CLASS_PHONE);
        final LinearLayout linearLayout = new LinearLayout(c);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(name);
        linearLayout.addView(mobile);
        linearLayout.addView(pass);
        linearLayout.addView(res);

        pass.setTransformationMethod(new PasswordTransformationMethod());
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add new admin")
                .setView(linearLayout)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Name = String.valueOf(name.getText());
                        String Pass = String.valueOf(pass.getText());
                        String Mobile = String.valueOf(mobile.getText());
                        ContentValues values = new ContentValues();
                        values.put("name", Name);
                        values.put("phone", Mobile);
                        values.put("password", Pass);
                        long id = db.insert("admins", null, values);
                        db.close();

                        if (id != -1) {
                            if ((linear).getChildCount() > 0) {
                                linear.removeAllViews();
                            }
                            res.setText("Admin added successfully");
                            showAdmins(view);

                        } else {
                            res.setText("Error! try again");

                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create();
        dialog.show();
    }
}
