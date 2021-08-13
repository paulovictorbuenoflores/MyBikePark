package app.atividade1.pvbf.MyBikePark.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.model.Usuario;

public class CadastroPassoUmActivity extends AppCompatActivity {
    EditText editPrimeiroNome, editSobrenome;
    Button btSalvarContinuar, btCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_passo_um);

        initFormulario();
        Usuario usuario = new Usuario();
        btCancelar.setOnClickListener(view -> new FancyAlertDialog.Builder(CadastroPassoUmActivity.this)
                .setTitle("Confirme o Cancelamento")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Deseja realmente Cancelar ?")
                .setNegativeBtnText("Não")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Sim")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Intent intent = new Intent(CadastroPassoUmActivity.this, LoginUserActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(() -> Toast.makeText(getApplicationContext(), "Continue o Cadastro !", Toast.LENGTH_LONG).show())
                .build());

        //salvar e continuar
        btSalvarContinuar.setOnClickListener(view -> {
            if (validarFormulario()) {
                usuario.setPrimeiroNome(editPrimeiroNome.getText().toString());
                usuario.setSobreNome(editSobrenome.getText().toString());
                Intent intent = new Intent(CadastroPassoUmActivity.this, CadastroPassoDoisActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", usuario);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean validarFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editPrimeiroNome.getText().toString())) {
            editPrimeiroNome.setError("*");
            editPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSobrenome.getText().toString())) {
            editSobrenome.setError("*");
            editSobrenome.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    private void initFormulario() {
        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobreNome);
        btSalvarContinuar = findViewById(R.id.btSalvar);
        btCancelar = findViewById(R.id.btCancelar);
    }
}