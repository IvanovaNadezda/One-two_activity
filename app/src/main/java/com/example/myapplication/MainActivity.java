package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn, btn2;
    private EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        ed = findViewById(R.id.editText);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == InfoActivity.INFOACTIVITY_CODE) {
                        ed.setText(result.getData().getStringExtra("ppp"));
                    }
                }
            }
    );

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            //Открываем гугл
            String url = "https://google.com";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        else {
            //Явное намерение
            //Переход на другую активность
            Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
            intent.putExtra("ccc",ed.getText().toString());
            //startActivity(intent);
            someActivityResultLauncher.launch(intent);
        }
    }
}