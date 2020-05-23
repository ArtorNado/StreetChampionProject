package com.example.streetchampionproject.commandMatch.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.commandMatch.presentation.ui.EndMatchDialogFragment
import com.example.streetchampionproject.main.presentation.MainActivity
import kotlinx.android.synthetic.main.command_match_fragment.*
import javax.inject.Inject

class CommandMatchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: CommandMatchViewModel? = null

    private var matchId: Int? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.command_match_fragment, container, false)


    override fun onAttach(context: Context) {
        super.onAttach(context)
        bundle = this.arguments
        matchId = bundle?.getInt("matchId")
        matchId.let { Injector.plusCommandMatchFeatureComponent(it ?: 0).inject(this) }
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.updateCommandMatch()
        initObservers()
        initToolbar(view)
        initClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK)
                    viewModel?.endCommandMatch(
                        data?.extras?.get("first").toString().toInt() ?: 0,
                        data?.extras?.get("second").toString().toInt() ?: 0
                    )
            }
        }
    }

    private fun initObservers() {
        viewModel?.match?.observe(viewLifecycleOwner, Observer {
            tv_city.text = it.matchCity
            tv_date.text = it.date
            tv_description.text = it.description
        })
        viewModel?.userStatus?.observe(viewLifecycleOwner, Observer {
            when (it) {
                ARG_ADMIN_MATCH -> btn_end.visibility = View.VISIBLE
                ARG_ADMIN_TEAM -> btn_apply.visibility = View.VISIBLE
            }
        })
        viewModel?.events?.observe(viewLifecycleOwner, Observer {
            when (it) {
                EVENT_GO_BACK ->
                    findNavController().popBackStack()
            }
        })
    }

    private fun initClickListeners() {
        btn_apply.setOnClickListener {
            val dialog = EndMatchDialogFragment.newInstance()
            dialog.setTargetFragment(this, 1)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dialog.show((activity as MainActivity).supportFragmentManager, ARG_DIALOG_TAG)
            } else {
                dialog.show(fragmentManager!!, ARG_DIALOG_TAG)
            }
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(CommandMatchViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    companion object {
        private const val EVENT_GO_BACK = "Go back"
        private const val ARG_DIALOG_TAG = "Dialog"
        private const val ARG_ADMIN_MATCH = "Admin match"
        private const val ARG_ADMIN_TEAM = "Admin another team"
    }

}
