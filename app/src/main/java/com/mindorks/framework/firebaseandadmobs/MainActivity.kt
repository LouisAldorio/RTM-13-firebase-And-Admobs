package com.mindorks.framework.firebaseandadmobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mInterAds : InterstitialAd? = null
    private var mRewardVid : RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        adView.loadAd(AdRequest.Builder().build())

        adView.adListener = object : AdListener() {

        }
        button.visibility = View.GONE

        //InterstitiakAD
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712",
            AdRequest.Builder().build(), object : InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    Toast.makeText(this@MainActivity, "Load Faild",
                        Toast.LENGTH_SHORT).show()
                    mInterAds = null
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    super.onAdLoaded(p0)
                    mInterAds = p0
                    button.visibility = View.VISIBLE
                }
            })
        //RewardAd
        RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917",
            AdRequest.Builder().build(), object  : RewardedAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    Toast.makeText(this@MainActivity, "Load Faild",
                        Toast.LENGTH_SHORT).show()
                    mRewardVid = null
                }

                override fun onAdLoaded(p0: RewardedAd) {
                    super.onAdLoaded(p0)
                    mRewardVid = p0
                    button.visibility = View.VISIBLE
                }
            })
    }

    fun showInterstitial(view: View) {
//        if(mInterAds!=null){
//            mInterAds?.show(this)
//        }
        var type = "Coin"
        var amount = 0
        if(mRewardVid!=null){
            mRewardVid?.show(this, OnUserEarnedRewardListener() {
                amount = 1000000
                textView.setText("$type = $amount")
            })
        }
    }
}