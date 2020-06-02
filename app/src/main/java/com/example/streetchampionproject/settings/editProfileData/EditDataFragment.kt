package com.example.streetchampionproject.settings.editProfileData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streetchampionproject.R

class EditDataFragment : Fragment() {

    companion object {
        fun newInstance() = EditDataFragment()
    }

    private lateinit var viewModel: EditDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.edit_data_fragment, container, false)


}
