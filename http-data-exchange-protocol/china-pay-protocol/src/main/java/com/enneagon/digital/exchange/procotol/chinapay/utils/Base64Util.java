package com.enneagon.digital.exchange.procotol.chinapay.utils;

/**
 * Base64Util
 *
 * @author Jim
 * @version 1.0
 * @date 2019/8/8 11:24
 */
public class Base64Util {

    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] codes;

    private Base64Util(){}

    public static byte[] decode(char data[]) throws Exception {
        int tempLen = data.length;
        for (int ix = 0; ix < data.length; ix++) {
            int value = codes[data[ix] & 0xff];
            if (value < 0 && data[ix] != '=') {
                tempLen--;
            }
        }

        int len = ((tempLen + 3) / 4) * 3;
        if (tempLen > 0 && data[tempLen - 1] == '=')
            len--;
        if (tempLen > 1 && data[tempLen - 2] == '=')
            len--;
        byte out[] = new byte[len];
        int shift = 0;
        int accum = 0;
        int index = 0;
        for (int ix = 0; ix < data.length; ix++) {
            int value = codes[data[ix] & 0xff];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte) (accum >> shift & 0xff);
                }
            }
        }

        if (index != out.length)
            throw new Exception("Miscalculated data length (wrote " + index + " instead of "
                    + out.length + ")");
        else
            return out;
    }

    public static char[] encode(byte data[]) {
        char out[] = new char[((data.length + 2) / 3) * 4];
        int i = 0;
        for (int index = 0; i < data.length; index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = 0xff & data[i];
            val <<= 8;
            if (i + 1 < data.length) {
                val |= 0xff & data[i + 1];
                trip = true;
            }
            val <<= 8;
            if (i + 2 < data.length) {
                val |= 0xff & data[i + 2];
                quad = true;
            }
            out[index + 3] = alphabet[quad ? val & 0x3f : 64];
            val >>= 6;
            out[index + 2] = alphabet[trip ? val & 0x3f : 64];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3f];
            val >>= 6;
            out[index] = alphabet[val & 0x3f];
            i += 3;
        }
        return out;
    }



    static {
        codes = new byte[256];
        for (int i = 0; i < 256; i++)
            codes[i] = -1;

        for (int i = 65; i <= 90; i++)
            codes[i] = (byte) (i - 65);

        for (int i = 97; i <= 122; i++)
            codes[i] = (byte) ((26 + i) - 97);

        for (int i = 48; i <= 57; i++)
            codes[i] = (byte) ((52 + i) - 48);

        codes[43] = 62;
        codes[47] = 63;
    }
}
