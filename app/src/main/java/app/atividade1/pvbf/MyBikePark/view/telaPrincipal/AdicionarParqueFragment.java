package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.io.File;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AdapterParques;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ParqueController;
import app.atividade1.pvbf.MyBikePark.model.ParquesORM;
import app.atividade1.pvbf.MyBikePark.view.usuario.CadastroUsuarioActivity;
import app.atividade1.pvbf.MyBikePark.view.usuario.LoginUserActivity;

import static android.app.Activity.RESULT_OK;
import static io.realm.Realm.getApplicationContext;


public class AdicionarParqueFragment extends Fragment {
    public static int IMAGEM_INTERNA = 12;
    private final int GALERIA_IMAGENS=1;
    View view;
    List<ParquesORM> parqueList;
    AdapterParques adapter;
    ParquesORM parquesORM;
    RecyclerView recyclerView;
    TextView txtIMG;
    EditText editTextNome, editTextLatitude, editTextLongitude;
    Button btCancelar, btSalvar;
    ParqueController controller;
    Boolean isFormularioOK;
    ImageView imageView;
    Bitmap bitmapGlobal;

    public AdicionarParqueFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.adicionar_parque, container, false);

        TextView txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTitulo.setText("Adicionar Parque");
        initFormulario();






        //btsalvar
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


                   // ImageView imageView = new ImageView(getApplicationContext());


                    controller =new ParqueController(getApplicationContext());
                    parquesORM = new ParquesORM();
                    parquesORM.setNome(editTextNome.getText().toString());
                    parquesORM.setLongitude(Double.parseDouble(editTextLongitude.getText().toString()));
                    parquesORM.setLatitude(Double.parseDouble(editTextLatitude.getText().toString()));
                    parquesORM.setFoto(AppUtil.convertImageViewToByteArray(imageView));
                    controller.insert(parquesORM);



                    Toast.makeText(getApplicationContext(), "cadastrando", Toast.LENGTH_SHORT).show();



                }
            }
        });
        //btcancelar
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        //pegar img
        txtIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGEM_INTERNA);*/
                Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALERIA_IMAGENS);

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   /*  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGEM_INTERNA);*/
                Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALERIA_IMAGENS);
            }
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
        imageView=view.findViewById(R.id.imgPark);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode,intent);
        if (requestCode == GALERIA_IMAGENS) {
            if (resultCode == RESULT_OK && requestCode ==1)  {
                Uri imagemSelecionada = intent.getData();

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
   /* public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode,intent);
        if (requestCode == IMAGEM_INTERNA) {
            if (resultCode == RESULT_OK && requestCode ==1)  {
                Uri imagemSelecionada = intent.getData();

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
    }*/

}
