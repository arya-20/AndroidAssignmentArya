package com.example.bottomnav1.suite

import com.example.bottomnav1.components.CustomButtonTests
import com.example.bottomnav1.components.CustomTextFieldTests
import com.example.bottomnav1.screens.AddScreenTests
import com.example.bottomnav1.screens.HomeScreenTests
import com.example.bottomnav1.screens.LoginScreenTests
import com.example.bottomnav1.screens.SignUpPageTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
                CustomButtonTests::class,
                CustomTextFieldTests::class,
                LoginScreenTests::class,
                SignUpPageTests::class,
                HomeScreenTests::class,
                AddScreenTests::class
                //EditScreenTests::class
                )
class TestSuite{

}