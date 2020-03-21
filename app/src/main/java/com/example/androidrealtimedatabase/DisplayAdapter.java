package com.example.androidrealtimedatabase;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.zip.Inflater;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder>{
   
    int currentSelection;
    private static final String TAG = "DisplayAdapter";
private List<Student> students;
private onClickEvent activity;
private Context context;



    public interface onClickEvent{
        void onClickedItem(int selection);
    }
    public DisplayAdapter( Context context,List<Student> pupils) {
        this.students = pupils;
        Log.d(TAG, "DisplayAdapter: " +pupils.size());
        activity = (onClickEvent) context;
        this.context=context;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
    private TextView rollnumberTempId,nameTempID,genderID;
        private ImageView imageView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);
            rollnumberTempId=   itemView.findViewById(R.id.rollnumberTempId);
            nameTempID=   itemView.findViewById(R.id.nameTempID);
            genderID=   itemView.findViewById(R.id.genderID);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   activity.onClickedItem( students.indexOf((Student)v.getTag()));
                    //currentSelection=students.indexOf((Student)v.getTag());
                    //Toast.makeText(context, "Iam Selected "+ currentSelection, Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public boolean onLongClick(View v) {
            currentSelection=  students.indexOf((Student)v.getTag());
            Student stud=students.get(currentSelection);
           // Toast.makeText(context, "Iam Selected "+ currentSelection, Toast.LENGTH_SHORT).show();
            updateStudentDialog(stud.getStudentID(),stud.getName(),stud.getGender());
            return true;
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

    public void updateStudentDialog(final String studentid, String StudentName,String gender)
    {
            int x = 0;
       
        if(gender.equals("Male"))
        {
            x=0;
        }else if(gender.equals("FeMale"))
        {
            x=1;
        }else if(gender.equals("SheMale"))
        {
            x=2;
        }else if(gender.equals("HeFeMale"))
        {
            x=3;
        }
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        View dialogView=inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText newName =dialogView.findViewById(R.id.newName);
        Button updateBTN=dialogView.findViewById(R.id.updateBTN);
        final Spinner updateGender=dialogView.findViewById(R.id.updateGender);
        updateGender.setSelection(x);
        dialogBuilder.setTitle("Updating Student : "+StudentName);
        final AlertDialog alertDialog=dialogBuilder.create();
        alertDialog.show();

        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dataUpdateRefrence= FirebaseDatabase.getInstance().getReference("students").child(studentid);

                if(!TextUtils.isEmpty(newName.getText().toString()))
                {
                    Student student_update=new Student(studentid,newName.getText().toString().trim(),updateGender.getSelectedItem().toString());
                    dataUpdateRefrence.setValue(student_update);
                    Toast.makeText(context, "Data Updated Sucessfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else
                {
                    Toast.makeText(context, "Updation Not sucessfull", Toast.LENGTH_SHORT).show();
                }

            }
        });
       
        
    }
}
