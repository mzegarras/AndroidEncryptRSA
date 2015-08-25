package net.msonic.cifrardata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnclick(View v){




        try {


            X509Certificate cert = X509Certificate.getInstance(getResources().getAssets().open("public.crt"));


            Log.d(MainActivity.class.getCanonicalName(), "OK");

            Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            encryptCipher.init(Cipher.ENCRYPT_MODE, cert.getPublicKey());


            String message = "secret message, es una pruebas de clientes. Manuel Zegarra";
            byte[] messageACrypter = message.getBytes();
            byte[] messageCrypte = encryptCipher.doFinal(messageACrypter);



            String messageCrypteB64 = Base64.encodeToString(messageCrypte, Base64.DEFAULT);
            Log.d(MainActivity.class.getCanonicalName(), "messageACrypter : '" + messageCrypteB64 + "'");
            //System.out.println("Source crypted: "+Base64.encodeBytes(messageCrypte));




            /*
            InputStream is = getResources().getAssets().open("public.crt");
            int size = is.available();
            byte[] buffer = new byte[size]; //declare the size of the byte array with size of the file
            is.read(buffer); //read file
            is.close(); //close file


            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(buffer);
            RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
            */

            //Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");





        } catch (IOException e) {
            e.printStackTrace();
        /*} catch (CertificateException e) {
            e.printStackTrace();*/

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

    }
}
