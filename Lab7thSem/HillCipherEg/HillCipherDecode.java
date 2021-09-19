package Lab7thSem.HillCipherEg;

import static java.lang.Math.sqrt;

interface decodeInterface {
    String decodeFunc(String msg, String key);
}

public class HillCipherDecode {

    private static String Alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .?";
    private static int KEY[][]; 

    private static void keyGenerator(String key) {
        int len = (int) sqrt(key.length());
        KEY = new int[len][len];
        int p = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                KEY[i][j] = Alpha.indexOf(key.charAt(p));
                ++p;
            }
        }
        /*for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(KEY[i][j] + " ");
            }
            System.out.println();
        }*/
        KEY = MatrixCalc.invMatrix(KEY, Alpha.length());
        /*for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(KEY[i][j] + " ");
            }
            System.out.println();
        }*/
    }  

    private static int[] intConvertor(String msg){
        int[] MSG = new int[msg.length()];
        for(int i = 0; i < msg.length(); i++){
            MSG[i] = Alpha.indexOf(msg.toUpperCase().charAt(i));
        }
        return MSG;
    }

    private static String stringConvertor(int[] MSG){
        String str = "";
        for (int i : MSG) {
            str += Alpha.charAt(i);
        }
        return str;
    }

    static String decode (String msg, String key){
        keyGenerator(key);
        int[] MSG = intConvertor(msg);
        MSG = MatrixCalc.matrixMultiplication(MSG, KEY, Alpha.length());
        msg = stringConvertor(MSG);
        return msg;
    }

    /*public static void main(String[] args) {
        keyGenerator("RRFVSVCCT"); //RRFVSVCCT, BEAHLCAFB, GYBNQKURP, mmnteeizetgztohz = 0, ***ehcdbdbccfdebecd*** , CHGEFNCGB
    } */
     
}
