package com.talhadengiz.tomurcuk.ui.fragment.foundation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.talhadengiz.tomurcuk.R
import com.talhadengiz.tomurcuk.databinding.FragmentLoginBinding
import com.talhadengiz.tomurcuk.utils.SharedPrefHelper

class LoginFragment : Fragment() {

    private lateinit var binding:FragmentLoginBinding

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

    private fun initClickListener() {
        binding.btnLogin.setOnClickListener {
            if(binding.etMail.text.toString() == "talhadengiz@gmail.com" && binding.etPassword.text.toString() == "Asd123"){
                SharedPrefHelper(requireContext()).saveLogin(true)
                findNavController().navigate(R.id.action_loginFragment_to_foundationFragment)
            }else{
                Toast.makeText(requireContext(),"Mail veya şifre hatalı",Toast.LENGTH_LONG).show()
            }
        }
    }
}