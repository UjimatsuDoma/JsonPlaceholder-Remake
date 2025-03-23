package prac.tanken.jsonplaceholder.list

import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.core.BasePresenter
import prac.tanken.jsonplaceholder.core.Failure
import prac.tanken.jsonplaceholder.list.usecase.GetItemsListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemsListPresenter(
    private val getItemsListUseCase: GetItemsListUseCase,
    private val dispatcher: CoroutineDispatcher
) : BasePresenter<ItemsListContract.View>(),
    ItemsListContract.Presenter {

    override fun onViewReady() {
        view?.showProgress()

        GlobalScope.launch(context = dispatcher) {
            val comments = getItemsListUseCase.execute()
            comments.either(::handleError, ::handleSuccess)
            view?.hideProgress()
        }
    }

    private fun handleError(failure: Failure) {
        view?.showInternetError()
    }

    private fun handleSuccess(items: List<Item>) {
        view?.showItems(items)
    }

    override fun onItemClick(it: Item) {
        view?.navigateToDetail(it)
    }
}