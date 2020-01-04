package com.e_learning_kotlin.login

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.UserDictionary.Words.APP_ID
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e_learning_kotlin.MainActivity
import com.e_learning_kotlin.Myreceiver
import com.e_learning_kotlin.R
import com.e_learning_kotlin.register.RegisterActivity
import com.tencent.connect.UserInfo
import com.tencent.connect.auth.QQToken
import com.tencent.connect.common.Constants
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    var mTencent = Tencent.createInstance(APP_ID,this.getApplicationContext())
    var mIUiListener: BaseUiListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreferences = getSharedPreferences("config", 0)

        val login_button=findViewById(R.id.button_login) as Button?
        val editText_username=findViewById(R.id.editText_username) as EditText?
        val editText_password=findViewById(R.id.editText_password) as EditText?
        var check1=findViewById<CheckBox>(R.id.checkBox)
        var check2=findViewById<CheckBox>(R.id.checkBox2)
        //取出数据，如果取出的数据时空时，只需把getString("","")第二个参数设置成空字符串就行了，不用在判断
        val name = sharedPreferences.getString("name", "")
        val password = sharedPreferences.getString("password", "")
        //获取勾选的状态
        val checkbox = sharedPreferences.getBoolean("checkbox", false)
        editText_username?.setText(name)
        editText_password?.setText(password)
        check1.setChecked(checkbox)
        if (check2.isChecked){
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra("username",name);
            startActivity(intent)
        }
        if(check1.isChecked){
            editText_username?.setText("mengchuize")
            editText_password?.setText("testpassword")
            if(check2.isChecked){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)

                intent.putExtra("username",editText_username?.text);
                startActivity(intent)
            }
        }

        //登录按钮事件

        login_button?.setOnClickListener {
            //获取用户名与密码

            var username=editText_username?.text.toString()
            var password=editText_password?.text.toString()
            if(logincheck(username,password)){
                val username: String = editText_username?.getText().toString()
                val passwowrd: String = editText_password?.getText().toString()
                //文本判断是否为空，新的API:TextUtils.isEmty()
                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(passwowrd)) {
                } else {
                    System.out.println("以后补上")

                        //把密码和用户名存起来
                        //getSharedPreferences(name,model);,name 会生成一个xml文件，model ：模式，可读可写等模式
                        //把密码和用户名存起来
                        //getSharedPreferences(name,model)
                        val sp = getSharedPreferences("config", 0)
                        val editor: SharedPreferences.Editor = sp.edit()
                        //把数据进行保存

                        editor.putString("name", username)
                        editor.putString("password", passwowrd)
                        //记住勾选的状态

                        editor.putBoolean("checkbox", check1.isChecked())
                        editor.putBoolean("checkbox1", check2.isChecked())
                        //提交数据

                        editor.commit()

                }
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }else{
                "用户名或密码错误!".toast(this)
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                startActivity(intent)



                val intentFilter = IntentFilter()
                intentFilter.addAction("BROADCAST_ACTION")
                var myReceiver =  Myreceiver()
                registerReceiver(myReceiver,intentFilter)

                val intent = Intent()
                intent.putExtra("key", "shipinguangbo")
                intent.action = "BROADCAST_ACTION"
                sendBroadcast(intent)

            }

        }
        //注册按钮事件
        val register_button=findViewById(R.id.button_register) as Button?

        register_button?.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        val qq_button=findViewById(R.id.button13) as Button?
        qq_button?.setOnClickListener {

            mIUiListener =  BaseUiListener();
            //all表示获取所有权限
            mTencent.login(this,"all", mIUiListener);
        }
    }
    //验证登录是否合法
    fun logincheck(username:String,password:String):Boolean{
        if(username=="mengchuize"&&password=="testpassword"){
            return true
        }
        return false
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }


    class BaseUiListener : IUiListener {
        override  fun onComplete(response: Any) {
            var mUserInfo: UserInfo? = null
            var mTencent = Tencent.createInstance(APP_ID,getApplicationContext())
//            Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show()
            val obj: JSONObject = response as JSONObject
            try {
                val openID: String = obj.getString("openid")
                val accessToken: String = obj.getString("access_token")
                val expires: String = obj.getString("expires_in")

               mTencent.setOpenId(openID)
              mTencent.setAccessToken(accessToken, expires)
               val qqToken: QQToken = mTencent.getQQToken()
                mUserInfo = UserInfo(getApplicationContext(), qqToken)
                mUserInfo?.getUserInfo(object : IUiListener {
                    override fun  onComplete(response: Any) {
                        // Log.i(TAG, "登录成功$response")
                    }

                    override fun onError(uiError: UiError) {
                        //Log.e(TAG, "登录失败" + uiError.toString())
                    }

                    override fun onCancel() {
                        //Log.e(TAG, "登录取消")
                    }
                })
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        private fun getApplicationContext(): Context? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onError(uiError: UiError?) {
            // Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show()
        }

        override  fun onCancel() {
            // Toast.makeText(getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     */
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun getApplicationContext(): Context? {
        return this
    }


//    var handler: Handler = object : Handler() {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            val data: Bundle = msg.getData()
//            val `val` = data.getString("value")
//            Log.i("mylog", "请求结果为-->$`val`")
//            // TODO
//// UI界面的更新等相关操作
//        }
//    }

    /**
     * 网络操作相关的子线程
     */
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu)
//        return true
//    }


}

