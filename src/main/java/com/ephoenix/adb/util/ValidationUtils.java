package com.ephoenix.adb.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static final Pattern VALID_PHONE_NUMBER = Pattern.compile("^0\\d{9,10}$");

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_OPEN_ID_USERNAME = Pattern.compile("^(cn|fb)\\d+$");

    // Mã thẻ điện thoại
    public static final Pattern VALID_CARD_CODE = Pattern.compile("^[0-9]+$");

    public static final Pattern VALID_CARD_SERIAL = Pattern.compile("^[a-zA-Z0-9]+$");

    /**
     * Is Valid Phone ???
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhoneNumber(String phone) {
        Matcher matcher = VALID_PHONE_NUMBER.matcher(phone);
        return matcher.find();
    }

    /**
     * Is Valid Email Address ???
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    /**
     * Mật khẩu phải có chữ và số
     *
     * @param str
     * @return
     */
    public static boolean isValidPassword(final String str) {
        String n = ".*[0-9].*";
        String a = ".*[a-zA-Z].*";
        return str.matches(n) && str.matches(a);
    }

    /**
     * Kiểm tra tên nhập vào có phải tiếng việt có dấu không False - Có dấu True
     * - Không dấu
     *
     * @param v
     * @return
     */
    public static boolean isPureAscii(String v) {
        byte bytearray[] = v.getBytes();
        CharsetDecoder d = Charset.forName("US-ASCII").newDecoder();
        try {
            CharBuffer r = d.decode(ByteBuffer.wrap(bytearray));
            r.toString();
        } catch (CharacterCodingException e) {
            return false;
        }
        return true;
    }

    /**
     * Tên đăng nhập phải là chữ hoặc số
     *
     * @param str
     * @return
     */
    public static boolean isValidUsername(final String str) {
        return str.matches("[a-zA-Z0-9_]*");
    }

    /**
     * Check mã thẻ có hợp lệ hay không
     *
     * @param code
     * @return
     */
    public static boolean isValidCardCode(final String code) {
        Matcher matcher = VALID_CARD_CODE.matcher(code);
        return matcher.find();
    }

    /**
     * Check mã thẻ có hợp lệ hay không
     *
     * @param serial
     * @return
     */
    public static boolean isValidCardSerial(final String serial) {
        Matcher matcher = VALID_CARD_SERIAL.matcher(serial);
        return matcher.find();
    }

}
