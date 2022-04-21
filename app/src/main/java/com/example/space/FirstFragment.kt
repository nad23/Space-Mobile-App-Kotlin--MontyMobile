package com.example.space

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.space.adapter.RecyclerViewAdapter
//import com.example.space.models.RecyclerList
import com.example.space.viewmodel.MainActivityViewModel




class FirstFragment : Fragment()  {
    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var recyclerAdapter :RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_first, container, false)
       // initRecyclerView(view)
        initViewModel(view)
        initViewModel()
        return view
    }


    private fun initViewModel(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        val decoration =DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter= RecyclerViewAdapter()
        recyclerView.adapter=recyclerAdapter
    }


    private fun initViewModel(){
        val viewModel =ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner,{
            if(it !=null){
                recyclerAdapter.setUpdatedData(it)
            }else{
                Toast.makeText(activity,"Error in getting data",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

}