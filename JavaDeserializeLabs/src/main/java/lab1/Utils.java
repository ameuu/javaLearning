package lab1;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class Utils {
    public static String bytesTohexString(byte[] bytes) {
        if (bytes == null)
            return null;
        StringBuilder ret = new StringBuilder(2 * bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            int b = 0xF & bytes[i] >> 4;
            ret.append("0123456789abcdef".charAt(b));
            b = 0xF & bytes[i];
            ret.append("0123456789abcdef".charAt(b));
        }
        return ret.toString();
    }

    static int hexCharToInt(char c) {
        if (c >= '0' && c <= '9')
            return c - 48;
        if (c >= 'A' && c <= 'F')
            return c - 65 + 10;
        if (c >= 'a' && c <= 'f')
            return c - 97 + 10;
        throw new RuntimeException("invalid hex char '" + c + "'");
    }

    public static byte[] hexStringToBytes(String s) {
        if (s == null)
            return null;
        int sz = s.length();
        byte[] ret = new byte[sz / 2];
        for (int i = 0; i < sz; i += 2)
            ret[i / 2] = (byte)(hexCharToInt(s.charAt(i)) << 4 | hexCharToInt(s.charAt(i + 1)));
        return ret;
    }

    public static String objectToHexString(Object obj) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(bos);
        out.writeObject(obj);
        out.flush();
        byte[] bytes = bos.toByteArray();
        bos.close();
        String hex = bytesTohexString(bytes);
        return hex;
    }
}
