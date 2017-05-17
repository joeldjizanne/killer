/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.util;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author kevinash
 */
public class DateConvertionUtil {
    public static LocalDate toLocalDate(Date date){
        return new java.sql.Date(date.getTime()).toLocalDate();
    }
    
    public static Date toDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
}
