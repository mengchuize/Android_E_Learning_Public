package com.e_learning_kotlin.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.e_learning_kotlin.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //返回按钮事件
        val ImageButton_GoBack=findViewById(R.id.ImageButton_GoBack) as ImageButton?
        ImageButton_GoBack?.setOnClickListener {
            this.finish()
        }
    }
}
