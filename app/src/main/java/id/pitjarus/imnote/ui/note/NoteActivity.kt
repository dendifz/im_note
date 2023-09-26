package id.pitjarus.imnote.ui.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.pitjarus.imnote.R
import id.pitjarus.imnote.ui.addnote.AddNoteActivity

class NoteActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val fabNote = findViewById<FloatingActionButton>(R.id.fabAddNote)
        val rvNote = findViewById<RecyclerView>(R.id.rvNote)

        fabNote.setOnClickListener {
            Intent(this, AddNoteActivity::class.java).also {
                startActivity(it)
            }
        }

        rvNote.layoutManager = LinearLayoutManager(this)

        val adapter = NoteAdapter()
        rvNote.adapter = adapter
        rvNote.setHasFixedSize(true)

        noteViewModel.getNotesDB()

        noteViewModel.getNotes().observe(this) {
            adapter.items = it
        }

        adapter.setOnItemClickCallback(object : NoteAdapter.OnItemClickCallback {
            override fun onItemClicked(id: Long) {
                noteViewModel.deleteNote (id)
                noteViewModel.getNotesDB()
            }
        })
    }

     fun refresh() {
        noteViewModel.getNotesDB()
    }
}