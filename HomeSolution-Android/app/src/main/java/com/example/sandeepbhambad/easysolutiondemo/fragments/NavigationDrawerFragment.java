package com.example.sandeepbhambad.easysolutiondemo.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sandeepbhambad.easysolutiondemo.R;
import com.example.sandeepbhambad.easysolutiondemo.activities.Feedback;
import com.example.sandeepbhambad.easysolutiondemo.activities.Home;
import com.example.sandeepbhambad.easysolutiondemo.adapters.NavigationAdapter;
import com.example.sandeepbhambad.easysolutiondemo.models.NavigationListData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements NavigationAdapter.ClickListener {

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private View containerView;
    private NavigationAdapter adpater;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPrefrences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));

        if (!mFromSavedInstanceState) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.draweList);
        adpater = new NavigationAdapter(getActivity(), getData());
        adpater.setClickListner(this);
        recyclerView.setAdapter(adpater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<NavigationListData> getData() {
        List<NavigationListData> data = new ArrayList<>();
        int[] icons = {R.drawable.home, R.drawable.schedule, R.drawable.feedback, R.drawable.phone};
        String[] title = {"Home", "My Bokking", "Feedback", "Call"};

        for (int i = 0; i < icons.length && i < title.length; i++) {
            NavigationListData currnt = new NavigationListData();
            currnt.setIconId(icons[i]);
            currnt.setTitle(title[i]);
            data.add(currnt);
        }

        return data;
    }


    public void setUp(int fragmenId, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmenId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPrefrences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPrefrences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreference = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }

    public static String readFromPrefrences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreference = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreference.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {

        if (position == 0) {
//            Intent i = new Intent(getActivity(), Feedback.class);
//            startActivity(i);
            mDrawerLayout.closeDrawers();
        } else if (position == 1) {
            Intent i = new Intent(getActivity(), Feedback.class);
            startActivity(i);
        } else if (position == 2) {
            Intent i = new Intent(getActivity(), Feedback.class);
            startActivity(i);
        } else if (position == 3) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8692988558"));
            startActivity(intent);
        }

    }
}
