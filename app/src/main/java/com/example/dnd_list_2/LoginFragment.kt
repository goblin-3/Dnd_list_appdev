package com.example.dnd_list_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
    //private val main: MainActivity = MainActivity()
    private val emailPasswordActivity = EmailPasswordActivity()

    private lateinit var mail : EditText
    private lateinit var password : EditText
    private lateinit var login : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)


        mail = binding.txtTheMail
        password = binding.txtThePassword
        login = binding.btnLogin

        login.setOnClickListener {
            //emailPasswordActivity.createAccount(mail.toString(), password.toString())
            //emailPasswordActivity.signIn(mail.toString(), password.toString())

            //main.hideKeyboard(it)

            val startFragment = StartFragment()
            (activity as MainActivity).switchTo(startFragment)
        }

        return binding.root
    }

}