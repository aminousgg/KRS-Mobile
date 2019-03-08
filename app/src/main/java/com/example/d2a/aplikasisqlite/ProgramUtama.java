package com.example.d2a.aplikasisqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class ProgramUtama extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_utama);
        myDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editMarks = (EditText)findViewById(R.id.editText_marks);
        editTextId = (EditText)findViewById(R.id.editTextId);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_view);
        btnUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        deleteData();
    }
    //fungsi hapus
    public void deleteData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Integer deletedRows =
                                                     myDb.deleteData(editTextId.getText().toString());
                                             if (deletedRows > 0)
                                                 Toast.makeText(ProgramUtama.this,"Data Deleted",Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(ProgramUtama.this,"Data Failed to Deleted!",Toast.LENGTH_LONG).show();
                                         }
                                     }
        );
    }
    //fungsi update
    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate =
                                myDb.updateData(editTextId.getText().toString(),
                                        editName.getText().toString(),
                                        editSurname.getText().toString(),
                                        editMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(ProgramUtama.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProgramUtama.this,"Data Failed to Update",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    //fungsi tambah
    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              boolean isInserted =
                                                      myDb.insertData(editTextId.getText().toString(),editName.getText().toString
                                                                      (),
                                                              editSurname.getText().toString(),
                                                              editMarks.getText().toString() );
                                              if(isInserted == true)
                                                  Toast.makeText(ProgramUtama.this,"Data Terimpan",Toast.LENGTH_LONG).show();
                                              else
                                                  Toast.makeText(ProgramUtama.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
                                          }
                                      }
        );
    }
    //fungsi menampilkan data
    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
// show message
                            showMessage("Error","Noting Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext() ) {
                            buffer.append("Kode :"+ res.getString(0)+"\n");
                            buffer.append("Nama Matakuliah :"+
                                    res.getString(1)+"\n");
                            buffer.append("SKS :"+ res.getString(2)+"\n");
                            buffer.append("Baru/Ulang :"+
                                    res.getString(3)+"\n\n");
                        }
// show all data
                        showMessage("Matakuliah :",buffer.toString());
                    }
                }
        );
    }
    //membuat alert dialog
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
