package com.example.android.bestphone

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar
import com.aafanasev.fonoapi.DeviceEntity
import com.example.android.bestphone.phone_details.DetailViewModel
import com.example.android.bestphone.phone_details.DetailViewModelFactory
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {
    //default device
    val DEFAULT_DEVICE = DeviceEntity("","","","","","","",""
    ,"","","","","","","","","","","",
            "","","","","","","","","","","",
            "","","","","","","","","","","","",
            "","","","","","","","","","","","",
            "","","","","","","","","","","")

    //the phone body section TextViews
    lateinit var dimensionTextView : TextView
    lateinit var weightTextView: TextView
    lateinit var simTextView: TextView

    //the phone display section TextViews
    lateinit var typeTextView: TextView
    lateinit var sizeTextView: TextView
    lateinit var resolutionTextView: TextView
    lateinit var multitouchTextView: TextView
    lateinit var protectionTextView: TextView

    //the phone platform section TextViews
    lateinit var osTextView: TextView
    lateinit var chipsetTextView: TextView
    lateinit var cpuTextView: TextView
    lateinit var gpuTextView: TextView

    //the phone storage section TextViews
    lateinit var cardSlotTextView: TextView
    lateinit var internalTextView: TextView

    //the phone primary camera section TextViews
    lateinit var primaryCameraTextView: TextView

    //the phone secondary camera section TextViews
    lateinit var secondaryTextView: TextView

    //the phone video section TextViews
    lateinit var videoTextView: TextView

    //the phone sound section TextView
    lateinit var alertTypesTextView: TextView
    lateinit var loudspeakerTextView: TextView
    lateinit var jackTextView: TextView

    //the phone communication section TextViews
    lateinit var wlanTextView: TextView
    lateinit var bluetoothTextView: TextView
    lateinit var nfcTextView: TextView
    lateinit var gpsTextView: TextView
    lateinit var radioTextView: TextView
    lateinit var usbTextView: TextView

    //the phone features section TextViews
    lateinit var sensorTextView: TextView
    lateinit var messagingTextView: TextView
    lateinit var browserTextView: TextView

    //the phone battery TextViews
    lateinit var batteryTextView: TextView
    lateinit var batteryLifeTextView: TextView
    lateinit var talkTimeTextView: TextView
    lateinit var musicPlayTextView: TextView

    //the phone color TextViews
    lateinit var colorTextView: TextView

    //the phone test TextViews
    lateinit var displayTextView: TextView
    lateinit var cameraTextView: TextView
    lateinit var loudspeakerTestTextView: TextView
    lateinit var audioQualityTextView: TextView

    //the phone networking TextViews
    lateinit var technologyTextView: TextView
    lateinit var g2BandTextView: TextView
    lateinit var g3BandTextView: TextView
    lateinit var g4BandTextView: TextView
    lateinit var gprsTextView: TextView
    lateinit var speedTextView: TextView
    lateinit var edgeTextView: TextView

    //the phone launch TextViews
    lateinit var announcedTextView: TextView
    lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //init the toolbar
        val tooblar = findViewById<android.support.v7.widget.Toolbar>(R.id.detail_toolbar)
        //sets the toolbar as the action bar
        setSupportActionBar(tooblar)
        //sets the home button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //inits the views
        initViews()
        //gets the intent
        val intent = intent
        //gets the json extra
        val json = intent.getStringExtra(getString(R.string.phone_key))
        //parses the json into a DeviceEntity
        val device = Gson().fromJson<DeviceEntity>(json, DeviceEntity::class.java)
        //gets a DetailViewModelFactory
        val detailModelFactory = DetailViewModelFactory(device)
        //gets a DetailViewModel
        val detailViewModel = ViewModelProviders.of(this, detailModelFactory).get(DetailViewModel::class.java)
        //updates the UI when the ViewModel data changes
        detailViewModel.phone.observe(this, Observer {
            updateUI(it ?: DEFAULT_DEVICE)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**Initializes the views*/
    private fun initViews (){
        //initis the views for the body section
        dimensionTextView = findViewById(R.id.dimension_text_view)
        weightTextView = findViewById(R.id.weight_text_view)
        simTextView = findViewById(R.id.sim_text_view)

        //intis the views for the display section
        typeTextView = findViewById(R.id.type_text_view)
        sizeTextView = findViewById(R.id.size_text_view)
        resolutionTextView = findViewById(R.id.resolution_text_view)
        multitouchTextView = findViewById(R.id.multitouch_text_view)
        protectionTextView = findViewById(R.id.protection_text_view)

        //intis the views for the platform section
        osTextView = findViewById(R.id.os_text_view)
        chipsetTextView = findViewById(R.id.chipset_text_view)
        cpuTextView = findViewById(R.id.cpu_text_view)
        gpuTextView = findViewById(R.id.gpu_text_view)

        //intis the views for the storage section
        cardSlotTextView = findViewById(R.id.card_slot_text_view)
        internalTextView = findViewById(R.id.internal_text_view)

        //intis the views for the primary camera section
        primaryCameraTextView = findViewById(R.id.primary_camera_text_view)

        //intis the views for the secondary camera section
        secondaryTextView = findViewById(R.id.secondary_camera_text_view)

        //intis the views for the video section
        videoTextView = findViewById(R.id.video_text_view)

        //intis the views for the sound section
        alertTypesTextView = findViewById(R.id.alter_types_text_view)
        loudspeakerTextView = findViewById(R.id.loudpeaker_text_view)
        jackTextView = findViewById(R.id._3_5_jack_text_view)

        //intis the views for the communication section
        wlanTextView = findViewById(R.id.wlan_text_view)
        bluetoothTextView = findViewById(R.id.bluetooth_text_view)
        nfcTextView = findViewById(R.id.nfc_text_view)
        gpsTextView = findViewById(R.id.gps_text_view)
        radioTextView = findViewById(R.id.radio_text_view)
        usbTextView = findViewById(R.id.usb_text_view)

        //intis the views for the features section
        sensorTextView = findViewById(R.id.sensors_text_view)
        messagingTextView = findViewById(R.id.messaging_text_view)
        browserTextView = findViewById(R.id.browser_text_view)

        //inits the views for battery section
        batteryTextView = findViewById(R.id.battery_text_view)
        batteryLifeTextView = findViewById(R.id.battery_lifex_text_view)
        talkTimeTextView = findViewById(R.id.talk_time_text_view)
        musicPlayTextView = findViewById(R.id.music_play_text_view)

        //intis the view for the color section
        colorTextView = findViewById(R.id.color_text_view)

        //inits the views for the tests section
        displayTextView = findViewById(R.id.display_text_view)
        cameraTextView = findViewById(R.id.camera_text_view)
        loudspeakerTestTextView = findViewById(R.id.loudspeaker_perf_text_view)
        audioQualityTextView = findViewById(R.id.audio_quality_text_view)

        //inits the views for the networking section
        technologyTextView = findViewById(R.id.technology_text_view)
        g2BandTextView = findViewById(R.id.a2g_text_view)
        g3BandTextView = findViewById(R.id.a3g_band_text_view)
        g4BandTextView = findViewById(R.id.a4g_band_text_view)
        speedTextView = findViewById(R.id.speed_text_view)
        gprsTextView = findViewById(R.id.gprs_text_view)
        edgeTextView = findViewById(R.id.edge_text_view)

        //intis the views for the launch section
        announcedTextView = findViewById(R.id.announced_text_view)
        statusTextView = findViewById(R.id.status_text_view)
    }

    /**Updates the UI
     * @param device a DeviceEntity that holds the data needed to populate the UI*/
    private fun updateUI(device : DeviceEntity){
        //sets the title of the toolbar
        supportActionBar?.title = device.deviceName
        updateBody(device)
        updateDisplay(device)
        updatePlatform(device)
        updateStorage(device)
        updatePrimaryCamera(device)
        updateSecondaryCamera(device)
        updateVideo(device)
        updateSound(device)
        updateCommunication(device)
        updateFeatures(device)
        updateBattery(device)
        updateColor(device)
        updateTest(device)
        updateNetworking(device)
        updateLaunch(device)
    }

    /**Updates the body section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the body section of the UI*/
    private fun updateBody(device: DeviceEntity){
        setTextInTextView(device.dimensions, dimensionTextView)
        setTextInTextView(device.weight, weightTextView)
        setTextInTextView(device.sim, simTextView)
        setTextInTextView(device.multitouch, multitouchTextView)
        setTextInTextView(device.protection, protectionTextView)
    }

    /**Updates the display section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the display section of the UI*/
    private fun updateDisplay(device: DeviceEntity){
        setTextInTextView(device.type, typeTextView)
        setTextInTextView(device.size, sizeTextView)
        setTextInTextView(device.resolution, resolutionTextView)
    }

    /**Updates the platform section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the platform section of the UI*/
    private fun updatePlatform(device: DeviceEntity){
        setTextInTextView(device.os, osTextView)
        setTextInTextView(device.chipset, chipsetTextView)
        setTextInTextView(device.cpu, cpuTextView)
        setTextInTextView(device.gpu, gpuTextView)
    }
    /**Updates the storage section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the storage section of the UI*/
    private fun updateStorage(device: DeviceEntity){
        setTextInTextView(device.card_slot, cardSlotTextView)
        setTextInTextView(device.internal, internalTextView)
    }

    /**Updates the primary camera section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the primary camera section of the UI*/
    private fun updatePrimaryCamera(device: DeviceEntity){
        setTextInTextView(device.primary_, primaryCameraTextView)
    }

    /**Updates the seconday camera section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the secondary camera section of the UI*/
    private fun updateSecondaryCamera(device: DeviceEntity){
        setTextInTextView(device.secondary, secondaryTextView)
    }

    /**Updates the video section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the video section of the UI*/
    private fun updateVideo(device: DeviceEntity){
        setTextInTextView(device.video, videoTextView)
    }

    /**Updates the sound section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the sound section of the UI*/
    private fun updateSound(device: DeviceEntity){
        setTextInTextView(device.alert_types,alertTypesTextView)
        setTextInTextView(device.loudspeaker_, loudspeakerTextView)
        setTextInTextView(device._3_5mm_jack_, jackTextView)
    }

    /**Updates the communication section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the communication section of the UI*/
    private fun updateCommunication(device: DeviceEntity){
        setTextInTextView(device.wlan, wlanTextView)
        setTextInTextView(device.bluetooth, bluetoothTextView)
        setTextInTextView(device.nfc, nfcTextView)
        setTextInTextView(device.gps, gpsTextView)
        setTextInTextView(device.radio, radioTextView)
        setTextInTextView(device.usb, usbTextView)
    }

    /**Updates the features section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the features section of the UI*/
    private fun updateFeatures(device: DeviceEntity){
        setTextInTextView(device.sensors, sensorTextView)
        setTextInTextView(device.messaging, messagingTextView)
        setTextInTextView(device.browser, browserTextView)
    }

    /**Updates the  battery section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the battery section of the UI*/
    private fun updateBattery(device: DeviceEntity){
        setTextInTextView(device.battery_c, batteryTextView)
        setTextInTextView(device.battery_life, batteryLifeTextView)
        setTextInTextView(device.talk_time, talkTimeTextView)
        setTextInTextView(device.music_play, musicPlayTextView)
    }

    /**Updates the colors section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the colors section of the UI*/
    private fun updateColor(device: DeviceEntity){
        setTextInTextView(device.colors, colorTextView)
    }

    /**Updates the tests section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the tests section of the UI*/
    private fun updateTest(device: DeviceEntity){
        setTextInTextView(device.display, displayTextView)
        setTextInTextView(device.camera, cameraTextView)
        setTextInTextView(device.loudspeaker, loudspeakerTestTextView)
        setTextInTextView(device.audio_quality, audioQualityTextView)
    }

    /**Updates the networking section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the networking section of the UI*/
    private fun updateNetworking(device: DeviceEntity){
        setTextInTextView(device.technology, technologyTextView)
        setTextInTextView(device._2g_bands, g2BandTextView)
        setTextInTextView(device._3g_bands, g3BandTextView)
        setTextInTextView(device._4g_bands, g4BandTextView)
        setTextInTextView(device.speed, speedTextView)
        setTextInTextView(device.gprs, gprsTextView)
        setTextInTextView(device.edge, edgeTextView)
    }

    /**Updates the launch section of the UI
     * @param device a DeviceEntity that holds the data needed to populate the launch section of the UI*/
    private fun updateLaunch(device: DeviceEntity){
        setTextInTextView(device.announced, announcedTextView)
        setTextInTextView(device.status, statusTextView)
    }

    /**Sets the provided text in a TextView if it's not null or blank.
     * Otherwise it sets the text to "???"
     * @param text the text to be set in a TextView
     * @param textView the TextView where the text should be set*/
    private fun setTextInTextView(text : String, textView: TextView){
        if (!text.isNullOrBlank()){
            textView.text = text
        } else {
            textView.text = "???"
        }
    }
}
