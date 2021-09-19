package Lab7thSem.Ex2;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class ColumnTranspositionCipher {

    private static String encode(String msg, String key) {
        System.out.println("ENCODING.....");

        Hashtable<Integer, Character> keys = new Hashtable<>(key.length());
        char[] keyArr = key.toCharArray();

        Arrays.sort(keyArr);
        int m = 0;
        for (char c : keyArr) {
            keys.put(m++, c);
        }

        System.out.println(keys);

        char[][] MSGBOX = new char[((msg.length() / key.length()) + (msg.length() % key.length()))][key.length()];

        int index = 0;
        for (int i = 0; i < MSGBOX.length; i++) {
            for (int j = 0; j < MSGBOX[0].length; j++) {
                if (index >= msg.length()) {
                    MSGBOX[i][j] = 'X';
                    System.out.print(MSGBOX[i][j] + " ");
                    continue;
                }

                MSGBOX[i][j] = msg.charAt(index);
                ++index;
                System.out.print(MSGBOX[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        String cipher = "";
        index = 0;
        for (int i = 0; i < MSGBOX[0].length; i++) {
            System.out.print("Column Index: " + key.indexOf(keys.get(i)) + " -> ");
            for (int j = 0; j < MSGBOX.length; j++) {
                System.out.print(MSGBOX[j][key.indexOf(keys.get(i))] + " ");
                cipher += MSGBOX[j][key.indexOf(keys.get(i))];
            }
            System.out.println();
        }

        return cipher;
    }

    private static String decode(String msg, String key) {
        System.out.println("DECODING.....");

        Hashtable<Integer, Character> keys = new Hashtable<>(key.length());
        char[] keyArr = key.toCharArray();

        Arrays.sort(keyArr);
        int m = 0;
        for (char c : keyArr) {
            keys.put(m++, c);
        }

        System.out.println(keys);

        char[][] MSGBOX = new char[((msg.length() / key.length()) + (msg.length() % key.length()))][key.length()];

        int index = 0;
        for (int i = 0; i < MSGBOX[0].length; i++) {
            System.out.print("Row Index: " + key.indexOf(keys.get(i)) + " -> ");
            for (int j = 0; j < MSGBOX.length; j++) {
                MSGBOX[j][key.indexOf(keys.get(i))] = msg.charAt(index);
                ++index;
                System.out.print(MSGBOX[j][key.indexOf(keys.get(i))] + " ");
            }
            System.out.println();
        }
        System.out.println();

        String cipher = "";
        index = 0;
        for (int i = 0; i < MSGBOX.length; i++) {
            for (int j = 0; j < MSGBOX[0].length; j++) {
                System.out.print(MSGBOX[i][j] + " ");
                cipher += MSGBOX[i][j];
            }
            System.out.println();
        }

        return cipher;
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            System.out.print("Enter the plain text: ");
            String msg = s.nextLine();// WE ARE DISCOVERED FLEE AT ONCE

            System.out.print("Enter the Key: ");
            String key = s.nextLine();// ZEBRAS

            String encoded = encode(msg, key);
            System.out.println("Encoded msg: " + encoded);

            String decoded = decode(encoded, key);
            System.out.println("Decoded msg: " + decoded);
        } catch (Exception e) {
            System.out.println("Caught: " + e);
        }

    }

}