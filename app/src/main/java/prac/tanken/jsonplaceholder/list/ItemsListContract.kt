package prac.tanken.jsonplaceholder.list

import prac.tanken.jsonplaceholder.core.MvpPresenter
import prac.tanken.jsonplaceholder.core.MvpView
import prac.tanken.jsonplaceholder.business.Item

interface ItemsListContract {
    interface View : MvpView {
        fun showProgress()
        fun hideProgress()
        fun showItems(items: List<Item>)
        fun navigateToDetail(it: Item)
        fun showInternetError()
    }

    interface Presenter : MvpPresenter<View> {
        fun onViewReady()
        fun onItemClick(it: Item)
    }
}