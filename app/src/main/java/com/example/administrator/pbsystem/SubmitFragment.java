package com.example.administrator.pbsystem;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/16.
 */
public class SubmitFragment extends Fragment {
    /** 第一个无内容的格子 */
    protected TextView empty;
    /** 星期一的格子 */
    protected TextView monColum;
    /** 星期二的格子 */
    protected TextView tueColum;
    /** 星期三的格子 */
    protected TextView wedColum;
    /** 星期四的格子 */
    protected TextView thrusColum;
    /** 星期五的格子 */
    protected TextView friColum;
    /** 星期六的格子 */
    protected TextView satColum;
    /** 星期日的格子 */
    protected TextView sunColum;
    /** 班表body部分布局 */
    protected RelativeLayout course_table_layout;
    /** 屏幕宽度 **/
    protected int screenWidth;
    /** 课程格子平均宽度 **/
    protected int aveWidth;

    //手动注入班表时间
    private final String s1="8:00\n-\n8:45";
    private final String s2="8:45\n-\n9:30";
    private final String s3="9:30\n-\n10:15";
    private final String s4="10:15\n-\n11:00";
    private final String s5="11:00\n-\n11:45";
    private final String s6="11:45\n-\n12:30";
    private final String s7="12:30\n-\n13:15";
    private final String s8="13:15\n-\n14:00";
    private final String s9="14:00\n-\n14:45";
    private final String s10="14:45\n-\n15:30";
    private final String s11="15:30\n-\n16:15";
    private final String s12="16:15\n-\n17:00";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View submitLayout = inflater.inflate(R.layout.submit_layout,
                container, false);
        //获得列头的控件
        empty = (TextView) submitLayout.findViewById(R.id.test_empty);
        monColum = (TextView) submitLayout.findViewById(R.id.test_monday_course);
        tueColum = (TextView) submitLayout.findViewById(R.id.test_tuesday_course);
        wedColum = (TextView) submitLayout.findViewById(R.id.test_wednesday_course);
        thrusColum = (TextView) submitLayout.findViewById(R.id.test_thursday_course);
        friColum = (TextView) submitLayout.findViewById(R.id.test_friday_course);
        satColum  = (TextView) submitLayout.findViewById(R.id.test_saturday_course);
        sunColum = (TextView) submitLayout.findViewById(R.id.test_sunday_course);
        course_table_layout = (RelativeLayout) submitLayout.findViewById(R.id.test_course_rl);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //屏幕宽度
        int width = dm.widthPixels;
        //平均宽度
        int aveWidth = width / 8;
        //第一个空白格子设置为25宽
        empty.setWidth(aveWidth * 3/4);
        monColum.setWidth(aveWidth * 33/32 + 1);
        tueColum.setWidth(aveWidth * 33/32 + 1);
        wedColum.setWidth(aveWidth * 33/32 + 1);
        thrusColum.setWidth(aveWidth * 33/32 + 1);
        friColum.setWidth(aveWidth * 33/32 + 1);
        satColum.setWidth(aveWidth * 33/32 + 1);
        sunColum.setWidth(aveWidth * 33/32 + 1);
        this.screenWidth = width;
        this.aveWidth = aveWidth;
        int height = dm.heightPixels;
        int gridHeight = height / 12;
        //设置班表界面
        //动态生成12 * maxCourseNum个格子
        //它们的id包括时间是从1开始横向到96，之后的数据封装用tag来表示
        for(int i = 1; i <= 12; i ++){
            for(int j = 1; j <= 8; j ++){
                TextView tx;
                CheckBox bx;
                //除了最后一列，都使用course_text_view_bg背景（最后一列没有右边框）
                if (j == 1) {
                    tx = new TextView(getActivity());
                    tx.setId((i - 1) * 8 + j);
                    tx.setBackgroundDrawable(getActivity().
                            getResources().getDrawable(R.drawable.course_text_view_bg));
                    //相对布局参数
                    RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                            aveWidth * 3/4,
                            gridHeight);
                    //文字对齐方式
                    tx.setGravity(Gravity.CENTER);
                    //字体样式
                    tx.setTextAppearance(getActivity(), R.style.courseTableText);
                    String temp = "s"+String.valueOf(i);
                    tx.setText(times(temp));
                    //设置他们的相对位置
                    if(i == 1)
                        rp.addRule(RelativeLayout.BELOW, empty.getId());
                    else
                        rp.addRule(RelativeLayout.BELOW, (i - 1) * 8);
                    tx.setLayoutParams(rp);
                    course_table_layout.addView(tx);
                } else {
                    bx = new CheckBox(getActivity());
                    bx.setId((i - 1) * 8 + j);
                    bx.setButtonDrawable(R.drawable.checkbox_bg);
                    //bx.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
                    if (j == 8) {
                        bx.setBackgroundDrawable(getActivity().
                                getResources().getDrawable(R.drawable.course_table_last_colum));
                    } else {
                        bx.setBackgroundDrawable(getActivity().
                                getResources().getDrawable(R.drawable.course_text_view_bg));
                    }
                    RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                            aveWidth * 33/32 + 1,
                            gridHeight);
                    rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 8  + j - 1);
                    rp.addRule(RelativeLayout.ALIGN_TOP, (i - 1) * 8 + j - 1);
                    bx.setLayoutParams(rp);
                    course_table_layout.addView(bx);
                }
            }
        }

        Button save = new Button(getActivity());
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams((aveWidth * 33/32 + 1)*2, gridHeight);
        rp.addRule(RelativeLayout.BELOW, 89);
        rp.addRule(RelativeLayout.RIGHT_OF, 92);
        save.setText(R.string.save);
        save.setTextColor(getResources().getColor(R.color.button));
        save.setGravity(Gravity.CENTER);
        save.setLayoutParams(rp);
        course_table_layout.addView(save);

        Button submit = new Button(getActivity());
        RelativeLayout.LayoutParams rp2 = new RelativeLayout.LayoutParams((aveWidth * 33/32 + 1)*2, gridHeight);
        rp2.addRule(RelativeLayout.BELOW, 89);
        rp2.addRule(RelativeLayout.RIGHT_OF, 94);
        submit.setText(R.string.submit);
        submit.setTextColor(getResources().getColor(R.color.button));
        submit.setGravity(Gravity.CENTER);
        submit.setLayoutParams(rp2);
        course_table_layout.addView(submit);

        TextView hint = new TextView(getActivity());
        RelativeLayout.LayoutParams rp3 = new RelativeLayout.LayoutParams((aveWidth * 33/32 + 1)*2, gridHeight);
        rp3.addRule(RelativeLayout.BELOW, 89);
        rp3.addRule(RelativeLayout.RIGHT_OF, 89);
        hint.setText(R.string.hint);
        hint.setTextColor(getResources().getColor(R.color.button));
        hint.setGravity(Gravity.CENTER);
        hint.setLayoutParams(rp3);
        course_table_layout.addView(hint);
        return submitLayout;
    }

    //班表第一列时间
    public String times(String temp) {
        if (temp.equals("s1"))
            return s1;
        if (temp.equals("s2"))
            return s2;
        if (temp.equals("s3"))
            return s3;
        if (temp.equals("s4"))
            return s4;
        if (temp.equals("s5"))
            return s5;
        if (temp.equals("s6"))
            return s6;
        if (temp.equals("s7"))
            return s7;
        if (temp.equals("s8"))
            return s8;
        if (temp.equals("s9"))
            return s9;
        if (temp.equals("s10"))
            return s10;
        if (temp.equals("s11"))
            return s11;
        if (temp.equals("s12"))
            return s12;
        return null;
    }
}
