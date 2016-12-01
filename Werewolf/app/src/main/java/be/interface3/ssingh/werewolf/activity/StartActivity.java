package be.interface3.ssingh.werewolf.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import be.interface3.ssingh.werewolf.GlobalVariable;
import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBDeleteUserTask;
import be.interface3.ssingh.werewolf.db_asynctask.DBSelectUserTask;
import be.interface3.ssingh.werewolf.db_asynctask.DBUpdateUserTask;
import be.interface3.ssingh.werewolf.game_fragment.GMRoleChoosingFragment;
import be.interface3.ssingh.werewolf.game_fragment.GMStartChoosingFragment;
import be.interface3.ssingh.werewolf.game_fragment.StatusFragment;
import be.interface3.ssingh.werewolf.global_fragment.MenuFragment;
import be.interface3.ssingh.werewolf.global_fragment.OnStopTimerFragment;
import be.interface3.ssingh.werewolf.global_fragment.PopUpWindowFragment;

public class StartActivity extends AppCompatActivity implements DBSelectUserTask.IDBSelectUserTask, DBUpdateUserTask.IDBUpdateUserTask, StatusFragment.IStatusFragmentCallback,
        GMStartChoosingFragment.IGMStartChoosing, DBDeleteUserTask.IDBDeleteUserTask, PopUpWindowFragment.IPopUpWindowFragment, OnStopTimerFragment.IOnStopTimerFragment,
        MenuFragment.IMenuFragment, GMRoleChoosingFragment.IGMRoleChoosing{

    private StatusFragment statusFragment;
    FragmentTransaction transactionStatus;
    private PopUpWindowFragment popUpWindowFragment;
    FragmentTransaction transactionPopUp;
    OnStopTimerFragment onStopTimerFragment;
    FragmentTransaction transactionTimer;
    MenuFragment menuFragment;
    FragmentTransaction transactionMenu;
    private GMRoleChoosingFragment roleChoosingFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        DBSelectUserTask dbSelectUserTask = new DBSelectUserTask();
        dbSelectUserTask.setCallback(this);
        dbSelectUserTask.execute("user", "username", "isGM", "1", "isConnected", "1");

        transaction = getFragmentManager().beginTransaction();

        statusFragment = StatusFragment.getInstance();
        statusFragment.setCallback(this);
        FragmentTransaction transactionStatus = getFragmentManager().beginTransaction();
        transactionStatus.add(R.id.fl_startActivity_statusFragment, statusFragment);
        transactionStatus.commit();

//        popUpWindowFragment = PopUpWindowFragment.getInstance();
//        popUpWindowFragment.setCallback(this);
//        FragmentTransaction transactionPopUp = getFragmentManager().beginTransaction();
//        transactionPopUp.add(popUpWindowFragment, "popUp");
//        transactionPopUp.commit();

        onStopTimerFragment = OnStopTimerFragment.getInstance();
        onStopTimerFragment.setCallback(this);
        FragmentTransaction transactionTimer = getFragmentManager().beginTransaction();
        transactionTimer.add(onStopTimerFragment, "timer");
        transactionTimer.commit();

        MenuFragment menuFragment = MenuFragment.getInstance();
        menuFragment.setCallback(this);
        FragmentTransaction transactionMenu = getFragmentManager().beginTransaction();
        transactionMenu.add(menuFragment, "menu");
        transactionMenu.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ON_DESTROY", "TRUE");
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
//        popUpWindowFragment.popUpWindowAgain();
    }

    @Override
    public void getSelectedData(ArrayList<String> dataList) {
        if (dataList.size() > 0) {
            Log.i("GM_EXISTS", "true");
            GlobalVariable.PARTY.setRoundGM(dataList.get(0));

            statusFragment.refresh();

        }
        else {
            Log.i("GM_EXISTS", "false");
            DBUpdateUserTask dbUpdateUserTask = new DBUpdateUserTask();
            dbUpdateUserTask.setCallback(this);
            dbUpdateUserTask.execute("user", "isGM", "1", "username", GlobalVariable.USER.getUsername(), "isConnected", "1");
        }
    }

    @Override
    public void updateUser() {
        Log.i("UPDATING", "true");
        GlobalVariable.USER.setGM(true);

        GlobalVariable.PARTY.setRoundGM(GlobalVariable.USER.getUsername());

        statusFragment.refresh();

        GMStartChoosingFragment startChoosingFragment = GMStartChoosingFragment.getInstance();
        startChoosingFragment.setCallback(this);
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.add(R.id.fl_startActivity_gmFragment, startChoosingFragment);
        transaction.commit();
    }

    @Override
    public void goToRoleChoosingFragment() {
        statusFragment.refresh();

        roleChoosingFragment = GMRoleChoosingFragment.getInstance();
        roleChoosingFragment.setCallback(this);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_startActivity_gmFragment, roleChoosingFragment);
        transaction.commit();
    }

    @Override
    public void roleChoosing() {
        roleChoosingFragment.refreshRoleStats();
        finish();
    }
}
