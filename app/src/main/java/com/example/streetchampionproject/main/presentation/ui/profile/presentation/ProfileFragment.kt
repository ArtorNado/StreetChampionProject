package com.example.streetchampionproject.main.presentation.ui.profile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class ProfileFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: ProfileViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusProfileFeatureComponent().inject(this)
        initViewModel()
        initUserData(activity?.intent?.extras?.getInt("id")?: 0)
    }

    private fun initViewModel(){
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ProfileViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    fun initUserData(id: Int){
        viewModel?.getUserData(id)
        viewModel?.userLogin?.observe(this, Observer {
            tv_user_login.text = it
        })
        viewModel?.userFirstName?.observe(this, Observer {
            tv_user_firstName.text = it
        })
        viewModel?.userSecondname?.observe(this, Observer {
            tv_user_secondName.text = it
        })
        viewModel?.userGender?.observe(this, Observer {
            tv_user_gender.text = it
        })
        viewModel?.userCity?.observe(this, Observer {
            tv_city_name.text = it
        })
    }
}