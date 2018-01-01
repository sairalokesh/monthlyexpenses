/**
 *
 */
package com.monthly.expenses.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.monthly.expenses.util.LocaleUtil;

/**
 * @author G Lokesh
 *
 */
@Service
public class MessageLocaleService {

    @Autowired
    MessageSource messageSource;

    public String getMessageByLocale(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * @param mailUserRegistrationSubject
     * @param emailSubjectArgs
     * @param locale
     * @return
     */
    public String getMessageByLocale(String key, String[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }

    public String getMessage(String key){
        Locale locale = LocaleUtil.getLocale();
        return messageSource.getMessage(key, null, locale);
    }
}
