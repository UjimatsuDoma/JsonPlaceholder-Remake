package prac.tanken.jsonplaceholder.core

interface MvpPresenter<V : MvpView> {

    val isViewAttached: Boolean

    fun attachView(mvpView: V)

    fun detachView()
}