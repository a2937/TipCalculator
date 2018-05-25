package edu.wvup.acottri9.tipcalculator;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.wvup.acottri9.tipcalculator.model.TipCalculator;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity
{

    public final static int SPACING_VERTICAL = 50;
    public final static int SPACING_HORIZONTAL = 25;

    protected void onCreate( Bundle savedInstanceState )
    {
        Log.w( "MainActivity", "Inside onCreate" );
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_portrait );
        Configuration config = getResources( ).getConfiguration( );

        modifyLayout( config );
    }


    public void onConfigurationChanged( Configuration newConfig )
    {
        super.onConfigurationChanged( newConfig );
        modifyLayout( newConfig );
    }

    public void modifyLayout( Configuration newConfig )
    {
        EditText billEditText = (EditText) findViewById(R.id.bill_input);

        EditText numOfGuestsEditText = (EditText) findViewById(R.id.numOfGuests_input);
        CheckBox isPercentCheckbox = (CheckBox) findViewById(R.id.isPercentBox);
        EditText tipEditText = (EditText) findViewById(R.id.tipPercent_input);
        Button calculateButton = (Button)findViewById(R.id.button_calculate);

        TextView totalBillView = (TextView)findViewById(R.id.totalBillName);
        TextView totalBillInput = (TextView)findViewById(R.id.totalBillInput);

        TextView billPerGuestsView = (TextView)findViewById(R.id.billPerGuests);
        TextView billPerGuestsInput = (TextView)findViewById(R.id.billPerGuestsInput);

        TextView tipPerGuestsView = (TextView)findViewById(R.id.tipPerGuests);
        TextView tipPerGuestsInput = (TextView)findViewById(R.id.tipPerGuestsInput);

        ViewGroup.MarginLayoutParams billParams = (ViewGroup.MarginLayoutParams)billEditText.getLayoutParams();
        ViewGroup.MarginLayoutParams numOfGuestParams = (ViewGroup.MarginLayoutParams)numOfGuestsEditText.getLayoutParams();
        ViewGroup.MarginLayoutParams isPercentParams = (ViewGroup.MarginLayoutParams)isPercentCheckbox.getLayoutParams();
        ViewGroup.MarginLayoutParams tipEditParams = (ViewGroup.MarginLayoutParams)tipEditText.getLayoutParams();
        ViewGroup.MarginLayoutParams calculateParams = (ViewGroup.MarginLayoutParams)calculateButton.getLayoutParams();


        ViewGroup.MarginLayoutParams totalBillViewParams = (ViewGroup.MarginLayoutParams)totalBillView.getLayoutParams();
        ViewGroup.MarginLayoutParams totalBillInputParams = (ViewGroup.MarginLayoutParams)totalBillInput.getLayoutParams();

        ViewGroup.MarginLayoutParams tipPerGuestsViewParams = (ViewGroup.MarginLayoutParams)tipPerGuestsView.getLayoutParams();
        ViewGroup.MarginLayoutParams tipPerGuestsInputParams = (ViewGroup.MarginLayoutParams)tipPerGuestsInput.getLayoutParams();

        ViewGroup.MarginLayoutParams billPerGuestsViewParams = (ViewGroup.MarginLayoutParams)billPerGuestsView.getLayoutParams();
        ViewGroup.MarginLayoutParams billPerGuestsInputParams = (ViewGroup.MarginLayoutParams)billPerGuestsInput.getLayoutParams();

        if( newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE )
        {
            billParams.setMargins(0,SPACING_HORIZONTAL,0,0);
            numOfGuestParams.setMargins(0,SPACING_HORIZONTAL,0,0);
            isPercentParams.setMargins(0,SPACING_HORIZONTAL,0,0);
            tipEditParams.setMargins(0,SPACING_HORIZONTAL,0,0);
            calculateParams.setMargins(0,SPACING_HORIZONTAL,0,0);

            totalBillViewParams.setMargins(0,SPACING_HORIZONTAL,0,0);
            totalBillInputParams.setMargins(0,SPACING_HORIZONTAL,0,0);

            tipPerGuestsViewParams.setMargins(0,SPACING_HORIZONTAL,0,0);
            tipPerGuestsInputParams.setMargins(0,SPACING_HORIZONTAL,0,0);

            billPerGuestsInputParams.setMargins(0,SPACING_HORIZONTAL,0,0);
            billPerGuestsViewParams.setMargins(0,SPACING_HORIZONTAL,0,0);
        }
        else if( newConfig.orientation == Configuration.ORIENTATION_PORTRAIT )
        {
            billParams.setMargins(0,SPACING_VERTICAL,11,0);
            numOfGuestParams.setMargins(0,SPACING_VERTICAL,0,0);
            isPercentParams.setMargins(0,SPACING_VERTICAL,0,0);
            tipEditParams.setMargins(0,SPACING_VERTICAL,0,0);
            calculateParams.setMargins(0,24,0,0);

            totalBillViewParams.setMargins(0,SPACING_VERTICAL,0,0);
            totalBillViewParams.setMargins(0,SPACING_VERTICAL,0,0);

            tipPerGuestsViewParams.setMargins(0,SPACING_VERTICAL,0,0);
            tipPerGuestsInputParams.setMargins(0,SPACING_VERTICAL,0,0);

            billPerGuestsInputParams.setMargins(0,SPACING_VERTICAL,0,0);
            billPerGuestsViewParams.setMargins(0,SPACING_VERTICAL,0,0);
        }
    }

    /**
     * Calculates and sets the fields below
     * the calculate button.
     */
    public void calculate(View view)
    {
        TextView totalView = findViewById(R.id.totalBillInput);
        TextView tipPerGuestView = findViewById(R.id.tipPerGuestsInput);
        TextView billPerGuestView = findViewById(R.id.billPerGuestsInput);
        TextView totalTipView = findViewById(R.id.totalTipInput);

        CheckBox isPercentCheckbox = (CheckBox) findViewById(R.id.isPercentBox);
        EditText billEditText = (EditText) findViewById(R.id.bill_input);
        EditText numOfGuestsEditText = (EditText) findViewById(R.id.numOfGuests_input);
        EditText tipEditText = (EditText) findViewById(R.id.tipPercent_input);

        boolean isPercent = isPercentCheckbox.isChecked();
        double tip = processString(tipEditText.getText().toString());
        double bill = processString(billEditText.getText().toString());
        int guests = (int)processString(numOfGuestsEditText.getText().toString());

        TipCalculator newCalculator = new TipCalculator(guests,tip,bill,isPercent);

        totalView.setText(newCalculator.getFormattedTotalBill());
        tipPerGuestView.setText(newCalculator.getFormattedTipPerGuest());
        billPerGuestView.setText(newCalculator.getFormattedTotalCostPerGuest());
        totalTipView.setText(newCalculator.getFormattedTip());
    }

    /**
     * Parse the strings into ints and catching any number format exceptions that
     * could occur, although very unlikely
     * @param string
     * @return returnValue
     */
    private double processString(String string)
    {
        double returnValue = -1;
        try
        {
            returnValue = Double.parseDouble(string);
        }
        catch (NumberFormatException nfse)
        {
            //Should be unlikely to have an error.
            //However we should place something like this here
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "App Error please alert the developers:" + nfse.getMessage(), duration);
            toast.show();
        }
        return returnValue;
    }


}
