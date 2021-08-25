package com.example.firebaseauthapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth

class PasswordDialogFrag : DialogFragment() {

    private lateinit var btnUpDatePassword: Button
    private lateinit var btnPwCancel: Button
    private lateinit var tvPwHeading: TextView
    private lateinit var etUpdatePassword: EditText
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_password_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnUpDatePassword = view.findViewById(R.id.btnUpdatePassword)
        btnPwCancel = view.findViewById(R.id.btnPwCancelUpdate)
        tvPwHeading = view.findViewById(R.id.tvPwHeading)
        etUpdatePassword = view.findViewById(R.id.etUpdatePassword)
        firebaseAuth = FirebaseAuth.getInstance()

        tvPwHeading.text = requireActivity().getString(R.string.update_your_password)

        btnUpDatePassword.setOnClickListener {
            updatePassword()
            dismiss()
        }

        btnPwCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun updatePassword() {
        val password = etUpdatePassword.text.toString().trim()
        // [START update_password]
        val user = firebaseAuth.currentUser
        if (password.isNotBlank()) {
            user!!.updatePassword(password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "User password updated successfully.")
                    }
                }
        }
        // [END update_password]
    }

    companion object {
        const val TAG = "Dialog Fragment"
    }
}