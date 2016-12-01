package be.interface3.ssingh.werewolf.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import be.interface3.ssingh.werewolf.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_main_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_start = (Button) findViewById(R.id.btn_main_start);
        btn_main_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_start:
                Intent intent = new Intent(getApplicationContext(), ConnexionActivity.class);
                startActivity(intent);
                break;
        }
    }
}