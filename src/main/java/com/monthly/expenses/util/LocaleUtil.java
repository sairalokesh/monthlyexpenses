/**
 *
 */
package com.monthly.expenses.util;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.thymeleaf.context.Context;

import com.monthly.expenses.domain.User;

/**
 * @author G Lokesh
 *
 */
public class LocaleUtil {

    static final Locale GERMANY_LOCALE = Locale.GERMANY;
    static final Locale AUSTRIA_LOCALE = new Locale("de", "AT");
    static final Locale NETHERLANDS_LOCALE = new Locale("nl", "NL");
    static final Locale ENGLISH_LOCALE = new Locale("en", "US");
    static final List<Locale> supportedLocales = Arrays.asList(Locale.ENGLISH);

    public static String getCountry() {
        return LocaleContextHolder.getLocale().getCountry();
    }

    /**
     * @return
     */
    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }



    /**
     * @return
     */
    public static List<Locale> getSupportedLocales() {
        return supportedLocales;
    }

    public static Locale setUserLocaleForEmail(final Context context, User user) {
        final Locale locale = LocaleUtil.getLocale();
        context.setLocale(locale);
        return locale;
    }
}
