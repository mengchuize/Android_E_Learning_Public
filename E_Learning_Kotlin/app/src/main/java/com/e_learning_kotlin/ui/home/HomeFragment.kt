package com.e_learning_kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.e_learning_kotlin.R
import com.e_learning_kotlin.ui.dashboard.fragment_zhiquanguanzhu
import com.google.android.material.tabs.TabLayout
import java.util.*

class HomeFragment : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var size=5
    var table =
        arrayOf("推荐", "Python", "Java", "数据库", "前端开发") //定义一个数组存放标题内容

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = root.findViewById<TabLayout>(R.id.tablayout)
        viewPager = root.findViewById<ViewPager>(R.id.viewpager)

        val adapter = Adapter(childFragmentManager) //参数1为fragment管理器
        viewPager!!.adapter = adapter //给viewPager设置适配器
        tabLayout!!.setupWithViewPager(viewPager) //将tabLayout与viewPager建立匹配
        return root
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