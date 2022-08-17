package com.example.dnd_list_2

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        val mail = binding.txtTheMail
        val password = binding.txtThePassword

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val sMail = mail.text.toString()
            val sPassword = password.text.toString()

            if (!isEmailValid(sMail)){
                binding.txtTheMail.setError("Enter correct mail")
            } else if (sPassword.length < 7) {
                binding.txtThePassword.setError("Password must be 7 characters")
            } else {
                firebaseAuth.signInWithEmailAndPassword(sMail, sPassword).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val startFragment = StartFragment()
                        (activity as MainActivity).switchTo(startFragment)
                    } else {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.txtSignUp.setOnClickListener{
            val sMail = mail.text.toString()
            val sPassword = password.text.toString()

            if (!isEmailValid(sMail)){
                binding.txtTheMail.setError("Enter correct mail")
            } else if (sPassword.length < 7) {
                binding.txtThePassword.setError("Password must be 7 characters")
            } else {
                firebaseAuth.createUserWithEmailAndPassword(sMail, sPassword).addOnCompleteListener {
                    if (!it.isSuccessful) {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding.root
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}

