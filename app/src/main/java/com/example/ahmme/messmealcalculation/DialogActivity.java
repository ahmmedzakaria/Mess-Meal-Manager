package com.example.ahmme.messmealcalculation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DialogActivity extends AppCompatActivity {
    MealInfo mealInfo;
    EditText totalBazarET;
    EditText totalExtraET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        totalBazarET=(EditText)findViewById(R.id.totalBazar);
        totalExtraET=(EditText)findViewById(R.id.totalExtra);
        mealInfo=new MealInfo();
    }


    public void calculate(View view) {
        mealInfo.setTotalBazar(Double.parseDouble(totalBazarET.getText().toString()));
        mealInfo.setTotalExtra(Double.parseDouble(totalExtraET.getText().toString()));
        Intent nexPageIntent=new Intent(this,MealResultActivity.class);
        nexPageIntent.putExtra("totalBazar",mealInfo.getTotalBazar());
        nexPageIntent.putExtra("totalExtra",mealInfo.getTotalExtra());
        startActivity(nexPageIntent);
        try{
            super.onDestroy();
        }catch (Exception e){

        }


    }
}
