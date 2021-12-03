package com.example.hueapp

import org.junit.Assert.*

import org.junit.Test

class LampTest {

    private val lampstate = LampState(true,50,5600,40)
    private val lamp = Lamp(2,"Philips Hue", "Satan13", lampstate)

    @Test
    fun ToStringTest() {
        assertEquals("Lamp(name='Philips Hue',Model ID=Satan13,state=LampState(on=true, brightness=50, hue=5600, sat=40))",lamp.toString())
    }

    @Test
    fun getIndexTest() {
        assertEquals(2,lamp.index)
    }

    @Test
    fun getNameTest() {
        assertEquals("Philips Hue", lamp.name)
    }

    @Test
    fun getModelidTest() {
        assertEquals("Satan13", lamp.modelid)
    }

    @Test
    fun getStateTest() {
        assertEquals("LampState(on=true, brightness=50, hue=5600, sat=40)", lamp.state.toString())
    }
}