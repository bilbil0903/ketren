package com.ketren.kino;

public class Base64Coder {
    private static char[] map1 = new char[64];
    private static byte[] map2;

    static {
        int n = 0;
        for (int n2 = 65; n2 <= 90; n2 = (int)((char)(n2 + 1))) {
            char[] arrc = map1;
            int n3 = n + 1;
            arrc[n] = (char) n2;
            n = n3;
        }
        for (int n4 = 97; n4 <= 122; n4 = (int)((char)(n4 + 1))) {
            char[] arrc = map1;
            int n5 = n + 1;
            arrc[n] = (char) n4;
            n = n5;
        }
        for (int n6 = 48; n6 <= 57; n6 = (int)((char)(n6 + 1))) {
            char[] arrc = map1;
            int n7 = n + 1;
            arrc[n] = (char) n6;
            n = n7;
        }
        char[] arrc = map1;
        int n8 = n + 1;
        arrc[n] = 43;
        char[] arrc2 = map1;
        arrc2[n8] = 47;
        map2 = new byte[128];
        for (int i = 0; i < map2.length; ++i) {
            Base64Coder.map2[i] = -1;
        }
        for (int j = 0; j < 64; ++j) {
            Base64Coder.map2[Base64Coder.map1[j]] = (byte)j;
        }
    }

    private Base64Coder() {
    }

    public static byte[] decode(String string) {
        return Base64Coder.decode(string.toCharArray());
    }

    /*
     * Enabled aggressive block sorting
     */
    public static byte[] decode(char[] arrc) {
        int n;
        int n2 = arrc.length;
        if (n2 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4. length = " + n2);
        }
        for (n = n2; n > 0 && arrc[n - 1] == '='; --n) {
        }
        int n3 = n * 3 / 4;
        byte[] arrby = new byte[n3];
        int n4 = 0;
        int n5 = 0;
        while (n4 < n) {
            int n6;
            int n7;
            int n8;
            int n9;
            int n10;
            int n11 = n4 + 1;
            char c = arrc[n4];
            int n12 = n11 + 1;
            char c2 = arrc[n11];
            if (n12 < n) {
                int n13 = n12 + 1;
                n7 = arrc[n12];
                n9 = n13;
            } else {
                n9 = n12;
                n7 = 65;
            }
            if (n9 < n) {
                int n14 = n9 + 1;
                int n15 = arrc[n9];
                n9 = n14;
                n8 = n15;
            } else {
                n8 = 65;
            }

            byte by = map2[c];
            byte by2 = map2[c2];
            byte by3 = map2[n7];
            byte by4 = map2[n8];
            if (by < 0 || by2 < 0 || by3 < 0 || by4 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int n16 = by << 2 | by2 >>> 4;
            int n17 = (by2 & 15) << 4 | by3 >>> 2;
            int n18 = by4 | (by3 & 3) << 6;
            int n19 = n5 + 1;
            arrby[n5] = (byte)n16;
            if (n19 < n3) {
                int n20 = n19 + 1;
                arrby[n19] = (byte)n17;
                n10 = n20;
            } else {
                n10 = n19;
            }
            if (n10 < n3) {
                n6 = n10 + 1;
                arrby[n10] = (byte)n18;
            } else {
                n6 = n10;
            }
            n5 = n6;
            n4 = n9;
        }
        return arrby;
    }

    public static String decodeString(String string) {
        return new String(Base64Coder.decode(string));
    }

    public static char[] encode(byte[] arrby) {
        return Base64Coder.encode(arrby, arrby.length);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static char[] encode(byte[] arrby, int n) {
        int n2 = (2 + n * 4) / 3;
        char[] arrc = new char[4 * ((n + 2) / 3)];
        int n3 = 0;
        int n4 = 0;
        while (n3 < n) {
            int n5;
            int n6;
            int n7;
            int n8 = n3 + 1;
            int n9 = 255 & arrby[n3];
            if (n8 < n) {
                int n10 = n8 + 1;
                n6 = 255 & arrby[n8];
                n7 = n10;
            } else {
                n7 = n8;
                n6 = 0;
            }
            if (n7 < n) {
                int n11 = n7 + 1;
                int n12 = 255 & arrby[n7];
                n7 = n11;
                n5 = n12;
            } else {
                n5 = 0;
            }
            int n13 = n9 >>> 2;
            int n14 = (n9 & 3) << 4 | n6 >>> 4;
            int n15 = (n6 & 15) << 2 | n5 >>> 6;
            int n16 = n5 & 63;
            int n17 = n4 + 1;
            arrc[n4] = map1[n13];
            int n18 = n17 + 1;
            arrc[n17] = map1[n14];
            int n19 = n18 < n2 ? map1[n15] : 61;
            arrc[n18] = (char) n19;
            int n20 = n18 + 1;
            int n21 = n20 < n2 ? map1[n16] : 61;
            arrc[n20] = (char) n21;
            n4 = n20 + 1;
            n3 = n7;
        }
        return arrc;
    }

    public static String encodeString(String string) {
        return new String(Base64Coder.encode(string.getBytes()));
    }
}







