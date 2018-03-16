package com.codegene.femicodes.roomlivedataviewmodel.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codegene.femicodes.roomlivedataviewmodel.R;
import com.codegene.femicodes.roomlivedataviewmodel.db.User;

import java.util.List;

/**
 * Created by femicodes on 3/16/2018.
 */

public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PeopleRecyclerViewAdapter.PeopleRecyclerViewHolder> {

    private List<User> peopleList;
    private View.OnLongClickListener longClickListener;

    public PeopleRecyclerViewAdapter(List<User> peopleList, View.OnLongClickListener longClickListener) {
        this.peopleList = peopleList;
        this.longClickListener = longClickListener;
    }

    @Override
    public PeopleRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PeopleRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final PeopleRecyclerViewHolder holder, int position) {
        User userModel = peopleList.get(position);
        holder.itemTextView.setText(userModel.getEmail());
        holder.nameTextView.setText(userModel.getPassword());
//        holder.itemTextView.setText(borrowModel.getItemName());
//        holder.nameTextView.setText(borrowModel.getPersonName());
//        holder.dateTextView.setText(borrowModel.getBorrowDate().toLocaleString().substring(0, 11));
        holder.itemView.setTag(userModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public void addItems(List<User> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    static class PeopleRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        PeopleRecyclerViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.itemTextView);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }
    }
}
