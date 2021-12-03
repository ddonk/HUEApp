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
    public var aedfrh245w6tju = ArrayList<Lamp>()

    public fun getLamps() : ArrayList<Lamp> {
        val arrayList = ArrayList<Lamp>()


    val url = "http://192.168.1.224:80/api/newdeveloper"
    val request = JsonObjectRequest(
        Request.Method.GET, url, null,
        { response ->
            try {
                val lightsJsonObject = response.getJSONObject("lights")

                for (i in 1..3) {
                    arrayList.add(GetLamp(lightsJsonObject, i))
                }


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

        Log.d("TESTING", "Arraylist size: " + arrayList.size)
        return arrayList
    }

    public fun GetLamp(lightsJsonObject : JSONObject, index : Int) : Lamp {
        var lampJSON = lightsJsonObject.getJSONObject("" + index)
        var nameJSON = lampJSON.getString("name")

        var stateJSON = lampJSON.getJSONObject("state")
        var booleanJSON = stateJSON.getBoolean("on")
        var brightnessJSON = stateJSON.getInt("bri")
        var hueJSON = stateJSON.getInt("hue")
        var satJSON = stateJSON.getInt("sat")
        var modelIDJSON = lampJSON.getString("modelid")

        var lampState = LampState(booleanJSON, brightnessJSON, hueJSON, satJSON)
        var lamp = Lamp(index, nameJSON, modelIDJSON, lampState)
        Log.d("TESTING", "Lamp: " + lamp)
        return lamp
    }

//    fun CreateLamp(lampJSON : JSONObject, index : Int) {
//        var nameJSON = lampJSON.getString("name")
//
//        var stateJSON = lampJSON.getJSONObject("state")
//        var booleanJSON = stateJSON.getBoolean("on")
//        var brightnessJSON = stateJSON.getInt("bri")
//        var hueJSON = stateJSON.getInt("hue")
//        var satJSON = stateJSON.getInt("sat")
//        var modelIDJSON = lampJSON.getString("modelid")
//
//        var lampState = LampState(booleanJSON, brightnessJSON, hueJSON, satJSON)
//        var lamp = Lamp(index, nameJSON, modelIDJSON, lampState)
//
//
//        lamps.add(lamp)
//        Log.d("TESTING", "Adding lamp to lamps: " + lamp.toString() + "New lamps size: " + lamps.size)
//        Log.d("TESTING", "List: " + lamps)
//    }

    private fun PutStateRequest(lampState: LampState, index: Int) {
        val url = "http://192.168.1.224:80/api/newdeveloper/lights/" + index + "/state"

        val stateJSON = JSONObject()

        stateJSON.put("on", lampState.on)
        stateJSON.put("bri", lampState.brightness)
        stateJSON.put("hue", lampState.hue)
        stateJSON.put("sat", lampState.sat)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.PUT, url, stateJSON,
            { response ->  Log.d(LOGTAG, "JSON PUT Response: " + response) }
        ) { error -> Log.d(LOGTAG, "JSON PUT Error: " + error.toString()) }

        queue.add(jsonObjectRequest)
    }


    public fun PutRequest(lamp : Lamp) {
        Log.d("JSON PUT: ", "Testing PUT request")
        val url = "http://192.168.1.224:80/api/newdeveloper/lights/" + lamp.index

        val json = JSONObject()

        json.put("name", "" + lamp.name)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.PUT, url, json,
            { response ->  Log.d(LOGTAG, "JSON PUT Response: " + response) }
        ) { error -> Log.d(LOGTAG, "JSON PUT Error: " + error.toString()) }

        queue.add(jsonObjectRequest)

        PutStateRequest(lamp.state, lamp.index)
    }

    public companion object {
        public val LOGTAG = JSONAdapter::class.java.name
    }

    init {
        queue = Volley.newRequestQueue(appContext)
    }
}