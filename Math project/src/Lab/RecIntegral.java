/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab;

import java.io.Serializable;

/**
 *
 * @author Gesser
 */
public class RecIntegral implements Serializable {

    private double upperBound,
            bottomLine,
            step;

    public void setInputRow(String up, String bl, String s) throws RecIntegralException {

        if (!(0.000001 <= Double.parseDouble(up) && Double.parseDouble(up) <= 1000000.0)
                || !(0.000001 <= Double.parseDouble(bl) && Double.parseDouble(bl) <= 1000000.0)
                || !(0.000001 <= Double.parseDouble(s) && Double.parseDouble(s) <= 1000000.0)) {
            throw new RecIntegralException("<html><p>Value not in range:</p><p>[ 0.000001 &#60= value  &#60= 1000000.0 ]</p></html>");
        } else if (!(Double.parseDouble(up) > Double.parseDouble(bl))) {
            throw new RecIntegralException("<html><p>The upper bound must be greater</p><p>than the lower bound</p></html>");
        } else if (!(Double.parseDouble(s) <= (Double.parseDouble(up) - Double.parseDouble(bl)))) {
            throw new RecIntegralException("<html><p>The step value must be:</p><p>[ step &#60= ( upper bound - lower bound ) ]</p></html>");
        }

        upperBound = Double.parseDouble(up);
        bottomLine = Double.parseDouble(bl);
        step = Double.parseDouble(s);
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getBottomLine() {
        return bottomLine;
    }

    public double getStep() {
        return step;
    }

}
