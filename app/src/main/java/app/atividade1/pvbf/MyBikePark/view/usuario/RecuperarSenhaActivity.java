package app.atividade1.pvbf.MyBikePark.view.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.atividade1.pvbf.MyBikePark.R;

public class RecuperarSenhaActivity extends AppCompatActivity {
    EditText editEmail;
    Button btRecuperar, btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        initFormulario();

        btRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarFormulario()) {
                    Toast.makeText(getApplicationContext(), "Sua senha foi enviado para o email cadastrado!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RecuperarSenhaActivity.this, LoginUserActivity.class);
                    startActivity(intent);
                    finish();
                    return;

                }
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecuperarSenhaActivity.this, LoginUserActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    private void initFormulario() {
        editEmail = findViewById(R.id.editEmailRecuperar);
        btRecuperar = findViewById(R.id.btSalvarRecuperarSenha);
        btVoltar = findViewById(R.id.btVoltarRecuperarSenha);
    }

    private boolean validarFormulario() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editEmail.getText().toString())) {
            editEmail.setError("*");
            editEmail.requestFocus();
            retorno = false;
        }

        return retorno;
    }
}