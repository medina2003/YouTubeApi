package kg.junesqo.youtubeapi41.ui.playlist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kg.junesqo.youtubeapi41.base.BaseActivity
import kg.junesqo.youtubeapi41.databinding.ActivityPlaylistBinding
import kg.junesqo.youtubeapi41.model.Item
import kg.junesqo.youtubeapi41.ui.playlist_detail.PlaylistDetailActivity

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    companion object {
        const val KEY = "key"
    }

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(inflater)
    }

    override fun checkInternet() {
        checkConnection()
        binding.networkLayout.btnTryAgain.setOnClickListener{
            checkConnection()
        }

    }

    override fun initViewModel() {
        initVM()
    }

    override fun initView() {

    }

    override fun initListener() {
    }

    private fun initRecyclerView(playlistsList: List<Item>) {
        binding.recyclerMain.adapter = PlaylistAdapter(playlistsList, this::onItemClick)
    }

    private fun onItemClick(channelId: String){
        val intent = Intent(this, PlaylistDetailActivity::class.java)
                intent.putExtra(KEY,channelId)
                startActivity(intent)

    }

    fun initVM() {
        viewModel.getPlaylist().observe(this) {
            Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
            initRecyclerView(it.items)
        }
    }

    private fun checkConnection() {
        if (isOnline(this)){
            binding.recyclerMain.visibility = View.VISIBLE
            binding.networkLayout.root.visibility = View.GONE
            initVM()
        } else {
            binding.recyclerMain.visibility = View.GONE
            binding.networkLayout.root.visibility = View.VISIBLE
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }


}