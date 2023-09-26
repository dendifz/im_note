package id.pitjarus.imnote.ui.note

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.pitjarus.imnote.databinding.ItemNoteBinding
import id.pitjarus.imnote.model.NoteModel
import id.pitjarus.imnote.util.AutoUpdatableAdapter
import kotlin.properties.Delegates

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ListViewHolder>(), AutoUpdatableAdapter {
    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    var items: List<NoteModel> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    class ListViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = items[position]
        Log.d("NoteAdapter", "onBindViewHolder: $item")
        holder.binding.tvNote.text = item.notes
        holder.binding.tvDate.text = item.date.toString()
        holder.binding.btnDelete.setOnClickListener {
            onItemClickCallback.onItemClicked(item.id)
        }
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickCallback {
        fun onItemClicked(id: Long)
    }
}