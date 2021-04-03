package com.denis.foxpictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.denis.foxpictures.api.ApiRequests
import com.denis.foxpictures.api.FoxJson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

const val BASE_URL = "https://randomfox.ca"

class PictureActivity : AppCompatActivity() {

    private var TAG = "PictureActivity"
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiRequests::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        val actionBar = supportActionBar
        this.title = "Вот тебе лисичка"
        actionBar.run {

            this!!.title = "Вот тебе лисичка"
            setDisplayHomeAsUpEnabled(true)
        }

        getCurrentData()

        layout_generate_new_fact.setOnClickListener { getCurrentData() }


    }

    private fun getCurrentData() {
        iv_foxView.visibility = View.INVISIBLE
        tv_timeStamp.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: FoxJson = api.getFoxPicture()
                Log.d(TAG, response.image)

                withContext(Dispatchers.Main) {
                    Picasso.get().
                        load(response.image)
                        .resize(1000,1750)
                        .centerCrop()
                        .into(iv_foxView)

                    tv_timeStamp.text = response.link

                    iv_foxView.visibility = View.VISIBLE
                    tv_timeStamp.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "Скорее всего, у вас нету подключения к интернету",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}