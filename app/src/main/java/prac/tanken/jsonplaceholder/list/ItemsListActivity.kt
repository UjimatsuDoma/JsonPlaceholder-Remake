package prac.tanken.jsonplaceholder.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.detail.ItemDetailActivity
import prac.tanken.jsonplaceholder.extensions.internetErrorDialog
import prac.tanken.jsonplaceholder.extensions.show
import org.koin.android.ext.android.inject
import prac.tanken.jsonplaceholder.databinding.ActivityListItemsBinding

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
