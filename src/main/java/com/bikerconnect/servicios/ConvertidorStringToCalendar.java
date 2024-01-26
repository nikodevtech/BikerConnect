package com.bikerconnect.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.core.convert.converter.Converter;

/**
 * Clase que implementa la interface {@link Converter} y sobreescribe
 * el metodo convert para detallar el pasar de String a un objeto Calendar. Esta clase
 * se le informa a Spring en la clase configuracion.
 */
public class ConvertidorStringToCalendar implements Converter<String, Calendar> {

	@Override
	public Calendar convert(String fechaString) {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Calendar calendar = Calendar.getInstance();

		try {
			calendar.setTime(formatoDeFecha.parse(fechaString));
		} catch (ParseException e) {
			System.out.println("\n[ERROR ConvertidorStringToCalendar - convert()] - Al convertir String a Calendar (return null): "+ e); 
		}

		return calendar;
	}

}
