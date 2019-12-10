package com.e_learning_kotlin.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.e_learning_kotlin.My.changeActivity
import com.e_learning_kotlin.My.classActivity
import com.e_learning_kotlin.My.messageActivity
import com.e_learning_kotlin.R
import com.e_learning_kotlin.tools.NorthernScrollView

class NotificationsFragment : Fragment(), NorthernScrollView.NorthernScrollViewListener {
    var root: View? = null
    var northernScrollView: NorthernScrollView? = null
    var title: LinearLayout? = null
    var view1: ImageView? = null
    var height = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_notifications, container, false)
        //实力化控件
        initView()
        //计算控件高度
        getHetght()
        val b1 = root!!.findViewById(R.id.button5) as Button?
        val b2 = root!!.findViewById(R.id.button4) as Button?
        val b3 = root!!.findViewById(R.id.button3) as Button?
        val b11 = root!!.findViewById(R.id.button9) as Button?
        val b12 = root!!.findViewById(R.id.button8) as Button?
        val b13 = root!!.findViewById(R.id.button7) as Button?
        b1?.setOnClickListener{
            val intent = Intent(activity, changeActivity::class.java)
            startActivity(intent)
        }
        b2?.setOnClickListener{
            val intent = Intent(activity, classActivity::class.java)
            startActivity(intent)
        }
        b3?.setOnClickListener{
            val intent = Intent(activity, messageActivity::class.java)
            startActivity(intent)
        }
        b11?.setOnClickListener{
            val intent = Intent(activity, changeActivity::class.java)
            startActivity(intent)
        }
        b12?.setOnClickListener{
            val intent = Intent(activity, classActivity::class.java)
            startActivity(intent)
        }
        b13?.setOnClickListener{
            val intent = Intent(activity, messageActivity::class.java)
            startActivity(intent)
        }
        //退出登录事件
        val buttonlogout=root!!.findViewById<Button>(R.id.button19)
        buttonlogout.setOnClickListener {
            this.activity!!.finish()
        }
        return root
    }

    fun getHetght() {
        val vto = view1!!.viewTreeObserver
        vto.addOnGlobalLayoutListener {
            height = view1!!.height
            northernScrollView!!.setScrollViewListener(this@NotificationsFragment)
        }
    }

    fun initView() {
        northernScrollView = root!!.findViewById(R.id.northernScrollView)
        title = root!!.findViewById(R.id.title)
        view1 = root!!.findViewById(R.id.view1)
        title?.bringToFront()
    }

    override fun onScrollChanged(
        scrollView: NorthernScrollView?,
        x: Int,
        y: Int,
        oldx: Int,
        oldy: Int
    ) {
        if (y <= height) {
            title!!.visibility = View.INVISIBLE
        } else {
            title!!.visibility = View.VISIBLE
        }
    }
}