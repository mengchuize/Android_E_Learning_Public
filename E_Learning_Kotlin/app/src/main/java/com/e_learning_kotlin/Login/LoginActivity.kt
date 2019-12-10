package com.e_learning_kotlin.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e_learning_kotlin.MainActivity
import com.e_learning_kotlin.R
import com.e_learning_kotlin.Register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //登录按钮事件
        val login_button=findViewById(R.id.button_login) as Button?
        val editText_username=findViewById(R.id.editText_username) as EditText?
        val editText_password=findViewById(R.id.editText_password) as EditText?
        login_button?.setOnClickListener {
            //获取用户名与密码
            var username=editText_username?.text.toString()
            var password=editText_password?.text.toString()
            if(logincheck(username,password)){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }else{
                //"用户名或密码错误!".toast(this)
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
        //注册按钮事件
        val register_button=findViewById(R.id.button_register) as Button?

        register_button?.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    //验证登录是否合法
    fun logincheck(username:String,password:String):Boolean{
        if(username=="testusername"&&password=="testpassword"){
            return true
        }
        return false
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }
}
