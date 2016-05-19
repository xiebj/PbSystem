package com.example.administrator.pbsystem.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.administrator.pbsystem.R;
import com.example.administrator.pbsystem.activity.MainActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CheckoutFragment extends Fragment {
    private EditText edittime;
    private EditText editban;
    private EditText editreason;
    private CheckBox already;
    private EditText editname;
    private Button qingjiabtn;
    private TextView daibanname;
    private int iYear;
    private int iMonth;
    private int iDay;
    private Calendar objTime;
    private DatePicker datePicker;
    private View checkoutLayout = null;
    PopupMenu popup = null;

    public void onDestroyView() {
        Log.d("des", "des2");

        super.onDestroyView();
    }

    public void onStart() {
        Log.d("start", "start2");
        super.onStart();
    }

    public void onResume() {
        Log.d("res", "resume2");
        super.onResume();
    }

    public void onPause() {
        Log.d("pau", "pause2");

        super.onPause();
    }

    public void onStop() {
        Log.d("stop", "stop2");

        super.onStop();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("tag", "oncreateviewc");
        objTime = Calendar.getInstance();
        if (checkoutLayout == null) {
            Log.d("null", "yes");
            checkoutLayout = inflater.inflate(R.layout.checkout_layout,
                    container, false);
            //initViews
            edittime = (EditText) checkoutLayout.findViewById(R.id.time);
            editban = (EditText) checkoutLayout.findViewById(R.id.banbie);
            editreason = (EditText) checkoutLayout.findViewById(R.id.reason);
            editname = (EditText) checkoutLayout.findViewById(R.id.editname);
            already = (CheckBox) checkoutLayout.findViewById(R.id.already);
            qingjiabtn = (Button) checkoutLayout.findViewById(R.id.qingjia);
            daibanname = (TextView) checkoutLayout.findViewById(R.id.daibanname);
            //点击日期弹出日期dialog供选择
            edittime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //通过自定义控件AlertDialog实现
                    MainActivity mainActivity = (MainActivity) getActivity();
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                    LinearLayout l = (LinearLayout) mainActivity.getLayoutInflater().inflate(R.layout.date_layout, null);
                    datePicker = (DatePicker) l.findViewById(R.id.date_picker);
                    //设置日期简略显示 否则详细显示 包括:星期周
                    datePicker.setCalendarViewShown(false);
                    //初始化当前日期,为设置最小日期为当日做准备
                    objTime.set(objTime.get(Calendar.YEAR), objTime.get(Calendar.MONTH), objTime.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                    datePicker.init(objTime.get(Calendar.YEAR), objTime.get(Calendar.MONTH), objTime.get(Calendar.DAY_OF_MONTH), null);
                    long time = objTime.getTimeInMillis();
                    datePicker.setMinDate(time);
                    //设置date布局
                    builder.setView(l);
                    builder.setTitle("请选择请假日期");
                    builder.setPositiveButton("确 定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            iYear = datePicker.getYear();
                            iMonth = datePicker.getMonth();
                            iDay = datePicker.getDayOfMonth();
                            iMonth++;
                            String time = String.valueOf(iYear) + "/" + String.valueOf(iMonth) + "/" + String.valueOf(iDay);
                            edittime.setText(time);
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("取 消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                }
            });

            //点击复选框如果有已代班的人则弹出新的代班人姓名编辑框
            already.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        editname.setVisibility(View.VISIBLE);
                        daibanname.setVisibility(View.VISIBLE);
                    } else {
                        editname.setVisibility(View.GONE);
                        daibanname.setVisibility(View.GONE);
                    }
                }
            });
            //班别选项，用了popupMenu弹出菜单
            editban.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popup = new PopupMenu(getActivity(), v);
                    getActivity().getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            editban.setText(item.getTitle());
                            return true;
                        }
                    });
                    popup.show();
                }
            });
            //点击提交按钮，传送数据到服务器
            qingjiabtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return checkoutLayout;
    }
}
