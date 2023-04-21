package com.example.rickandmorty
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Data.characterdata
import com.example.rickandmorty.Data.locationdata
import com.example.rickandmorty.adapter.characteradapter
import com.example.rickandmorty.adapter.locationadapter
import com.example.rickandmorty.databinding.ActivityHomePageBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory



class HomePage : AppCompatActivity(), locationadapter.Listener ,characteradapter.Listener{

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var locationdata: ArrayList<locationdata>
    private lateinit var locadapter:locationadapter
    private  var resId = mutableListOf<String>()
    private  var characterdata=ArrayList<characterdata>()
    private lateinit var  charadapter: characteradapter
    private var check=1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
         val check1=intent.getStringExtra("check1")
        if (check1!=null){
            check=check1.toInt()
        }

        //RecyclerView
        val layoutManger:RecyclerView.LayoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerview.layoutManager=layoutManger
        val layoutManager2:RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.characterrecycler.layoutManager=layoutManager2
        Loadlocationdata()





    }

    override fun onPause() {
        if (check==1){
            val intent= Intent(this,SplashScreen::class.java)
            val text="Hello!"
            intent.putExtra("text",text)
            startActivity(intent)
            finish()
        }else{

        }
        super.onPause()
    }





    private fun Loadlocationdata(){
        val moshi=Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit=Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api=retrofit.create(API::class.java)
        val call= api.getdata()
        call.enqueue(object :Callback<List<locationdata>>{
            override fun onResponse(
                call: Call<List<locationdata>>,
                response: Response<List<locationdata>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        locationdata=ArrayList(it)
                        locadapter=locationadapter(locationdata,this@HomePage)
                        binding.recyclerview.adapter=locadapter

                    }
                }
            }

            override fun onFailure(call: Call<List<locationdata>>, t: Throwable) {

            }
        })
    }

    override fun onitemclick(locationdata: locationdata) {
        if (resId.count()>0){
            resId.clear()
        }
        getId(locationdata)

        loadcharacterdata()

    }


    private fun getId(locationdata: locationdata){

         for (index in 0..locationdata.residents.size-1){
             resId.add(locationdata.residents[index].substringAfterLast("/"))
         }
    }

    private fun loadcharacterdata(){
        val moshi=Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit=Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api=retrofit.create(API::class.java)
        val call= api.getcharacter(resId)
        call.enqueue(object :Callback<List<characterdata>>{
            override fun onResponse(
                call: Call<List<characterdata>>,
                response: Response<List<characterdata>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {

                        characterdata= ArrayList(it)
                        charadapter=characteradapter(characterdata,this@HomePage)
                        binding.characterrecycler.adapter=charadapter

                    }
                }
            }

            override fun onFailure(call: Call<List<characterdata>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
    private fun intent(characterdata: characterdata){
        val intent=Intent(this,DetailsPage::class.java)
        val intentId=characterdata.id
        check=0
        intent.putExtra("id",intentId.toString())
        startActivity(intent)
    }
    override fun onItemClick(characterdata: characterdata) {

    intent(characterdata)

    }

}