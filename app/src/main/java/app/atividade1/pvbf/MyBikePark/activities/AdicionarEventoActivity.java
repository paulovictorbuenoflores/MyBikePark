package app.atividade1.pvbf.MyBikePark.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.model.Evento;
import app.atividade1.pvbf.MyBikePark.controller.EventoController;

public class AdicionarEventoActivity extends AppCompatActivity {
    EditText editNome, editDescricao, editData;
    Button btSalvar, btCancelar;
    boolean isFormulario = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_evento);

        iniciarFormulario();


        btSalvar.setOnClickListener(view -> {
            if (isFormulario = validarFormulario()) {
                Evento evento = new Evento();
                EventoController eventoController = new EventoController();
                evento.setNome(editNome.getText().toString());
                evento.setDescricao(editDescricao.getText().toString());
                evento.setData(editData.getText().toString());
                eventoController.insert(evento);
                Toast.makeText(AdicionarEventoActivity.this, "Evento Salvo Com Sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdicionarEventoActivity.this, MainActivity.class));
                finish();
            }

        });
        btCancelar.setOnClickListener(view -> {
            Toast.makeText(this, "Cancelado!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdicionarEventoActivity.this, MainActivity.class));
            finish();


        });

    }

    private void iniciarFormulario() {
        editNome = findViewById(R.id.edit_adicionar_envento_nome);
        editDescricao = findViewById(R.id.edit_adicionar_envento_descricao);
        editData = findViewById(R.id.edit_adicionar_envento_Data);
        btSalvar = findViewById(R.id.bt_adicionar_evento_salvar);
        btCancelar = findViewById(R.id.bt_adicionar_evento_cancelar);
    }

    private boolean validarFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editNome.getText().toString())) {
            editNome.setError("*");
            editNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editDescricao.getText().toString())) {
            editDescricao.setError("*");
            editDescricao.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editData.getText().toString())) {
            editData.setError("*");
            editData.requestFocus();
            retorno = false;
        }
        return retorno;
    }
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
    }
}