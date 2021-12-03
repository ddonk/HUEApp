package com.example.EindNasaApp

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import com.example.hueapp.Lamp
import com.example.hueapp.LampState
import java.lang.Exception
import com.android.volley.VolleyError

import com.android.volley.Response

import com.android.volley.toolbox.StringRequest





class JSONAdapter(private val appContext: Context) {
    private val queue: RequestQueue
    val lamps = mutableListOf<Lamp>();

    public fun getLamps() {
    val url = "http://192.168.1.224:80/api/newdeveloper"
    val request = JsonObjectRequest(
        Request.Method.GET, url, null,
        { response ->
            try {
                val lightsJsonObject = response.getJSONObject("lights")

                GetLamp(lightsJsonObject, 1)
            } catch (exception: JSONException) {
                Log.e(
                    LOGTAG,
                    "Error while parsing JSON data: " + exception.localizedMessage
                )
            }
        }
    ) { error ->
        Log.e(
            LOGTAG,
            error.localizedMessage
        )
    }
    queue.add(request)
    }

    public fun GetLamp(lightsJsonObject : JSONObject, index : Int) {
        var success = true

        try {
            var lampJSON = lightsJsonObject.getJSONObject("" + index)
           CreateLamp(lampJSON)

        } catch (e: Exception) {
            success = false
        }

        if (success) {
            GetLamp(lightsJsonObject, index + 1)
        }
    }

    fun CreateLamp(lampJSON : JSONObject) {
        var nameJSON = lampJSON.getString("name")

        var stateJSON = lampJSON.getJSONObject("state")
        var booleanJSON = stateJSON.getBoolean("on")
        var brightnessJSON = stateJSON.getInt("bri")
        var hueJSON = stateJSON.getInt("hue")
        var satJSON = stateJSON.getInt("sat")
        var xyJSON = stateJSON.getJSONArray("xy")

        var lampState = LampState(booleanJSON, brightnessJSON, hueJSON, satJSON, xyJSON.getDouble(0), xyJSON.getDouble(1))
        var lamp = Lamp(nameJSON, lampState)

        lamps.add(lamp)
    }

    public fun volleyPost() {
        Log.d("JSON PUT: ", "Testing POST request")
        val url = "http://192.168.1.224:80/api/newdeveloper/lights/1"

        val json = JSONObject()
        json.put("name", "hophee")


        Log.d("JSON PUT", json.toString())

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.PUT, url, json,
            { response ->  Log.d(LOGTAG, "JSON PUT Response: " + response) }
        ) { error -> Log.d(LOGTAG, "JSON PUT Error: " + error.toString()) }

        queue.add(jsonObjectRequest)
    }

    public companion object {
        public val LOGTAG = JSONAdapter::class.java.name
    }

    init {
        queue = Volley.newRequestQueue(appContext)
    }
}