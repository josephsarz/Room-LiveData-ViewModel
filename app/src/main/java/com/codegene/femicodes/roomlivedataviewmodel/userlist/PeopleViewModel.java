package com.codegene.femicodes.roomlivedataviewmodel.userlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.codegene.femicodes.roomlivedataviewmodel.borrowlist.BorrowedListViewModel;
import com.codegene.femicodes.roomlivedataviewmodel.db.AppDatabase;
import com.codegene.femicodes.roomlivedataviewmodel.db.Borrow;
import com.codegene.femicodes.roomlivedataviewmodel.db.User;

import java.util.List;

/**
 * Created by femicodes on 3/16/2018.
 */

public class PeopleViewModel extends AndroidViewModel {

    private final LiveData<List<User>> PersonList;

    private AppDatabase appDatabase;

    public PeopleViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        PersonList = appDatabase.userModel().getAllUsers();
    }


    public LiveData<List<User>> getPersonList() {
        return PersonList;
    }

    public void deleteItem(User userModel) {
        new PeopleViewModel.deleteAsyncTask(appDatabase).execute(userModel);
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            db.userModel().deleteUser(params[0]);
            return null;
        }

    }


}
