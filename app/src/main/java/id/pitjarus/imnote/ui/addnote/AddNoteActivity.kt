package id.pitjarus.imnote.ui.addnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import id.pitjarus.imnote.R
import id.pitjarus.imnote.model.NoteModel
import id.pitjarus.imnote.ui.note.NoteActivity
import java.util.Date

class AddNoteActivity : AppCompatActivity() {

    private lateinit var addNoteViewModel: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        addNoteViewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val edtNote = findViewById<EditText>(R.id.edtNote)

        btnSave.setOnClickListener {
            val note = NoteModel()
            note.notes = edtNote.text.toString()
            note.date = Date()
            addNoteViewModel.addNote (note)
            NoteActivity().refresh()
            finish()
        }


    }
}