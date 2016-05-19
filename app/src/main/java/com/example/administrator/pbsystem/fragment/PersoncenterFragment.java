package com.example.administrator.pbsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.pbsystem.R;

/**
 * Created by Administrator on 2016/5/16.
 */
public class PersoncenterFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("tag", "oncreateviewp");
        View personLayout = inflater.inflate(R.layout.personcenter_layout,
                container, false);
        return personLayout;
    }
    public void onDestroyView() {
        Log.d("des", "des4");

        super.onDestroyView();
    }
}

