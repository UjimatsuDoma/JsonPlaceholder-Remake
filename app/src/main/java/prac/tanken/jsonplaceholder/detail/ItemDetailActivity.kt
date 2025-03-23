package prac.tanken.jsonplaceholder.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import prac.tanken.jsonplaceholder.business.Comment
import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.databinding.ActivityItemDetailsBinding
import prac.tanken.jsonplaceholder.extensions.internetErrorDialog
import org.koin.android.ext.android.inject


class ItemDetailActivity : AppCompatActivity(), ItemDetailContract.View {

    private var _binding: ActivityItemDetailsBinding? = null
    val binding get() = requireNotNull(_binding)
    private val presenter: ItemDetailContract.Presenter by inject()

    companion object {
        fun getCallingIntent(context: Context, item: Item): Intent {
            val intent = Intent(context, ItemDetailActivity::class.java)
            intent.putExtra(ARG_ITEM, item)
            return intent
        }

        const val ARG_ITEM = "ARG_ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initView()

        presenter.attachView(this)
        presenter.onViewReady(getItem())
    }

    private fun initView() {
        val commentsListAdapter = CommentsAdapter()
        with(binding.recyclerView) {
            adapter = commentsListAdapter
            layoutManager = LinearLayoutManager(this@ItemDetailActivity)
        }
        ViewCompat.setNestedScrollingEnabled(binding.recyclerView, false)

        binding.sendComment.setOnClickListener { presenter.onSendCommentClick(binding.commentText.text.toString()) }
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    private fun getItem(): Item = intent.getParcelableExtra(ARG_ITEM)!!

    override fun hideProgress() = binding.progressView.hide()

    override fun showProgress() = binding.progressView.show()

    override fun showComments(comments: List<Comment>) {
        val adapter = binding.recyclerView.adapter as? CommentsAdapter
        adapter?.setItems(comments.toMutableList())
    }

    override fun showItemData(id: String, itemTitle: String, bodyItem: String) {
        with(binding) {
            item.text = id
            titleItem.text = itemTitle
            body.text = bodyItem
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
        presenter.detachView()
    }

    override fun clearCommentArea() = binding.commentText.text.clear()

    override fun addComment(comment: Comment) {
        val adapter = binding.recyclerView.adapter as? CommentsAdapter
        adapter?.addComment(comment)
        binding.nestedScrollView.fullScroll(View.FOCUS_DOWN)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showInternetError() {
        internetErrorDialog()
    }
}