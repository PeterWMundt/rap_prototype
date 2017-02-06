package ch.ethz.id.sws.eduapp.administration;

import java.util.Locale;

import org.eclipse.rap.rwt.RWT;

public class LocaleConfig {
    public static final String LOCALE_COUNTRY_DEFAULT = "DE";


    public static void setDefaultLocale() {
        final Locale locale = RWT.getLocale();
        if (LOCALE_COUNTRY_DEFAULT.equals(locale.getCountry())) {
            return;
        }
        Locale defaultLocale = null;
        final Locale[] locales = Locale.getAvailableLocales();

        for (final Locale loc : locales) {
            if (LOCALE_COUNTRY_DEFAULT.equals(loc.getCountry())) {
                defaultLocale = loc;
                break;
            }
        }
        if (defaultLocale != null) {
            RWT.setLocale(defaultLocale);
        }
    }

}