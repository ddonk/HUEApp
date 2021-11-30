package com.example.hueapp;

public class HUELamp {
    private int name;
    private HUELampState state;
//    private int ct;
//    private String alert;
//    private String effect;
//    private String colormode;
//    private boolean reachable;

    public HUELamp(int name, HUELampState state) {
        this.name = name;
        this.state = state;
    }

    @Override
    public String toString() {
        return "HUELamp{" +
                "name=" + name +
                ", state=" + state +
                '}';
    }
}
