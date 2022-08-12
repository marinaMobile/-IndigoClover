package com.pnixg

import android.app.Application
import android.content.Context
import com.my.tracker.MyTracker
import com.my.tracker.MyTrackerConfig
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import com.pnixg.black.Advert
import com.pnixg.black.CNST
import com.pnixg.black.CNST.ONESIGNAL_APP_ID
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@HiltAndroidApp
class AppClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        // При необходимости, настройте конфигурацию трекера
        val trackerParams = MyTracker.getTrackerParams()
        val trackerConfig = MyTracker.getTrackerConfig()
//        trackerParams.customUserId = "user_id"
        val userId = trackerParams.customUserId()
        println(userId)
        trackerConfig.isTrackingLaunchEnabled = true
        MyTracker.initTracker("10083586393852631494", this)

        GlobalScope.launch(Dispatchers.IO) {
            applyDeviceId(context = applicationContext)
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

    private suspend fun applyDeviceId(context: Context) {
        val advertisingInfo = Advert(context)
        val idInfo = advertisingInfo.getAdvertisingId()
        Hawk.put(CNST.MAIN_ID, idInfo)
    }
}