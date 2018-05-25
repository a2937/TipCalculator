package edu.wvup.acottri9.tipcalculator;

import org.junit.Test;

import edu.wvup.acottri9.tipcalculator.model.TipCalculator;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() throws Exception
    {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testConstructors() throws Exception
    {
        assertNotNull(new TipCalculator());

        TipCalculator percentBasedCalc = new TipCalculator(2,2,10.00,true);

        TipCalculator amountBasedCalc = new TipCalculator(2,2.00,10.00,false);

        assertNotNull(amountBasedCalc);
        assertNotNull(percentBasedCalc);

        assertEquals(2,amountBasedCalc.getTipAmount(),0.001);
        assertEquals(0.02,percentBasedCalc.getTipPercent(),0.001);
    }

    @Test
    public void testSetGuestAmount() throws Exception
    {
        TipCalculator tipCalculator = new TipCalculator();
        assertEquals(2,tipCalculator.getGuestAmount());
        tipCalculator.setGuestAmount(5);
        assertEquals(5,tipCalculator.getGuestAmount());
    }

    @Test
    public void testSetTipAmount() throws Exception
    {
        TipCalculator tipCalculator = new TipCalculator();
        assertEquals(0,tipCalculator.getTipAmount(),0.001);
        tipCalculator.setTipAmount(5.00);
        assertEquals(5,tipCalculator.getTipAmount(),0.001);
    }

    @Test
    public void testSetTipPercent() throws Exception
    {
        TipCalculator tipCalculator = new TipCalculator();
        assertEquals(0,tipCalculator.getTipPercent(),0.001);
        tipCalculator.setTipPercent(5.00);
        assertEquals(0.05,tipCalculator.getTipPercent(),0.001);
    }

    @Test
    public void testSetBill() throws Exception
    {
        TipCalculator tipCalculator = new TipCalculator();
        assertEquals(0,tipCalculator.getBillTotal(),0.001);
        tipCalculator.setBillTotal(5.00);
        assertEquals(5.00,tipCalculator.getBillTotal(),0.001);
    }

}