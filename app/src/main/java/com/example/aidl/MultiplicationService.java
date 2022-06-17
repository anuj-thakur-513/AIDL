package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MultiplicationService extends Service {
    public MultiplicationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    MultiplyInterface.Stub myBinder = new MultiplyInterface.Stub() {
        @Override
        public int multiplyTwoValues(int firstNumber, int secondNumber) throws RemoteException {
            return firstNumber * secondNumber;
        }
    };
}