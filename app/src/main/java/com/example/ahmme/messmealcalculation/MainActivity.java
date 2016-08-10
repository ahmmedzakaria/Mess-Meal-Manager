package com.example.ahmme.messmealcalculation;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText nameET;
    private EditText depositET;
    private EditText mealET;
    MealInfo mealInfo;
    MealAdapter adapter;
    ArrayList<MealInfo> messMealArrayList;
    //    ArrayList<MealInfo> messMillArrayList;
    MealInfoManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        nameET = (EditText) findViewById(R.id.name);
        depositET = (EditText) findViewById(R.id.deposit);
        mealET = (EditText) findViewById(R.id.meal);
        mealInfo=new MealInfo();
        messMealArrayList = new ArrayList<MealInfo>();
        manager=new MealInfoManager(this);

        try{
            messMealArrayList=manager.getAllMealInfo();
            adapter = new MealAdapter(MainActivity.this, messMealArrayList);
            listView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Please Insert Data", Toast.LENGTH_SHORT).show();
        }
    }

  /*  public void saveTotalbazar(View view) {
        mealInfo.setTotalBazar(Double.valueOf(totalbazarET.getText().toString()));
        Toast.makeText(MainActivity.this,String.valueOf(mealInfo.getTotalBazar()), Toast.LENGTH_SHORT).show();
    }*/

    public void addToList(View view) {
        mealInfo.setName(nameET.getText().toString());
        mealInfo.setDeposit(Double.valueOf(depositET.getText().toString()));
        mealInfo.setMeal(Double.valueOf(mealET.getText().toString()));

        if (mealInfo.getName().length()>0 && mealInfo.getDeposit()>=0 && mealInfo.getMeal()>=0) {
            mealInfo = new MealInfo(mealInfo.getName(), mealInfo.getDeposit(), mealInfo.getMeal());
            manager.addMealInfo(mealInfo);
            messMealArrayList=manager.getAllMealInfo();
            adapter = new MealAdapter(MainActivity.this, messMealArrayList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
            clearEditTExt();

        } else {
            Toast toast = Toast.makeText(MainActivity.this, "Please Insert Data", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void calculate(View view) {
        /*Intent nexPageIntent=new Intent(this,MealResultActivity.class);
        nexPageIntent.putExtra("totalBazar",mealInfo.getTotalBazar());
        startActivity(nexPageIntent);*/


        Intent intent =new Intent(MainActivity.this,DialogActivity.class);
        startActivity(intent);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteAll:
                try{manager.deleteAllContact();
                    messMealArrayList=manager.getAllMealInfo();
                    adapter = new MealAdapter(MainActivity.this, messMealArrayList);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "All Data Deleted", Toast.LENGTH_SHORT).show();
                    listView.setAdapter(adapter);
                }
                return true;
            case R.id.changePassword:
                Toast.makeText(MainActivity.this, "password", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit:
                finish();
                System.exit(0);
                Toast.makeText(MainActivity.this, "exit", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearAll(View view) {
        try{manager.deleteAllContact();
            messMealArrayList=manager.getAllMealInfo();
            adapter = new MealAdapter(MainActivity.this, messMealArrayList);

        }catch (Exception e){
            Toast.makeText(MainActivity.this, "All Data Deleted", Toast.LENGTH_SHORT).show();
            listView.setAdapter(adapter);
        }
    }


    public void clearEditTExt(){
        nameET.setText("");
        depositET.setText("");
        mealET.setText("");
    }

}
