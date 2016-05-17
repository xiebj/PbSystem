package com.example.administrator.pbsystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/5/16.
 */
public class PersoncenterFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View personLayout = inflater.inflate(R.layout.personcenter_layout,
                container, false);
        return personLayout;
    }
}

