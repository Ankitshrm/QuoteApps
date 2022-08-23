package com.insidecoderz.quoteapps.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.insidecoderz.quoteapps.dataclass.Quotes
import java.nio.ByteBuffer

class MainViewModel(val context :Context) :ViewModel() {

    private var quotesList : Array<Quotes> = emptyArray()
    private var index =0

    init {
        quotesList = loadQuotesFromAsset()
    }

    private fun loadQuotesFromAsset(): Array<Quotes> {
        val input =context.assets.open("quotes.json")
        val size:Int =input.available()
        val buffer =ByteArray(size)
        input.read(buffer)
        input.close()

        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return  gson.fromJson(json,Array<Quotes>::class.java)
    }

    fun currIndex() =quotesList[index]
    fun nextIndex() =quotesList[++index]
    fun prevIndex() =quotesList[--index]
}