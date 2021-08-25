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

class EmailDialogFrag : DialogFragment() {

    private lateinit var btnUpDateEmail: Button
    private lateinit var btnCancel: Button
    private lateinit var tvHeading: TextView
    private lateinit var etUpdateEmail: EditText
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_email_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etUpdateEmail = view.findViewById(R.id.etUpdateEmail)
        tvHeading = view.findViewById(R.id.tvHeading)
        btnUpDateEmail = view.findViewById(R.id.btnUpdateEmail)
        btnCancel = view.findViewById(R.id.btnCancelUpdate)

        firebaseAuth = FirebaseAuth.getInstance()

        tvHeading.text = requireActivity().getString(R.string.update_your_email)

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnUpDateEmail.setOnClickListener {
            updateEmail()
            dismiss()
        }
    }

    private fun updateEmail() {
        val email = etUpdateEmail.text.toString().trim()
        // [START update_email]
        val user = firebaseAuth.currentUser

        if (email.isNotBlank()) {
            user!!.updateEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "User email address updated successfully.")
                    }
                }
        }
        // [END update_email]
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val TAG = "Dialog Fragment"
    }

}