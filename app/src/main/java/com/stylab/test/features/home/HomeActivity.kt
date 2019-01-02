package com.stylab.test.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stylab.test.R
import com.stylab.test.databinding.ActivityHomeBinding
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.snackbar.Snackbar
import com.stylab.test.util.action
import com.stylab.test.util.animator.SpacesItemDecoration
import com.stylab.test.util.snack
import javax.inject.Inject
import androidx.recyclerview.widget.SimpleItemAnimator




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

        listAdapter = HomeListAdapter(this)
        binding.homeList.layoutManager = GridLayoutManager(this, 2)
        binding.homeList.adapter = listAdapter
        binding.homeList.addItemDecoration(SpacesItemDecoration(14))

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
                listAdapter.updateList(it)
            }
        )
        homeViewModel.uiState.observe( this, Observer {
                val uiModel = it

                if (uiModel.showError != null && !uiModel.showError.consumed) {
                    uiModel.showError.consume()?.let { showLoadingFailed(it) }
                }
            }
        )
    }

    private fun showLoadingFailed(@StringRes errorString : Int) {
        binding.container.snack(errorString, length = Snackbar.LENGTH_INDEFINITE) {
            action(R.string.retry) {
                homeViewModel.onRefresh()
            }
        }
    }
}