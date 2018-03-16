package com.codegene.femicodes.roomlivedataviewmodel.borrowlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.codegene.femicodes.roomlivedataviewmodel.db.AppDatabase;
import com.codegene.femicodes.roomlivedataviewmodel.db.Borrow;

import java.util.List;

/**
 * Created by femicodes on 3/16/2018.
 */

public class BorrowedListViewModel extends AndroidViewModel {

    private final LiveData<List<Borrow>> itemAndPersonList;

    private AppDatabase appDatabase;

    public BorrowedListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }


    public LiveData<List<Borrow>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(Borrow borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<Borrow, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Borrow... params) {
            db.itemAndPersonModel().deleteBorrow(params[0]);
            return null;
        }

    }

}