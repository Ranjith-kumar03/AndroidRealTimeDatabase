package com.example.androidrealtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements DisplayAdapter.onClickEvent {
    private static final String TAG = "Main2Activity";
private RecyclerView recyclerID;
private LinearLayoutManager linearLayoutManager;
private RecyclerView.Adapter adapter;
private DatabaseReference databaseStudents;
private List<Student> students=new ArrayList<>();
private Student student=new Student();
private int curSel;
    @Override
    public void onClickedItem(int selection) {
        curSel=selection;
        student=students.get(curSel);
        Log.d(TAG, "on Click Method: "+curSel);
        // Toast.makeText(this, "Name of the Selcted Student is :"+student.getName(), Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Main2Activity.this,StudentCourseActivity.class);
        intent.putExtra("Student", student);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseStudents= FirebaseDatabase.getInstance().getReference("students");
        recyclerID=findViewById(R.id.recyclerID);
        recyclerID.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerID.setLayoutManager(linearLayoutManager);

        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
students.clear();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    Student student=data.getValue(Student.class);
                    // Log.d(TAG, "onDataChange: "+student.getName());
                    // Log.d(TAG, "onDataChange: "+student.getGender());
                    // Log.d(TAG, "onDataChange: "+student.getStudentID());
                    students.add(student);
                    Log.d(TAG, "onDataChange: "+students.size());
                }

                Log.d(TAG, "on Database Method: "+curSel);
                adapter=new DisplayAdapter( Main2Activity.this,students);
                Log.d(TAG, "onDataChange Iam OutSide Init: "+students.size());
                recyclerID.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }


}
