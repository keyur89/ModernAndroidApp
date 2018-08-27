package modern.android.app.data

import android.content.Context
import io.reactivex.Observable
import me.mladenrakonjac.modernandroidapp.androidmanagers.NetManager
import modern.android.app.Repository

/**
 * Created by Keyur on 27-08-18.
 */
class GitRepoRepository(context: Context) {
    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()
    val netManager = NetManager(context)

    fun getRepositories(): Observable<ArrayList<Repository>> {
        netManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource.getRepositories().flatMap {
                    return@flatMap localDataSource.saveRepositories(it)
                            .toSingleDefault(it)
                            .toObservable()
                }
            }
        }
        return localDataSource.getRepositories()
    }
}
