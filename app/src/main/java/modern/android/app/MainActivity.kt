package modern.android.app

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import modern.android.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    var mainViewModel = MainViewModel()
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.viewModel = mainViewModel
        binding.executePendingBindings()

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        mainViewModel.repositories.observe(this,
                Observer<ArrayList<Repository>> {
                    it?.let {
                        repositoryRecyclerViewAdapter.replaceData((it))
                    }
                })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Clicked " /*+ mainViewModel.repositories[position].repositoryName*/, Toast.LENGTH_SHORT).show()
    }
}
