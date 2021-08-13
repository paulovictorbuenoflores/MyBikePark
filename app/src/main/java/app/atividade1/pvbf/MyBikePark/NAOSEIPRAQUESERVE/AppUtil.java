package app.atividade1.pvbf.MyBikePark.NAOSEIPRAQUESERVE;

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

    /**
     * @return devolve a hora atual
     */
    public static String getHoraAtual() {

        String horaAtual = "00:00:00";

        String hora, minuto, segudo;

        try {

            Calendar calendar = Calendar.getInstance();

            int iHora = calendar.get(Calendar.HOUR_OF_DAY);
            int iMinuto = calendar.get(Calendar.MINUTE);
            int iSegundo = calendar.get(Calendar.SECOND);


            hora = (iHora <= 9) ? "0" + iHora : Integer.toString(iHora);
            minuto = (iMinuto <= 9) ? "0" + iMinuto : Integer.toString(iMinuto);
            segudo = (iSegundo <= 9) ? "0" + iSegundo : Integer.toString(iSegundo);

            horaAtual = hora + ":" + minuto + ":" + segudo;

            return horaAtual;


        } catch (Exception e) {

        }

        return horaAtual;

    }

    public static String getDataAtual() {
        String dataAtual;

        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        Date data = new Date();

        dataAtual = df.format(data);

        return dataAtual;
    }

    ////////////VERIFICAR SE CPF OU CNPJ É VALIDO ///////////
    public static boolean isCNPJ(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14))
            return (false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {

            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char) ((11 - r) + 48);


            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isCPF(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {

            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {

                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48);


            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    ////////////////////////Mascara/////////////////////////

    public static String mascaraCNPJ(String CNPJ) {

        String retorno;

        retorno = (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
                CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
                CNPJ.substring(12, 14));

        return retorno;
    }

    public static String mascaraCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

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
