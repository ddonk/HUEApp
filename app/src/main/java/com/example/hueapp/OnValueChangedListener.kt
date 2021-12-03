package com.example.hueapp

import android.util.Log

class OnValueChangedListener(var value: ArrayList<Lamp>) {

    public fun UpdateValue(changedValue : ArrayList<Lamp>, lamda : () -> Unit) {
        Log.d("ChangedTesting", "Value: " + value + ", Changed Value: " + changedValue)
        Log.d("ChangedTesting", "Are values equel: " + !(value != changedValue))

        if(value != changedValue) {
            Log.d("ChangedTesting", "Coming here")
            value = changedValue
            OnValueChanged(lamda)
        }
    }

    public fun OnValueChanged( lmbd: () -> Unit ) {
        lmbd()
    }
}