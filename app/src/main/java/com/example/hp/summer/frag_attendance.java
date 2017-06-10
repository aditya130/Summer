package com.example.hp.summer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HP on 03-06-2017.
 */

public class frag_attendance extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances)
    {
        View v = inflater.inflate(R.layout.frag_attendance_layout,container,false);
        return  v;
    }
}
