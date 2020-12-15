package com.saulwiggin.breakingbadactormodule.ui.actordetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.saulwiggin.breakingbadactormodule.R
import com.saulwiggin.breakingbadactormodule.databinding.FragmentActorDetailBinding


class ActorDetailFragment : Fragment() {
    private lateinit var binding: FragmentActorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_actor_detail, container, false)
        var actor = ActorDetailFragmentArgs.fromBundle(requireArguments()).actorModel
        binding.results = actor
        return binding.root
    }
}