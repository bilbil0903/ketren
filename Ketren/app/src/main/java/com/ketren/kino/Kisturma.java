package com.ketren.kino;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.*;
import java.text.StringCharacterIterator;
import java.io.StringBufferInputStream;

public class Kisturma {


	/**
	 * كۆپ رەڭلىك  ئۆزگەرتىش
	 * 
	 * @param color
	 *            ئۆزگىردىغان رەڭ
	 * @param text
	 *            خەت
	 * @param keyword
	 *            ھالقىلىق خەتنقڭ  چوڭلىقى
	 * @return
	 */
	public static SpannableString code(int color, String text,
									   String[]keyword ) {
		SpannableString s = new SpannableString(text);

		for (char i =0; i<keyword[i].length(); i++) {

			Pattern p = Pattern.compile(keyword[i]);
			Matcher m = p.matcher(s);
			while (m.find()) {
				int start = m.start();
				int end = m.end();
				s.setSpan(new ForegroundColorSpan(color), start, end,

						  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			}
		}
		return s;

	}

}
