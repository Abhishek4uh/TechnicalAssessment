package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSignupBinding
import com.example.myapplication.storage.SharedPreferencesManager


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment() {

    private var mBinding: FragmentSignupBinding? = null
    private val passwordRegex = "^(?=.*[0-9])(?=.*[!@#\$%&\\(\\)])(?=.*[a-z])(?=.*[A-Z]).{8,}$".toRegex()
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding= FragmentSignupBinding.inflate(layoutInflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferencesManager = SharedPreferencesManager(context!!)

        initClick()
    }

    private fun isPasswordValid(password: String): Boolean {
        return passwordRegex.matches(password)
    }

    private fun initClick() {
        mBinding?.signUpButton?.setOnClickListener {
            if (mBinding?.usernameEditText?.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please Enter the Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (mBinding?.passwordEditText?.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please Enter the Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!isPasswordValid(mBinding?.passwordEditText?.text.toString())){
                Toast.makeText(requireContext(), "Please Enter the valid Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val username = mBinding?.usernameEditText?.text.toString()
            val password = mBinding?.passwordEditText?.text.toString()
            sharedPreferencesManager.saveCredentials(username,password,false)
            Toast.makeText(requireContext(), "Signup Success", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_signupFragment3_to_loginFragment5)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SignupFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}