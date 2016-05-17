package com.example.administrator.pbsystem;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CheckoutFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View checkoutLayout = inflater.inflate(R.layout.checkout_layout,
                container, false);
        return checkoutLayout;
    }
}
