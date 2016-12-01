package be.interface3.ssingh.werewolf.activity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBSelectUserTask;
import be.interface3.ssingh.werewolf.game_fragment.GameStateFragment;
import be.interface3.ssingh.werewolf.game_fragment.StatusFragment;
import be.interface3.ssingh.werewolf.global_fragment.MenuFragment;
import be.interface3.ssingh.werewolf.global_fragment.OnStopTimerFragment;
import be.interface3.ssingh.werewolf.global_fragment.PopUpWindowFragment;
import be.interface3.ssingh.werewolf.role_fragment.WerewolfFragment;

public class GameActivity extends AppCompatActivity implements StatusFragment.IStatusFragmentCallback, PopUpWindowFragment.IPopUpWindowFragment, OnStopTimerFragment.IOnStopTimerFragment,
        MenuFragment.IMenuFragment, GameStateFragment.IGameStateFragment, WerewolfFragment.IWerewolfFragment, DBSelectUserTask.IDBSelectUserTask{

    private StatusFragment statusFragment;
    private PopUpWindowFragment popUpWindowFragment;
    private GameStateFragment gameStateFragment;

    private TextView tv_gameActivity_narration;

    private boolean witchExists;
    private boolean seerExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        witchExists = false;
        seerExists = false;

        tv_gameActivity_narration = (TextView) findViewById(R.id.tv_gameActivity_narration);

        statusFragment = StatusFragment.getInstance();
        statusFragment.setCallback(this);
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.add(R.id.fl_gameActivity_statusFragment, statusFragment);
        transaction.commit();
//
//        popUpWindowFragment = PopUpWindowFragment.getInstance();
//        popUpWindowFragment.setCallback(this);
//        FragmentTransaction transactionPopUp = getFragmentManager().beginTransaction();
//        transactionPopUp.add(popUpWindowFragment, "popUp");
//        transactionPopUp.commit();

        OnStopTimerFragment onStopTimerFragment = OnStopTimerFragment.getInstance();
        onStopTimerFragment.setCallback(this);
        FragmentTransaction transactionTimer = getFragmentManager().beginTransaction();
        transactionTimer.add(onStopTimerFragment, "timer");
        transactionTimer.commit();

        MenuFragment menuFragment = MenuFragment.getInstance();
        menuFragment.setCallback(this);
        FragmentTransaction transactionMenu = getFragmentManager().beginTransaction();
        transactionMenu.add(menuFragment, "menu");
        transactionMenu.commit();

        DBSelectUserTask dbSelectUserTask = new DBSelectUserTask();
        dbSelectUserTask.setCallback(this);
        dbSelectUserTask.execute("user", "role", "isActive", "1", "isAlive", "1");
    }

    @Override
    public void onBackPressed() {
//        popUpWindowFragment.popUpWindowAgain();
    }

    @Override
    public void getState(int state) {
        switch (state) {
            case 0:
                tv_gameActivity_narration.setText("Everyone is going to sleep");
                Log.i("STATE_00", "TRUE");
                startTime(1);
                break;
            case 1:
                tv_gameActivity_narration.setText("The Werewolf is waking");
                Log.i("STATE_01", "TRUE");
                WerewolfFragment werewolfFragment = WerewolfFragment.getInstance();
                werewolfFragment.setCallback(this);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fl_gameActivity_roleFragment,werewolfFragment);
                transaction.commit();
                break;
            case 2:
                tv_gameActivity_narration.setText("The Witch is waking");
                break;
            case 3:
                tv_gameActivity_narration.setText("The Seer is waking");
                break;
            case 4:
                tv_gameActivity_narration.setText("Everyone is waking");
                break;
            case 5:
                tv_gameActivity_narration.setText("There is " + " dead");
                break;
        }
    }

    private void startTime(final int gameState) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameStateFragment.updateState(gameState);
            }
        };
        Timer timer = new Timer("nextStateTimer");
        timer.schedule(task, 10000);
    }

    @Override
    public void updateStateWerewolfConfirm() {
        if(witchExists) {
            gameStateFragment.updateState(2);
        }
        else if(!witchExists && seerExists) {
            gameStateFragment.updateState(3);
        }
        else if (!witchExists && !seerExists) {
            gameStateFragment.updateState(4);
        }
    }

    @Override
    public void getSelectedData(ArrayList<String> dataList) {
        Log.i("LIST", dataList.toString());
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i) == "Witch") {
                witchExists = true;
            }
            if (dataList.get(i) == "Seer") {
                seerExists = true;
            }
            if (witchExists && seerExists) {
                break;
            }
        }

        gameStateFragment = GameStateFragment.getInstance();
        gameStateFragment.setCallback(this);
        FragmentTransaction transactionState = getFragmentManager().beginTransaction();
        transactionState.add(gameStateFragment, "gameState");
        transactionState.commit();
    }
}
