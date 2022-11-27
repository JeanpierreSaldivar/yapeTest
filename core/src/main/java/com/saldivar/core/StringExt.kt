package com.saldivar.core

import com.google.gson.Gson
import java.util.*

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
inline fun <reified T:Any> convertJson(value: T):String = Gson().toJson(value)

fun String.convertCapitalized() = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

inline fun <reified T:Any> String.convertObject(): T = Gson().fromJson(this,T::class.java)