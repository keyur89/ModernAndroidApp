package modern.android.app.data

import android.os.Handler
import modern.android.app.Repository

/**
 * Created by Keyur on 27-08-18.
 */
class GitRepoRemoteDataSource {
    fun getRepositories(onRepoRemoteReadyCallback: OnRepoRemoteReadyCallback) {
        val arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First", "Owner 1", 5, false))
        arrayList.add(Repository("Second", "Owner 2", 4, true))
        arrayList.add(Repository("Third", "Owner 3", 3, false))
        arrayList.add(Repository("Fourth", "Owner 4", 2, true))
        arrayList.add(Repository("Fifth", "Owner 5", 1, false))
        Handler().postDelayed({ onRepoRemoteReadyCallback.onRemoteDataReady(arrayList) }, 1000)
    }

}

interface OnRepoRemoteReadyCallback {
    fun onRemoteDataReady(data: ArrayList<Repository>)
}