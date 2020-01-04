package com.e_learning_kotlin.login

import org.junit.Test

import org.junit.Assert.*

class LoginActivityTest {

    @Test
    fun logincheck() {
        val result = LoginActivity().logincheck("mengchuize", "testpassword")
        assertEquals(true, result)
    }
}