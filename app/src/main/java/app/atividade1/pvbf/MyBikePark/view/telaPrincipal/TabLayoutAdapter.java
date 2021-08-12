package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabLayoutAdapter extends FragmentStateAdapter {
    public TabLayoutAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new ListarClientesCardsFragment();
        }
        return new ListaDeParquesFragmentoRecyclerView();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
