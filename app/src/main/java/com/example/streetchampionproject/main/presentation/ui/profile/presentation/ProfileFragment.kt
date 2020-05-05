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
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: ProfileViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusProfileFeatureComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        viewModel?.user?.observe(viewLifecycleOwner, Observer {
            tv_user_login.text = it.userId.toString()
            tv_user_firstName.text = it.userFirstName
            tv_user_secondName.text = it.userSecondName
            tv_user_gender.text = it.userGender
            tv_city_name.text = it.userCity
        })
    }
}
