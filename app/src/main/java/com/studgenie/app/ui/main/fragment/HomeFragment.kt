package com.studgenie.app.ui.main.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.studgenie.app.R
import com.studgenie.app.ui.onboarding.activity.SignUpActivity
import kotlinx.android.synthetic.main.fragment_home.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.studgenie.app.data.local.tokenDatabase.AuthViewModel
import com.studgenie.app.data.local.userDetailsDatabase.UserViewModel
import com.studgenie.app.data.local.userStatusDatabase.UserStatusViewModel

class HomeFragment : Fragment() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var statusViewModel: UserStatusViewModel

    var isStatusEmpty = 1
    var isTokenEmpty = 1
    var isUserEmpty = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)


        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        statusViewModel = ViewModelProvider(requireActivity()).get(UserStatusViewModel::class.java)

        authViewModel.readAllData?.observe(viewLifecycleOwner, Observer { auth ->
            if (auth.isEmpty()) {
                isTokenEmpty = 1
                Log.d("CoroutineHomeFragAuth", "List is empty")
            } else {
                isTokenEmpty = 0
                Log.d("CoroutineHomeFragAuth", auth[0].id.toString() + auth[0].authToken)
                Log.d(
                    "CoroutineHomeFragAuthR",
                    auth[auth.size - 1].id.toString() + auth[auth.size - 1].authToken
                )
            }
        })
        userViewModel.readAllData?.observe(viewLifecycleOwner, Observer { user ->
            if (user.isEmpty()) {
                isUserEmpty = 1
                Log.d("CoroutineHomFragUserDat", "List is empty")
            } else {
                isUserEmpty = 0
                Log.d("CoroutineHomFragUserDat", user[0].id.toString() + user[0].number)
                Log.d(
                    "CoroutineHomFragUserDaR",
                    user[user.size - 1].id.toString() + user[user.size - 1].number
                )
            }
        })
        rootView.update_details_button.setOnClickListener {
            val intent = Intent(activity, SignUpActivity::class.java)
            startActivity(intent)
            (activity as Activity?)!!.overridePendingTransition(0, 0)
            activity?.finish()
        }
        rootView.button_logout.setOnClickListener {
//            if (isTokenEmpty==1){
//                Toast.makeText(requireContext(), "Login first", Toast.LENGTH_SHORT).show()
//            }else{
            authViewModel.deleteAuthToken()
            Log.d("CoroutineAuth", "Successfully deleted")
            userViewModel.deleteUserData()
            Log.d("CoroutineUserData", "Successfully deleted")
            statusViewModel.deleteStatusData()
            Log.d("CoroutineStatus", "Successfully deleted")
            Toast.makeText(requireContext(), "Successfully Logged out", Toast.LENGTH_SHORT).show()
//            }
        }
        userViewModel.readAllData?.observe(viewLifecycleOwner, Observer { user ->
            if (user.isEmpty()) {
                Log.d("HomeFragment", "User not created yet")
            } else {
                Log.d(
                    "CoroutineUserData",
                    user[0].id.toString() + user[0].number.toString() + user[0].firstName.toString() + user[0].lastName.toString() + user[0].dob.toString() + user[0].pictureUrl.toString() + user[0].accountStatus.toString() + user[0].maxDevices.toString() + user[0].userName.toString() + user[0].studentId.toString() + user[0].instituteId.toString() + user[0].email.toString()
                )
                Toast.makeText(
                    requireContext(),
                    user[0].id.toString() + user[0].number.toString() + user[0].firstName.toString() + user[0].lastName.toString() + user[0].dob.toString() + user[0].pictureUrl.toString() + user[0].accountStatus.toString() + user[0].maxDevices.toString() + user[0].userName.toString() + user[0].studentId.toString() + user[0].instituteId.toString() + user[0].email.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return rootView
    }
}