package com.sex.hall;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class TabsParentFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    public static final String MODE = "第一";
    public static final String MODE_TITLE = "二";
    public static final String CURRENT_TAB = "三";

    public static final int LEFT_ALIGNED_TABS = 0;
    public static final int FIXED_CENTERED_TABS = 1;
    public static final int FIXED_FILLED_TABS = 2;
    public static final int SCROLLABLE_TABS = 3;
    public static final int ICON_TABS = 4;
    public static final int ICON_TEXT_TABS = 5;
    public static final int ANIMATED_TABS = 9;

    private boolean mAnimate;
    private Animation mTabAnimation;
    private ViewPager mViewPager;
    private int mCurrentTab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Prepare Animation
        mTabAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.grow_fade_in_from_bottom);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tabs_parent, container, false);

        Toolbar toolbar = (Toolbar) layout.findViewById(R.id.toolbar);
        View fakeShadow = layout.findViewById(R.id.fakeShadow);
        mViewPager = (ViewPager) layout.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) layout.findViewById(R.id.tabs);
        // Hide fake shadow for Lollipop and above since the app bar already casts a shadow
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fakeShadow.setVisibility(View.GONE);
        }

        toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
        int mode = 0;

        Bundle bundle = getArguments();

        if (bundle != null) {
            // Get tab mode
            mode = bundle.getInt(MODE);
            mAnimate = mode == ANIMATED_TABS;
            if (actionBar != null) {
                actionBar.setTitle(bundle.getString(MODE_TITLE));
            }
        }

        int fragments = setupTabLayout(tabLayout, mode);

        CustomViewPagerAdapter adapter = setupAdapter(tabLayout, mode, fragments);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);

        // Add custom view to all tabs
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            final TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = adapter.createTabView(i);
            if (view != null && tab != null) {
                tab.setCustomView(view);

                if (i == 0) {
                    setAlpha(view, true);
                } else {
                    setAlpha(view, false);
                }
            }
        }

        // Set this fragment as this TabLayout listener
        tabLayout.setOnTabSelectedListener(this);

        // If the state was restored, select the previous selected tab
        if (savedInstanceState != null) {
            mCurrentTab = savedInstanceState.getInt(CURRENT_TAB);
            if (mCurrentTab != 0) {
                TabLayout.Tab tab = tabLayout.getTabAt(mCurrentTab);
                if (tab != null) {
                    tab.select();
                }
            }
        }

        return layout;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        // Get this tab view
        View v = tab.getCustomView();

        if (v != null) {
            setAlpha(v, true);

            if (mAnimate) {
                v.startAnimation(mTabAnimation);
            }
        }

        mCurrentTab = tab.getPosition();

        if (mViewPager.getCurrentItem() != mCurrentTab) {
            mViewPager.setCurrentItem(mCurrentTab);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

        // Get this tab view
        View v = tab.getCustomView();

        if (v != null) {
            setAlpha(v, false);

            // Stop the animation if the tab was animating.
            // This makes the transition smooth
            Animation viewAnimation = v.getAnimation();

            if (viewAnimation != null)
                viewAnimation.cancel();
        }

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private CustomViewPagerAdapter setupAdapter(TabLayout tabLayout, int mode, int fragments) {

        
        CustomViewPagerAdapter adapter = new CustomViewPagerAdapter(getActivity().getLayoutInflater(),
                getChildFragmentManager(), tabLayout, CustomViewPagerAdapter.TEXT_ONLY);
		tab tabFragment1 = new tab();
		Bundle args1 = new Bundle();
		//args1.putString(TabFragment.TAB_NUMBER, "" + ( 1));
		tabFragment1.setArguments(args1);
		adapter.addFragment(tabFragment1, "热门");
		
		mao tabFragment2 = new mao();
		Bundle args2 = new Bundle();
		//args2.putString(TabFragment.TAB_NUMBER,"" + ( 1));
		tabFragment2.setArguments(args2);
		adapter.addFragment(tabFragment2, "国产");
		
		mao1 tabFragment3 = new mao1();
		Bundle args3 = new Bundle();
       // args3.putString(TabFragment.TAB_NUMBER,"" + ( 1));
		tabFragment1.setArguments(args3);
		adapter.addFragment(tabFragment3, "动漫");
		
		mao2 tabFragment4 = new mao2();
		Bundle args4 = new Bundle();
		//args4.putString(TabFragment.TAB_NUMBER,"" + ( 1));
		//  args.putString(TabFragment.TAB_NUMBER, "" + (i + 1));
		tabFragment4.setArguments(args4);
		adapter.addFragment(tabFragment4, "欧美");
		
		mao3 mao3 = new mao3();
		Bundle args5 = new Bundle();
		//  args.putString(TabFragment.TAB_NUMBER, "" + (i + 1));
		mao3.setArguments(args5);
		adapter.addFragment(mao3, "短片");
		
		tab1 tab1 = new tab1();
		Bundle args6 = new Bundle();
		//  args.putString(TabFragment.TAB_NUMBER, "" + (i + 1));
		tab1.setArguments(args6);
		adapter.addFragment(tab1, "收藏");
        
        return adapter;

    }

    /**
     * Setup the TabLayout with a certain mode
     *
     * @param tabLayout TabLayout to be setup
     * @param mode      tabs' mode
     * @return number of fragments appropriate for the mode
     */
    private int setupTabLayout(TabLayout tabLayout, int mode) {
        int fragments = 9;
        if (mode == ANIMATED_TABS) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }

        return fragments;
    }

    private void setAlpha(View view, boolean selected) {
        if (selected) {
            if (Build.VERSION.SDK_INT < 11) {
                AlphaAnimation alpha = new AlphaAnimation(1F, 1F);
                alpha.setDuration(0);
                alpha.setFillAfter(true);
                view.startAnimation(alpha);
            } else {
                view.setAlpha(1);
            }
        } else {
            if (Build.VERSION.SDK_INT < 11) {
                AlphaAnimation alpha = new AlphaAnimation(0.7F, 0.7F);
                alpha.setDuration(0);
                alpha.setFillAfter(true);
                view.startAnimation(alpha);
            } else {
                view.setAlpha((float) 0.7);
            }
        }
    }

}
