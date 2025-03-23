package prac.tanken.jsonplaceholder.detail

import prac.tanken.jsonplaceholder.business.Comment
import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.core.MvpPresenter
import prac.tanken.jsonplaceholder.core.MvpView

interface ItemDetailContract {

    interface View : MvpView {
        fun hideProgress()
        fun showProgress()
        fun showComments(comments: List<Comment>)
        fun showItemData(id: String, itemTitle: String, bodyItem: String)
        fun clearCommentArea()
        fun addComment(comment: Comment)
        fun showInternetError()

    }

    interface Presenter : MvpPresenter<View> {
        fun onViewReady(item: Item)
        fun onSendCommentClick(text: String)
    }
}