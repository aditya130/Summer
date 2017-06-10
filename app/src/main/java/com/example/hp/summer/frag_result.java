package com.example.hp.summer;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by HP on 04-06-2017.
 */

public class frag_result extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances)
    {
        final Context context = this.getActivity().getApplicationContext();
        View v = inflater.inflate(R.layout.frag_result_layout,container,false);
        final ListView lv_result = (ListView)v.findViewById(R.id.lv_result);

        //JSON Code starts
        String url = "http://192.168.43.212/summer_api/result_api.php";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                            //do the things here
                            //return dataArray;
                            lv_result.setAdapter(new adapter_x(context,response));
                        Log.w("respose", response.toString());

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.w("error", "url no");
                        Toast.makeText(context,"hello this is 3106\n"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        CustomVolleyRequestQueue.getInstance(context).addToRequestQueue(jsObjRequest);
        //JSON code ends

        return v;
    }
}

class adapter_x extends BaseAdapter implements View.OnClickListener
{
    int no_of_exam=0;
    Context ct;
    JSONObject json_main = new JSONObject();

    public adapter_x(Context context,JSONObject response)
    {
        ct = context;
        json_main = response;
        try{
            no_of_exam=json_main.getInt("number_of_exam_titles");
        }
        catch(Exception e){
            no_of_exam=0;
        }
    }

    @Override
    public int getCount() {
       return no_of_exam;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lf = (LayoutInflater) ct.getSystemService(ct.LAYOUT_INFLATER_SERVICE);

        View v = lf.inflate(R.layout.single_row_result,parent,false);
        Button btn_ex = (Button) v.findViewById(R.id.btn_expand);
        btn_ex.setOnClickListener(this);
        TableLayout tableLayout = (TableLayout) v.findViewById(R.id.table_result);

        //for(int j=0;j<5;j++)
          //  tableLayout.addView(lf.inflate(R.layout.single_result_table_row,null,false));
        try {
            btn_ex.setText(json_main.getString(""+position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        View v_row;
        TextView tv_sub,tv_got,tv_max,tv_grade;
        JSONObject dummy_json;
        int x=0;
        try{x=json_main.getJSONArray(json_main.getString(""+position)).length();}
        catch(Exception e){};

        v_row = lf.inflate(R.layout.single_result_table_row, null, false);
        tv_sub = (TextView) v_row.findViewById(R.id.tv_table_subject);
        tv_got = (TextView) v_row.findViewById(R.id.tv_table_marks_obtained);
        tv_max = (TextView) v_row.findViewById(R.id.tv_table_marks_max);
        tv_grade = (TextView) v_row.findViewById(R.id.tv_table_grade);

        tv_sub.setText("Subject");
        tv_got.setText("Got");
        tv_max.setText("Max");
        tv_grade.setText("Grade");

        tv_sub.setTypeface(null,Typeface.BOLD);
        tv_got.setTypeface(null,Typeface.BOLD);
        tv_max.setTypeface(null,Typeface.BOLD);
        tv_grade.setTypeface(null,Typeface.BOLD);

        tableLayout.addView(v_row);

        for(int i=0;i<x;i++)
        {
            v_row = lf.inflate(R.layout.single_result_table_row, null, false);
            tv_sub = (TextView) v_row.findViewById(R.id.tv_table_subject);
            tv_got = (TextView) v_row.findViewById(R.id.tv_table_marks_obtained);
            tv_max = (TextView) v_row.findViewById(R.id.tv_table_marks_max);
            tv_grade = (TextView) v_row.findViewById(R.id.tv_table_grade);

            tv_sub.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

            try{
                dummy_json =(JSONObject)(json_main.getJSONArray(json_main.getString(""+position))).get(i);
                int max = Integer.parseInt(dummy_json.getString("max"));
                int got = Integer.parseInt(dummy_json.getString("got"));

                tv_sub.setText(dummy_json.getString("subject"));
                tv_got.setText(""+got);
                tv_max.setText(""+max);
                tv_grade.setText(cal_grade((((float)got)/max)*100));
                tableLayout.addView(v_row);
            }
            catch(Exception e){

            }
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        View par = (View)v.getParent();
        TableLayout tl = (TableLayout) par.findViewById(R.id.table_result);
        if(tl.getVisibility() == View.VISIBLE) {
            tl.setVisibility(View.GONE);
            btn.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icon_plus,0);
        }
        else {
            tl.setVisibility(View.VISIBLE);
            btn.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icon_minus,0);
        }
    }

    public String cal_grade(float p)
    {
        String s;
        if(p>=90)
            s="A+";
        else if(p>=80)
            s="A";
        else if(p>=70)
            s="B+";
        else if(p>=60)
            s="B";
        else if(p>=50)
            s="C+";
        else if(p>=40)
            s="C";
        else
            s="F";
        return s;
    }
}


