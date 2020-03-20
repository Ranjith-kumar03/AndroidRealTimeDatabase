package com.example.androidrealtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentCourseActivity extends AppCompatActivity {
     TextView StudentName;
       EditText courseName;
        SeekBar seekbarRating;
        Button AddStudentCourseBTN;
        DatabaseReference databaseCourseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        StudentName=findViewById(R.id.StudentName);
        courseName=findViewById(R.id.courseName);
        seekbarRating=findViewById(R.id.seekbarRating);
        AddStudentCourseBTN=findViewById(R.id.AddStudentCourseBTN);

      Student student= (Student) getIntent().getSerializableExtra("Student");
        //Toast.makeText(this, "Student Information is : "+student, Toast.LENGTH_SHORT).show();
        String studentID=student.getStudentID().toString();
        databaseCourseReference= FirebaseDatabase.getInstance().getReference("courses").child(studentID);
        StudentName.setText(student.getName().toString());
        AddStudentCourseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String course_name=   courseName.getText().toString();
                int seekbar_Rating= seekbarRating.getProgress();
                if(!TextUtils.isEmpty(course_name))
                {
                    String id = databaseCourseReference.push().getKey();
                    Course course=new Course(id,course_name,seekbar_Rating);
                    databaseCourseReference.child(id).setValue(course);
                    Toast.makeText(StudentCourseActivity.this, "Course Added Sucessfully", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(StudentCourseActivity.this, "Course Name Cannot be Blank", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
