package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import app.atividade1.pvbf.MyBikePark.R;

public class TabLayoutFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view =  inflater.inflate(R.layout.fragment_tab_layout, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(tabLayoutAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Lista de parques"));
        tabLayout.addTab(tabLayout.newTab().setText("Lista de bikers"));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return  view;
    }
}