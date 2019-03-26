package com.example.appetizer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText etName = (EditText)findViewById(R.id.etName);
        EditText etPhone = (EditText)findViewById(R.id.etPhone);
        EditText etAddress = (EditText)findViewById(R.id.etAddress);
        EditText etZIP = (EditText)findViewById(R.id.etZIP);
        EditText etEmail = (EditText)findViewById(R.id.etEmail);
        EditText etPassword = (EditText)findViewById(R.id.etPassword);
        Button btnSignup = findViewById(R.id.btSignup);

        Intent getAccount = new Intent();
        getAccount = getIntent();
        if(getAccount != null){
            isEdit = true;
            etName.setText(getAccount.getStringExtra("name"));
            etEmail.setText(getAccount.getStringExtra("email"));
            etAddress.setText(getAccount.getStringExtra("address"));
            etPhone.setText(getAccount.getStringExtra("phone"));
            etZIP.setText(getAccount.getStringExtra("zip"));
            btnSignup.setText("Update Information");
        }

    }

    public void Signup(View view){
        EditText etName = (EditText)findViewById(R.id.etName);
        EditText etPhone = (EditText)findViewById(R.id.etPhone);
        EditText etAddress = (EditText)findViewById(R.id.etAddress);
        EditText etZIP = (EditText)findViewById(R.id.etZIP);
        EditText etEmail = (EditText)findViewById(R.id.etEmail);
        EditText etPassword = (EditText)findViewById(R.id.etPassword);

        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String names[] = etName.getText().toString().split(" ");
        User newUser = new User(etEmail.getText().toString(),
                names[0], names[1],
                etPhone.getText().toString(),
                etAddress.getText().toString(),
                etZIP.getText().toString(),
                etPassword.getText().toString());
        DatabaseHelper dbh = new DatabaseHelper(this);

        if(isEdit){
            newUser.setUserId(sp.getInt("userId", 0));
            dbh.updateUser(newUser);
        }else {
        int id = (int)dbh.addUser(newUser);
        if(id == -1){
            Toast.makeText(this, "Please check information", Toast.LENGTH_LONG).show(); ;
        }else{
            Toast.makeText(this, "Information saved.", Toast.LENGTH_LONG).show(); ;
            //start next activity
            Intent options = new Intent(Signup.this, Options.class);

            //storage id in the shared preferences
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("userId", id);
            editor.commit();
            startActivity(options);
        }
    }
    }

}
