package com.example.franc.tipcalc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.Bind;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if( item.getItemId() == R.id.action_about)
        {
            about();
        }
        return  super.onOptionsItemSelected(item);
    }

    private void about() {
        TipCalcApp app = (TipCalcApp)getApplication();
        String strUrl = TipCalcApp.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }


}
