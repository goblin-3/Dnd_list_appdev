package com.example.dnd_list_2

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
    private val emailPasswordActivity = EmailPasswordActivity()

    private lateinit var mail : EditText
    lateinit var sMail : String
    private lateinit var password : EditText
    lateinit var sPassword : String
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
            sMail = mail.text.toString()
            sPassword = password.text.toString()

            if (!isEmailValid(sMail)){
                binding.txtTheMail.setError("Enter correct mail")
            } else if (sPassword.length < 7) {
                binding.txtThePassword.setError("Password must be 7 characters")
            } else {
                //emailPasswordActivity.createAccount(mail.toString(), password.toString())
                //emailPasswordActivity.signIn(mail.toString(), password.toString())

                val startFragment = StartFragment()
                (activity as MainActivity).switchTo(startFragment)
            }
        }

        return binding.root
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}

