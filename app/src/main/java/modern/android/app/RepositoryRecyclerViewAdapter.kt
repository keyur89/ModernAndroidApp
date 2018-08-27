package modern.android.app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import modern.android.app.databinding.RepositoryDataItemBinding

/**
 * Created by Keyur on 27-08-18.
 */
class RepositoryRecyclerViewAdapter(private var items: ArrayList<Repository>,
                                    private var listener: OnItemClickListener)
    : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RepositoryDataItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)


    class ViewHolder(private var binding: RepositoryDataItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repository, listener: OnItemClickListener) {
            binding.repository = repo

            binding.root.setOnClickListener {
                listener.onItemClick(layoutPosition)
            }

            binding.executePendingBindings()
        }
    }

    fun replaceData(arrayList: ArrayList<Repository>) {
        items = arrayList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}