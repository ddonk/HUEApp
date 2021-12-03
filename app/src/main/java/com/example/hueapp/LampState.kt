package com.example.hueapp

class LampState(val on : Boolean, val brightness : Int, val hue : Int, val sat : Int)  {
    override fun toString(): String {
        return "LampState(on=$on, brightness=$brightness, hue=$hue, sat=$sat)"
    }
}