/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.project;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Rayhan
 */
public class Rupiah {
    public String formatRupiah(int value){
        DecimalFormat formater = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols simbol = formater.getDecimalFormatSymbols();

        simbol.setCurrencySymbol("Rp. ");
        simbol.setMonetaryDecimalSeparator(',');// belakang sendiri pada format rupiah
        simbol.setGroupingSeparator('.');
        formater.setDecimalFormatSymbols(simbol);
        return formater.format(value);
    }
}
