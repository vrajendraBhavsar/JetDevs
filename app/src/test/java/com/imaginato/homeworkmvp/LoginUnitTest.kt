package com.imaginato.homeworkmvp

import org.junit.Test
import java.util.regex.Pattern

class LoginUnitTest {

    private val PASSWORD_PATTERN = "^[a-zA-Z0-9 ]*$"
    private val userName = "username"
    private val password = "1111111"


    @Test
    fun checkUserName() {
        assert(userName.isNotEmpty())
    }

    @Test
    fun checkPassword() {
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        assert(  !pattern.matcher(password).matches() || password.length > 16)
    }
}