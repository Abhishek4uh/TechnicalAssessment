package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.adapter.BookItemsAdapter
import com.example.myapplication.callback.AddToFavorite
import com.example.myapplication.data.JsonDataProvider
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.model.ItemModel
import com.example.myapplication.roomdb.FavBookModel
import com.example.myapplication.roomdb.FavoriteBookViewModel
import com.google.gson.Gson


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(),AddToFavorite {

    private var mBinding: FragmentMainBinding? = null
    private lateinit var bookItemsAdapter: BookItemsAdapter
    private val viewModel:FavoriteBookViewModel by viewModels()
    private var switchToggle=-1


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding=FragmentMainBinding.inflate(layoutInflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SetonClickListeners
        initClickListeners()

        //Data Passing to Adapter
        val jsonData = JsonDataProvider.getJsonData()
        val gson = Gson()
        val animeItemList: List<ItemModel> = gson.fromJson(jsonData, Array<ItemModel>::class.java).toList()
        bookItemsAdapter= BookItemsAdapter(this)
        mBinding?.rvBooks?.adapter=bookItemsAdapter
        bookItemsAdapter.populateData(animeItemList)
    }

    private fun initClickListeners() {

        mBinding?.sortTitle?.setOnClickListener {
                Log.d(TAG,"Sort by Title")
                switchToggle=1
                mBinding?.idSwitch?.visibility=View.VISIBLE
                resetBg()
                mBinding?.sortTitle?.background = ContextCompat.getDrawable(requireContext(), R.drawable.filter_selected_bg)
                bookItemsAdapter.sortItemsByTitleAsc()
            }

        mBinding?.sortHits?.setOnClickListener {
                Log.d(TAG,"Sort by Hits")
                switchToggle=2
                mBinding?.idSwitch?.visibility=View.VISIBLE
                resetBg()
                mBinding?.sortHits?.background = ContextCompat.getDrawable(requireContext(), R.drawable.filter_selected_bg)
                bookItemsAdapter.sortByHitsAsc()
            }

        mBinding?.sortFavs?.setOnClickListener {
                Log.d(TAG,"Sort by Favs")
                switchToggle=-1
                mBinding?.idSwitch?.visibility=View.INVISIBLE
                resetBg()
                mBinding?.sortFavs?.background = ContextCompat.getDrawable(requireContext(), R.drawable.filter_selected_bg)
                //getting data from db
                gettingDataFromDb()


            }

        mBinding?.idSwitch?.setOnCheckedChangeListener {_,isChecked ->

                when(switchToggle){
                    1->{
                        if (isChecked){
                            bookItemsAdapter.sortItemsByTitleAsc()
                        }
                        else{
                            bookItemsAdapter.sortItemsByTitleDescending()
                        }
                    }
                    2->{
                        if (isChecked){
                            bookItemsAdapter.sortByHitsAsc()
                        }
                        else{
                            bookItemsAdapter.sortByHitsDescending()
                        }
                    }
                }
            }

    }

    private fun gettingDataFromDb() {
        viewModel.allBooks.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                //Db has some Data
            }
            else{
                //Db is Empty
            }
        })
    }

    private fun resetBg(){
        mBinding?.sortTitle?.background = ContextCompat.getDrawable(requireContext(),R.drawable.filter_unselected_bg)
        mBinding?.sortHits?.background  =  ContextCompat.getDrawable(requireContext(),R.drawable.filter_unselected_bg)
        mBinding?.sortFavs?.background  =  ContextCompat.getDrawable(requireContext(),R.drawable.filter_unselected_bg)
    }

    companion object {

        private const val TAG = "MainFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    override fun addedToFavorite(favBookModel: FavBookModel,isChecked: Boolean) {
        if (isChecked){
            //Inserting
            viewModel.addItem(favBookModel)
        }
        else{
            //Deleting
            viewModel.deleteItem(favBookModel.contentId.toString())
        }
    }
}