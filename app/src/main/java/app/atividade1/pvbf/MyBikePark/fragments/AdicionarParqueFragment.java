package app.atividade1.pvbf.MyBikePark.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ParqueController;
import app.atividade1.pvbf.MyBikePark.activities.MainActivity;
import app.atividade1.pvbf.MyBikePark.model.Parque;

import static android.app.Activity.RESULT_OK;
import static io.realm.Realm.getApplicationContext;


public class AdicionarParqueFragment extends Fragment {
    private final int GALERIA_IMAGENS=1;
    View view;
    Bitmap bitmapGlobal;
    Parque parque;
    TextView txtIMG;
    EditText editTextNome, editTextLatitude, editTextLongitude;
    Button btCancelar, btSalvar;
    ParqueController controller;
    Boolean isFormularioOK;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adicionar_parque, container, false);
        initFormulario();
        btSalvar.setOnClickListener(view -> {
            isFormularioOK = true;
            if (TextUtils.isEmpty(editTextNome.getText().toString())) {
                isFormularioOK = false;
                editTextNome.setError("*");
                editTextNome.requestFocus();
            }
            if (TextUtils.isEmpty(editTextLatitude.getText().toString())) {
                isFormularioOK = false;
                editTextLatitude.setError("*");
                editTextLatitude.requestFocus();
            }
            if (TextUtils.isEmpty(editTextLongitude.getText().toString())) {
                isFormularioOK = false;
                editTextLongitude.setError("*");
                editTextLongitude.requestFocus();
            }
            if (TextUtils.isEmpty(txtIMG.getText().toString())) {
                isFormularioOK = false;
                txtIMG.setError("*");
                txtIMG.requestFocus();
            }
            if (isFormularioOK != false) {
                controller =new ParqueController();
                parque = new Parque();
                parque.setNome(editTextNome.getText().toString());
                parque.setLongitude(Double.parseDouble(editTextLongitude.getText().toString()));
                parque.setLatitude(Double.parseDouble(editTextLatitude.getText().toString()));
                try {
                    parque.setFoto(AppUtil.convertImageViewToByteArray(imageView));
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
                controller.insert(parque);
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        btCancelar.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), MainActivity.class));
        });
        txtIMG.setOnClickListener(view -> {
            Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,GALERIA_IMAGENS);

        });
        imageView.setOnClickListener(view -> {
            Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,GALERIA_IMAGENS);
        });
        return view;
    }

    private void initFormulario() {
        editTextNome = view.findViewById(R.id.editNomeParque);
        editTextLatitude = view.findViewById(R.id.editLatitude);
        editTextLongitude = view.findViewById(R.id.editLondigtude);
        txtIMG = view.findViewById(R.id.txtIMG);
        btCancelar = view.findViewById(R.id.btnCancelar);
        btSalvar = view.findViewById(R.id.btnSalvar);
        imageView = view.findViewById(R.id.imgPark);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERIA_IMAGENS) {
            if (resultCode == RESULT_OK && requestCode ==1)  {
                Uri imagemSelecionada = data.getData();

                String[] colunas ={MediaStore.Images.Media.DATA};

                Cursor cursor = getApplicationContext().getContentResolver().query(imagemSelecionada, colunas,null,null,null);
                cursor.moveToFirst();
                int indexColuna = cursor.getColumnIndex(colunas[0]);
                String pathImg =cursor.getString(indexColuna);
                cursor.close();

                Bitmap bitmap= BitmapFactory.decodeFile(pathImg);
                bitmapGlobal=bitmap;
                imageView.setImageBitmap(bitmap);

            }

        }
    }

}
