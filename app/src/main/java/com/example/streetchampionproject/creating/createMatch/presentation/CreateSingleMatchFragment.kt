package com.example.streetchampionproject.creating.createMatch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.create_single_match_fragment.*

class CreateSingleMatchFragment : BaseFragment<CreateMatchViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.create_single_match_fragment, container, false)

    override fun inject() {
        Injector.plusCreateMatchFeatureComponent(this).inject(this)
    }

    override fun subscribe(viewModel: CreateMatchViewModel) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun initClickListeners() {
        btn_create.setOnClickListener {
            viewModel.createSingleMatch(
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
