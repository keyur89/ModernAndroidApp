package modern.android.app

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import modern.android.app.data.GitRepoRepository
import plusAssign

/**
 * Created by Keyur on 27-08-18.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    var gitRepoRepository: GitRepoRepository = GitRepoRepository(getApplication())
    val isLoading = ObservableField(false)
    var repositories = MutableLiveData<ArrayList<Repository>>()
    private val compositeDisposable = CompositeDisposable()

    fun loadRepositories(){
        isLoading.set(true)
        compositeDisposable += gitRepoRepository
                .getRepositories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<ArrayList<Repository>>(){
            override fun onComplete() {
                isLoading.set(false)
            }

            override fun onNext(data: ArrayList<Repository>) {
                repositories.value = data
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}