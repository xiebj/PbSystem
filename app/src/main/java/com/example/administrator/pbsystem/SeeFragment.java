package com.example.administrator.pbsystem;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/5/16.
 */
public class SeeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View seeLayout = inflater.inflate(R.layout.see_layout,
                container, false);
        return seeLayout;
    }
}
