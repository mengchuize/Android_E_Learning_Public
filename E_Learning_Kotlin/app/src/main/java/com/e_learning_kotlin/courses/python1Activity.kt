package com.e_learning_kotlin.courses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.e_learning_kotlin.R

class python1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_python1)
        initData()
    }
    private fun initData() {
        val jiaozi_videoplayer: JzvdStd = findViewById(R.id.jiaozi_videoplayer)
        jiaozi_videoplayer.setUp(
            "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4",
            "视频播放", Jzvd.SCREEN_WINDOW_NORMAL
        )
        //        jiaozi_videoplayer.thumbImageView.setImageResource(R.drawable.default_picture);
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }

}
