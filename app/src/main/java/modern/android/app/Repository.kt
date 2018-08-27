package modern.android.app

import android.databinding.BaseObservable
import android.databinding.Bindable

/**
 * Created by Keyur on 24-08-18.
 */
class Repository
(repositoryName: String?, var repositoryOwner: String?, var numberOfStars: Int?, var hasIssues: Boolean = false)
    : BaseObservable() {
    @get:Bindable
    var repositoryName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.repositoryName)
        }
}