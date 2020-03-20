package com.example.androidrealtimedatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseDisplayAdapter extends RecyclerView.Adapter<CourseDisplayAdapter.ViewHolder> {
    private static final String TAG = "DisplayAdapter";
private List<Course> courses;
private onClickEvent activity;
   public interface onClickEvent{
        void onClickedItem(int selection);
    }
    public CourseDisplayAdapter(onClickEvent context, List<Course> courses) {
        this.courses = courses;

        activity = (onClickEvent) context;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView rollnumberTempId,nameCourseTempID,CourseRanking;
        private ImageView imageView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            rollnumberTempId=   itemView.findViewById(R.id.rollnumberTempId);
            nameCourseTempID=   itemView.findViewById(R.id.nameCourseTempID);
            CourseRanking=   itemView.findViewById(R.id.CourseRanking);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   activity.onClickedItem( courses.indexOf((Course)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public CourseDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.courserecyclerlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDisplayAdapter.ViewHolder holder, int position) {
     holder.itemView.setTag(courses.get(position));
        //        Log.d(TAG, "onBindViewHolder: "+courses.get(position).getStudentID().toString());
//        Log.d(TAG, "onBindViewHolder: "+students.get(position).getName().toString());
//        Log.d(TAG, "onBindViewHolder: "+students.get(position).getGender().toString());
        holder.rollnumberTempId.setText(courses.get(position).getCourseid().toString());
        holder.nameCourseTempID.setText(courses.get(position).getCoursename().toString());
        holder.CourseRanking.setText(String.valueOf(courses.get(position).getCourserating()));

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+courses.size());
        return courses.size();
    }
}
