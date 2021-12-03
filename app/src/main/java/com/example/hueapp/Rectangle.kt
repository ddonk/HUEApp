package com.example.hueapp

class Rectangle(val width: Int, val height: Int) {
    val square: Int
        get() = this.width * this.height
}