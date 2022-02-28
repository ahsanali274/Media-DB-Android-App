package com.app.mediadbapp.ui.mediaList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.app.mediadbapp.R
import com.app.mediadbapp.baseClasses.BaseFragment
import com.app.mediadbapp.callback.ItemClickListener
import com.app.mediadbapp.data.models.Movie
import com.app.mediadbapp.data.viewModels.MediaListViewModel
import com.app.mediadbapp.databinding.FragmentMovieListBinding
import com.app.mediadbapp.extensions.getDimen
import com.app.mediadbapp.ui.widgets.OnSnapPositionChangeListener
import com.app.mediadbapp.ui.widgets.SnapOnScrollListener
import com.app.mediadbapp.utils.MiscUtils


class MediaListFragment : BaseFragment(), ItemClickListener<Movie> {

    companion object {
        fun newInstance() = MediaListFragment()
    }

    private lateinit var binding: FragmentMovieListBinding

    private val viewModel: MediaListViewModel by activityViewModels()

    private var currentPos = 0
    private val medialCategoryListAdapter by lazy { MediaCategoryListAdapterAdapter(this) }
    private var snapListener: SnapOnScrollListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater)
        setViews()
        setListeners()
        setObservers()
        return binding.root
    }

    override fun onDestroyView() {
        snapListener?.let { binding.rvMediaList.removeOnScrollListener(it) }
        super.onDestroyView()
    }

    private fun setViews() {
        val pageMargin = getDimen(R.dimen.media_card_vertical_margin)

        medialCategoryListAdapter.submitList(viewModel.getList(context!!))

        binding.rvMediaList.apply {
            adapter = medialCategoryListAdapter
            //scrollToPosition(playerViewModel.currentEpIndex.get())
        }
    }

    private fun setListeners() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvMediaList)
        snapListener = SnapOnScrollListener(snapHelper, object : OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                if (currentPos == position)
                    return
                currentPos = position

            }
        })
        snapListener?.let { binding.rvMediaList.addOnScrollListener(it) }

    }

    private fun setObservers() {

    }

    override fun onClickItem(item: Movie) {

    }

}