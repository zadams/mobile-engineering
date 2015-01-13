package com.livingsocial.challenge.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.livingsocial.challenge.R;
import com.livingsocial.challenge.fragment.InvoiceGridFragment;
import com.livingsocial.challenge.fragment.InvoiceListFragment;
import com.livingsocial.challenge.view.FragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ale on 1/12/15.
 */

public class MainActivity extends ActionbarActivity {

    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_activity_main);
        viewpager = (ViewPager)findViewById(R.id.ls_viewpager);
        viewpager.setPageTransformer(true, new PageTransformer());
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new InvoiceListFragment());
        list.add(new InvoiceGridFragment());
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        adapter.setData(list);
        viewpager.setAdapter(adapter);
    }

    public static Intent prepareIntent(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        Bundle bundle  = new Bundle();
        intent.putExtras(bundle);
        return intent;
    }

    public class PageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 0) {
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) {
                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else {
                view.setAlpha(0);
            }
        }
    }
}
