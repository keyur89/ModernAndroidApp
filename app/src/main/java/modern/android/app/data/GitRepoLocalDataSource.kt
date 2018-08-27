package modern.android.app.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import modern.android.app.Repository
import java.util.concurrent.TimeUnit

/**
 * Created by Keyur on 27-08-18.
 */
class GitRepoLocalDataSource {
    fun getRepositories(): Observable<ArrayList<Repository>> {
        var arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First From Local", "Owner 1", 100, false))
        arrayList.add(Repository("Second From Local", "Owner 2", 30, true))
        arrayList.add(Repository("Third From Local", "Owner 3", 430, false))

        return Observable.just(arrayList).delay(2, TimeUnit.SECONDS)
    }

    fun saveRepositories(arrayList: ArrayList<Repository>): Completable {
        return Single.just(1).delay(1,TimeUnit.SECONDS).toCompletable()
    }
}
