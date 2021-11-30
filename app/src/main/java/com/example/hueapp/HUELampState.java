package com.example.hueapp;

public class HUELampState {
    private boolean on;
    private int brightness;
    private int hue;
    private int sat;
    private double x;
    private double y;

    public HUELampState(boolean on, int brightness, int hue, int sat, double x, double y) {
        this.on = on;
        this.brightness = brightness;
        this.hue = hue;
        this.sat = sat;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "HUELampState{" +
                "on=" + on +
                ", brightness=" + brightness +
                ", hue=" + hue +
                ", sat=" + sat +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
