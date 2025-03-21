package com.marcgdiez.jsonplaceholder.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcgdiez.jsonplaceholder.R
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.databinding.ActivityListItemsBinding
import com.marcgdiez.jsonplaceholder.detail.ItemDetailActivity
import com.marcgdiez.jsonplaceholder.extensions.internetErrorDialog
import com.marcgdiez.jsonplaceholder.extensions.show
import org.koin.android.ext.android.inject

class ItemsListActivity : AppCompatActivity(), ItemsListContract.View {

    private var _binding: ActivityListItemsBinding? = null
    val binding get() = requireNotNull(_binding)
    private val presenter: ItemsListContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.recyclerView) {
            adapter = ItemsAdapter { presenter.onItemClick(it) }
            layoutManager = LinearLayoutManager(this@ItemsListActivity)
        }

        presenter.attachView(this)
        presenter.onViewReady()
    }

    override fun showProgress() = binding.progressView.show()

    override fun hideProgress() = binding.progressView.hide()

    override fun showItems(items: List<Item>) {
        binding.recyclerView.show()
        val transactionsListAdapter = binding.recyclerView.adapter as? ItemsAdapter
        transactionsListAdapter?.setItems(items)
    }

    override fun navigateToDetail(it: Item) =
        startActivity(ItemDetailActivity.getCallingIntent(this, it))

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
        presenter.detachView()
    }

    override fun showInternetError() {
        internetErrorDialog()
    }
}
