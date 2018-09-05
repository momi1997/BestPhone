package com.example.android.bestphone

import android.arch.lifecycle.Observer
import android.arch.persistence.room.util.StringUtil
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.android.bestphone.Dagger.ContextModule
import com.example.android.bestphone.Dagger.DaggerPhoneComponent
import com.example.android.bestphone.phones_list.PhoneAdapter
import com.example.android.bestphone.phones_list.PhoneViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var recylerView : RecyclerView
    private lateinit var phoneLayoutManager : RecyclerView.LayoutManager
    private lateinit var phoneViewModel : PhoneViewModel
    private lateinit var phoneAdapter: PhoneAdapter
    private lateinit var connectivityManager: ConnectivityManager
    //the progress bar
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //gets an instance of phone component
        val phoneComponent = DaggerPhoneComponent.builder()
                .contextModule(ContextModule(this)).build()
        //gets a PhoneViewModel
        phoneViewModel = phoneComponent.getPhoneModel()
        //gets a PhoneAdapter
        phoneAdapter = phoneComponent.getPhoneAdapter()
        //gets the ConnectivityManager
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //intializes the views
        initViews(phoneViewModel, phoneAdapter)

    }


    /**Initializes the view for this activity
     * @param phoneViewModel ViewModel object that provides the required data for initialization*/
    private fun initViews (phoneViewModel: PhoneViewModel, phoneAdapter: PhoneAdapter){
        //init progress bar
        progressBar = findViewById(R.id.progressBar)
        //sets up the recyler view
        setupRecyclerView(phoneViewModel, phoneAdapter)
        //sets up the searchview
        setupSearchView()

    }
    /**Sets up the SearchView and its behaviour*/
    private fun setupSearchView (){
        //gets searchview
        val searchView = findViewById<SearchView>(R.id.searchView2)
        searchView.setIconifiedByDefault(false)
        //sets the the behaviour when text is submitted to the search view
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //checks if the device is connected
                val activeNetwork : NetworkInfo? = connectivityManager.activeNetworkInfo
                //performs the call if the network
                val isConnected = activeNetwork?.isConnectedOrConnecting == true ?: false
                if (isConnected){
                    //when the text is submitted, the app queries the database
                    phoneViewModel.getPossiblePhones(query ?: "")
                    //makes the progress bar visible
                    progressBar.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this@MainActivity, "No network access",Toast.LENGTH_LONG).show()
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //clears the adapter when the search field is empty
                if (TextUtils.isEmpty(newText)){
                    phoneAdapter.dataSet = mutableListOf()
                }
                return true
            }


        })

        //clears the search view field when the search view is closed
        searchView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                phoneAdapter.dataSet = mutableListOf()
                return true
            }

        })


    }

    /**Sets up the RecyclerView
     * @param phoneViewModel for observing the data*/
    private fun setupRecyclerView(phoneViewModel: PhoneViewModel, phoneAdapter: PhoneAdapter){
        //inits the necessary components of the recycler view
        phoneViewModel.phones.observe(this, Observer {
            //sets the new list to the adapter
            if (it == null || it.status == Resource.Status.NEW){

                phoneAdapter.dataSet = mutableListOf()

            } else if (it.status == Resource.Status.SUCCESS){

                phoneAdapter.dataSet = it.theData

            } else {

                phoneAdapter.dataSet = mutableListOf()
                Toast.makeText(this, "Device not found", Toast.LENGTH_SHORT).show()

            }
            //hides the progress bar
            progressBar.visibility = View.GONE
        })


        phoneLayoutManager = LinearLayoutManager(this)

        recylerView = findViewById<RecyclerView>(R.id.recyclerView).apply{

            adapter = phoneAdapter

            layoutManager = phoneLayoutManager

            setHasFixedSize(true)
        }
    }
}
