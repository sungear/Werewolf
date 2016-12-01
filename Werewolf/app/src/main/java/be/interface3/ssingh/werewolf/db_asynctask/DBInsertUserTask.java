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
 * Created by s.singh on 3/10/2016.
 */
public class DBInsertUserTask extends AsyncTask<String, Void, String> {

    private  IDBInsertUserTask callback;


    public void setCallback(IDBInsertUserTask callback) {
        this.callback = callback;
    }

    public interface IDBInsertUserTask{
        void checkIfCanContinue();
    }

    @Override
    protected String doInBackground(String... params) {
        String s ="";
//        User u = new User();

        try {
            URL url = new URL(GlobalVariable.URL_QUERY_INSERT + "?table=" + params[0] + "&username=" + params[1] + "&role=" + params[2]
                    + "&isConnected=" + params[3] + "&isActive=" + params[4] + "&isAlive=" + params[5] + "&isVictim=" + params[6] + "&isGM=" + params[7]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            GlobalVariable.USER.setUsername(params[1]);
            GlobalVariable.USER.setRole(params[2]);
            GlobalVariable.USER.setConnectedStatus(Boolean.valueOf(params[3]));
            GlobalVariable.USER.setActivityStatus(Boolean.valueOf(params[4]));
            GlobalVariable.USER.setLivingStatus(Boolean.valueOf(params[5]));
            GlobalVariable.USER.setVictim(Boolean.valueOf(params[6]));
            GlobalVariable.USER.setGM(Boolean.valueOf(params[7]));

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
        Log.i("INSERT", s);
        Log.i(GlobalVariable.USER_USERNAME, GlobalVariable.USER.getUsername());
    }
}
