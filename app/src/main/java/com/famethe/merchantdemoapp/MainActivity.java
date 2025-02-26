package com.famethe.merchantdemoapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMakeCardPayment, btnMakeMobilePayment;

    EditText txtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
    }

    private void initView() {
        btnMakeCardPayment = findViewById(R.id.btnMakeCardPayment);
        btnMakeMobilePayment = findViewById(R.id.btnMakeMobilePayment);
        txtAmount = findViewById(R.id.txtAmount);

        btnMakeCardPayment.setOnClickListener(this);
        btnMakeMobilePayment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnMakeCardPayment){

            Intent intent = new Intent();
            intent.setComponent(new ComponentName("zw.co.poscloud.zinara", "zw.co.poscloud.a90.activity.MainActivity"));
            intent.putExtra("reference", "REF0000011");
            intent.putExtra("amount", Double.parseDouble(txtAmount.getText().toString()));
            intent.putExtra("currency", "USD");             // ZIG   OR   ZWG   OR   USD
            intent.putExtra("method", "CARD-SWIPE");              // CARD-SWIPE   OR   MOBILE-MONEY
            startActivityForResult(intent, 1);

        } else if (view.getId() == R.id.btnMakeMobilePayment){

            Intent intent = new Intent();
            intent.setComponent(new ComponentName("zw.co.poscloud.zinara", "zw.co.poscloud.a90.activity.MainActivity"));
            intent.putExtra("reference", "REF0000011");
            intent.putExtra("amount",Double.parseDouble(txtAmount.getText().toString()));
            intent.putExtra("currency", "USD");             // ZIG   OR   ZWG   OR   USD
            intent.putExtra("method", "MOBILE-MONEY");              // CARD-SWIPE   OR   MOBILE-MONEY
            startActivityForResult(intent, 1);

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                // Handle the data received from the Google Play Store app
                String responseCode = data.getStringExtra("code");
                String responseDescription = data.getStringExtra("description");
                // Process the result here
                Toast.makeText(this, responseCode + ": " + responseDescription, Toast.LENGTH_SHORT).show();

            }
        }
    }
}