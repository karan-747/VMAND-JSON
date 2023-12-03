package com.testers.unique.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var mainVM: MainVM
    private lateinit var tvQuote: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button
    private lateinit var btnShare: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainVM = ViewModelProvider(this,MainVMFactory(applicationContext))[MainVM::class.java]
        tvQuote =this.findViewById(R.id.tvQuote)
        tvAuthor =this.findViewById(R.id.tvAuthor)
        btnNext =this.findViewById(R.id.btnNext)
        btnPrevious =this.findViewById(R.id.btnPrevious)
        btnShare =this.findViewById(R.id.fabShare)
        setQuote()
        btnNext.setOnClickListener(){
            setNextQuote()
        }
        btnPrevious.setOnClickListener(){
            setPreviousQuote()
        }
        btnShare.setOnClickListener(){
            sentQuote()
        }
    }

    private fun sentQuote() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,mainVM.getQuote().text.toString())
        startActivity(intent)
    }

    private fun setPreviousQuote() {
        var quote = mainVM.getPreviousQuote()
        tvQuote.text= quote.text
        tvAuthor.text=quote.author
    }

    private fun setNextQuote() {
        var quote = mainVM.getNextQuote()
        tvQuote.text= quote.text
        tvAuthor.text=quote.author
    }

    private fun setQuote() {
        var quote = mainVM.getQuote()
        tvQuote.text= quote.text
        tvAuthor.text=quote.author
    }
}