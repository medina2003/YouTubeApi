package kg.junesqo.youtubeapi41.ui.playlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.junesqo.youtubeapi41.databinding.ItemPlaylistBinding
import kg.junesqo.youtubeapi41.model.Item

class PlaylistAdapter(private val list:List<Item>, private val onItemClick: (itemsId: String) -> Unit?): RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    lateinit var binding: ItemPlaylistBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int  = list.size

    inner class ViewHolder(itemView: ItemPlaylistBinding):RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(items: Item) {
            Glide.with(binding.root)
                .load(items.snippet.thumbnails.default.url)
                .into(binding.imageEv)
            binding.playlistNameTv.text = items.snippet.title
            binding.playlistCountTv.text = items.contentDetails.itemCount.toString() + " video series"
            itemView.setOnClickListener{
                onItemClick(items.id)
            }
        }
    }

}

