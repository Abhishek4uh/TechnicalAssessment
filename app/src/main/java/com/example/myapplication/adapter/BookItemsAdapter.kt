package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.BooksItemBinding
import com.example.myapplication.model.ItemModel


class BookItemsAdapter:RecyclerView.Adapter<BookItemsAdapter.MyViewHolder>()  {

    private var cxt: Context?=null
    private var dataList: MutableList<ItemModel> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        cxt=parent.context
        return MyViewHolder(BooksItemBinding.inflate(LayoutInflater.from(cxt),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData= dataList[position]
        holder.mBinding.tvTitle.text = currentData.title
        holder.mBinding.tvHits.text = "Hits ${currentData.hits}"
        Glide.with(cxt!!).load(currentData.image).into(holder.mBinding.ivBookPoster)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    inner class MyViewHolder(val mBinding: BooksItemBinding) : RecyclerView.ViewHolder(mBinding.root)

    //getting Data
    fun populateData(results: List<ItemModel>) {
        dataList.clear()
        dataList.addAll(results)
        notifyDataSetChanged()
    }



    //Sort By Title
    fun sortItemsByTitleAsc() {
        dataList = dataList.sortedBy{ it.title }.toMutableList()
        notifyDataSetChanged()
    }

    fun sortItemsByTitleDescending() {
        dataList = dataList.sortedByDescending{ it.title }.toMutableList()
        notifyDataSetChanged()
    }

    //Sort By Hits
    fun  sortByHitsAsc(){
        dataList=dataList.sortedBy { it.hits }.toMutableList()
        notifyDataSetChanged()
    }

    fun sortByHitsDescending(){
        dataList=dataList.sortedByDescending { it.hits }.toMutableList()
        notifyDataSetChanged()
    }
}