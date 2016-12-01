package be.interface3.ssingh.werewolf.game_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.interface3.ssingh.werewolf.GlobalVariable;
import be.interface3.ssingh.werewolf.R;

/**
 * Created by s.singh on 10/10/2016.
 */
public class StatusFragment extends Fragment{

    private TextView tv_statusFragm_username;
    private TextView tv_statusFragm_role;
    private TextView tv_statusFragm_status;
    private TextView tv_statusFragm_GM;
    private TextView tv_statusFragm_activity;

    private static StatusFragment instance;

    public static StatusFragment getInstance() {
        if (instance == null) {
            instance = new StatusFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_status, container, false);
        tv_statusFragm_username = (TextView) v.findViewById(R.id.tv_statusFragm_username);
        tv_statusFragm_role = (TextView) v.findViewById(R.id.tv_statusFragm_role);
        tv_statusFragm_status = (TextView) v.findViewById(R.id.tv_statusFragm_status);
        tv_statusFragm_GM = (TextView) v.findViewById(R.id.tv_statusFragm_GM);
        tv_statusFragm_activity = (TextView) v.findViewById(R.id.tv_statusFragm_activity);

        refresh();

        return v;
    }

    public void refresh() {

        tv_statusFragm_username.setText(GlobalVariable.USER.getUsername());
        tv_statusFragm_role.setText(GlobalVariable.USER.getRole());
        if (GlobalVariable.USER.isLivingStatus()) {
            tv_statusFragm_status.setText("Alive");
        }
        else {
            tv_statusFragm_status.setText("Dead");
        }
        tv_statusFragm_GM.setText(GlobalVariable.PARTY.getRoundGM());
        if(GlobalVariable.USER.isActivityStatus()) {
            tv_statusFragm_activity.setText("Active");
        }
        else {
            tv_statusFragm_activity.setText("Not Active");
        }
    }

    private IStatusFragmentCallback callback;

    public void setCallback(IStatusFragmentCallback callback) {
        this.callback = callback;
    }

    public interface IStatusFragmentCallback {
    }
}
