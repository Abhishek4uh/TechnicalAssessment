package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.storage.SharedPreferencesManager



/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var mBinding: FragmentLoginBinding? = null
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    lateinit var savedUsername:String
    lateinit var savedPassword:String


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding= FragmentLoginBinding.inflate(layoutInflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferencesManager = SharedPreferencesManager(context!!)
        initClick()
        savedUsername = sharedPreferencesManager.getSavedUsername().toString()
        savedPassword = sharedPreferencesManager.getSavedPassword().toString()
    }

    private fun initClick() {
        mBinding?.signUpTextView?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment5_to_signupFragment3)
        }

        mBinding?.loginButton?.setOnClickListener {
            if(mBinding?.usernameEditText?.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please Enter the Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(mBinding?.passwordEditText?.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please Enter the Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val username = mBinding?.usernameEditText?.text.toString()
            val password = mBinding?.passwordEditText?.text.toString()

            if (username!=savedUsername){
                Toast.makeText(requireContext(), "Username is incorrect", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password!=savedPassword){
                Toast.makeText(requireContext(), "Password is incorrect", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                val username = mBinding?.usernameEditText?.text.toString()
                val password = mBinding?.passwordEditText?.text.toString()
                sharedPreferencesManager.saveCredentials(username,password,true)
                findNavController().navigate(R.id.action_loginFragment5_to_mainFragment2)
            }
        }
    }
}