package com.saulwiggin.breakingbadactormodule.ui.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.saulwiggin.breakingbadactormodule.R
import com.saulwiggin.breakingbadactormodule.databinding.ActorListItemBinding
import com.saulwiggin.breakingbadactormodule.model.ActorModel

class ActorListAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {
    private val TAG = "BreakingBad"
    var results = emptyList<ActorModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        Log.i(TAG, "onCreateViewHolder: ")
        return ActorViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: ")
        holder.bind(results[position])
    }

    override fun getItemCount() = results.size

    fun filterList(filteredActorList: MutableList<ActorModel>) {
        results = filteredActorList
        notifyDataSetChanged()
    }

    class ActorViewHolder(var actorListItemBinding: ActorListItemBinding, var listener: OnItemClickListener):RecyclerView.ViewHolder(actorListItemBinding.root),
        View.OnClickListener{

        private val TAG = "BreakingBad"


        init {
            actorListItemBinding.card.setOnClickListener(this)
        }

        companion object {
            val LAYOUT = R.layout.actor_list_item

            fun from(parent: ViewGroup, listener: OnItemClickListener):ActorViewHolder {
                val binding = DataBindingUtil.inflate<ActorListItemBinding>(
                    LayoutInflater.from(parent.context),
                    LAYOUT, parent, false)
                return ActorViewHolder(binding, listener)
            }
        }

        fun bind(actorModel:ActorModel) {
            Log.i(TAG, "bind: ${actorModel.id}")
            actorListItemBinding.results = actorModel
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}