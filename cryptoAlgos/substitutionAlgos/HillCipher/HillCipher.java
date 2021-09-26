package cryptoAlgos.substitutionAlgos.HillCipher;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HillCipher {

    static boolean validKey(String key, validKeyInterface v) {
        return v.validKeyFunc(key);
    }

    static String callEncode(String msg, encodeInterface v){
        return v.encodeFunc(msg);
    }

    static String callDecode(String msg, decodeInterface v, String key){
        return v.decodeFunc(msg, key);
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean validK = false;
            String key = "";
            do {
                System.out.print("Please enter a valid Key: "); // RRFVSVCCT, BEAHLCAFB, GYBNQKURP, mmnteeizetgztohz = 0, ***ehcdbdbccfdebecd*** , CHGEFNCGB
                key = br.readLine();
                validK = validKey(key.toUpperCase(), HillCipherEncode::checkVaildKey);
            } while (validK);

            System.out.print("Enter the plain text: ");
            String msg = br.readLine();

            String encoded = callEncode(msg, HillCipherEncode::encode);
            System.out.println("Encoded Text: " + encoded);

            String decoded = callDecode(encoded, HillCipherDecode::decode, key.toUpperCase());
            System.out.println("Decoded Text: " + decoded);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
