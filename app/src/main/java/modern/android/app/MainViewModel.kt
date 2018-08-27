package modern.android.app

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

/**
 * Created by Keyur on 27-08-18.
 */
class MainViewModel : ViewModel() {

    var repoModel: GitRepoRepository = GitRepoRepository()
    val isLoading = ObservableField(false)
    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun loadRepositories(){
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }

}