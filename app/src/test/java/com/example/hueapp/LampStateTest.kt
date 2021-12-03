package com.example.hueapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LampStateTest{

    private val lampstate = LampState(true,50,5600,40)


    @Test
    fun validateGetOn() {
        assertTrue(lampstate.on)
    }

    @Test
    fun validateGetHue() {
        assertEquals( 5600, lampstate.hue)
    }

    @Test
    fun validateGetSat() {
        assertEquals(40, lampstate.sat)
    }

    @Test
    fun validateGetBri() {
        assertEquals(50, lampstate.brightness)
    }

    @Test
    fun ValidateToString() {
        assertEquals("LampState(on=true, brightness=50, hue=5600, sat=40)", lampstate.toString())
    }

}