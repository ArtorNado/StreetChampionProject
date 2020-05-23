package com.example.streetchampionproject.creating.createMatch.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.app.injector.Injector
import kotlinx.android.synthetic.main.create_command_match_fragment.*
import javax.inject.Inject

class CreateCommandMatchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: CreateMatchViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.create_command_match_fragment, container, false)


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("onAttach", "onAttach")
        Injector.plusCreateMatchFeatureComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("onViewCreated", "onViewCreated")
        viewModel?.determineUserStatus()
        initObservers()
        initClickListeners()
    }

    private fun initObservers() {
        viewModel?.role?.observe(viewLifecycleOwner, Observer {
            Log.e("STATUS", it.toString())
            if (it != "Admin") findNavController().popBackStack()
        })
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(CreateMatchViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initClickListeners() {
        btn_create.setOnClickListener {
            viewModel?.createCommandMatch(
                CreateCommandMatch(
                    et_date.text.toString(),
                    et_time.text.toString(),
                    0,
                    et_match_city.text.toString(),
                    et_description.text.toString()
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearCreateMatchFeatureComponent()
    }

}
