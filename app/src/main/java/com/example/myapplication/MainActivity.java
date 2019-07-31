package com.example.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //initialising the buttons and EditTexts
    EditText mItemName;
    EditText mPrice;
    EditText mId;
    Button mSubmit;
    Button mEdit;
    Button mDelete;
    Button mViewAll;

    //initialising database
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        // Capturing childviews from the activity file
        mId = findViewById(R.id.enter_id);
        mItemName = findViewById(R.id.enter_income_id);
        mPrice = findViewById(R.id.enter_expenditure_id);
        mSubmit = findViewById(R.id.submit_button_id);
        mEdit = findViewById(R.id.edit_button_id);
        mDelete = findViewById(R.id.delete_button_id);
        mViewAll = findViewById(R.id.view_button_id);


        addData();
        getData();
        updateData();
        deleteData();


    }

    private  void showMessage( String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void addData(){
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mItemName.getText().toString();
                String price = mPrice.getText().toString();

                boolean isInserted = mydb.insertIncomeDate(name,price);
                if (isInserted == true){
                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Data Couldn't be Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void getData(){
        mViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = mydb.getIncomeData();
                if (cursor.getCount() ==0){
                    //show Message
                    showMessage("Error","Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("ID = "+cursor.getString(0)+"\n");
                    buffer.append("ITEM NAME = "+cursor.getString(1)+"\n");
                    buffer.append("ITEM PRICE  = "+cursor.getString(2)+"\n");

                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void updateData(){
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mId.getText().toString();
                String name = mItemName.getText().toString();
                String price = mPrice.getText().toString();
                boolean isUpdate = mydb.updateIncomedata(id,name,price);
                if (isUpdate == true){
                    Toast.makeText(getApplicationContext(),"Data updated",Toast.LENGTH_SHORT).show();
                }else  {
                    Toast.makeText(getApplicationContext(),"Data Could Not be Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void deleteData(){
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mId.getText().toString();
                Integer deletedRows = mydb.deleteIncomeData(id);
                if (deletedRows>0){
                    Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Data could not be deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}