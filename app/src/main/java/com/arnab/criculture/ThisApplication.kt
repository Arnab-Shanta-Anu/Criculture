package com.arnab.criculture

import android.content.Context

class ThisApplication(context: Context) {
    init {
        ThisApplication.setContext(context)
    }
    companion object{
        lateinit var instance: Context
        fun getContext(): Context{
            return instance
        }

        fun setContext(context: Context){
            instance = context
        }
    }
}