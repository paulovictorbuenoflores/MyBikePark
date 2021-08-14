package app.atividade1.pvbf.MyBikePark.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Calendar;

/**
 * Classe de apoio contendo metodos que podem
 * ser reutilizados em todo o projeto
 * <p>
 * criada por PVBF- 01/2020
 * <p>
 * versão v1
 */

public class AppUtil {
    public static final String TAG = "DB_Crud";

    public static final int Time_SPLASH = 1 * 1000;
    public static final String PREF_APP = "app_ExpLenha";
    // public  static  final String Log_LOGIN = "app_TentativaLogin";
    public static final String LOG_APP = "LOG_APP";

    //Imagens
    //public static final String URL_IMG_BACKGROUD = "https://i.imgur.com/MDeiVnab.jpg";
    public static final String URL_IMG_BACKGROUD = "https://www.mountainbikingbc.ca/site/assets/files/1991/whistler-_vancouver_coast_and_mountains-_mountain_biking_bc_2_-_tourism_whistler-mike_crane.1350x760p46x57.webp";
    public static final String URL_IMG_LOGO = "https://www.ifgoiano.edu.br/home/images/REITORIA/Imagens/2017/Maio_2017/institucional.png";
    // public static final String URL_IMGSPLASH ="https://i.imgur.com/MDeiVnab.jpg";
    //https://i.imgur.com/4GVIOXEb.jpg
    //https://www.ifgoiano.edu.br/home/images/REITORIA/Imagens/2017/Maio_2017/institucional.png
    public static final String URL_IMGSPLASH = "https://www.ifgoiano.edu.br/home/images/REITORIA/Imagens/2017/Maio_2017/institucional.png";
    public static final String PREF_P ="p";





    /////////////////////CRIPTOGRAFIA DE SENHA ////////////

    public static String gerarMD5Hash(String password) {

        String retorno = "";

        if (!password.isEmpty()) {

            retorno = "falhou";

            try {
                // Create MD5 Hash
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(password.getBytes());
                byte messageDigest[] = digest.digest();

                StringBuffer MD5Hash = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++) {
                    String h = Integer.toHexString(0xFF & messageDigest[i]);
                    while (h.length() < 2)
                        h = "0" + h;
                    MD5Hash.append(h);
                }

                return MD5Hash.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
        return retorno;
    }
/////////////////////Descriptografia de senha MD5Hash Nao existe, temos que bater senha criptografada ////////////////////






    /////////////////////////converter img ///////////////////////////

    public static byte[] convertImageViewToByteArray(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    ///////////////////////////////////////////////////////////////
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


}
