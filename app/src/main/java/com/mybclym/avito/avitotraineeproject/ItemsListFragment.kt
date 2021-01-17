package com.mybclym.avito.avitotraineeproject

import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsListFragment : Fragment(), OnItemClickListener {

    private var itemRecycler: RecyclerView? = null
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var viewModel: ItemListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemListViewModel::class.java)
        findView(view)
        viewModel.itemList.observe(this.viewLifecycleOwner, this::updateList)
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateItemsList()
    }

    private fun findView(view: View) {
        itemRecycler = view.findViewById(R.id.item_list_rv)
        itemAdapter = ItemAdapter(this)
        itemRecycler?.adapter = itemAdapter
    }

    override fun onDestroyView() {
        itemRecycler = null
        super.onDestroyView()
    }

    private fun updateList(newItemList: List<Item>) {
        val callback = ItemsCallBack(itemAdapter.itemList, newItemList)
        val diff = DiffUtil.calculateDiff(callback)
        diff.dispatchUpdatesTo(itemAdapter)
        itemAdapter.setUpItemList(newItemList)
    }

    override fun deleteItem(position: Int) {
        viewModel.deleteItem(position)
    }
}
