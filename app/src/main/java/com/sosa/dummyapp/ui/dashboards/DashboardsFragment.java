package com.sosa.dummyapp.ui.dashboards;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.sosa.dummyapp.contentresources.DashboardResource;
import com.sosa.dummyapp.tasks.GetDashboardContentTask;
import com.sosa.dummyapp.R;

public class DashboardsFragment extends Fragment {

    ViewPager2 viewpager2;
    TabLayout tabLayout;
    DashboardFragmentAdapter adapter;
    DashboardsFragment thisReference = this;


    private static final String TAG = "DashboardFragment";
    private static final String webhost = "https://smartplugapi-dummy.herokuapp.com/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboards, container, false);


        //emulator host loopback url     //"http://127.0.0.1:5000";
//        String localhost = getResources().getString(R.string.emulator_local_host);
        viewpager2 = root.findViewById(R.id.view_pager_frag);
        tabLayout = root.findViewById(R.id.tab_layout_frag);
        FragmentManager manager = getParentFragmentManager();
        adapter = new DashboardFragmentAdapter(manager, getLifecycle());
        viewpager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Day"));
        tabLayout.addTab(tabLayout.newTab().setText("Plugs"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager2.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0 :
                        Log.i(TAG, "Month tab");
                        GetDashboardContentTask monthTask = new GetDashboardContentTask(thisReference);
                        monthTask.setTarget("month");
                        monthTask.execute(webhost + "/dashboard/month");
                        break;
                    case 1 :
                        Log.i(TAG, "Day tab");
                        GetDashboardContentTask dayTask = new GetDashboardContentTask(thisReference);
                        dayTask.setTarget("day");
                        dayTask.execute(webhost + "/dashboard/day");
                        break;
                    case 2 :
                        Log.i(TAG, "Plug tab");
                        break;
                    default: Log.i(TAG, "Invalid tab selected");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //Need to execute this at start to populate graph
        GetDashboardContentTask monthTask = new GetDashboardContentTask(thisReference);
        monthTask.setTarget("month");
        monthTask.execute(webhost + "/dashboard/month");

        return root;

    }


    public void updateMonthGraph(DashboardResource res){
        Log.i(TAG, "DashboardFragment got Month Resource");
        adapter.clearMonth();
        adapter.postMonthResource(res);
    }

    public void updateDayGraph(DashboardResource res){
        Log.i(TAG, "DashboardFragment got Day Resource");
        adapter.clearDay();
        adapter.postDayResource(res);
    }

}