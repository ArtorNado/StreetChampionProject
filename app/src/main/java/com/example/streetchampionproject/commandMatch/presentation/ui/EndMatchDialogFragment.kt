package com.example.streetchampionproject.commandMatch.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.streetchampionproject.R
import kotlinx.android.synthetic.main.dialog_fragment.*

class EndMatchDialogFragment : DialogFragment(){

    companion object {
        fun newInstance() = EndMatchDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_accept.setOnClickListener {
            val intent = Intent().apply {
                putExtra("first", et_first_team.text)
                putExtra("second", et_second_team.text)
            }
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            dismiss()
        }
    }

}
