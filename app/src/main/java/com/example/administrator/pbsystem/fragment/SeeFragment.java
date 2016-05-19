package com.example.administrator.pbsystem.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.pbsystem.R;

/**
 * Created by Administrator on 2016/5/16.
 */
public class SeeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("tag", "oncreateview333");
        View seeLayout = inflater.inflate(R.layout.see_layout,
                container, false);
        return seeLayout;
    }
    public void onDestroyView() {
        Log.d("des", "des3");

        super.onDestroyView();
    }
}
