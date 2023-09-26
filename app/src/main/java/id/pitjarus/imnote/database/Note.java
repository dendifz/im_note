package id.pitjarus.imnote.database;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import androidx.fragment.app.FragmentActivity;

import java.util.List;

import id.pitjarus.imnote.App;
import id.pitjarus.imnote.model.DaoSession;
import id.pitjarus.imnote.model.NoteModel;
import id.pitjarus.imnote.model.NoteModelDao;

public class Note {
    public static DaoSession getDB(Application application){
        return ((App) application).getDaoSession();
    }
    public static List<NoteModel> getAll(Application application){
        DaoSession daoSession = getDB(application);
        return daoSession.getNoteModelDao()
                .queryBuilder()
                .orderDesc(NoteModelDao.Properties.Date)
                .list();
    }

    public static NoteModel add(Application application, NoteModel note){
        DaoSession daoSession = getDB(application);
        daoSession.getNoteModelDao().insert(note);
        return note;
    }
    public static NoteModel find(Application application, long id){
        DaoSession daoSession = getDB(application);
        return daoSession.getNoteModelDao().load(id);
    }
    public static Boolean delete(Application application, long id){
        DaoSession daoSession = getDB(application);
        NoteModelDao noteDao = daoSession.getNoteModelDao();
        noteDao.deleteByKey(id);
        return true;
    }
    public static long getCountTotalData(Application application){
        DaoSession daoSession = getDB(application);
        NoteModelDao noteDao = daoSession.getNoteModelDao();
        Cursor cursor = noteDao.getDatabase().rawQuery("SELECT COUNT(*) as total FROM " + NoteModelDao.TABLENAME, new String[]{});
        cursor.moveToFirst();
        return cursor.getLong(0);
    }
}