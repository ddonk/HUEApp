package com.example.hueapp

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.json.JSONArray
import org.json.JSONException
import com.android.volley.VolleyError
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.lang.Error

class JSONAdapter {

    fun GetLampData(context: Context?, url: String?) {
        Log.d("JSONTEST", "Trying to get JSON")
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val lightsObject = response.getJSONObject("lights")
                //val lampObject = lightsObject.getJSONObject("1")
                //Log.d("Kaas", lampObject.toString())
                //val lampState = lampObject.getJSONObject("state")
//                val onBool = lampState.getBoolean("on")
//                val brightness = lampState.getInt("bri")
//                val hue = lampState.getInt("hue")
//                val saturation = lampState.getInt("sat")
//                val xy = lampState.getJSONArray("xy")
//
//                val hueLampState = HUELampState(onBool, brightness, hue, saturation, xy[0] as Double, xy[1] as Double)
//                val hueLamp = HUELamp(1, hueLampState)

//                Log.d("JSONTEST", hueLamp.toString())

//                val jsonArray = response.getJSONArray("lights")
//                for (i in 0 until jsonArray.length()) {
//                    Log.d("JSONTEST", jsonArray.getJSONObject(i).toString())
//                }

//                    String name = response.getString("courseName");
//                    String courseTracks = response.getString("courseTracks");
//                    String courseMode = response.getString("courseMode");
//                    String courseImageURL = response.getString("courseimg");
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        })
        {
            Log.d("JSONTEST", "Failed to get data: ")
            Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show() }
        queue.add(jsonObjectRequest)

        //return new HUELamp();
    }

    fun getLamps(context : Context) {
        val queue = Volley.newRequestQueue(context);
        val url =
            "http://localhost/api/newdeveloper"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val lightsObject = response.getJSONObject("lights")
//                    val photosJsonArray = response.getJSONArray("photos")
//                    for (i in 0 until photosJsonArray.length()) {
//                        val photoJsonObject = photosJsonArray.getJSONObject(i)
//                        val id = photoJsonObject.getString("id")
//                        val sol = photoJsonObject.getString("sol")
//                        val cameraName =
//                            photoJsonObject.getJSONObject("camera").getString("full_name")
//                        val imageUrl = photoJsonObject.getString("img_src")
//                        Log.d(LOGTAG, imageUrl)
//                        val roverName = photoJsonObject.getJSONObject("rover").getString("name")
//                        val photo = MarsRoverPhoto(id, sol, cameraName, imageUrl, roverName)
//                        listener.onPhotoAvailable(photo)
//                    }
                } catch (exception: JSONException) {
                    Log.e("JSONTEST", "Error while parsing JSON data: " + exception.localizedMessage)
                }
            }
        ) { error ->
            Log.e("JSONTEST", error.localizedMessage)
        }
        queue.add(request)
}

    private fun stringToBooleanConverter(string: String): Boolean {
        var bool = false
        when (string) {
            "on" -> {
                bool = true
                return bool
            }
            "off" -> {
                bool = false
                return bool
            }
        }
        return bool
    }
}