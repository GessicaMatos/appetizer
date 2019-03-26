package com.example.appetizer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Checkout extends AppCompatActivity {
    float subtotal= 0;
    double tax = 0;
    int orderId = 0;
    int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        DatabaseHelper dbh = new DatabaseHelper(this);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        orderId = sp.getInt("orderId", 0);
        userId = sp.getInt("userId", 0);

        Cursor c = dbh.GetSubTotal(orderId);

        if(c.getCount() > 0){
            while (c.moveToNext()){
                subtotal += c.getFloat(0);
            }
        }

        TextView tvCkoutSubTotal = findViewById(R.id.tvCkoutSubTotal);
        tvCkoutSubTotal.setText("Order subtotal: $"+ subtotal);
    }

    public void ConfirmOrder(View view){
        //pegar os valores da tela
        Switch delivery = findViewById(R.id.delivery);
        boolean isDelivery = delivery.isChecked();

        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setUserId(userId);
        order.setDate(new Date().toString());
        order.setStatus(true);
        order.setType(isDelivery);
        order.setTotal(subtotal);
        order.setDelivery(isDelivery ? 2.00 : 0.00);
        order.setTax();

        //RadioButton rbCC = (RadioButton) findViewById(R.id.rgPayment);
        RadioGroup rgPayment = findViewById(R.id.rgPayment);

        switch (rgPayment.getCheckedRadioButtonId()){
            case R.id.rbCC:
                order.setPayment("Credit Card");
                break;
            case R.id.rbDC:
                order.setPayment("Debit Card");
                break;
            case R.id.rbMoney:
                order.setPayment("Money");
                break;
        }

        //gravar
        DatabaseHelper dbh = new DatabaseHelper(this);
        boolean confirmed = dbh.updateOrder(order);

        if(confirmed){
            //apagar a ordem da shared pref
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("orderId");
            editor.commit();
            //mandar para a Restaurant list
            Intent gotoRestaurantList = new Intent(Checkout.this, RestaurantList.class);
            Toast.makeText(this, "Order confirmed", Toast.LENGTH_LONG).show();
            startActivity(gotoRestaurantList);
        }else{
            Toast.makeText(this, "Please check your order details", Toast.LENGTH_LONG).show();
        }

    }
}
