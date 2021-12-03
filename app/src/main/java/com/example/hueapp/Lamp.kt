package com.example.hueapp

class Lamp(val name : String, val state : LampState) {
    override fun toString(): String {
        return "Lamp(name='$name', state=$state)"
    }
}