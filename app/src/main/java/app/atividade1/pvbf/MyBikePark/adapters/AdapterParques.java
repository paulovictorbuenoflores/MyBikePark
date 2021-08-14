package app.atividade1.pvbf.MyBikePark.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.AppUtil;
import app.atividade1.pvbf.MyBikePark.fragments.ParquesFragment;
import app.atividade1.pvbf.MyBikePark.model.Parque;

public class AdapterParques extends RecyclerView.Adapter<AdapterParques.ViewHolder> {
    private AdapterParquesListener listener;
    private List<Parque> parques;
    private Context context;

    public AdapterParques(List<Parque> parques, Context context, ParquesFragment parquesFragment) {
        this.parques = parques;
        this.context = context;
        listener = (AdapterParquesListener) parquesFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_parque, parent, false));
    }

    @Override
    public void onBindViewHolder(AdapterParques.ViewHolder holder, int position) {
        Parque parque = parques.get(holder.getAdapterPosition());
        holder.primeiroNome.setText(parque.getNome());
        try{
            holder.imagemParque.setImageBitmap(AppUtil.getImage(parque.getFoto()));
        } catch (NullPointerException e){
            holder.imagemParque.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_account_circle_24));
        }
        holder.linearLayout.setOnClickListener(view -> {
            listener.getParque(parque);
        });
    }

    @Override
    public int getItemCount() {
        return parques.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView primeiroNome;
        private ImageView imagemParque;
        private LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            primeiroNome = itemView.findViewById(R.id.rvNome);
            imagemParque = itemView.findViewById(R.id.rvImageView);
            linearLayout = itemView.findViewById(R.id.adaaf);
        }
    }

    public interface AdapterParquesListener{
        public Parque getParque(Parque parque);
    }
}
