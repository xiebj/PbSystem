package com.example.administrator.pbsystem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

import com.example.administrator.pbsystem.fragment.CheckoutFragment;
import com.example.administrator.pbsystem.fragment.FragAdapter;
import com.example.administrator.pbsystem.fragment.PersoncenterFragment;
import com.example.administrator.pbsystem.R;
import com.example.administrator.pbsystem.fragment.SeeFragment;
import com.example.administrator.pbsystem.fragment.SubmitFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private List<Fragment> listfrag = new ArrayList<Fragment>();
    private ViewPager vp;
    private FragAdapter fa;
    /**
     * 用于提交班表的Fragment
     */
    private SubmitFragment submitFragment;

    /**
     * 用于签到请假的Fragment
     */
    private CheckoutFragment checkoutFragment;

    /**
     * 用于查看工时的Fragment
     */
    private SeeFragment seeFragment;

    /**
     * 用于个人中心的Fragment
     */
    private PersoncenterFragment personFragment;

    /**
     * 提交班表界面布局
     */
    private View submitLayout;

    /**
     * 签到请假界面布局
     */
    private View checkoutLayout;

    /**
     * 查看工时界面布局
     */
    private View seeLayout;

    /**
     * 个人中心界面布局
     */
    private View personLayout;

    private TextView submitText;

    private TextView checkoutText;

    private TextView seeText;

    private TextView personText;

    private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;

    TextPaint tp;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        vp = (ViewPager) findViewById(R.id.viewpager);
        fragmentManager = getSupportFragmentManager();
        fa = new FragAdapter(fragmentManager, listfrag);
        vp.setAdapter(fa);
        vp.setOnPageChangeListener(new TabOnPageChangeListener());
        //设置动画效果
        //vp.setPageTransformer(false, new CubeOutTransformer());
    }

    public void initViews() {
        submitLayout = findViewById(R.id.submit_layout);
        checkoutLayout = findViewById(R.id.checkout_layout);
        seeLayout = findViewById(R.id.see_layout);
        personLayout = findViewById(R.id.personcenter_layout);
        submitText = (TextView) findViewById(R.id.submit_text);
        checkoutText = (TextView) findViewById(R.id.checkout_text);
        seeText = (TextView) findViewById(R.id.see_text);
        submitText.setTextScaleX(1.3f);
        personText = (TextView) findViewById(R.id.person_text);

        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);

        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);

        submitLayout.setOnClickListener(new TabOnClickListener(0));
        submitLayout.setBackgroundResource(R.color.selectedbg);
        TextPaint tp = submitText.getPaint();
        tp.setFakeBoldText(true);
        checkoutLayout.setOnClickListener(new TabOnClickListener(1));
        seeLayout.setOnClickListener(new TabOnClickListener(2));
        personLayout.setOnClickListener(new TabOnClickListener(3));

        listfrag.add(new SubmitFragment());
        listfrag.add(new CheckoutFragment());
        listfrag.add(new SeeFragment());
        listfrag.add(new PersoncenterFragment());
    }

    /**
     * 功能：点击主页TAB事件
     */

    public class TabOnClickListener implements View.OnClickListener {
        private int index = 0;

        public TabOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            vp.setCurrentItem(index, false);//选择某一页
        }

    }


    /**
     * 功能：Fragment页面改变事件
     */
    public class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {

        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        //当新的页面被选中时调用,字体变大，加粗，上划线出现
        public void onPageSelected(int position) {
            // 每次选中之前先清楚掉上次的选中状态
            clearSelection();
            switch (position) {
                case 0:
                    line1.setVisibility(View.VISIBLE);
                    submitText.setTextScaleX(1.3f);
                    submitLayout.setBackgroundResource(R.color.selectedbg);
                    tp = submitText.getPaint();
                    tp.setFakeBoldText(true);
                    break;
                case 1:
                    line2.setVisibility(View.VISIBLE);
                    checkoutText.setTextScaleX(1.3f);
                    checkoutLayout.setBackgroundResource(R.color.selectedbg);
                    tp = checkoutText.getPaint();
                    tp.setFakeBoldText(true);
                    break;
                case 2:
                    line3.setVisibility(View.VISIBLE);
                    seeText.setTextScaleX(1.3f);
                    seeLayout.setBackgroundResource(R.color.selectedbg);
                    tp = seeText.getPaint();
                    tp.setFakeBoldText(true);
                    break;
                case 3:
                    line4.setVisibility(View.VISIBLE);
                    personText.setTextScaleX(1.3f);
                    personLayout.setBackgroundResource(R.color.selectedbg);
                    tp = personText.getPaint();
                    tp.setFakeBoldText(true);
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * 清除掉所有的选中状态，字体恢复原来大小，不加粗，隐藏上划线
     */
    private void clearSelection() {
        submitLayout.setBackgroundResource(R.color.background);
        checkoutLayout.setBackgroundResource(R.color.background);
        seeLayout.setBackgroundResource(R.color.background);
        personLayout.setBackgroundResource(R.color.background);
        submitText.setTextScaleX(10 / 13f);
        checkoutText.setTextScaleX(10 / 13f);
        seeText.setTextScaleX(10 / 13f);
        personText.setTextScaleX(10 / 13f);
        tp = submitText.getPaint();
        tp.setFakeBoldText(false);
        tp = checkoutText.getPaint();
        tp.setFakeBoldText(false);
        tp = seeText.getPaint();
        tp.setFakeBoldText(false);
        tp = personText.getPaint();
        tp.setFakeBoldText(false);
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //菜单注销
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent t = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(t);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //重写返回函数确认退出
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
        // super.onBackPressed();
    }
}
