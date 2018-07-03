package com.appbasic.task8sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id, name, age, occupation;
    Button insert, update, viewall;
    PersonDBHelper persondb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.id);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        occupation = (EditText) findViewById(R.id.occupation);

        persondb = new PersonDBHelper(this);


        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = persondb.saveNewPerson(name.getText().toString(), age.getText().toString(), occupation.getText().toString());

                if (result == true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = persondb.updatedata(id.getText().toString(), name.getText().toString(), age.getText().toString(), occupation.getText().toString());
                if (result == true)
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
            }
        });

        viewall = (Button) findViewById(R.id.viewall);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Viewall();
            }
        });


    }
    public void Viewall() {
        Cursor cur = persondb.getalldata();

        if (cur.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer sb = new StringBuffer();
        while (cur.moveToNext()) {
            sb.append("id:" + cur.getString(0) + "\n");
            sb.append("name: " + cur.getString(1) + "\n");
            sb.append("age: " + cur.getString(2) + "\n");
            sb.append("occupation: " + cur.getString(3) + "\n");

        }
        showMessage("data", sb.toString());
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
