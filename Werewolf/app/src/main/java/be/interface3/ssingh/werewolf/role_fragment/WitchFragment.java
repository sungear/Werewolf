package be.interface3.ssingh.werewolf.role_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBSelectUserTask;
import be.interface3.ssingh.werewolf.db_asynctask.DBUpdateUserTask;

/**
 * Created by s.singh on 18/10/2016.
 */
public class WitchFragment extends Fragment implements DBSelectUserTask.IDBSelectUserTask, DBUpdateUserTask.IDBUpdateUserTask {
    private Button btn_witchFragment_confirm;
    private ListView lv_witchFragment_victimUsernameList;
    private ListView lv_witchFragment_aliveUsernameList;
    private ArrayList<String> victimUsernameList;
    private ArrayList<String> aliveUsernameList;
    private ArrayAdapter<String> victimAdapter;
    private ArrayAdapter<String> aliveAdapter;

    private static WerewolfFragment instance;

    public static WerewolfFragment getInstance(){
        if(instance == null){
            instance = new WerewolfFragment();
        }
        return instance;
    }

    public interface IWerewolfFragment{
        void updateStateWerewolfConfirm();
    }

    private IWerewolfFragment callback;

    public void setCallback(IWerewolfFragment callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_werewolf, container, false);

        btn_witchFragment_confirm = (Button) view.findViewById(R.id.btn_witchFragment_confirm);
        lv_witchFragment_victimUsernameList = (ListView) view.findViewById(R.id.lv_witchfFragment_victimUsernameList);
        lv_witchFragment_aliveUsernameList = (ListView) view.findViewById(R.id.lv_witchfFragment_aliveUsernameList);

        DBSelectUserTask dbSelectUserTask = new DBSelectUserTask();
        dbSelectUserTask.setCallback(this);
        dbSelectUserTask.execute("user", "username", "isActive", "1", "isAlive", "1");

        final DBUpdateUserTask dbUpdateUserTask = new DBUpdateUserTask();
        dbUpdateUserTask.setCallback(this);
        btn_witchFragment_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choice;
                if (lv_witchFragment_victimUsernameList != null) {
                    choice = victimUsernameList.get(lv_witchFragment_victimUsernameList.getCheckedItemPosition());
                }
                else {
//                    choice = usernameList.get(lv_witchFragment_aliveUsernameList.getCheckedItemPosition());
                }
//                dbUpdateUserTask.execute("user", "isVictim", "1", "username", choice, "isAlive", "1");
            }
        });

        return view;
    }

    @Override
    public void getSelectedData(ArrayList<String> dataList) {
//        usernameList = dataList;
//        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_single_choice, usernameList);
//        lv_werewolfFragment_usernameList.setAdapter(adapter);
//        lv_werewolfFragment_usernameList.setItemChecked(0, true);
    }

    @Override
    public void updateUser() {
        callback.updateStateWerewolfConfirm();
    }
}
