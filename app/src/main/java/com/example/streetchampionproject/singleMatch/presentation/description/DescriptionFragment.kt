package com.example.streetchampionproject.singleMatch.presentation.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streetchampionproject.R
import kotlinx.android.synthetic.main.description_fragment.*

class DescriptionFragment : Fragment() {

    private var description: String? = null
    private var bundle: Bundle? = null

    companion object {
        fun newInstance(description: String): DescriptionFragment{
            val fragment = DescriptionFragment()
            val args = Bundle()
            args.putString("description", description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bundle = this.arguments
        description = bundle?.getString("description")
        return inflater.inflate(R.layout.description_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_description.text = description
    }

}
