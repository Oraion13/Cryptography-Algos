package cryptoAlgos.substitutionAlgos.HillCipher;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import javax.naming.InterruptedNamingException;

public class sample {
    // private static int SBOX[][][] = {
    // { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
    // { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
    // { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
    // { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },
    // { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
    // { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
    // { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
    // { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },
    // { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
    // { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
    // { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
    // { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },
    // { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
    // { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
    // { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
    // { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },
    // { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
    // { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
    // { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
    // { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },
    // { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
    // { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
    // { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
    // { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },
    // { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
    // { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
    // { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
    // { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },
    // { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
    // { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
    // { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
    // { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };

    // private static String xorOp(String R0, String X0){
    // String Xored = "";
    // int i = 0, x = 0, y = 0;
    // while(i < R0.length()){
    // x = R0.charAt(i);
    // y = X0.charAt(i);
    // Xored += ""+(x ^ y);
    // ++i;
    // }
    // return Xored;
    // }

    // private static void boxCalc(String xored){
    // short firstI = 0;
    // short lastI = 6;
    // int index = 0, FL = 0, IN = 0;
    // int result = 0;
    // String Inter = "", rslt = "", First = "", Last = "", XO = "";
    // while(lastI <= xored.length()){
    // First = "" + xored.charAt(firstI);
    // Last = "" + xored.charAt(lastI - 1);
    // Inter = xored.substring(firstI + 1, lastI - 1);
    // First += Last;
    // FL = Integer.parseInt(First, 2);
    // IN = Integer.parseInt(Inter, 2);
    // result = SBOX[index][FL][IN];
    // rslt = Integer.toBinaryString(result);
    // while(true){
    // if(rslt.length() == 4){
    // break;
    // }
    // rslt = '0' + rslt;
    // }
    // System.out.println(rslt);
    // XO += rslt;
    // ++index;
    // firstI = lastI;
    // lastI += 6;
    // }
    // System.out.println(XO);
    // }
    // static int Add(int x, int y) {
    //     System.out.println("X: " + x + " Y: " + y + "\nX-bits:  " + Integer.toBinaryString(x) + " Y-bits: " + Integer.toBinaryString(y) + "\nXOred: " + Integer.toBinaryString(x^y) + "\nOriginal AND:  " + Integer.toBinaryString(x&y) + " Shifted: " + Integer.toBinaryString((x&y) << 1) + "\n");
    //     if (y == 0)
    //         return x;
    //     else
    //         return Add(x ^ y, (x & y) << 1);
    // }

    // public static String toHex(String arg) throws UnsupportedEncodingException {
    //     return String.format("%x", new BigInteger(1, arg.getBytes("ASCII")));
    //   }

    //   private static String toBin(String s, int base) {
    //     String Bi = new BigInteger(s, base).toString(2);
    //     while (true) {
    //         if (Bi.length() == 128) {
    //             break;
    //         }
    //         Bi = '0' + Bi;
    //     }
    //     return Bi;
    // }
    public static void main(String[] args) throws UnsupportedEncodingException {
        // System.out.println("FINEWORDSBUTTERNOPARSNIPS".length());
        // String R0 = "011110100001010101010101011110100001010101010101";
        // String X0 = "000110110000001011101111111111000111000001110010";

        // String xo = xorOp(R0, X0);
        // boxCalc(xo);
        // System.out.println(Add(5, 6));

        // String hexStr = toHex("Thats my Kung Fu");
        // System.out.println(hexStr);
        // String binStr = toBin(hexStr, 16);
        // System.out.println(binStr);
        // int f = Integer.valueOf("69", 16);//73617469
        // int s = Integer.valueOf("40", 16);//f89f8540
        // System.out.println(Integer.toHexString(f ^ s));

        //column selection
        // int[] ar = new int[4];
        // for(int i = 0; i < 4; i++)
        // {
        //      ar[i] = SBOX[0][i][0];
        // }
        // for (int i : ar) {
        //     System.out.print(i + " ");
        // }

        // String str = "6e";
        // String s1 = str.substring(0, 1);
        // String s2 = str.substring(1);
        // System.out.println("Sub1: " + Integer.parseInt(s1, 16) + " Sub2: " + Integer.parseInt(s2, 16));
        // System.out.println(Integer.toHexString(0xCA));
        // System.out.println(0xCA);

        // int a = 0x02;
        // int b = Integer.parseInt("63", 16);
        // int c = (a*b)%0x11b;
        // System.out.println(Integer.toHexString(c));
        String[] m = {"84", "00", "19", "41"};
        int[] x = {0x02, 0x03, 0x01, 0x01};
        int sum = 0;
        for(int i = 0; i < 4; i++){
            int k = x[i]*Integer.parseInt(m[i], 16);
                k %= 0x11b;
            String bi = new BigInteger(Integer.toHexString(k), 16).toString(2);
            System.out.println("K: " + Integer.toHexString(k) + " bi:" + bi);
            sum ^= k;
        }
        sum %= 0x11b;
        System.out.println(Integer.toHexString(sum));
        String bi = new BigInteger(Integer.toHexString(0x03*0x2F), 16).toString(2);
        System.out.println(bi);

        // String str = " 67 4A ED DE C5 31 FE 18 0D 63 8C 80 C0 F7 70 07";
        // str = str.replace(" ", ", 0x");
        // System.out.println("{"+str.substring(2)+"},");
    }
}
