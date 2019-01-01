package com.stylab.test.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stylab.test.R
import com.stylab.test.databinding.ActivityHomeBinding
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.animation.AnimationUtils
import com.stylab.test.util.animator.SpacesItemDecoration
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    @Inject
    lateinit var homeViewModel: HomeViewModel

    lateinit var listAdapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = homeViewModel

        setActionBar(binding.toolbar)

        if(savedInstanceState == null) {
            animateToolbar()
        }

        listAdapter = HomeListAdapter()
        binding.homeList.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.homeList.adapter = listAdapter
        binding.homeList.addItemDecoration(SpacesItemDecoration(16))

        subScribeUi()
    }


    private fun animateToolbar() {
        // toolbar doesn't provide its child for animation `-_-`
        val title = binding.toolbar.getChildAt(0)
        if (title != null && title is TextView) {
            title.alpha = 0f
            title.scaleY = 0.7f

            title.animate()
                .alpha(1f)
                .scaleY(1f)
                .setStartDelay(200)
                .setDuration(900).interpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR
        }
    }

    private fun subScribeUi() {
        homeViewModel.list.observe(this, Observer {
                listAdapter.submitList(it)
            }
        )
    }
}