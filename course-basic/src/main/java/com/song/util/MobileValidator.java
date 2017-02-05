package com.song.util;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by song on 2017/2/5.
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private static final Pattern MOBILE_PATTERN = Pattern
            .compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");

    @Override
    public void initialize(Mobile mobile) {

    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        if (mobile == null) {
            return true;
        }
        return MOBILE_PATTERN.matcher(mobile).matches();
    }
}
