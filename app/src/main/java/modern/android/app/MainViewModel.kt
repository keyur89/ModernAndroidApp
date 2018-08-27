package modern.android.app

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import modern.android.app.data.GitRepoRepository
import modern.android.app.data.OnRepositoryReadyCallback

/**
 * Created by Keyur on 27-08-18.
 */
class MainViewModel : AndroidViewModel {
    constructor(application: Application) : super(application)
    var gitRepoRepository: GitRepoRepository = GitRepoRepository(getApplication())

    val isLoading = ObservableField(false)
    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun loadRepositories(){
        isLoading.set(true)
        gitRepoRepository.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }

}