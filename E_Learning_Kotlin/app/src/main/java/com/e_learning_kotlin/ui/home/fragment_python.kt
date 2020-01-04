package com.e_learning_kotlin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e_learning_kotlin.R
import com.e_learning_kotlin.courses.python1Activity
import com.e_learning_kotlin.courses.python2Activity
import com.e_learning_kotlin.sourse.sourse
import org.json.JSONArray
import java.util.*

class fragment_python : Fragment() {
    private val courseList: MutableList<course> = ArrayList<course>()
    var json_courses = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Thread(networkTask).start();
        val view: View = inflater.inflate(R.layout.fragment_python, container, false)
        // 初始化数据
        while(json_courses==""){
            var i=0
        }
        initcourse()
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerviewpy)
        recyclerView.removeAllViews()
        // 创建一个LinearLayoutManager对象，并把它设置到RecyclerView当中
// LayoutManager用于指定RecyclerView的布局方式，这里是线性布局的意思
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        // 创建FruitAdapter的实例，并将水果数据传入到FruitAdapter的构造函数中
        val adapter = courseAdapter(courseList)

        adapter.setOnItemClickListener(object : courseAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(activity, python1Activity::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        val intent1 = Intent(activity, python2Activity::class.java)
                        startActivity(intent1)
                    }
                }
            }
        })
        recyclerView.adapter = adapter
        return view
    }
    var networkTask = Runnable {
        // TODO
        // 在这里进行 http request.网络请求相关操作
        var hr= sourse()
        json_courses=hr.httprequestcourses()


    }
    private fun initcourse() {
        for (i in courseList.indices.reversed()) {
            courseList.removeAt(i)
        }

        val json_object=JSONArray(json_courses)
        for (i in 0..1) {
            val a =
                course(json_object.getJSONObject(0).getString("id"),json_object.getJSONObject(0).getString("name"),
                    json_object.getJSONObject(0).getString("code"),json_object.getJSONObject(0).getString("categoryId"),
                    json_object.getJSONObject(0).getString("description"),json_object.getJSONObject(0).getInt("price"),
                    json_object.getJSONObject(0).getString("status"),json_object.getJSONObject(0).getString("openDate"),
                    json_object.getJSONObject(0).getString("lastUpdateOn"),"北京交通大学",
                    json_object.getJSONObject(0).getString("level"), json_object.getJSONObject(0).getInt("shared"),
                    json_object.getJSONObject(0).getString("sharedUrl"),json_object.getJSONObject(0).getString("avatar"),
                    json_object.getJSONObject(0).getString("bigAvatar"),json_object.getJSONObject(0).getString("certification"),
                    json_object.getJSONObject(0).getString("certificationDuration"),
                    R.drawable.p2, R.drawable.school, R.drawable.free)
            courseList.add(a)
            val b =
                course(json_object.getJSONObject(1).getString("id"),json_object.getJSONObject(1).getString("name"),
                    json_object.getJSONObject(1).getString("code"),json_object.getJSONObject(1).getString("categoryId"),
                    json_object.getJSONObject(1).getString("description"),json_object.getJSONObject(1).getInt("price"),
                    json_object.getJSONObject(1).getString("status"),json_object.getJSONObject(1).getString("openDate"),
                    json_object.getJSONObject(1).getString("lastUpdateOn"),"北京交通大学",
                    json_object.getJSONObject(1).getString("level"), json_object.getJSONObject(1).getInt("shared"),
                    json_object.getJSONObject(1).getString("sharedUrl"),json_object.getJSONObject(1).getString("avatar"),
                    json_object.getJSONObject(1).getString("bigAvatar"),json_object.getJSONObject(1).getString("certification"),
                    json_object.getJSONObject(1).getString("certificationDuration"),
                    R.drawable.p3, R.drawable.school, R.drawable.free)
            courseList.add(b)

        }
    }
}