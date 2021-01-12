package com.kln.dailyhoroscope

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import okhttp3.*
import org.json.JSONObject


public class HoroscopeService {


    private val apiUrlUnformatted: String = "https://sameer-kumar-aztro-v1.p.rapidapi.com/?sign=%s&day=%s"
    private var apiUrlFormatted: String? = null
    public var horoscopeResponse: HoroscopeResponse? = null


    private val client = OkHttpClient()






    public fun setHoroscopeRequest(requestedHoroscope: HoroscopeRequest) {
        apiUrlFormatted =
            String.format(apiUrlUnformatted, requestedHoroscope.sign, requestedHoroscope.day)
    }

    fun getHoroscope(presenter: BaseHoroscopePresenter){

        AndroidNetworking.post(apiUrlFormatted)
            .addHeaders("x-rapidapi-key", "7a7744744amshd803a3f06c36da5p1cba75jsnb5ff710cb57c")
            .addHeaders("x-rapidapi-host", "sameer-kumar-aztro-v1.p.rapidapi.com")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {

                    if (response!=null){
                        presenter.onResponse(setHoroscope(response))}
                }

                override fun onError(anError: ANError?) {

                }
            })
    }

    private fun setHoroscope(horoscopeJSON: JSONObject): HoroscopeResponse{


            return HoroscopeResponse(
                horoscopeJSON.getString("date_range"),
                horoscopeJSON.getString("current_date"),
                horoscopeJSON.getString("description"),
                horoscopeJSON.getString("compatibility"),
                horoscopeJSON.getString("mood"),
                horoscopeJSON.getString("color"),
                horoscopeJSON.getString("lucky_number"),
                horoscopeJSON.getString("lucky_time")
            )



    }

}