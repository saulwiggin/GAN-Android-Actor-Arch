package com.saulwiggin.breakingbadactormodule.ui.actorlist

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulwiggin.breakingbadactormodule.R
import com.saulwiggin.breakingbadactormodule.databinding.FragmentActorListBinding
import com.saulwiggin.breakingbadactormodule.internal.di.LoadingState
import com.saulwiggin.breakingbadactormodule.model.ActorModel
import com.saulwiggin.breakingbadactormodule.ui.adapters.ActorListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorListFragment : Fragment(), ActorListAdapter.OnItemClickListener {
    private val TAG = "BreakingBad"

    private lateinit var binding: FragmentActorListBinding
    private var actorList = emptyList<ActorModel>()
    private lateinit var actorListAdapter: ActorListAdapter
    private val actorListViewModel:ActorListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_actor_list, container, false)

        Log.i(TAG, "onCreateView2: ")

        setupRecyclerView()
        setupRecyclerViewData()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                hideKeyboard()
                filter(s.toString())
            }
        })

        binding.btnClear.setOnClickListener {
            binding.etSearch.text.clear()
        }

        actorListViewModel.loadingState.observe(viewLifecycleOwner, Observer { loadingState ->
            when (loadingState.status) {
                LoadingState.Status.FAILED -> {
                    Toast.makeText(
                        context,
                        loadingState.msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                LoadingState.Status.RUNNING -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    binding.pbActorList.visibility = View.VISIBLE
                }

                LoadingState.Status.SUCCESS -> {
                    Toast.makeText(context, "Loaded", Toast.LENGTH_SHORT).show()
                    binding.pbActorList.visibility = View.GONE
                }
            }
        })


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_season_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.season1
            -> {
                Toast.makeText(context, "Selected1", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "onOptionsItemSelected: 1")
                actorListViewModel.retrieveActorsBySeason(1)
                return true
            }
            R.id.season2
            -> {
                Toast.makeText(context, "Selected2", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "onOptionsItemSelected: 2")
                actorListViewModel.retrieveActorsBySeason(2)
                return true
            }
            R.id.season3
            -> {
                Toast.makeText(context, "Selected3", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "onOptionsItemSelected: 3")
                actorListViewModel.retrieveActorsBySeason(3)
                return true
            }
            R.id.season4
            -> {
                Toast.makeText(context, "Selected4", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "onOptionsItemSelected: 4")
                actorListViewModel.retrieveActorsBySeason(4)
                return true
            }
            R.id.season5
            -> {
                Toast.makeText(context, "Selected5", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "onOptionsItemSelected: 5")
                actorListViewModel.retrieveActorsBySeason(5)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)

            }
        }
    }

    private fun filter(input: String) {
        val filteredActorList = mutableListOf<ActorModel>()

        actorList.forEach { actorModel ->
            if(actorModel.name.toLowerCase().contains(input.toLowerCase())) {
                filteredActorList.add(actorModel)
            }
        }
        actorListAdapter.filterList(filteredActorList)
    }

    private fun setupRecyclerViewData() {
        Log.i(TAG, "setupRecyclerViewData: ")
        actorListViewModel.actorList.observe(viewLifecycleOwner, Observer {
            actorList = it
            actorListAdapter.results = actorList
        })

        actorListViewModel.filteredActorList.observe(viewLifecycleOwner, Observer {
            actorList = it
            Log.i(TAG, "setupRecyclerViewData: ${actorList.size}")
            actorListAdapter.results = actorList
        })

    }

    private fun setupRecyclerView() {
        Log.i(TAG, "setupRecyclerView: ")
        binding.recyclerView.apply {
            actorListAdapter = ActorListAdapter(this@ActorListFragment)
            adapter = actorListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(requireContext(), "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = actorList[position]
        Navigation.findNavController(requireView()).navigate(ActorListFragmentDirections.actionActorListFragmentToActorDetailFragment(clickedItem))
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}