package be.interface3.ssingh.werewolf.game_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import be.interface3.ssingh.werewolf.GlobalVariable;
import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBUpdateUserTask;

/**
 * Created by s.singh on 10/10/2016.
 */
public class GMStartChoosingFragment extends Fragment implements DBUpdateUserTask.IDBUpdateUserTask{

    private Button btn_gmFragm_startChoosing;

    private static GMStartChoosingFragment instance;

    public static GMStartChoosingFragment getInstance() {
        if (instance == null) {
            instance = new GMStartChoosingFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gm_start_choosing, container, false);
        btn_gmFragm_startChoosing = (Button) view.findViewById(R.id.btn_gmFragm_startChoosing);


        final DBUpdateUserTask dbUpdateUserTask = new DBUpdateUserTask();
        dbUpdateUserTask.setCallback(this);

        btn_gmFragm_startChoosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbUpdateUserTask.execute("user", "isActive", "1", "isConnected", "1", "isConnected", "1");
            }
        });
        return view;
    }

    private IGMStartChoosing callback;

    public void setCallback(IGMStartChoosing callback) {
        this.callback = callback;
    }

    public interface IGMStartChoosing {
        void goToRoleChoosingFragment();
    }

    @Override
    public void updateUser() {
        GlobalVariable.USER.setActivityStatus(true);
        callback.goToRoleChoosingFragment();
    }
}
