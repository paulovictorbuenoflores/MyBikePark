package app.atividade1.pvbf.MyBikePark.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.activities.MainActivity;
import app.atividade1.pvbf.MyBikePark.adapters.ViewPagerAdapter;
import app.atividade1.pvbf.MyBikePark.model.Parque;

public class DadosCadastradosFragment extends Fragment implements ViewPagerAdapter.ViewPagerAdapterListener {

    public DadosCadastradosFragment(MainActivity mainActivity){
        this.listener = (DadosCastradosFragmentListener) mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dados_cadastrados, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle(), DadosCadastradosFragment.this);
        viewPager2.setAdapter(viewPagerAdapter);
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

        return view;
    }

    @Override
    public void getParque(Parque parque) {
        listener.getParque(parque);
    }
    private DadosCastradosFragmentListener listener;
    public interface DadosCastradosFragmentListener{
        public void getParque(Parque parque);
    }
}