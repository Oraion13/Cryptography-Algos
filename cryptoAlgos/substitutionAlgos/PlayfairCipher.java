package cryptoAlgos.substitutionAlgos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayfairCipher {
    private static char alpha[] = new char[25];
    private static char table[][] = new char[5][5];

    // to create the key array
    private static void charArray(boolean cngItoJ) {
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXY".toCharArray();

        if (cngItoJ) {
            for (int i = 9; i < alpha.length - 1; i++) {
                alpha[i] = alpha[i + 1];
            }
            alpha[24] = 'Z';
        } else {
            for (int i = 16; i < alpha.length - 1; i++) {
                alpha[i] = alpha[i + 1];
            }
            alpha[24] = 'Z';
        }

    }

    // create the playfair table
    private static void createTable(String key) {
        key = trim(key);
        char[] KEY = key.toUpperCase().toCharArray();
        int cntr = -1;
        for (int i = 0; i < KEY.length; i++) {
            for (int j = 0; j < 25; j++) {
                if (KEY[i] == alpha[j]) {
                    alpha[j] = '0';
                    cntr++;
                    table[cntr / 5][cntr % 5] = KEY[i];
                }
            }
        }

        for (int i = 0; i < 25; i++) {
            if (alpha[i] == '0') {
                continue;
            } else {
                cntr++;
                table[cntr / 5][cntr % 5] = alpha[i];
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ENCODE
    private static String encode(String msg, String key) {
        createTable(key);
        msg = trim(msg).toUpperCase();
        char[] msgArr = msg.toCharArray();
        ArrayList<Character> MSG = new ArrayList<>();
        for (Character character : msgArr) {
            MSG.add(character);
        }

        buildDiagram(MSG);

        int count = 0;
        for (Character character : MSG) {
            if (count % 2 == 0)
                System.out.print(" ");
            System.out.print(character);
            count++;
        }
        System.out.println();

        for (int i = 0; i < MSG.size() - 1; i += 2) {
            int r1 = 0, c1 = 0;// 1st letter index
            int r2 = 0, c2 = 0;// 2nd letter index
            boolean flag = false;

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (MSG.get(i) == table[row][col]) {
                        r1 = row;
                        c1 = col;
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }

            flag = false;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (MSG.get(i + 1) == table[row][col]) {
                        r2 = row;
                        c2 = col;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }

            if (c1 == c2) {
                MSG.set(i, table[(r1 + 1) % 5][c1]);
                MSG.set(i + 1, table[(r2 + 1) % 5][c2]);
            } else if (r1 == r2) {
                MSG.set(i, table[r1][(c1 + 1) % 5]);
                MSG.set(i + 1, table[r2][(c2 + 1) % 5]);
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
                MSG.set(i, table[r1][c1]);
                MSG.set(i + 1, table[r2][c2]);
            }
        }

        count = 0;
        for (Character character : MSG) {
            if (count % 2 == 0)
                System.out.print(" ");
            System.out.print(character);
            count++;
        }
        System.out.println();

        // ArrayList to String
        StringBuffer str = new StringBuffer();
        for (Character character : MSG) {
            str.append(character);
        }

        return str.toString();
    }

    private static String decode(String msg) {
        char[] msgArr = msg.toCharArray();
        ArrayList<Character> MSG = new ArrayList<>();
        for (char c : msgArr) {
            MSG.add(c);
        }

        for (int i = 0; i < MSG.size() - 1; i += 2) {
            int r1 = 0, c1 = 0;// 1st letter index
            int r2 = 0, c2 = 0;// 2nd letter index
            boolean flag = false;

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (MSG.get(i) == table[row][col]) {
                        r1 = row;
                        c1 = col;
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }

            flag = false;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (MSG.get(i + 1) == table[row][col]) {
                        r2 = row;
                        c2 = col;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }

            if (c1 == c2) {
                if (r1 - 1 == -1) {
                    MSG.set(i, table[4][c1]);
                    MSG.set(i + 1, table[(r2 - 1) % 5][c2]);
                } else if (r2 - 1 == -1) {
                    MSG.set(i, table[(r1 - 1) % 5][c1]);
                    MSG.set(i + 1, table[4][c2]);
                } else {
                    MSG.set(i, table[(r1 - 1) % 5][c1]);
                    MSG.set(i + 1, table[(r2 - 1) % 5][c2]);
                }
            } else if (r1 == r2) {
                if (c1 - 1 == -1) {
                    MSG.set(i, table[r1][4]);
                    MSG.set(i + 1, table[r2][(c2 - 1) % 5]);
                } else if (c2 - 1 == -1) {
                    MSG.set(i, table[r1][(c1 - 1) % 5]);
                    MSG.set(i + 1, table[r2][4]);
                } else {
                    MSG.set(i, table[r1][(c1 - 1) % 5]);
                    MSG.set(i + 1, table[r2][(c2 - 1) % 5]);
                }
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
                MSG.set(i, table[r1][c1]);
                MSG.set(i + 1, table[r2][c2]);
            }
        }

        int count = 0;
        for (Character character : MSG) {
            if (count % 2 == 0)
                System.out.print(" ");
            System.out.print(character);
            count++;
        }
        System.out.println();

        // ArrayList to String
        StringBuffer str = new StringBuffer();
        for (Character character : MSG) {
            str.append(character);
        }

        return str.toString();
    }

    // Bulid diagram
    private static void buildDiagram(ArrayList<Character> msg) {

        for (int i = 0; i < msg.size() - 1; i++) {
            if (i % 2 == 0 && msg.get(i) == msg.get(i + 1)) {
                msg.add(i + 1, 'X');
            }
        }

        if (msg.size() % 2 != 0) {
            msg.add('X');
        }
    }

    // trim spaces from key and message
    private static String trim(String str) {
        str = str.replaceAll("\\s+", "");
        return str;
    }

    

    public static void main(String[] args) throws IOException {
        try (BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
                Scanner sc = new Scanner(System.in)) {
            System.out.print("Type 'true' - T to J or 'false' - remove Q: ");
            boolean chngItoJ = sc.nextBoolean();
            charArray(chngItoJ);
            System.out.print("Enter the PlainText: ");
            String msg = s.readLine();
            System.out.print("Enter the Key: ");
            String key = s.readLine();
            String encodedMsg = encode(msg, key);
            System.out.println("Encoded Message: " + encodedMsg);
            String decodedMsg = decode(encodedMsg);
            System.out.println("Decoded Message: " + decodedMsg);
        }

    }
}