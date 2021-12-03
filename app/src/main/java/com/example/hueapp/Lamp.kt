package com.example.hueapp

class Lamp(val index: Int, val name : String,val modelid : String ,val state : LampState) {
    override fun toString(): String {
        return "Lamp(name='$name',Model ID=$modelid,state=$state)"
    }
}