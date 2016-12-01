package be.interface3.ssingh.werewolf.global_fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import be.interface3.ssingh.werewolf.GlobalVariable;
import be.interface3.ssingh.werewolf.activity.MainActivity;
import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBDeleteUserTask;

/**
 * Created by s.singh on 11/10/2016.
 */
public class PopUpWindowFragment extends Fragment implements DBDeleteUserTask.IDBDeleteUserTask{

    private ViewGroup viewGroup;

    private static PopUpWindowFragment instance;

    public static PopUpWindowFragment getInstance() {
        if (instance == null) {
            instance = new PopUpWindowFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pop_up_window, container, false);
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_pop_up_window, null);
        return view;
    }

    public void popUpWindowAgain() {
        RelativeLayout relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.layout_startActivity_popUpWindow);
        Button btn_popUp_exitGame = (Button) viewGroup.findViewById(R.id.btn_popUp_exitGame);
        Button btn_popUp_returnGame = (Button) viewGroup.findViewById(R.id.btn_popUp_returnGame);
        final PopupWindow popupWindow = new PopupWindow(viewGroup, 400, 400, false);
        popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);


        Log.i("DELETING_USERNAME", GlobalVariable.USER.getUsername());
        final DBDeleteUserTask dbDeleteUserTask = new DBDeleteUserTask();
        dbDeleteUserTask.setCallback(this);
        btn_popUp_exitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                dbDeleteUserTask.execute("user", "username", GlobalVariable.USER.getUsername());
                Log.i("DESTROYED", "true");
            }
        });

        btn_popUp_returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private IPopUpWindowFragment callback;

    public void setCallback(IPopUpWindowFragment callback) {
        this.callback = callback;
    }

    public interface IPopUpWindowFragment {
    }
}
