package com.example.administrator.pbsystem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

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

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        vp = (ViewPager)findViewById(R.id.viewpager);
        fragmentManager = getSupportFragmentManager();
        fa =new FragAdapter(fragmentManager, listfrag);
        vp.setAdapter(fa);
        vp.setOnPageChangeListener(new TabOnPageChangeListener());
    }

    public void initViews() {
        submitLayout = findViewById(R.id.submit_layout);
        checkoutLayout = findViewById(R.id.checkout_layout);
        seeLayout = findViewById(R.id.see_layout);
        personLayout = findViewById(R.id.personcenter_layout);
        submitText = (TextView)findViewById(R.id.submit_text);
        checkoutText = (TextView)findViewById(R.id.checkout_text);
        seeText = (TextView)findViewById(R.id.see_text);
        submitText.setTextScaleX(1.3f);
        personText = (TextView)findViewById(R.id.person_text);

        line1 = (TextView)findViewById(R.id.line1);
        line2 = (TextView)findViewById(R.id.line2);
        line3 = (TextView)findViewById(R.id.line3);
        line4 = (TextView)findViewById(R.id.line4);

        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);

        submitLayout.setOnClickListener(new TabOnClickListener(0));
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

    public class TabOnClickListener implements View.OnClickListener{
        private int index=0;

        public TabOnClickListener(int i){
            index=i;
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
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            // 每次选中之前先清楚掉上次的选中状态
            clearSelection();
            switch (position) {
                case 0:
                    line1.setVisibility(View.VISIBLE);
                    submitText.setTextScaleX(1.3f);
                    break;
                case 1:
                    line2.setVisibility(View.VISIBLE);
                    checkoutText.setTextScaleX(1.3f);
                    break;
                case 2:
                    line3.setVisibility(View.VISIBLE);
                    seeText.setTextScaleX(1.3f);
                    break;
                case 3:
                    line4.setVisibility(View.VISIBLE);
                    personText.setTextScaleX(1.3f);
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        submitText.setTextScaleX(10 / 13f);
        checkoutText.setTextScaleX(10/13f);
        seeText.setTextScaleX(10/13f);
        personText.setTextScaleX(10/13f);
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
