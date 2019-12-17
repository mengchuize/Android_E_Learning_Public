package com.e_learning_kotlin.courses

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.e_learning_kotlin.R
import com.e_learning_kotlin.sourse.sourse
import com.e_learning_kotlin.ui.home.course
import org.json.JSONArray

class python2Activity : AppCompatActivity() {
    var json_courses = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread(networkTask).start();
        while(json_courses == ""){
            var i=1
        }
        setContentView(R.layout.activity_python1)
        initData()
        var textViewjson=findViewById<TextView>(R.id.textViewjson)
        val json_object= JSONArray(json_courses)
        val a =
            course(json_object.getJSONObject(1).getString("id"),json_object.getJSONObject(1).getString("name"),
                json_object.getJSONObject(1).getString("code"),json_object.getJSONObject(1).getString("categoryId"),
                json_object.getJSONObject(1).getString("description"),json_object.getJSONObject(1).getInt("price"),
                json_object.getJSONObject(1).getString("status"),json_object.getJSONObject(1).getString("openDate"),
                json_object.getJSONObject(1).getString("lastUpdateOn"),"北京交通大学",
                json_object.getJSONObject(1).getString("level"), json_object.getJSONObject(1).getInt("shared"),
                json_object.getJSONObject(1).getString("sharedUrl"),json_object.getJSONObject(1).getString("avatar"),
                json_object.getJSONObject(1).getString("bigAvatar"),json_object.getJSONObject(1).getString("certification"),
                json_object.getJSONObject(1).getString("certificationDuration"),
                R.drawable.p2, R.drawable.school, R.drawable.free)
        var jsontext="  ID:"+a.id+"\n  CODE:"+a.code+"\n  STATUS:"+a.status+"\n  PRICE:"+a.price+"\n  LEVEL:"+a.level+"\n  OPENDATE:"+a.openDate
        textViewjson.setText(jsontext)
        var textViewname=findViewById<TextView>(R.id.textView21)
        textViewname.setText(a.name)
    }
    private fun initData() {
        val jiaozi_videoplayer: JzvdStd = this.findViewById(R.id.jiaozi_videoplayer)
        jiaozi_videoplayer.setUp(
            "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4",
            "视放", Jzvd.SCREEN_WINDOW_NORMAL
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
    var networkTask = Runnable {
        // TODO
        // 在这里进行 http request.网络请求相关操作
        var hr= sourse()
        json_courses=hr.httprequestcourses()

    }
}
