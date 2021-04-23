package com.deanmurray.fetchrewardsproject.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.deanmurray.fetchrewardsproject.R
import com.deanmurray.fetchrewardsproject.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class FetchRewardList : Fragment() {

    private val viewModel by viewModel<MainFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listController = ListController()
        fetch_rewards_list.setController(listController)

        viewModel.getListIdToList().observe(viewLifecycleOwner, Observer {
            listController.setData(it)
        })
    }
}