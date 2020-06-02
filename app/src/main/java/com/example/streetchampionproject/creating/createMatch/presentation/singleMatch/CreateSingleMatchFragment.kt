package com.example.streetchampionproject.creating.createMatch.presentation.singleMatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.common.presentation.CONSTANTS
import kotlinx.android.synthetic.main.create_single_match_fragment.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

class CreateSingleMatchFragment : BaseFragment<CreateSingleMatchViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.create_single_match_fragment, container, false)

    override fun inject() {
        Injector.plusCreateMatchFeatureComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
    }

    override fun subscribe(viewModel: CreateSingleMatchViewModel) {
        observe(viewModel.goTo, Observer {
            if (it == CONSTANTS.ACTION.EVENT_GO_BACK) findNavController().popBackStack()
        })
        observe(viewModel.status, Observer {
            when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> progress_bar.visibility = View.GONE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> progress_bar.visibility = View.VISIBLE
                else -> progress_bar.visibility = View.GONE
            }
        })
    }

    override fun initClickListeners() {
        et_date.addTextChangedListener {
            try {
                val df = DateFormat.getDateInstance(DateFormat.SHORT)
                df.parse(et_date.text.toString())
                if (tf_date.isErrorEnabled) tf_date.error = null
            } catch (e: ParseException) {
                tf_date.error = "Неверный формат: Пример: 29.05.19"
            }
        }
        et_time.addTextChangedListener {
            try {
                val format = SimpleDateFormat("hh:mm")
                format.parse(et_time.text.toString())
                if (tf_time.isErrorEnabled) tf_time.error = null
            } catch (e: ParseException) {
                tf_time.error = "Неверный формат. Пример: 18:09"
            }
        }
        et_participants.addTextChangedListener {
            try {
                et_participants.text.toString().toInt()
                if (tf_participants.isErrorEnabled) tf_participants.error = null
            } catch (e: NumberFormatException) {
                tf_participants.error = "Введите число участников"
            }
        }
        btn_create.setOnClickListener {
            if (checkNullable()) snackBar("Все поля должны быть заполнены")
            else {
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
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun checkNullable(): Boolean {
        return (et_date.text.toString() == "" || et_time.text.toString() == ""
                || et_match_city.text.toString() == "" || et_description.text.toString() == ""
                || et_participants.text.toString() == "" || tf_time.error != null || tf_date.error != null)
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearCreateMatchFeatureComponent()
    }

}
