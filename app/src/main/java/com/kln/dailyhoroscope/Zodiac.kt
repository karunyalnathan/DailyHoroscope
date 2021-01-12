package com.kln.dailyhoroscope

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class Zodiac(@StringRes val signName : Int, @DrawableRes val signGlyph:  Int, @DrawableRes val signSymbol:  Int ) {

    ARIES(R.string.aries, R.drawable.icon_glyph_aries, R.drawable.icon_symbol_aries_ram),
    TAURUS(R.string.taurus, R.drawable.icon_glyph_taurus, R.drawable.icon_symbol_taurus_bull),
    GEMINI(R.string.gemini, R.drawable.icon_glyph_gemini, R.drawable.icon_symbol_gemini_twins),
    CANCER(R.string.cancer, R.drawable.icon_glyph_cancer, R.drawable.icon_symbol_cancer_crab),
    LEO(R.string.leo, R.drawable.icon_glyph_leo, R.drawable.icon_symbol_leo_lion),
    VIRGO(R.string.virgo, R.drawable.icon_glyph_virgo, R.drawable.icon_symbol_virgo_virgin),
    LIBRA(R.string.libra, R.drawable.icon_glyph_libra, R.drawable.icon_symbol_libra_scales),
    SCORPIO(R.string.scorpio, R.drawable.icon_glyph_scorpio, R.drawable.icon_symbol_scorpio_scorpion),
    SAGITTARIUS(R.string.sagittarius, R.drawable.icon_glyph_sagittarius, R.drawable.icon_symbol_sagittarius_centor),
    CAPRICORN(R.string.capricorn, R.drawable.icon_glyph_capricorn, R.drawable.icon_symbol_capricorn_seagoat),
    AQUARIUS(R.string.aquarius, R.drawable.icon_glyph_aquarius, R.drawable.icon_symbol_aquarius_waterbearer),
    PISCES(R.string.pisces, R.drawable.icon_glyph_pisces, R.drawable.icon_symbol_pisces_fish);

}