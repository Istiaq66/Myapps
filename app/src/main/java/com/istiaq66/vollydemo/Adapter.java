package com.istiaq66.vollydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<Model> {


    public Adapter(List<Model> modellist, Context mCtx) {
        super(mCtx, R.layout.list_model, modellist);
        Modellist = modellist;
        this.mCtx = mCtx;
    }

    //the Model list that will be displayed
    private List<Model> Modellist;

    //the context object
    private Context mCtx;



@Override
    public View getView(int position, View convertView, ViewGroup parent) {



        //getting the layoutinflater
       LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_model, null, true);


    //getting text views
    TextView textid = listViewItem.findViewById(R.id.idg);
    TextView textViewName = listViewItem.findViewById(R.id.nameg);
    TextView textViewmobile = listViewItem.findViewById(R.id.mobileg);
    TextView textViewemail = listViewItem.findViewById(R.id.emailg);


    Model pro = Modellist.get(position);

    textid.setText(String.valueOf(pro.getC_id()));
    textViewName.setText(pro.getC_name());
    textViewmobile.setText(pro.getC_mobile());
    textViewemail.setText(pro.getC_email());



        return listViewItem;
    }



    }
