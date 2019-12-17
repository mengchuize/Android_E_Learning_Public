package com.e_learning_kotlin.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.e_learning_kotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import java.util.*

class DashboardFragment : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var size=2
    var table2 = arrayOf("推荐", "关注") //定义一个数组存放标题内容


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
        tabLayout = view.findViewById(R.id.tablayout1)
        viewPager = view.findViewById(R.id.viewpager1)
        val b1: FloatingActionButton
        b1 = view.findViewById(R.id.floatingActionButton)
        b1.setOnClickListener {
            //val intent = Intent(activity, createActivity::class.java)
            //startActivity(intent)
        }
        val adapter = Adapter(childFragmentManager)
        viewPager?.setAdapter(adapter) //给viewPager设置适配器

        tabLayout?.setupWithViewPager(viewPager) //将tabLayout与viewPager建立匹配
        return view
    }


    //创建一个内部类作为适配器
    inner class Adapter(
        fm: FragmentManager?
    ) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            if (position==0){
                return fragment_zhiquantuijian()
            }else{
                return fragment_zhiquanguanzhu()
            }
        }

        override fun getCount(): Int {
            return size
        }

        //重写getPageTitle()方法，获取页标题
        override fun getPageTitle(position: Int): CharSequence? {
            return table2.get(position)
        }

    }
}