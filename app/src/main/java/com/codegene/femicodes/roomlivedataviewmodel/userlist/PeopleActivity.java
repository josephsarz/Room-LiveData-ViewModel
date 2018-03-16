package com.codegene.femicodes.roomlivedataviewmodel.userlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codegene.femicodes.roomlivedataviewmodel.R;
import com.codegene.femicodes.roomlivedataviewmodel.db.User;
import com.codegene.femicodes.roomlivedataviewmodel.register.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

public class PeopleActivity extends AppCompatActivity implements View.OnLongClickListener {

    PeopleViewModel mPeopleViewModel;
    RecyclerView mPeopleRecyclerView;
    PeopleRecyclerViewAdapter mPeopleRecyclerViewAdapter;

    public static Intent getStartedIntent(Context context){

        return new Intent(context, PeopleActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        FloatingActionButton fab =  findViewById(R.id.people_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = RegisterActivity.getStartedIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mPeopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

        mPeopleRecyclerView =  findViewById(R.id.people_recyclerView);
        mPeopleRecyclerViewAdapter = new PeopleRecyclerViewAdapter(new ArrayList<User>(), this);
        mPeopleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPeopleRecyclerView.setAdapter(mPeopleRecyclerViewAdapter);

        mPeopleViewModel.getPersonList().observe(PeopleActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> people) {
                mPeopleRecyclerViewAdapter.addItems(people);
            }
        });


    }

    @Override
    public boolean onLongClick(View view) {
        User userModel = (User) view.getTag();
        mPeopleViewModel.deleteItem(userModel);
        return true;
    }
}
