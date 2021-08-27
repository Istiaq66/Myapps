package com.istiaq66.vollydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Get_Customer_RV extends AppCompatActivity {


    final private String JSONURL = "http://10.0.2.2/API/getallCustomer.php";

    //getting the recyclerview from xml
    RecyclerView recycleView;
    

    //the Model list where we will store all the Model objects after parsing json
    List<Model> Modellist;
    RVadapter rVadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_customer_rv);



        recycleView = (RecyclerView) findViewById(R.id.RCV);

        Modellist = new ArrayList<>();


        LoadListData();

    }






    private void LoadListData(){


        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar2);


        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSONURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                //hiding the progressbar after completion
                progressBar.setVisibility(View.INVISIBLE);



                try{

                //-----------way--2   //way 2 --array wise
                //converting the string to json array object
                    JSONArray array  = new JSONArray(response);
                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject modelObject = array.getJSONObject(i);

                        //adding the product to Model list
                        Model proo = new Model(modelObject.getInt("c_id"), modelObject.getString("c_name"), modelObject.getString("c_mobile"), modelObject.getString("c_email"));

                        //adding the proo2 to Modellist
                        Modellist.add(proo);
                    }

                    //creating custom adapter object
                   rVadapter = new RVadapter(Modellist, getApplicationContext());



                    recycleView.setHasFixedSize(true);
                    recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    //adding the adapter to RecycleView
                    recycleView.setAdapter(rVadapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);




    }

}