package com.estudos.common.convert;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateConvert {
	public static final SimpleDateFormat imdbRelesed = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
	public static final SimpleDateFormat imdbYear = new SimpleDateFormat("yyyy");
}
