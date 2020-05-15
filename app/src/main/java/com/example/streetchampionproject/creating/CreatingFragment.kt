package com.example.streetchampionproject.creating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import kotlinx.android.synthetic.main.creating_fragment.*

class CreatingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.creating_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners(){
        val navController = findNavController()
        btn_create_team.setOnClickListener {
            navController.navigate(R.id.action_creatingFragment_to_createTeamFragment)
        }
    }

}
