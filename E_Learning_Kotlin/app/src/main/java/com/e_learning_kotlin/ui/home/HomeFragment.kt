package com.e_learning_kotlin.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SlidingDrawer.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.e_learning_kotlin.R
import com.e_learning_kotlin.sourse.sourse
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var size=5
    var root:View?=null
    private var mdrawer: SlidingDrawer? = null
    private var mButton: ImageButton? = null
    private var mText: TextView? = null
    var slidingDrawer: SlidingDrawer? = null
    var flag1=true
    var table =
        arrayOf("推荐", "Python", "Java", "数据库", "前端开发") //定义一个数组存放标题内容
    private val searcoh =
        arrayOf("Android", "Listview怎么用", "html", "java", "python入门")
    var json_courses = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = root?.findViewById<TabLayout>(R.id.tablayout)
        viewPager = root?.findViewById<ViewPager>(R.id.viewpager)

        val adapter = Adapter(childFragmentManager) //参数1为fragment管理器
        viewPager!!.adapter = adapter //给viewPager设置适配器
        tabLayout!!.setupWithViewPager(viewPager) //将tabLayout与viewPager建立匹配
        Thread(networkTask).start()
        Thread(networkTaskui).start()
        val sharedPreferences = activity?.getSharedPreferences ("config", 0)
        var name = sharedPreferences?.getString("name", "")!!
        "服务器已经更新！".toast(context!!)
        return root
    }
    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }
    private fun drawerlistener() {
        mdrawer?.setOnDrawerOpenListener(OnDrawerOpenListener {
            slidingDrawer?.bringToFront()
            flag1 = true
            //mButton?.setImageResource(R.drawable.right)
        })
        mdrawer?.setOnDrawerCloseListener(OnDrawerCloseListener {
            flag1 = false
            //mButton?.setImageResource(R.drawable.left)
        })
        mdrawer?.setOnDrawerScrollListener(object : OnDrawerScrollListener {
            override fun onScrollStarted() {
                slidingDrawer?.bringToFront()
            }

            override fun onScrollEnded() {
                slidingDrawer?.bringToFront()
            }
        })
    }
    var networkTaskui = Runnable {
        // TODO
        // 在这里进行 http request.网络请求相关操作
        //抽屉
        //抽屉
        slidingDrawer=root?.findViewById<SlidingDrawer>(R.id.slidingDrawer)
        mdrawer = root?.findViewById<SlidingDrawer>(R.id.slidingDrawer)
        mButton = root?.findViewById<ImageButton>(R.id.handle)
        mText = root?.findViewById<TextView>(R.id.textView)
        drawerlistener()

    }
    var networkTask = Runnable {
        // TODO
        // 在这里进行 http request.网络请求相关操作
        var hr= sourse()
        json_courses=hr.httprequestcourses()

        val sp = activity?.getSharedPreferences("config", 0)
        val editor: SharedPreferences.Editor = sp!!.edit()
        //把数据进行保存
        editor.putString("courses", json_courses)
        //提交数据
        editor.commit()

    }
    //创建一个内部类作为适配器
    inner class Adapter(
        fm: FragmentManager?
    ) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            when(position){
                0->return fragment_tuijian()
                1->return fragment_python()
                2->return fragment_java()
                3->return fragment_database()
                else->return fragment_qianduan()
            }

        }

        override fun getCount(): Int {
            return size
        }

        //重写getPageTitle()方法，获取页标题
        override fun getPageTitle(position: Int): CharSequence? {
            return table.get(position)
        }

    }
}