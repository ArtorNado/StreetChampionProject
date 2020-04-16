package com.example.streetchampionproject.clubPage.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.streetchampionproject.R

class ClubPageFragment : Fragment() {

    companion object {
        fun newInstance() =
            ClubPageFragment()
    }

    private lateinit var viewModel: ClubPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.club_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ClubPageViewModel::class.java)
    }

}
