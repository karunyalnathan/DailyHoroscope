package com.kln.dailyhoroscope

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

class BaseHoroscopePresenter {
    private val horoscopeService : HoroscopeService = HoroscopeService();
    private var rootView: View? = null
    private var currentSign: Zodiac? = null

     enum class HoroscopeDay(){
        YESTERDAY, TODAY, TOMORROW
    }
    interface IBaseHoroscope{
        fun onResponse(horoscopeResponse: HoroscopeResponse)
    }

     fun setRootView(view: View){
        rootView = view
    }

     fun getHoroscope(sign: Zodiac, day: HoroscopeDay){
        currentSign = sign //get horoscope call made from adapter only ... separate to set Sign & get horoscope(Day)?
        horoscopeService.setHoroscopeRequest(HoroscopeRequest(sign.name.toLowerCase(),day.name.toLowerCase()))
        horoscopeService.getHoroscope(this)
         presetHoroscopeSign()
    }

     fun onResponse(horoscopeResponse: HoroscopeResponse){
        loadHoroscope(horoscopeResponse)
    }

    private fun loadHoroscope(horoscope: HoroscopeResponse){
        rootView?.apply {
            findViewById<AppCompatTextView>(R.id.horoscope_current_date).text = horoscope.current_date
            findViewById<AppCompatTextView>(R.id.horoscope_signDateRange).text = horoscope.date_range
            findViewById<AppCompatTextView>(R.id.horoscopeDescription).text = horoscope.description
            findViewById<DHRow>(R.id.horoscopeCompatibility).setValue(horoscope.compatibility)
            findViewById<DHRow>(R.id.horoscopeColor).setValue(horoscope.color)
            findViewById<DHRow>(R.id.horoscopeMood).setValue(horoscope.mood)
            findViewById<DHRow>(R.id.horoscopeLuckyNumber).setValue(horoscope.lucky_number)
            findViewById<DHRow>(R.id.horoscopeLuckyTime).setValue(horoscope.lucky_time)
        }
    }

    private fun presetHoroscopeSign(){
        if(currentSign != null){
            rootView?.apply {
                findViewById<AppCompatTextView>(R.id.horoscope_signName).text = currentSign!!.name
                findViewById<AppCompatImageView>(R.id.horoscope_signImage).setImageDrawable(resources.getDrawable(currentSign!!.signGlyph))

            }

        }
    }



}