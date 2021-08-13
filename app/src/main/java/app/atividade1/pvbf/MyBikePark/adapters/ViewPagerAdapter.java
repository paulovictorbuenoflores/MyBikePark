package app.atividade1.pvbf.MyBikePark.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.gms.maps.MapFragment;

import app.atividade1.pvbf.MyBikePark.fragments.DadosCadastradosFragment;
import app.atividade1.pvbf.MyBikePark.fragments.MapaFragment;
import app.atividade1.pvbf.MyBikePark.fragments.ParquesFragment;
import app.atividade1.pvbf.MyBikePark.fragments.UsuariosFragment;
import app.atividade1.pvbf.MyBikePark.model.Parque;

public class ViewPagerAdapter extends FragmentStateAdapter implements ParquesFragment.ParquesFramentListener {
    public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, DadosCadastradosFragment dadosCadastradosFragment) {
        super(fragmentManager, lifecycle);
        listener = (ViewPagerAdapterListener) dadosCadastradosFragment;
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new UsuariosFragment();
        }
        return new ParquesFragment(this);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void getParque(Parque parque) {
        listener.getParque(parque);
    }
    private ViewPagerAdapterListener listener;
    public interface ViewPagerAdapterListener{
        public void getParque(Parque parque);
    }




}
