package kg.junesqo.youtubeapi41.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.junesqo.youtubeapi41.BuildConfig.API_KEY
import kg.junesqo.youtubeapi41.`object`.Constant
import kg.junesqo.youtubeapi41.base.BaseViewModel
import kg.junesqo.youtubeapi41.model.Playlists
import kg.junesqo.youtubeapi41.remote.ApiService
import kg.junesqo.youtubeapi41.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaylistViewModel: BaseViewModel() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylist(): LiveData<Playlists> {

        return playlist() 
    }

    private fun playlist(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getPlaylists(Constant.part, Constant.channelId, API_KEY)
            .enqueue(object: Callback<Playlists>{
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {

                }

            })
        return data

    }
}