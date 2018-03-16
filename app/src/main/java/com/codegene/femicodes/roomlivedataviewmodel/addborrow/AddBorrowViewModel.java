package com.codegene.femicodes.roomlivedataviewmodel.addborrow;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.codegene.femicodes.roomlivedataviewmodel.db.AppDatabase;
import com.codegene.femicodes.roomlivedataviewmodel.db.Borrow;

/**
 * Created by femicodes on 3/16/2018.
 */

public class AddBorrowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBorrowViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addBorrow(final Borrow borrowModel) {
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<Borrow, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Borrow... params) {
            db.itemAndPersonModel().addBorrow(params[0]);
            return null;
        }

    }
}