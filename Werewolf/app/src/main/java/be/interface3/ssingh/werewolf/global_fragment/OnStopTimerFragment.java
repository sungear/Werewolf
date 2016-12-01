package be.interface3.ssingh.werewolf.global_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import be.interface3.ssingh.werewolf.GlobalVariable;
import be.interface3.ssingh.werewolf.db_asynctask.DBDeleteUserTask;

/**
 * Created by s.singh on 14/10/2016.
 */
public class OnStopTimerFragment extends Fragment implements DBDeleteUserTask.IDBDeleteUserTask{

    private Timer timer;

    private static OnStopTimerFragment instance;

    public static OnStopTimerFragment getInstance() {
        if (instance == null) {
            instance = new OnStopTimerFragment();
        }
        return instance;
    }

    public interface IOnStopTimerFragment {

    }

    private IOnStopTimerFragment callback;

    public void setCallback(IOnStopTimerFragment callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        super.onResume();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("DELETING_USERNAME", GlobalVariable.USER.getUsername());
        final DBDeleteUserTask dbDeleteUserTask = new DBDeleteUserTask();
        dbDeleteUserTask.setCallback(this);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getActivity().finish();
                dbDeleteUserTask.execute("user", "username", GlobalVariable.USER.getUsername());
                Log.i("DESTROYED", "true");
            }
        };
        timer = new Timer("logOut");
        timer.schedule(task, /*18000000*/10000);
        Log.i("STOPPED", "true");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
