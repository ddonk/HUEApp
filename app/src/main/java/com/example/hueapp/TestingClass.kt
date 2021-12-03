package com.example.hueapp

import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.EindNasaApp.JSONAdapter
import org.json.JSONException
import org.json.JSONObject

class TestingClass(private val appContext: Context, val sharedPreferencesManager: SharedPreferencesManager) {
    private val queue: RequestQueue
    private val arrayList = ArrayList<Lamp>()

    fun GetLamps() : ArrayList<Lamp>  {
        val arrayList = ArrayList<Lamp>()

        val url = "http://"+ sharedPreferencesManager.GetSetting("IP4") + ":" + sharedPreferencesManager.GetSetting("Port")+"/api/newdeveloper"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val lightsJsonObject = response.getJSONObject("lights")

                    for (i in 1..3) {
                        var lampJSON = lightsJsonObject.getJSONObject("" + i)
                        var nameJSON = lampJSON.getString("name")

                        var stateJSON = lampJSON.getJSONObject("state")
                        var booleanJSON = stateJSON.getBoolean("on")
                        var brightnessJSON = stateJSON.getInt("bri")
                        var hueJSON = stateJSON.getInt("hue")
                        var satJSON = stateJSON.getInt("sat")
                        var modelIDJSON = lampJSON.getString("modelid")

                        var lampState = LampState(booleanJSON, brightnessJSON, hueJSON, satJSON)
                        var lamp = Lamp(i, nameJSON, modelIDJSON, lampState)

                        arrayList.add(lamp)
                        Log.d("KAAS", "Lamp: " + lamp)
                        Log.d("KAAS", "List size: " + arrayList.size)
                    }

                    Log.d("KAAS", "List size: " + arrayList.size)

                } catch (exception: JSONException) {
                    Log.e(
                        JSONAdapter.LOGTAG,
                        "Error while parsing JSON data: " + exception.localizedMessage
                    )
                }
            }
        ) { error ->
            Log.e(
                JSONAdapter.LOGTAG,
                error.localizedMessage
            )
        }

        queue.add(request)

        Log.d("KAAS", "List size: " + arrayList.size)

        return arrayList
    }

    public fun PutRequest(lamp : Lamp) {
        Log.d("JSON PUT: ", "Testing PUT request")
        val url = "http://"+ sharedPreferencesManager.GetSetting("IP4") + ":" + sharedPreferencesManager.GetSetting("Port")+"/api/newdeveloper/lights/" + lamp.index

        val json = JSONObject()

        json.put("name", "" + lamp.name)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.PUT, url, json,
            { response ->  Log.d(JSONAdapter.LOGTAG, "JSON PUT Response: " + response) }
        ) { error -> Log.d(JSONAdapter.LOGTAG, "JSON PUT Error: " + error.toString()) }

        queue.add(jsonObjectRequest)

        PutStateRequest(lamp.state, lamp.index)
    }

    private fun PutStateRequest(lampState: LampState, index: Int) {
        val url = "http://"+ sharedPreferencesManager.GetSetting("IP4") + ":" + sharedPreferencesManager.GetSetting("Port")+"/api/newdeveloper/lights/" + index + "/state"

        val stateJSON = JSONObject()

        stateJSON.put("on", lampState.on)

        if(lampState.on == true) {
            stateJSON.put("bri", lampState.brightness)
            stateJSON.put("hue", lampState.hue)
            stateJSON.put("sat", lampState.sat)
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.PUT, url, stateJSON,
            { response ->  Log.d(JSONAdapter.LOGTAG, "JSON PUT Response: " + response) }
        ) { error -> Log.d(JSONAdapter.LOGTAG, "JSON PUT Error: " + error.toString()) }

        queue.add(jsonObjectRequest)
    }

    init {
        queue = Volley.newRequestQueue(appContext)
    }
}