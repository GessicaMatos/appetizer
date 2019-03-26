package com.example.appetizer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       DatabaseHelper dbh = new DatabaseHelper(this);
/*
        Restaurant rest = new Restaurant( "Subway", "subway");
        dbh.addRestaurant(rest);
        dbh.addZip(1, "V3M2B8");
     Dish dish = new Dish(1, "Sandwich", "Chicken and cheese", 5.99);
        dbh.addDish(dish);
*/
/*
        TextView result = (TextView)findViewById(R.id.txtResult);


        Cursor c = dbh.viewUsers();
        StringBuilder str = new StringBuilder();
        if(c.getCount()>0){
            while (c.moveToNext()){
                str.append(c.getString(0));
                str.append(c.getString(1));
                str.append(c.getString(2));
                str.append(c.getString(3));
                str.append(c.getString(4));
                str.append(c.getString(5));
                str.append(c.getString(6));
                str.append(c.getString(7));
                str.append("\n");
            }
            result.setText(str);
        }
        else{
            result.setText("Nada");
        }
*/

    }
    public void ToLogin(View view){
        startActivity(new Intent(MainActivity.this, Login.class));
    }

    public void ToSignup(View view){
        startActivity(new Intent(MainActivity.this, Signup.class));
    }
}
