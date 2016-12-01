package be.interface3.ssingh.werewolf.game_fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import be.interface3.ssingh.werewolf.activity.GameActivity;
import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBSelectUserTask;

/**
 * Created by s.singh on 10/10/2016.
 */
public class GMRoleChoosingFragment extends Fragment implements DBSelectUserTask.IDBSelectUserTask{

    private Button btn_roleChoosingFragm_confirm;

    private int playerNumber;
    private int maxPlayerNumber;
    private int roleNumber;

    private static GMRoleChoosingFragment instance;

    public static GMRoleChoosingFragment getInstance() {
        if (instance == null) {
            instance = new GMRoleChoosingFragment();
        }
        return instance;
    }

    private IGMRoleChoosing callback;

    public void setCallback(IGMRoleChoosing callback) {
        this.callback = callback;
    }

    @Override
    public void getSelectedData(ArrayList<String> dataList) {

    }

    public interface IGMRoleChoosing {
        void roleChoosing();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gm_role_choosing, container, false);

        btn_roleChoosingFragm_confirm = (Button) view.findViewById(R.id.btn_roleChoosingFragm_confirm);

        btn_roleChoosingFragm_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.roleChoosing();
            }
        });

        return view;
    }

    public void refreshRoleStats() {

    }
}
