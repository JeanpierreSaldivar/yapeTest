package com.saldivar.core

import com.google.gson.Gson

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
fun Any.convertJson(): String = Gson().toJson(this)

fun String.convertObject(value:Class<*>): Any = Gson().fromJson(this,value)