package com.e_learning_kotlin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e_learning_kotlin.Login.LoginActivity
import com.e_learning_kotlin.R
import com.e_learning_kotlin.courses.python1Activity
import java.util.*

class fragment_python : Fragment() {
    private val courseList: MutableList<course> = ArrayList<course>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_python, container, false)
        // 初始化水果数据
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
                        val intent1 = Intent(activity, python1Activity::class.java)
                        startActivity(intent1)
                    }
                    2 -> {
                        val intent2 = Intent(activity, python1Activity::class.java)
                        startActivity(intent2)
                    }
                    3 -> {
                        val intent3 = Intent(activity, python1Activity::class.java)
                        startActivity(intent3)
                    }
                }
            }
        })
        recyclerView.adapter = adapter
        return view
    }

    private fun initcourse() {
        for (i in courseList.indices.reversed()) {
            courseList.removeAt(i)
        }
        for (i in 0..100) {
            val a =
                course("python语言程序设计", "北京交通大学", R.drawable.p2, R.drawable.school, R.drawable.free)
            courseList.add(a)
            val b =
                course("零基础学python", "北京交通大学", R.drawable.p3, R.drawable.school, R.drawable.free)
            courseList.add(b)
            val c =
                course("Python入门", "北京交通大学", R.drawable.p4, R.drawable.school, R.drawable.free)
            courseList.add(c)
            val d =
                course("Python高阶", "北京交通大学", R.drawable.p5, R.drawable.school, R.drawable.free)
            courseList.add(d)
        }
    }
}