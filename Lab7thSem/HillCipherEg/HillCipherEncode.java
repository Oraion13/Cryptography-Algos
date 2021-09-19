package Lab7thSem.HillCipherEg;

import static java.lang.Math.sqrt;

interface encodeInterface {
    String encodeFunc(String msg);
}

interface validKeyInterface {
    boolean validKeyFunc(String key);
}

public class HillCipherEncode {
    private static String Alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .?";
    private static int KEY[][];

    // String key to Matrix key
    private static int[][] enKey(String key) {
        int len = (int) sqrt(key.length());
        KEY = new int[len][len];
        int p = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                KEY[i][j] = Alpha.indexOf(key.charAt(p));
                ++p;
            }
        }
        return KEY;
    }

    static boolean checkVaildKey(String key) {
        double keyLen = sqrt(key.length());
        if (keyLen == (int) keyLen) {
            KEY = enKey(key);
            int det = MatrixCalc.determinent(KEY);
            
            //System.out.println("Determinent: " + det);

            for (int i = 2; i < Alpha.length(); i++) {
                if (((det % i) == 0 && (Alpha.length() % i) == 0) || det == 0) {
                    System.out.println("Invalid key, determinent got value = 0 or had an comman factor: " + i
                            + " with Alpha length");
                    return true;
                }
            }
        } else if (keyLen != (int) keyLen) {
            System.out.println("Invalid key, length is not a perfect square");
            return true;
        }

        
          /*for(int i = 0; i < KEY[0].length; i++){ for(int j = 0; j < KEY[0].length;
          j++){ System.out.print(KEY[i][j] + " "); } System.out.println(); }*/
         
        return false;
    }

    private static int[] graphMessage(String msg) {
        if (msg.length() % KEY[0].length != 0) {
            while (true) {
                msg += 'X';
                if (msg.length() % KEY[0].length == 0)
                    break;
            }
        }
        System.out.println("Graph: " + msg);
        int[] MSG = new int[msg.length()];

        for (int i = 0; i < msg.length(); i++) {
            MSG[i] = Alpha.indexOf(msg.toUpperCase().charAt(i));
        }
        return MSG;
    }

    private static String stringConvertor(int[] MSG) {
        String str = "";
        for (int i : MSG) {
            str += Alpha.charAt(i);
        }
        return str;
    }

    static String encode(String msg) {
        int[] MSG = graphMessage(msg);
        /*
         * for (int i : MSG) { System.out.print(i + " = " + Alpha.charAt(i) + " || "); }
         * System.out.println();
         */
        MSG = MatrixCalc.matrixMultiplication(MSG, KEY, Alpha.length());
        /*
         * for (int i : MSG) { System.out.print(i + " = " + Alpha.charAt(i) + " || "); }
         */
        msg = stringConvertor(MSG);

        return msg;
    }
}
