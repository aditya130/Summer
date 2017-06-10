package com.example.hp.summer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by HP on 03-06-2017.
 */

public class frag_home extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances)
    {
        View v = inflater.inflate(R.layout.frag_home_layout,container,false);
        TextView tv_home = (TextView)v.findViewById(R.id.tv_home);
        tv_home.setText("This is Home Screen");
        return v;
    }
}
