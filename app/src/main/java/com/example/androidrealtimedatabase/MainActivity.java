package com.example.androidrealtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Spinner selectValue;
    private Button AddStudentBTN,Viewbtn;
    private DatabaseReference databaseStduent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddStudentBTN=findViewById(R.id.AddStudentBTN);
        Viewbtn=findViewById(R.id.Viewbtn);
        editText=findViewById(R.id.editText);
        selectValue=findViewById(R.id.selectValue);
        databaseStduent= FirebaseDatabase.getInstance().getReference("students");
        AddStudentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName=editText.getText().toString().trim();
                String gender=selectValue.getSelectedItem().toString();
                
                if(!TextUtils.isEmpty(studentName))
                {
                    String id = databaseStduent.push().getKey();
                    Student student=new Student(id,studentName,gender);
                    databaseStduent.child(id).setValue(student);
                    Toast.makeText(MainActivity.this, "Student Added Sucessfully", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(MainActivity.this, "Student Name is Empty", Toast.LENGTH_SHORT).show();        
                }
            }
        });

        Viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

    }
}
