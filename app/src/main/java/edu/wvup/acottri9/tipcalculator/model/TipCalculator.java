package edu.wvup.acottri9.tipcalculator.model;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * A class designed to calculate the tip
 * for a meal using a guest amount and a tip percent
 * as well a collection of food items.
 * The program believes the tax amount to be 3%.
 * Created by aaron on 2/14/2018.
 */
public class TipCalculator
{
    private NumberFormat moneys = NumberFormat.getCurrencyInstance();

    private NumberFormat percentages = NumberFormat.getPercentInstance();

    private int guestAmount = 2;

    private double tipPercent = 0;

    private double tipAmount = 0;


    private double billTotal;

    /**
     * Instantiates a new Tip calculator
     * without setting a guest amount or tip.
     */
    public TipCalculator()
    {
        setPercentageFormat();
        setCurrencyFormat();
    }

    /**
     * Instantiates a new Tip calculator and
     * assigns a guest amount and a tip. The tip
     * is either an amount or percentage.
     *
     * @param guestAmount  the guest amount
     * @param tip          : the tip either as a percent or amount
     * @param billTotal    : the combined prices of the meals
     * @param isPercentage : is this a percent value or amount
     */
    public TipCalculator(int guestAmount, double tip , double billTotal, boolean isPercentage)
    {
        setPercentageFormat();
        setCurrencyFormat();
        setGuestAmount(guestAmount);
        setBillTotal(billTotal);
        if(isPercentage)
        {
            setTipPercent(tip);
        }
        else
        {
            setTipAmount(tip);
        }
    }

    private void setPercentageFormat()
    {
        percentages.setMinimumFractionDigits(1);
        percentages.setMaximumFractionDigits(1);
        percentages.setMinimumIntegerDigits(1);
        percentages.setMaximumIntegerDigits(1);
    }

    private void setCurrencyFormat()
    {
        moneys.setMaximumFractionDigits(2);
    }

    /**
     * Gets the formatted tip percent.
     *
     * @return the formatted tip percent
     */
    public String getFormattedTipPercent()
    {
        return percentages.format(getTipPercent());
    }

    /**
     * Gets formatted meal total.
     *
     * @return the formatted meal total
     */
    public String getFormattedMealTotal()
    {
        return moneys.format(billTotal);
    }

    /**
     * Gets formatted amount of guests.
     *
     * @return the formatted guest amount
     */
    public String getFormattedGuestAmount()
    {
        return guestAmount + " guests";
    }

    /**
     * Gets formatted tip.
     *
     * @return the formatted tip
     */
    public String getFormattedTip()
    {
        return moneys.format(calculateTip());
    }

    /**
     * Gets formatted total bill.
     *
     * @return the formatted total bill
     */
    public String getFormattedTotalBill()
    {
        return moneys.format(calculateTotalBill());
    }


    /**
     * Gets tip per guest.
     *
     * @return the tip per guest
     */
    public String getFormattedTipPerGuest()
    {
        return moneys.format(calculateTipPerGuest());
    }

    /**
     * Gets total cost per guest.
     *
     * @return the total cost per guest
     */
    public String getFormattedTotalCostPerGuest()
    {
        return moneys.format(calculateTotalCostPerGuest());
    }


    /**
     * Gets guest amount.
     *
     * @return the guest amount
     */
    public int getGuestAmount()
    {
        return guestAmount;
    }

    /**
     * Sets guest amount.
     *
     * @param guestAmount the guest amount
     */
    public void setGuestAmount(int guestAmount)
    {
        if(guestAmount >= 0)
        {
            this.guestAmount = guestAmount;
        }
    }

    /**
     * Gets tip percent
     * and returns it as a number
     * divided by 100.
     *
     * @return the tip percent
     */
    public double getTipPercent()
    {
        if(tipPercent >= 1)
        {
            return tipPercent / 100;
        }
        return tipPercent;
    }

    /**
     * Sets tip percent.
     *
     * @param tipPercent the tip percent
     */
    public void setTipPercent(double tipPercent)
    {
        this.tipPercent = tipPercent;

        this.calculateTip();
    }




    private double calculateTotalBill()
    {
        return billTotal + calculateTip();
    }

    private double calculateTipPerGuest()
    {
        return calculateTip() / guestAmount;
    }

    /*
     If they have decided how to much to tip
     Then why bother recalculating.
     */
    private double calculateTip()
    {
        if(tipAmount == 0)
        {
            tipAmount = (billTotal * tipPercent) / 100;
            return (billTotal * tipPercent) / 100;
        }
        else
        {
            return tipAmount;
        }
    }

    private double calculateTotalCostPerGuest()
    {
        return calculateTotalBill() / guestAmount;
    }

    /**
     * Gets tip amount.
     *
     * @return the tip amount
     */
    public double getTipAmount()
    {
        return tipAmount;
    }

    /**
     * Sets tip amount.
     *
     * @param tipAmount the tip amount
     */
    public void setTipAmount(double tipAmount)
    {
        this.tipAmount = tipAmount;
    }


    public String toString()
    {
        return "";
    }


    /**
     * Gets bill total.
     *
     * @return the bill total
     */
    public double getBillTotal()
    {
        return billTotal;
    }

    /**
     * Sets bill total.
     *
     * @param billTotal the bill total
     */
    public void setBillTotal(double billTotal)
    {
        if(billTotal >= 0)
        {
            this.billTotal = billTotal;
        }
    }
}
