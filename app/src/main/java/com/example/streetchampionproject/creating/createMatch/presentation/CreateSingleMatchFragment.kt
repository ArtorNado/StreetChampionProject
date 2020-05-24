package com.example.streetchampionproject.creating.createMatch.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.app.injector.Injector
import kotlinx.android.synthetic.main.create_single_match_fragment.*
import javax.inject.Inject

class CreateSingleMatchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: CreateMatchViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.create_single_match_fragment, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusCreateMatchFeatureComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCLickListeners()
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

    private fun initCLickListeners(){
        btn_create.setOnClickListener {
            viewModel?.createSingleMatch(
                CreateSingleMatch(
                    et_date.text.toString(),
                    et_time.text.toString(),
                    0,
                    et_participants.text.toString().toInt(),
                    et_description.text.toString(),
                    et_match_city.text.toString()
                )
            )
        }
    }

}
