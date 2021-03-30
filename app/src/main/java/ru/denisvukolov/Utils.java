package ru.denisvukolov;

import android.text.SpannableString;
import android.text.Spanned;

import androidx.core.text.HtmlCompat;

public class Utils {

    private Utils(){
    }

    public static Spanned fromHtml(String html){
        if (html == null) {
            return new SpannableString("");
        } else {
            return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY);
        }
    }
}
