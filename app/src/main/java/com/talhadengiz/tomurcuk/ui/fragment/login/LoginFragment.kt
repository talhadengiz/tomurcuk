package com.talhadengiz.tomurcuk.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.databinding.FragmentLoginBinding
import com.talhadengiz.tomurcuk.utils.Constant
import com.talhadengiz.tomurcuk.utils.SharedPrefHelper

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun setInput() {
        email = binding.etMail.text.toString()
        password = binding.etPassword.text.toString()
    }

    private fun initClickListener() {
        binding.btnLogin.setOnClickListener {
            setInput()
            if (email == Constant.email && password == Constant.password) {
                SharedPrefHelper(requireContext()).saveLogin(true)
                findNavController().navigate(R.id.action_loginFragment_to_foundationFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.login_message),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}