package id.pitjarus.imnote.ui.note

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.pitjarus.imnote.database.Note
import id.pitjarus.imnote.model.NoteModel

class NoteViewModel constructor(
    application: android.app.Application
) : AndroidViewModel(application) {

    private val notes : MutableLiveData<List<NoteModel>> = MutableLiveData(
        emptyList()
    )
    fun getNotes() : LiveData<List<NoteModel>> = notes

    fun getNotesDB () {
        notes.value = Note.getAll(getApplication())
        Log.d("NoteViewModel", "getNotesDB: ${notes.value?.map { it.notes }}")
    }

    fun deleteNote (id: Long) {
        Note.delete(getApplication(), id)
    }
}