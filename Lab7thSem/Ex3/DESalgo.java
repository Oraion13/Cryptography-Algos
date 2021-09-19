package Lab7thSem.Ex3;

import java.math.BigInteger;

public class DESalgo {
    private static String KEY = "", C0 = "", D0 = "", L0 = "", R0 = "";
    private static int[] PS1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19,
            11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21,
            13, 5, 28, 20, 12, 4 };
    private static int[] PS2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
    private static int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22,
            14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53,
            45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
    private static int[] EBIT = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16,
            17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
    private static int SBOX[][][] = {
            { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                    { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                    { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                    { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },
            { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                    { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },
            { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },
            { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },
            { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                    { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },
            { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },
            { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },
            { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };
    private static int[] PER = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3,
            9, 19, 13, 30, 6, 22, 11, 4, 25 };
    private static int[] INVPER = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22,
            62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2,
            42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
    private static int[] ITERSHIFT = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
    private static String[] KEYAGE = new String[16];

    private static String coder(String msg, String[] key, String option, int iter) {
        L0 = msg.substring(0, msg.length() / 2);
        R0 = msg.substring(msg.length() / 2, msg.length());
        System.out.printf("L0 : %s length: %d\nR0 : %s length: %d\n", L0, L0.length(), R0, R0.length());
        if (iter > 15 && option == "ENCODE") {
            msg = R0 + L0;
            msg = perKey(msg, INVPER);
            return msg;
        }if(iter < 0 && option == "DECODE"){
            msg = R0 + L0;
            msg = perKey(msg, INVPER);
            return msg;
        }

       

        
        String r = perKey(R0, EBIT);
        System.out.println("Permuted R0: " + r);// 48 - bits
        String Xored = xorOp(r, key[iter]);
        System.out.println("Xored: " + Xored);
        String XO = boxCalc(Xored);
        System.out.println("Function(R0, K0): " + XO);
        String funRslt = perKey(XO, PER);
        System.out.println("After function(R0, K0): " + funRslt);
        String L1 = R0;
        R0 = xorOp(L0, funRslt);
        msg = L1 + R0;
        System.out.println("Final output at Round " + iter + " : " + msg + "\n");
        iter = (option == "ENCODE") ? iter + 1 : iter - 1;
 
        return coder(msg, key, option, iter);
    }

    private static String keyGenerator(String key, int iter){
        if(iter > 15){
            return key;
        }
        C0 = key.substring(0, key.length() / 2);
        D0 = key.substring(key.length() / 2, key.length());
        System.out.printf("C0 : %s length: %d\nD0 : %s length: %d\n", C0, C0.length(), D0, D0.length());

        C0 = leftRotate(C0, ITERSHIFT[iter], C0.length());
        System.out.println("C0C: " + C0);

        D0 = leftRotate(D0, ITERSHIFT[iter], D0.length());
        System.out.println("D0C: " + D0);

        key = "" + C0 + D0;
        // System.out.println(key);
        String k = perKey(key, PS2);
        System.out.println("Key[" + iter + "]: " + k + "\n");// 48 - bit
        KEYAGE[iter] = k;
        return keyGenerator(key, ++iter);
    }

    private static String boxCalc(String xored) {
        short firstI = 0;
        short lastI = 6;
        int index = 0, FL = 0, IN = 0;
        int result = 0;
        String Inter = "", rslt = "", First = "", Last = "", XO = "";
        while (lastI <= xored.length()) {
            First = "" + xored.charAt(firstI);
            Last = "" + xored.charAt(lastI - 1);
            Inter = xored.substring(firstI + 1, lastI - 1);
            First += Last;
            FL = Integer.parseInt(First, 2);
            IN = Integer.parseInt(Inter, 2);
            result = SBOX[index][FL][IN];
            rslt = Integer.toBinaryString(result);
            while (true) {
                if (rslt.length() == 4) {
                    break;
                }
                rslt = '0' + rslt;
            }
            // System.out.println(rslt);
            XO += rslt;
            ++index;
            firstI = lastI;
            lastI += 6;
        }

        return XO;
    }

    private static String xorOp(String R0, String X0) {
        String Xored = "";
        int i = 0, x = 0, y = 0;
        while (i < R0.length()) {
            x = R0.charAt(i);
            y = X0.charAt(i);
            Xored += "" + (x ^ y);
            ++i;
        }
        return Xored;
    }

    static String leftRotate(String n, int shift, int bits) {
        n += n.substring(0, shift);
        n = n.substring(shift, bits + shift);
        return n;
    }

    private static String perKey(String key, int[] PS) {
        String pKey = "";
        int i = 0;

        while (i < PS.length) {
            pKey += key.charAt((PS[i] - 1));
            ++i;
        }
        return pKey;// 56-bit key, 64-bit message
    }

    private static String toBin(String s, int base) {
        String Bi = new BigInteger(s, base).toString(2);
        while (true) {
            if (Bi.length() == 64) {
                break;
            }
            Bi = '0' + Bi;
        }
        return Bi;
    }

    public static void main(String[] args) {
        String key = toBin("0F1571C947D9E859", 16);// 0001001100110100010101110111100110011011101111001101111111110001 - 133457799BBCDFF1 - INVALID KEY ONLY FOR 1st PT, 0F1571C947D9E859, AABB09182736CCDD
        System.out.println("Key: " + key);// 64-bit
        KEY = perKey(key, PS1);// 56-bit
        System.out.println(KEY);// 11110000110011001010101011110101010101100110011110001111
        // C0: 1111000011001100101010101111 28 - bit
        // D0: 0101010101100110011110001111 28 - bit
        // System.out.printf("C0: %s\nD0: %s\n", C0, D0);
        // System.out.println(""+5+6);
        keyGenerator(KEY, 0);

        String msg = toBin("0123456789ABCDEF", 16);// 0000000100100011010001010110011110001001101010111100110111101111 - 0123456789ABCDEF, 02468ACEECA86420, 123456ABCD132536
        System.out.println("Message: " + msg);// 64-bit
        msg = perKey(msg, IP);
        System.out.println("Permuted msg: " + msg + "\n");// 1100110000000000110011001111111111110000101010101111000010101010
        // L0: 11001100000000001100110011111111 32 - bit
        // R0: 11110000101010101111000010101010 32 - bit
        // System.out.printf("L0: %s\nR0: %s\n", L0, R0);
        String encoded = coder(msg, KEYAGE, "ENCODE", 0);
        System.out.println("Encoded: " + encoded);//1000010111101000000100110101010000001111000010101011010000000101
        L0 = encoded.substring(0, encoded.length() / 2);
        R0 = encoded.substring(encoded.length() / 2, encoded.length()); 
        L0 = new BigInteger(L0, 2).toString(16);
        R0 = new BigInteger(R0, 2).toString(16);
        encoded = L0 + R0;
        System.out.println("\n******Encoded Hex: " + encoded + " ******\n\n");//85e81354f0ab405


        encoded = perKey(toBin(encoded, 16), IP);
        System.out.println("Permuted cipher msg: " + encoded + "\n");
        String decoded = coder(encoded, KEYAGE, "DECODE", 15);
        System.out.println("Decoded: " + decoded);//1000010111101000000100110101010000001111000010101011010000000101
        L0 = decoded.substring(0, decoded.length() / 2);
        R0 = decoded.substring(decoded.length() / 2, decoded.length()); 
        L0 = new BigInteger(L0, 2).toString(16);
        R0 = new BigInteger(R0, 2).toString(16);
        decoded = L0 + R0;
        System.out.println("\n******Decoded Hex: " + decoded + " ******");
    }
}