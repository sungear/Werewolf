package be.interface3.ssingh.werewolf.db_asynctask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import be.interface3.ssingh.werewolf.GlobalVariable;


/**
 * Created by s.singh on 30/09/2016.
 */
public class DBSelectUserTask extends AsyncTask<String, Void, String> {


    String param ="";
    private  IDBSelectUserTask callback;

    public void setCallback(IDBSelectUserTask callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String s ="";

        try {
            URL url = new URL(GlobalVariable.URL_QUERY_SELECT + "?table=" + params[0] + "&column=" + params[1] + "&refColumn=" + params[2] + "&refValue=" + params[3]
            + "&refColumn2=" + params[4] + "&refValue2=" + params[5]);

            param = params[1];

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
        ArrayList<String> dataList = new ArrayList<String>();
        try {
            JSONArray mainArray = new JSONArray(s);
            for (int i = 0; i < mainArray.length(); i++) {
                JSONObject userJSON = mainArray.getJSONObject(i);
                String data = userJSON.getString(param);
                dataList.add(data);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("SELECT", s);
        callback.getSelectedData(dataList);
    }

    public interface IDBSelectUserTask{
        void getSelectedData(ArrayList<String> dataList);
    }
}
