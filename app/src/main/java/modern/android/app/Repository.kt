package modern.android.app

import android.databinding.BaseObservable

/**
 * Created by Keyur on 24-08-18.
 */
class Repository
(var repositoryName: String?, var repositoryOwner: String?, var numberOfStars: Int?, var hasIssues: Boolean = false)
    : BaseObservable() {
    /*@get:Bindable
    var repositoryName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.repositoryName)
        }*/
}