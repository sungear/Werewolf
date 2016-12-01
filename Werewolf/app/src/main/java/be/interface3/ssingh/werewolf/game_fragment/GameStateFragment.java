package be.interface3.ssingh.werewolf.game_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBSelectUserTask;
import be.interface3.ssingh.werewolf.db_asynctask.DBUpdateUserTask;

/**
 * Created by s.singh on 17/10/2016.
 */
public class GameStateFragment extends Fragment implements DBSelectUserTask.IDBSelectUserTask, DBUpdateUserTask.IDBUpdateUserTask{

    private int gameState = 0;
    private TextView tv_gameStateFragment_narration;

    private static GameStateFragment instance;

    public static GameStateFragment getInstance() {
        if (instance == null) {
            instance = new GameStateFragment();
        }
        return instance;
    }

    @Override
    public void updateUser() {
        refreshState();
    }

    public interface IGameStateFragment {
        void getState(int state);
    }

    private IGameStateFragment callback;

    public void setCallback(IGameStateFragment callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_state, container, false);

        tv_gameStateFragment_narration = (TextView) view.findViewById(R.id.tv_gameStateFragment_narration);
        updateState(gameState);

        return view;
    }

    private void refreshState() {
        DBSelectUserTask dbSelectUserTask = new DBSelectUserTask();
        dbSelectUserTask.setCallback(this);
        dbSelectUserTask.execute("party", "gameState" , "name", /*GlobalVariable.PARTY.getName()*/"test", "name", /*GlobalVariable.PARTY.getName()*/"test");
    }

    public void updateState(int state) {
        Log.i("UPDATE_STATE", "TRUE");
        DBUpdateUserTask dbUpdateUserTask = new DBUpdateUserTask();
        dbUpdateUserTask.setCallback(this);
        String gameState = String.valueOf(state);
        Log.i("gameState", gameState);
        dbUpdateUserTask.execute("party", "gameState", gameState, "name", /*GlobalVariable.PARTY.getName()*/"test", "name", /*GlobalVariable.PARTY.getName()*/"test");
    }

    @Override
    public void getSelectedData(ArrayList<String> dataList) {
        gameState = Integer.parseInt(dataList.get(0));
        callback.getState(gameState);
    }
}
