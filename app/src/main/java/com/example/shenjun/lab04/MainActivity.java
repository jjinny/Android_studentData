package com.example.shenjun.lab04;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //
    Database myDb;
    EditText id,firstName, lastName, Marks;
    Button add,show,delete,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        myDb = new Database(this);

        id = (EditText)findViewById(R.id.id);
        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        Marks = (EditText)findViewById(R.id.Marks);
        add = (Button)findViewById(R.id.add);
        show = (Button)findViewById(R.id.show);
        delete = (Button)findViewById(R.id.delete);
        update = (Button)findViewById(R.id.update);
        Add();
        Show();
        Update();
        Delete();
    }

    //https://www.youtube.com/watch?v=T0ClYrJukPA
    //Android SQLite Database Tutorial 3 # Insert values to SQLite Database table using Android
    public  void Add(){
        add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    boolean addDate = myDb.addData(firstName.getText().toString(),lastName.getText().toString(),Marks.getText().toString());
                    if(addDate = true)
                        Toast.makeText(MainActivity.this,"Add successfully!",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Add failed!",Toast.LENGTH_LONG).show();
                }
        });
    }

    //https://www.youtube.com/watch?v=KUq5wf3Mh0c
    //Android SQLite Database Tutorial 4 # Show SQLite Database table Values using Android
    public void Show(){
        show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Cursor result = myDb.displayData();
                if(result.getCount() == 0){
                    //show
                    displayData("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("ID: " + result.getString(0) + "\n");
                    buffer.append("First Name : " + result.getString(1) + "\n");
                    buffer.append("Last Name: " + result.getString(2) + "\n");
                    buffer.append("Marks: " + result.getString(3) + "\n\n");
                }

                //
                displayData("Data", buffer.toString());
            }
        });
    }

    public void displayData(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    //https://www.youtube.com/watch?v=neaCUaHa2Ek
    // Android SQLite Database Tutorial 6 # Delete values in SQLite Database table using Android
    public void Delete(){
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Integer deleteRow = myDb.deleteDate(id.getText().toString());
                if(deleteRow > 0)
                    Toast.makeText(MainActivity.this,"Delete successfully!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Delete failed!",Toast.LENGTH_LONG).show();
            }
        });
    }

    //https://www.youtube.com/watch?v=PA4A9IesyCg
    //Android SQLite Database Tutorial 5 # Update values in SQLite Database table using Android
    public  void Update(){
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean isUpdate = myDb.updateData(id.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),Marks.getText().toString());
                if(isUpdate = true)
                    Toast.makeText(MainActivity.this,"Update successfully!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Update failed!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
