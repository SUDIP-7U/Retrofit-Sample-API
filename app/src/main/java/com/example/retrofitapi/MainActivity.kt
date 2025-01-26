package com.example.retrofitapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofitapi.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val retrofitservice = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

        val reponselivedata : LiveData<Response<Album>> =
            liveData {
                val response = retrofitservice.getalbum()
                emit(response)
            }

        reponselivedata.observe(this, Observer {
            val albumlist = it.body()?.listIterator()

            if (albumlist != null){

                while (albumlist.hasNext()){
                    val albumitem = albumlist.next()

                    val albumtitle = "Album title : ${albumitem.title}\n"


                    binding.titleTextView.append(albumtitle)



                }
            }

        })
    }
}