package com.example.franc.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.franc.tipcalc.R;
import com.example.franc.tipcalc.TipCalcApp;
import com.example.franc.tipcalc.fragments.TipHistoryListFragment;
import com.example.franc.tipcalc.fragments.TipHistoryListFragmentListener;
import com.example.franc.tipcalc.models.TipRecord;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputBill)
    EditText inputBill;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.inputPercentage)
    EditText inputPercentage;
    @BindView(R.id.btnIncrease)
    Button btnIncrease;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener)fragment;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnClear)
    public void handlerClickClear()
    {
        fragmentListener.clearList();
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit()
    {
        Log.e(getLocalClassName(), "click en submit");
        hideKeybord();
        String strInputTotal = inputBill.getText().toString().trim();
        if(!strInputTotal.isEmpty())
        {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord = new TipRecord();

            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimeStamp(new Date());


            String strTip = String.format(getString(R.string.global_message_tip),
                    tipRecord.getTip());
            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }


    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease()
    {
        hideKeybord();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease()
    {
        hideKeybord();
        handleTipChange(TIP_STEP_CHANGE);
    }

    private void handleTipChange(int change)
    {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if(tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }

    }

    private void hideKeybord()
    {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try
        {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS );
        }
        catch(NullPointerException npe)
        {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }

    public int getTipPercentage()
    {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        if(!strInputTipPercentage.isEmpty())
        {
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }
        else{
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = TipCalcApp.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }

}