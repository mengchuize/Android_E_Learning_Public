package com.e_learning_kotlin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e_learning_kotlin.R


class courseAdapter // 用于把要展示的数据传进来 ，并赋值给一个全局变量mFruitList，后续的操作都将在这个数据源的基础上进行
    (private val mCourseList: List<course>) :
    RecyclerView.Adapter<courseAdapter.ViewHolder>() {

    //第一步 定义接口
    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    private var listener: OnItemClickListener? = null
    //第二步， 写一个公共的方法
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    // 定义一个内部类ViewHolder，继承自RecyclerView.ViewHolder。然后ViewHolder的构造函数中要传入一个
// view参数，这个参数通常就是RecyclerView子项的最外层布局，那么我们就可以通过findViewById（）方法
// 来获取到布局中的ImageView等的实例了
    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var courseImage: ImageView
        var coursename: TextView
        var schoolImage: ImageView
        var schoolname: TextView
        var freeimage: ImageView

        init {
            courseImage = view.findViewById(R.id.courseimage)
            coursename = view.findViewById(R.id.coursetext)
            schoolImage = view.findViewById(R.id.schoolimage)
            schoolname = view.findViewById(R.id.schooltext)
            freeimage = view.findViewById(R.id.freeimage)
        }
    }

    // 用于创建ViewHolder实例，我们在这个方法中将fruit_item布局加载进来，然后创建一个ViewHolder实例，
// 并把加载出来的布局传入到构造函数当中，最后将ViewHolder的实例返回
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return ViewHolder(view)
    }

    // 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过position
// 参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView当中
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val cou = mCourseList[position]
        holder.courseImage.setImageResource(cou.courseimageid)
        holder.schoolname.setText(cou.courseschool)
        holder.schoolImage.setImageResource(cou.schoolimageid)
        holder.coursename.setText(cou.coursename)
        holder.freeimage.setImageResource(cou.freeimageid)
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }
        holder.courseImage.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }
        holder.schoolImage.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }
        holder.schoolname.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }
        holder.coursename.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }
    }

    // 用于告诉RecyclerView一共有多少子项，直接返回数据源长度
    override fun getItemCount(): Int {
        return mCourseList.size
    }

}