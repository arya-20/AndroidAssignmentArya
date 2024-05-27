package com.example.bottomnav1.suite

import com.example.bottomnav1.components.CustomButtonTests
import com.example.bottomnav1.components.CustomTextFieldTests
import com.example.bottomnav1.screens.AddScreenTests
import com.example.bottomnav1.screens.BulkScreenTests
import com.example.bottomnav1.screens.EditScreenTests
import com.example.bottomnav1.screens.HomeScreenTests
import com.example.bottomnav1.screens.LoginScreenTests
import com.example.bottomnav1.screens.RecipeDetailsScreenTests
import com.example.bottomnav1.screens.SignUpScreenTests
import com.example.bottomnav1.screens.TrackScreenTests
import com.example.bottomnav1.screens.VeganScreenTests
import com.example.bottomnav1.screens.WeightGainScreenTests
import com.example.bottomnav1.screens.WeightLossScreenTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
                CustomButtonTests::class,
                CustomTextFieldTests::class,
                LoginScreenTests::class,
                SignUpScreenTests::class,
                HomeScreenTests::class,
                AddScreenTests::class,
                EditScreenTests::class,
                BulkScreenTests::class,
                VeganScreenTests::class,
                WeightGainScreenTests::class,
                WeightLossScreenTests::class,
                RecipeDetailsScreenTests::class,
                TrackScreenTests::class

                )
class TestSuite{

}