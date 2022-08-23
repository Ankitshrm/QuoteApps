package com.insidecoderz.quoteapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.insidecoderz.quoteapps.dataclass.Quotes
import com.insidecoderz.quoteapps.factory.MainViewModelFactory
import com.insidecoderz.quoteapps.models.MainViewModel
import kotlin.coroutines.EmptyCoroutineContext.get

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel:MainViewModel

    private val quoteText:TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor:TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        // here we are using application instead of context such that during screen rotation, it maintain their values

        setQuotes(mainViewModel.currIndex())
    }

    fun setQuotes(quotes: Quotes){
        quoteText.text =quotes.text
        quoteAuthor.text =quotes.author
    }


    fun onPrevious(view: View) {
        setQuotes(mainViewModel.prevIndex())
    }
    fun onShare(view: View) {
        val intent =Intent(Intent.ACTION_SEND)
        intent.type ="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.currIndex().text)
        startActivity(intent)
    }
    fun onNext(view: View) {
        setQuotes(mainViewModel.nextIndex())
    }
}