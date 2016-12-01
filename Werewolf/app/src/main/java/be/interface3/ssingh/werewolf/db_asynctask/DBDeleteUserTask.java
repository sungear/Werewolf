package be.interface3.ssingh.werewolf.db_asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import be.interface3.ssingh.werewolf.GlobalVariable;
import be.interface3.ssingh.werewolf.model.User;

/**
 * Created by s.singh on 10/10/2016.
 */
public class DBDeleteUserTask extends AsyncTask<String, Void, String> {

    public interface IDBDeleteUserTask {

    }

    private IDBDeleteUserTask callback;

    public void setCallback(IDBDeleteUserTask callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String s ="";

        try {
            URL url = new URL(GlobalVariable.URL_QUERY_DELETE + "?table=" + params[0] + "&column=" + params[1] + "&value=" + params[2]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            GlobalVariable.USER.setUsername("");
            GlobalVariable.USER.setRole("");
            GlobalVariable.USER.setConnectedStatus(false);
            GlobalVariable.USER.setActivityStatus(false);
            GlobalVariable.USER.setLivingStatus(false);
            GlobalVariable.USER.setVictim(false);
            GlobalVariable.USER.setGM(false);

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder out = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null){
                out.append(line);
            }
            s += out;
            reader.close();
            inputStream.close();

            httpURLConnection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return s;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("DELETED", s);
    }
}
