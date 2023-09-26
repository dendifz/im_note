package id.pitjarus.imnote.ui.addnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.pitjarus.imnote.database.Note
import id.pitjarus.imnote.model.NoteModel

class AddNoteViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    fun addNote (note: NoteModel) {
        Note.add(getApplication(), note)
    }

}