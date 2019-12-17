package com.e_learning_kotlin.sourse

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection

public class sourse{

    fun  httprequestcourses():String{
        val urltext="http://172.24.53.53:8080/elearn/courses"

        val json = StringBuilder()
        try {
            val urlObject = URL(urltext)
            val uc: URLConnection = urlObject.openConnection()
            val inbuff =
                BufferedReader(InputStreamReader(uc.getInputStream(), "UTF-8"))
            var inputLine: String? = null
            while (inbuff.readLine().also({ inputLine = it }) != null) {
                json.append(inputLine)
            }
            inbuff.close()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        System.out.println("data");
        System.out.println(json.toString())
        return json.toString()
    }
    fun  httprequestteacher(courseId:String):String{
        val urltext="http://172.24.53.53:8080/elearn/courses/"+courseId+"/teachers"

        val json = StringBuilder()
        try {
            val urlObject = URL(urltext)
            val uc: URLConnection = urlObject.openConnection()
            val inbuff =
                BufferedReader(InputStreamReader(uc.getInputStream(), "UTF-8"))
            var inputLine: String? = null
            while (inbuff.readLine().also({ inputLine = it }) != null) {
                json.append(inputLine)
            }
            inbuff.close()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        System.out.println("data");
        System.out.println(json.toString())
        return json.toString()
    }

}