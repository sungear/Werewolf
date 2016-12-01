package be.interface3.ssingh.werewolfv3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import be.interface3.ssingh.werewolfv3.R;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_initialActivity_createParty;
    private Button btn_initialActivity_joinParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        btn_initialActivity_createParty = (Button) findViewById(R.id.btn_initialActivity_createParty);
        btn_initialActivity_createParty = (Button) findViewById(R.id.btn_initialActivity_joinParty);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_initialActivity_createParty:
                break;
            case R.id.btn_initialActivity_joinParty:
                break;
        }
    }
}
