package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentAuthorisationBinding

class AuthorisationFragment : Fragment() {
    private var _binding: FragmentAuthorisationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorisationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCreate.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_authorisationFragment2_to_registrationFragment)
        }
        binding.btnLogin.setOnClickListener {
            view.findNavController()
            setUpUserAuthorisation(view)
        }
        binding.ibBack.setOnClickListener {
            super.requireActivity().onBackPressed()
        }
    }

    private fun setUpUserAuthorisation(view: View) {
        when {
            TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(context, getString(R.string.enter_email_msg), Toast.LENGTH_SHORT)
                    .show()
            }
            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    context,
                    getString(R.string.enter_password_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
                val password: String = binding.etPassword.text.toString().trim { it <= ' ' }
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val fbUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                context,
                                "Welcome back!",
                                Toast.LENGTH_SHORT
                            ).show()
                            bundleOf(MarketFragment.userEmailKey to email)
                            bundleOf(MarketFragment.userIdKey to fbUser.uid)
                            view.findNavController()
                                .navigate(
                                    R.id.action_authorisationFragment2_to_marketFragment2)
                        } else {
                            Toast.makeText(
                                context,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}