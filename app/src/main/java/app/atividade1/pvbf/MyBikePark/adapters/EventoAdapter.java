package app.atividade1.pvbf.MyBikePark.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.model.Evento;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    List<Evento> eventos;
    Context context;
    public EventoAdapter(Context context, List<Evento> eventos){
        this.context = context;
        this.eventos = eventos;
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventoAdapter.EventoViewHolder holder, int position) {
        Evento evento = eventos.get(holder.getAdapterPosition());
        holder.eventoNome.setText(evento.getNome());
        holder.eventoPessoas.setText(evento.getPessoas()+"");
        holder.eventoDias.setText(evento.getData()+"");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abrir tela de detalhes do evento aqui
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder{
        TextView eventoNome, eventoPessoas, eventoDias;
        CardView cardView;
        public EventoViewHolder( View itemView) {
            super(itemView);
            eventoNome = itemView.findViewById(R.id.evento_nome);
            eventoDias = itemView.findViewById(R.id.evento_dias);
            eventoPessoas = itemView.findViewById(R.id.evento_pessoas);
            cardView = itemView.findViewById(R.id.evento_cardView);
        }
    }
}
