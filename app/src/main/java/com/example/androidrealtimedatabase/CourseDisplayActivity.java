package com.example.androidrealtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseDisplayActivity extends AppCompatActivity implements CourseDisplayAdapter.onClickEvent {
    private static final String TAG = "CourseDisplayActivity";
    private RecyclerView recyclerCourseID;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;
    private DatabaseReference databaseCourses;
    private List<Course> courses=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);
        String ID=getIntent().getStringExtra("studentID");
        databaseCourses= FirebaseDatabase.getInstance().getReference("courses").child(ID);
        recyclerCourseID=findViewById(R.id.recyclerCourseID);
        recyclerCourseID.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerCourseID.setLayoutManager(linearLayoutManager);

        databaseCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courses.clear();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    Course course=data.getValue(Course.class);
                    Log.d(TAG, "onDataChange Course ID: "+course.getCourseid());
                     Log.d(TAG, "onDataChange Course Name: "+course.getCoursename());
                    Log.d(TAG, "onDataChange: "+course.getCourserating());
                    courses.add(course);
                    Log.d(TAG, "onDataChange: "+courses.size());
                }


                adapter=new CourseDisplayAdapter( CourseDisplayActivity.this,courses);
                Log.d(TAG, "onDataChange Iam OutSide Init: "+courses.size());
                recyclerCourseID.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClickedItem(int selection) {

    }
}
