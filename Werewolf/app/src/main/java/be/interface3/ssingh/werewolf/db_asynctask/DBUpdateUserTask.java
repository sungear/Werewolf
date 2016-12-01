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

/**
 * Created by s.singh on 4/10/2016.
 */
public class DBUpdateUserTask extends AsyncTask<String, Void, String> {

    private  IDBUpdateUserTask callback;

    public void setCallback(IDBUpdateUserTask callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String s ="";

        try {
            URL url = new URL(GlobalVariable.URL_QUERY_UPDATE + "?table=" + params[0] + "&column=" + params[1] + "&value=" + params [2] + "&refColumn=" + params[3] + "&refValue=" + params[4]
                    + "&refColumn2=" + params[5] + "&refValue2=" + params[6]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

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
        Log.i("updateResult", s);
        callback.updateUser();
    }

    public interface IDBUpdateUserTask{
        void updateUser();
    }
}
