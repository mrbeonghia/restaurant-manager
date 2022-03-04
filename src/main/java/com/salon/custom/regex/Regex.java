package com.salon.custom.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean checkPhoneNumber(String phoneNumber){
        if (phoneNumber == null){
            return false;
        }
        Pattern pattern = Pattern.compile("^(?:(0([7,9,5]{1})0([0-9]{8}))|(0([8]{1})0([1-9]{1}[0-9]{7})))$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return (matcher.find()&&matcher.group().equals(phoneNumber));
    }

    public static boolean checkFuriganaName(String furiganaName){
        if (furiganaName == null){
            return false;
        }
        Pattern pattern = Pattern.compile("^[ぁ-ん]+$");
        Matcher matcher = pattern.matcher(furiganaName);
        return (matcher.find()&&matcher.group().equals(furiganaName));
    }
}
