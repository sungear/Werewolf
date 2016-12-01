package be.interface3.ssingh.werewolf.activity;

import android.content.Intent;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBDeleteUserTask;
import be.interface3.ssingh.werewolf.db_asynctask.DBInsertUserTask;
import be.interface3.ssingh.werewolf.db_asynctask.DBSelectUserTask;
import be.interface3.ssingh.werewolf.global_fragment.OnStopTimerFragment;
import be.interface3.ssingh.werewolf.global_fragment.PopUpWindowFragment;

public class ConnexionActivity extends AppCompatActivity implements DBInsertUserTask.IDBInsertUserTask, DBSelectUserTask.IDBSelectUserTask, View.OnClickListener, DBDeleteUserTask.IDBDeleteUserTask,
        OnStopTimerFragment.IOnStopTimerFragment, PopUpWindowFragment.IPopUpWindowFragment{

    private TextView tv_connexion_msg;
    private EditText et_connexion_username;
    private Button btn_connexion_signIn;
    private String username;

    private PopUpWindowFragment popUpWindowFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        tv_connexion_msg = (TextView) findViewById(R.id.tv_connexion_msg);
        et_connexion_username = (EditText) findViewById(R.id.et_connexion_username);
        btn_connexion_signIn = (Button) findViewById(R.id.btn_connexion_signIn);
        btn_connexion_signIn.setOnClickListener(this);

        popUpWindowFragment = PopUpWindowFragment.getInstance();
        popUpWindowFragment.setCallback(this);
        FragmentTransaction transactionPopUp = getFragmentManager().beginTransaction();
        transactionPopUp.add(popUpWindowFragment, "popUp");
        transactionPopUp.commit();

        OnStopTimerFragment onStopTimerFragment = OnStopTimerFragment.getInstance();
        onStopTimerFragment.setCallback(this);
        FragmentTransaction transactionTimer = getFragmentManager().beginTransaction();
        transactionTimer.add(onStopTimerFragment, "timer");
        transactionTimer.commit();
    }

    @Override
    public void onBackPressed() {
        popUpWindowFragment.popUpWindowAgain();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connexion_signIn :
                username = et_connexion_username.getText().toString();
                if (username.length() > 2) {
                    DBSelectUserTask dbSelectUserTask = new DBSelectUserTask();
                    dbSelectUserTask.setCallback(this);
                    dbSelectUserTask.execute("user", "username", "isConnected", "1", "isConnected", "1");
                }
                else {
                    tv_connexion_msg.setText("Your username must be composed of minimum 3 characters");
                }
                break;
        }
    }

    private Boolean checkIfUsernameAlreadyExist(String username, ArrayList<String> existingUsernames) {
        Boolean itExist = false;
        if (existingUsernames.size() > 0) {
            for (int i = 0; i < existingUsernames.size(); i++) {
                if (username.equals(existingUsernames.get(i))) {
                    itExist = true;
                    break;
                }
            }
        }
        return itExist;
    }

    private void decideAction(Boolean usernameTaken) {
        if (tv_connexion_msg.getText() != "") {
            tv_connexion_msg.setText("");
        }
        if (!et_connexion_username.getText().equals(null)) {
            et_connexion_username.setText(null);
        }
        if (usernameTaken) {
            tv_connexion_msg.setText("This username is already in use.");
        }
        else {
            DBInsertUserTask dbInsertUserTask = new DBInsertUserTask();
            dbInsertUserTask.setCallback(this);
            dbInsertUserTask.execute("user", username, "Observer", "1", "0", "0", "0", "0");

            Intent startActivityIntent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(startActivityIntent);
            finish();
        }
    }

    @Override
    public void getSelectedData(ArrayList<String> dataList){
        Boolean usernameTaken = checkIfUsernameAlreadyExist(username, dataList);
        decideAction(usernameTaken);
    }

    @Override
    public void checkIfCanContinue() {
    }
}
