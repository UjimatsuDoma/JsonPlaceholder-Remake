package prac.tanken.jsonplaceholder.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.databinding.ItemAdapterBinding

class ItemsAdapter(val onItemClickListener: (Item) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Item> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(ItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position]) { item -> onItemClickListener(item) }
}

class ItemViewHolder(private val binding: ItemAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, listener: (Item) -> Unit) = with(binding) {
        title.text = item.title
        body.text = item.body.take(MAX_CHARACTERS)
        itemId.text = item.id
        root.setOnClickListener { listener(item) }
    }

    companion object {
        const val MAX_CHARACTERS = 80
    }
}
