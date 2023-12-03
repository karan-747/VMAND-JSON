package com.testers.unique.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainVM(val context:Context):ViewModel() {
    private var quoteList:Array<Quote> = emptyArray()
    private var index =0
    init {
        quoteList =loadQuotesFromAsset()
    }

    private fun loadQuotesFromAsset(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size:Int=inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, charset = Charsets.UTF_8)
        val gson =Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }
    fun getQuote()= quoteList[index]
    fun getNextQuote(): Quote {
        return if(index<quoteList.size){
            quoteList[++index]
        }else{
            quoteList[index]
        }
    }
    fun getPreviousQuote(): Quote {
        return if(index>0){
            quoteList[--index]
        }else{
            quoteList[index]
        }
    }
}