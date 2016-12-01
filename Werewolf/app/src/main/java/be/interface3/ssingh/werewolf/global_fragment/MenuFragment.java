package be.interface3.ssingh.werewolf.global_fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import be.interface3.ssingh.werewolf.GlobalVariable;
import be.interface3.ssingh.werewolf.activity.ConnexionActivity;
import be.interface3.ssingh.werewolf.R;
import be.interface3.ssingh.werewolf.db_asynctask.DBDeleteUserTask;

/**
 * Created by s.singh on 14/10/2016.
 */
public class MenuFragment extends Fragment implements DBDeleteUserTask.IDBDeleteUserTask{

    private static MenuFragment instance;

    public static MenuFragment getInstance() {
        if (instance == null) {
            instance = new MenuFragment();
        }
        return instance;
    }

    public interface IMenuFragment {

    }

    private IMenuFragment callback;

    public void setCallback(IMenuFragment callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("DELETING_USERNAME", GlobalVariable.USER.getUsername());
        DBDeleteUserTask dbDeleteUserTask = new DBDeleteUserTask();
        dbDeleteUserTask.setCallback(this);
        switch (item.getItemId()) {
            case R.id.menuLeaveParty:
                return true;
            case R.id.menuLogOut:
                Intent intent = new Intent(getActivity(), ConnexionActivity.class);
                startActivity(intent);
                getActivity().finish();
                dbDeleteUserTask.execute("user", "username", GlobalVariable.USER.getUsername());
                Log.i("DESTROYED", "true");
            case R.id.menuExitGame:
                getActivity().finish();
                dbDeleteUserTask.execute("user", "username", GlobalVariable.USER.getUsername());
                Log.i("DESTROYED", "true");
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
