package com.codegene.femicodes.roomlivedataviewmodel.register;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.codegene.femicodes.roomlivedataviewmodel.db.AppDatabase;
import com.codegene.femicodes.roomlivedataviewmodel.db.User;

/**
 * Created by femicodes on 3/16/2018.
 */

public class RegisterViewModel extends AndroidViewModel{

    private AppDatabase appDatabase;

    public RegisterViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addUser(final User userModel) {
        new RegisterViewModel.addAsyncTask(appDatabase).execute(userModel);
    }

    private static class addAsyncTask extends AsyncTask<User, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            db.userModel().addUser(params[0]);
            return null;
        }

    }

}
