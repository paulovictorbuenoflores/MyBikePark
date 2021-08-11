package app.atividade1.pvbf.MyBikePark.api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.model.ParquesORM;
import app.atividade1.pvbf.MyBikePark.view.mapas.MainMapActivity;

public class AdapterParques extends RecyclerView.Adapter<AdapterParques.ViewHolder> {

    private List<ParquesORM> aParques;//lista de parques adapter
    private Context aContext;
    //
    private ArrayList<ParquesORM> lista;

    public AdapterParques(List<ParquesORM> aParques, Context contextAdapter) {
   this.aParques=aParques;
   this.aContext=contextAdapter;
    }
    //


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View linhaView = inflater.inflate(R.layout.item_parque, parent, false);
        ViewHolder viewHolder = new ViewHolder(linhaView);
        return viewHolder;
    }



    //onBindViewHolder vai popular a nossa linha na tela do celular
    @Override
    public void onBindViewHolder(@NonNull AdapterParques.ViewHolder holder, int position) {
        ParquesORM objDaLinha = aParques.get(position);

        TextView txtPrimeiroNome = holder.rvPrimeiroNome;
        txtPrimeiroNome.setText(objDaLinha.getNome());
        ImageView imageView = holder.rvImageView;
        imageView.setImageBitmap(AppUtil.getImage(objDaLinha.getFoto()));
         //Button btnPessoaFisica = holder.rvPessoaFisica;
        //ImageView img= holder.itemView;
        // btnPessoaFisica.setText(objDaLinha.isPessoaFisica() ? "CPF" : "CNPJ");//teste ternario
       /* if (objDaLinha.isPessoaFisica()) {
            //cpf

        } else {
            //cnpj

        }*/
    }

    @Override
    public int getItemCount() {
        return aParques.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView rvPrimeiroNome;
        //public Button rvPessoaFisica;

        //
        public ImageView rvImageView;

        //
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvPrimeiroNome = itemView.findViewById(R.id.rvNome);
            // rvPessoaFisica = itemView.findViewById(R.id.rvPessoaFisica);
            rvImageView = (ImageView) itemView.findViewById(R.id.rvImageView);
            itemView.setOnClickListener(this);//esse this ta falando que e pra usar o onClick que esta abaixo
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ParquesORM clienteSelecionado = aParques.get(position);
            if (position != RecyclerView.NO_POSITION) {
                // Log.i(AppUtil.LOG_APP,"Cliente ID "+position+"Primeiro nome: "+clienteSelecionado.getPrimeiroNome());
                //Toast.makeText(aContext, "cliente ID" + position + "primeiro nome" + clienteSelecionado.getNome(), Toast.LENGTH_LONG).show();

                if (position == 0) {

                    Intent intent = new Intent(aContext, MainMapActivity.class);
                    aContext.startActivity(intent);
                } else if (position == 1) {

                    // Intent intent = new Intent(aContext, AlagoasActivity.class);
                    //aContext.startActivity(intent);
                } else if (position == 2) {
                    //Intent intent = new Intent(aContext, AmapaActivity.class);
                    //aContext.startActivity(intent);
                }else if (position == 9) {
                    Intent intent = new Intent(aContext, MainMapActivity.class);
                    aContext.startActivity(intent);
                }
            }
        }
    }

}
