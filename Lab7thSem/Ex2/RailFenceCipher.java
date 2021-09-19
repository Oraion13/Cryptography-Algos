package Lab7thSem.Ex2;

import java.util.Scanner;

public class RailFenceCipher {

    private static String encode(char[][] MSGBOX, String msg) {
        int index = 0;
        for(int i = 0; i < msg.length(); i++){
            for(int j = 0; j < MSGBOX.length; j++){
                if(MSGBOX[j][i] == '*'){
                    MSGBOX[j][i] = msg.charAt(index);
                    ++index;
                }
                //System.out.print(MSGBOX[j][i] + " ");
            }
            //System.out.println();
        }

        String cipher = "";
        for(int i = 0; i < MSGBOX.length; i++){
            for(int j = 0; j < msg.length(); j++){
                if(MSGBOX[i][j] == '\s' || Character.isLetter(MSGBOX[i][j])){
                    cipher += MSGBOX[i][j];
                }
            }
        }

        return cipher;
    }

    private static String starPath(String msg, int DEPTH, String option) {
        char[][] MSGBOX = new char[DEPTH][msg.length()];
        int i = 0, j = 0, follow = 0;
        // System.out.println("msg.length(): " + msg.length());

        while (j < msg.length()) {
            // System.out.printf("i: %d, j: %d, follow: %d || ", i, j, follow);
            if ((follow <= i) && i < DEPTH) {
                MSGBOX[i][j] = '*';
                // System.out.println(MSGBOX[i][j]);
                follow = i;
                if (i == DEPTH - 1) {
                    --i;
                    ++j;
                    continue;
                }
                ++i;
                ++j;
            } else if ((follow >= i) && (i >= 0)) {
                MSGBOX[i][j] = '*';
                follow = i;
                if (i == 0) {
                    ++i;
                    ++j;
                    continue;
                }
                --i;
                ++j;
            }
        }

        for(int x = 0; x < DEPTH; x++){
            for(int y = 0; y < msg.length(); y++){
                System.out.print(MSGBOX[x][y] + " ");
            }
            System.out.println();
        }

        if(option.toUpperCase() == "ENCODE"){
            return encode(MSGBOX, msg);
        }

        return decode(MSGBOX, msg);
        
    }

    private static String decode(char[][] MSGBOX, String msg) {
        int index = 0;
        for(int i = 0; i < MSGBOX.length; i++){
            for(int j = 0; j < msg.length(); j++){
                if(MSGBOX[i][j] == '*'){
                    MSGBOX[i][j] = msg.charAt(index);    
                    ++index;
                }
                System.out.print(MSGBOX[i][j] + " ");
            }
            System.out.println();
        }

        String cipher = "";
        for(int i = 0; i < msg.length(); i++){
            for(int j = 0; j < MSGBOX.length; j++){
                if(MSGBOX[j][i] == '\s' || Character.isLetter(MSGBOX[j][i])){
                    cipher += MSGBOX[j][i];
                }
            }
        }

        return cipher;
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            System.out.print("Enter the plain text: ");
            String msg = s.nextLine();// WE ARE DISCOVERED RUN AT ONCE

            System.out.print("Enter the Depth: ");
            int DEPTH = s.nextInt();

            System.out.println("Encoding...");
            String encoded = starPath(msg, DEPTH, "ENCODE");
            System.out.println("Encrypted message: " + encoded);

            System.out.println("Decoding...");
            String decoded = starPath(encoded, DEPTH, "DECODE");
            System.out.println("Decrypted text: " + decoded);
        } catch (Exception e) {
            System.out.println("Caught: " + e);
        }
    }
}
