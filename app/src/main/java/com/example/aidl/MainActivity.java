package com.example.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnMultiply;
    TextView txtResult;
    EditText edtFirstNumber, edtSecondNumber;

    MultiplyInterface myInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        txtResult = (TextView) findViewById(R.id.txtMultiplyResult);
        edtFirstNumber = (EditText) findViewById(R.id.edtFirstNumber);
        edtSecondNumber = (EditText) findViewById(R.id.edtSecondNumber);

        btnMultiply.setOnClickListener(this);

        // binding the service with AIDL for the client i.e. MainActivity.java
        Intent multiplyServiceIntent = new Intent(this, MultiplicationService.class);
        bindService(multiplyServiceIntent, myServiceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection myServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myInterface = MultiplyInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnMultiply){
            try {

                int first = Integer.parseInt(edtFirstNumber.getText().toString());
                int second = Integer.parseInt(edtSecondNumber.getText().toString());
                int result = myInterface.multiplyTwoValues(first,second);
                txtResult.setText(new StringBuilder().append(result).append("").toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}