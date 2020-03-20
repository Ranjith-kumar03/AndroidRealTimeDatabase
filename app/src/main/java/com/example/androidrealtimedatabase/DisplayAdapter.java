package com.example.androidrealtimedatabase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {
    private static final String TAG = "DisplayAdapter";
private List<Student> students;
private onClickEvent activity;
   public interface onClickEvent{
        void onClickedItem(int selection);
    }
    public DisplayAdapter( onClickEvent context,List<Student> pupils) {
        this.students = pupils;
        Log.d(TAG, "DisplayAdapter: " +pupils.size());
        activity = (onClickEvent) context;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView rollnumberTempId,nameTempID,genderID;
        private ImageView imageView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            rollnumberTempId=   itemView.findViewById(R.id.rollnumberTempId);
            nameTempID=   itemView.findViewById(R.id.nameTempID);
            genderID=   itemView.findViewById(R.id.genderID);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   activity.onClickedItem( students.indexOf((Student)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public DisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdapter.ViewHolder holder, int position) {
     holder.itemView.setTag(students.get(position));
        holder.itemView.setTag(students.get(position));
        Log.d(TAG, "onBindViewHolder: "+students.get(position).getStudentID().toString());
        Log.d(TAG, "onBindViewHolder: "+students.get(position).getName().toString());
        Log.d(TAG, "onBindViewHolder: "+students.get(position).getGender().toString());
        holder.rollnumberTempId.setText(students.get(position).getStudentID().toString());
        holder.nameTempID.setText(students.get(position).getName().toString());
        holder.genderID.setText(students.get(position).getGender().toString());

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+students.size());
        return students.size();
    }
}
