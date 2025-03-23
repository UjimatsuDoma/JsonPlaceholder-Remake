package prac.tanken.jsonplaceholder.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import prac.tanken.jsonplaceholder.business.Comment
import prac.tanken.jsonplaceholder.databinding.CommentAdapterBinding

class CommentsAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var comments: MutableList<Comment> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(CommentAdapterBinding.inflate(LayoutInflater.from(parent.context)))


    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = comments.size

    fun setItems(comments: MutableList<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    fun addComment(comment: Comment) {
        comments.add(comment)
        notifyItemInserted(comments.lastIndex)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(comments[position])
}

class ItemViewHolder(private val binding: CommentAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: Comment) = with(binding) {
        name.text = comment.name
        email.text = comment.email
        commentBody.text = comment.body
    }
}
