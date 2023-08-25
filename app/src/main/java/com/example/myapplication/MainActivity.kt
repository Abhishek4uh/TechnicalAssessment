package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment


import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.storage.SharedPreferencesManager

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private lateinit var sharedPreferencesManager: SharedPreferencesManager


    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
        Log.d(TAG,"onCreate")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.naHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        sharedPreferencesManager = SharedPreferencesManager(this)

        val existingUser = sharedPreferencesManager.getExistingUser()
        if (existingUser){
            navController.navigate(R.id.action_loginFragment5_to_mainFragment2)
        }
    }
}