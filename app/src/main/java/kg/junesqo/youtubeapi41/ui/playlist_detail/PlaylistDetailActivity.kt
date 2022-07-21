package kg.junesqo.youtubeapi41.ui.playlist_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kg.junesqo.youtubeapi41.R
import kg.junesqo.youtubeapi41.base.BaseActivity
import kg.junesqo.youtubeapi41.base.BaseViewModel
import kg.junesqo.youtubeapi41.databinding.ActivityPlaylistDetailBinding
import kg.junesqo.youtubeapi41.ui.playlist.PlaylistActivity
import kg.junesqo.youtubeapi41.ui.playlist.PlaylistViewModel

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding,PlaylistDetailViewModel>() {

    override val viewModel: PlaylistDetailViewModel by lazy {
        ViewModelProvider(this)[PlaylistDetailViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(inflater)
    }

    override fun initView() {
        val channelId = intent.getStringExtra(PlaylistActivity.KEY)
        Toast.makeText(this, channelId, Toast.LENGTH_SHORT).show()
    }

    override fun initListener() {

    }

    override fun initViewModel() {

    }

    override fun checkInternet() {

    }

}