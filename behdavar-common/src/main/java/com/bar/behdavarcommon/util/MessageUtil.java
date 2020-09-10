package com.bar.behdavarcommon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageUtil {

    private static MessageSource staticMessageSource;
    @Autowired
    private MessageSource messageSource;

    public MessageUtil() {
        messageSource = null;
    }

    public static String getMessage(String var1, @Nullable Object[] var2, Locale var3) {
        return staticMessageSource.getMessage(var1, var2, var3);
    }

    public static String getMessage(String var1, @Nullable Object[] var2) {
        return staticMessageSource.getMessage(var1, var2, new Locale("fa"));
    }

    public static String getMessage(String var1) {
        return staticMessageSource.getMessage(var1, null, new Locale("fa"));
    }

    @PostConstruct
    private void init() {
        staticMessageSource = messageSource;
    }
}
