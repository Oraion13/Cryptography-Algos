package cryptoAlgos.advancedEncryptionStandard;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class AESalgo {
    private static String[][][] KEYGEN = new String[11][4][4];
    // private static String[] KEY = new String[11];

    private static int[] RCON = { 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36 };
    private static int[][] SBOX = {
            { 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76 },
            { 0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0 },
            { 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15 },
            { 0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75 },
            { 0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84 },
            { 0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF },
            { 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8 },
            { 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2 },
            { 0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73 },
            { 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB },
            { 0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79 },
            { 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08 },
            { 0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A },
            { 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E },
            { 0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF },
            { 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16 } };
    private static int[][] INV_SBOX = {
            { 0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB },
            { 0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB },
            { 0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E },
            { 0x08, 0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25 },
            { 0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92 },
            { 0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84 },
            { 0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06 },
            { 0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B },
            { 0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73 },
            { 0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E },
            { 0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B },
            { 0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4 },
            { 0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F },
            { 0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF },
            { 0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61 },
            { 0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D } };

    private static int[][] FIXEDMATRIX1 = { { 0x02, 0x03, 0x01, 0x01 }, { 0x01, 0x02, 0x03, 0x01 },
            { 0x01, 0x01, 0x02, 0x03 }, { 0x03, 0x01, 0x01, 0x02 } };

    private static int[][] FIXEDMATRIX2 = { { 0x0E, 0x0B, 0x0D, 0x09 }, { 0x09, 0x0E, 0x0B, 0x0D },
            { 0x0D, 0x09, 0x0E, 0x0B }, { 0x0B, 0x0D, 0x09, 0x0E } };

    private static int[][] E_TABLE = {
            { 0x01, 0x03, 0x05, 0x0F, 0x11, 0x33, 0x55, 0xFF, 0x1A, 0x2E, 0x72, 0x96, 0xA1, 0xF8, 0x13, 0x35 },
            { 0x5F, 0xE1, 0x38, 0x48, 0xD8, 0x73, 0x95, 0xA4, 0xF7, 0x02, 0x06, 0x0A, 0x1E, 0x22, 0x66, 0xAA },
            { 0xE5, 0x34, 0x5C, 0xE4, 0x37, 0x59, 0xEB, 0x26, 0x6A, 0xBE, 0xD9, 0x70, 0x90, 0xAB, 0xE6, 0x31 },
            { 0x53, 0xF5, 0x04, 0x0C, 0x14, 0x3C, 0x44, 0xCC, 0x4F, 0xD1, 0x68, 0xB8, 0xD3, 0x6E, 0xB2, 0xCD },
            { 0x4C, 0xD4, 0x67, 0xA9, 0xE0, 0x3B, 0x4D, 0xD7, 0x62, 0xA6, 0xF1, 0x08, 0x18, 0x28, 0x78, 0x88 },
            { 0x83, 0x9E, 0xB9, 0xD0, 0x6B, 0xBD, 0xDC, 0x7F, 0x81, 0x98, 0xB3, 0xCE, 0x49, 0xDB, 0x76, 0x9A },
            { 0xB5, 0xC4, 0x57, 0xF9, 0x10, 0x30, 0x50, 0xF0, 0x0B, 0x1D, 0x27, 0x69, 0xBB, 0xD6, 0x61, 0xA3 },
            { 0xFE, 0x19, 0x2B, 0x7D, 0x87, 0x92, 0xAD, 0xEC, 0x2F, 0x71, 0x93, 0xAE, 0xE9, 0x20, 0x60, 0xA0 },
            { 0xFB, 0x16, 0x3A, 0x4E, 0xD2, 0x6D, 0xB7, 0xC2, 0x5D, 0xE7, 0x32, 0x56, 0xFA, 0x15, 0x3F, 0x41 },
            { 0xC3, 0x5E, 0xE2, 0x3D, 0x47, 0xC9, 0x40, 0xC0, 0x5B, 0xED, 0x2C, 0x74, 0x9C, 0xBF, 0xDA, 0x75 },
            { 0x9F, 0xBA, 0xD5, 0x64, 0xAC, 0xEF, 0x2A, 0x7E, 0x82, 0x9D, 0xBC, 0xDF, 0x7A, 0x8E, 0x89, 0x80 },
            { 0x9B, 0xB6, 0xC1, 0x58, 0xE8, 0x23, 0x65, 0xAF, 0xEA, 0x25, 0x6F, 0xB1, 0xC8, 0x43, 0xC5, 0x54 },
            { 0xFC, 0x1F, 0x21, 0x63, 0xA5, 0xF4, 0x07, 0x09, 0x1B, 0x2D, 0x77, 0x99, 0xB0, 0xCB, 0x46, 0xCA },
            { 0x45, 0xCF, 0x4A, 0xDE, 0x79, 0x8B, 0x86, 0x91, 0xA8, 0xE3, 0x3E, 0x42, 0xC6, 0x51, 0xF3, 0x0E },
            { 0x12, 0x36, 0x5A, 0xEE, 0x29, 0x7B, 0x8D, 0x8C, 0x8F, 0x8A, 0x85, 0x94, 0xA7, 0xF2, 0x0D, 0x17 },
            { 0x39, 0x4B, 0xDD, 0x7C, 0x84, 0x97, 0xA2, 0xFD, 0x1C, 0x24, 0x6C, 0xB4, 0xC7, 0x52, 0xF6, 0x01 }, };
    private static int[][] L_TABLE = {
            { 0x00, 0x00, 0x19, 0x01, 0x32, 0x02, 0x1A, 0xC6, 0x4B, 0xC7, 0x1B, 0x68, 0x33, 0xEE, 0xDF, 0x03 },
            { 0x64, 0x04, 0xE0, 0x0E, 0x34, 0x8D, 0x81, 0xEF, 0x4C, 0x71, 0x08, 0xC8, 0xF8, 0x69, 0x1C, 0xC1 },
            { 0x7D, 0xC2, 0x1D, 0xB5, 0xF9, 0xB9, 0x27, 0x6A, 0x4D, 0xE4, 0xA6, 0x72, 0x9A, 0xC9, 0x09, 0x78 },
            { 0x65, 0x2F, 0x8A, 0x05, 0x21, 0x0F, 0xE1, 0x24, 0x12, 0xF0, 0x82, 0x45, 0x35, 0x93, 0xDA, 0x8E },
            { 0x96, 0x8F, 0xDB, 0xBD, 0x36, 0xD0, 0xCE, 0x94, 0x13, 0x5C, 0xD2, 0xF1, 0x40, 0x46, 0x83, 0x38 },
            { 0x66, 0xDD, 0xFD, 0x30, 0xBF, 0x06, 0x8B, 0x62, 0xB3, 0x25, 0xE2, 0x98, 0x22, 0x88, 0x91, 0x10 },
            { 0x7E, 0x6E, 0x48, 0xC3, 0xA3, 0xB6, 0x1E, 0x42, 0x3A, 0x6B, 0x28, 0x54, 0xFA, 0x85, 0x3D, 0xBA },
            { 0x2B, 0x79, 0x0A, 0x15, 0x9B, 0x9F, 0x5E, 0xCA, 0x4E, 0xD4, 0xAC, 0xE5, 0xF3, 0x73, 0xA7, 0x57 },
            { 0xAF, 0x58, 0xA8, 0x50, 0xF4, 0xEA, 0xD6, 0x74, 0x4F, 0xAE, 0xE9, 0xD5, 0xE7, 0xE6, 0xAD, 0xE8 },
            { 0x2C, 0xD7, 0x75, 0x7A, 0xEB, 0x16, 0x0B, 0xF5, 0x59, 0xCB, 0x5F, 0xB0, 0x9C, 0xA9, 0x51, 0xA0 },
            { 0x7F, 0x0C, 0xF6, 0x6F, 0x17, 0xC4, 0x49, 0xEC, 0xD8, 0x43, 0x1F, 0x2D, 0xA4, 0x76, 0x7B, 0xB7 },
            { 0xCC, 0xBB, 0x3E, 0x5A, 0xFB, 0x60, 0xB1, 0x86, 0x3B, 0x52, 0xA1, 0x6C, 0xAA, 0x55, 0x29, 0x9D },
            { 0x97, 0xB2, 0x87, 0x90, 0x61, 0xBE, 0xDC, 0xFC, 0xBC, 0x95, 0xCF, 0xCD, 0x37, 0x3F, 0x5B, 0xD1 },
            { 0x53, 0x39, 0x84, 0x3C, 0x41, 0xA2, 0x6D, 0x47, 0x14, 0x2A, 0x9E, 0x5D, 0x56, 0xF2, 0xD3, 0xAB },
            { 0x44, 0x11, 0x92, 0xD9, 0x23, 0x20, 0x2E, 0x89, 0xB4, 0x7C, 0xB8, 0x26, 0x77, 0x99, 0xE3, 0xA5 },
            { 0x67, 0x4A, 0xED, 0xDE, 0xC5, 0x31, 0xFE, 0x18, 0x0D, 0x63, 0x8C, 0x80, 0xC0, 0xF7, 0x70, 0x07 }, };

    // Mix columns operation
    private static String[][] matrixMul(String[][] MSG, int[][] FIXED) {
        int[][] storage = new int[MSG.length][MSG[0].length];

        // Traverse the matrix(i, j, k)
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[0].length; j++) {
                for (int k = 0; k < MSG[0].length; k++) {
                    // int msg = Integer.parseInt(MSG[k][j], 16);
                    // storage[i][j] ^= ((FIXEDMATRIX[i][k] * msg) % 0x11b);

                    // if the matrix's element(4 x 1 matrix) is 1 then no need to multiply,
                    // because(1 x n = n)
                    if (FIXED[i][k] != 0x01) {
                        // At L_TABLE[0][0] = null, so if MSG[0][0] then skip.
                        if ((Integer.parseInt(MSG[k][j].substring(0, 1), 16) == 0)
                                && (Integer.parseInt(MSG[k][j].substring(1), 16) == 0))
                            continue;
                        String fstr = "0" + Integer.toHexString(FIXED[i][k]);// Converting FixedMatrix element to string
                                                                             // for later finding the index in L-Table
                        // System.out.println("Fixed Matrix String: " + fstr);
                        // Finding the result => L_TABLE
                        int rslt = L_TABLE[Integer.parseInt(fstr.substring(0, 1), 16)][Integer
                                .parseInt(fstr.substring(1), 16)]// FixedMatrix Element for Index in L_TABLE
                                + L_TABLE[Integer.parseInt(MSG[k][j].substring(0, 1), 16)][Integer
                                        .parseInt(MSG[k][j].substring(1), 16)];// MSGMatrix Element for Index in L
                        // if result greater than FF then decrease using FF(Like mod)
                        if (rslt > 0xFF) {
                            rslt -= 0xFF;
                        }

                        String rsltStr = Integer.toHexString(rslt);
                        if (rsltStr.length() != 2) {
                            rsltStr = "0" + rsltStr;
                        }
                        // System.out.println("Result String after L table: " + rsltStr);
                        // Skip E_TABLE[0][0]
                        // if(Integer.parseInt(rsltStr.substring(0, 1), 16) == 0 &&
                        // Integer.parseInt(rsltStr.substring(1), 16) == 0){
                        // continue;
                        // }

                        // Final result will be taken from E_TABLE
                        storage[i][j] ^= E_TABLE[Integer.parseInt(rsltStr.substring(0, 1), 16)][Integer
                                .parseInt(rsltStr.substring(1), 16)];// Finding the resultant index using E-Table
                        continue;
                    }
                    storage[i][j] ^= Integer.parseInt(MSG[k][j], 16);// if FixedMatrix element is 0x01
                }
                // storage[i][j] = storage[i][j] % 0x11b;//x8 + x4 + x3 + x + 1
                // storage[i][j] = Integer.toHexString(storage[i][j]);
            }
        }

        // converting to hex string matrix
        String[][] msgMat = new String[MSG.length][MSG[0].length];
        for (int i = 0; i < msgMat.length; i++) {
            for (int j = 0; j < msgMat.length; j++) {
                msgMat[i][j] = Integer.toHexString(storage[i][j]);
                if (msgMat[i][j].length() != 2) {
                    msgMat[i][j] = "0" + msgMat[i][j];
                }
                System.out.print(msgMat[i][j] + " ");
            }
            System.out.println();
        }
        return msgMat;
    }

    // shift rows by left for encoding and right for decoding
    private static String rowShifter(String str, int i) {
        return str.substring(i, str.length()) + str.substring(0, i);
    }

    private static String enCoder(String[][] MSG, int count, String option) {

        if (((count > 10) && (option == "ENCODE")) || (count < 0) && (option == "DECODE")) {
            String cipher = "";
            for (int i = 0; i < MSG.length; i++) {
                for (int j = 0; j < MSG.length; j++) {
                    cipher += MSG[j][i];
                }
            }
            return cipher;
        }
        // Byte substitution
        System.out.println("\nSubstitution matrix at round: " + (count));
        for (int i = 0; i < MSG.length; i++) {
            for (int j = 0; j < MSG.length; j++) {
                int row = Integer.parseInt(MSG[i][j].substring(0, 1), 16);

                int col = Integer.parseInt(MSG[i][j].substring(1), 16);

                MSG[i][j] = Integer.toHexString((option == "ENCODE") ? SBOX[row][col] : INV_SBOX[row][col]);
                if (MSG[i][j].length() != 2) {
                    MSG[i][j] = "0" + MSG[i][j];
                }
                System.out.print(MSG[i][j] + " ");
            }
            System.out.println();
        }

        // Shift rows
        System.out.println("\nShifted row matrix at round: " + (count));
        for (int i = 1; i < MSG.length; i++) {
            String str = "";
            for (int j = 0; j < MSG.length; j++) {
                str += MSG[i][j];
            }
            System.out.printf("\nBefore shifting rows at round: %d - %s\n", (count), str);

            str = (option == "ENCODE") ? rowShifter(str, i * 2) : rowShifter(str, str.length() - (i * 2));
            System.out.printf("\nAfter shifting rows at round: %d - %s\n", (count), str);

            short start = 0, end = 2;
            for (int j = 0; j < MSG.length; j++) {
                MSG[i][j] = str.substring(start, end);
                System.out.print(MSG[i][j] + " ");
                start = end;
                end += 2;
            }
            System.out.println();
        }

        // Mix columns
        if ((count != 10) && (option == "ENCODE") || ((count != 0) && (option == "DECODE"))) {
            System.out.println("\nMix column matrix at round: " + (count));
            if (option == "DECODE") {
                MSG = matrixMul(MSG, FIXEDMATRIX2);
            } else {
                MSG = matrixMul(MSG, FIXEDMATRIX1);
            }
        }

        // Add round key
        System.out.println("\nCipher matrix at round: " + (count));
        for (int i = 0; i < MSG.length; i++) {
            for (int j = 0; j < MSG.length; j++) {
                MSG[i][j] = Integer.toHexString(Integer.parseInt(MSG[i][j], 16)
                        ^ Integer.parseInt(KEYGEN[(option == "ENCODE") ? (count) : (count)][i][j], 16));
                if (MSG[i][j].length() != 2) {
                    MSG[i][j] = "0" + MSG[i][j];
                }
                System.out.print(MSG[i][j] + " ");
            }
            System.out.println();
        }
        if (option == "ENCODE") {
            return enCoder(MSG, ++count, "ENCODE");
        } else {
            return enCoder(MSG, --count, "DECODE");
        }

    }

    private static String deCoder(String[][] MSG, int count) {

        if (count < 0) {
            String cipher = "";
            for (int i = 0; i < MSG.length; i++) {
                for (int j = 0; j < MSG.length; j++) {
                    cipher += MSG[j][i];
                }
            }
            return cipher;
        }

        // Byte substitution
        System.out.println("\nSubstitution matrix at round: " + (count));
        for (int i = 0; i < MSG.length; i++) {
            for (int j = 0; j < MSG.length; j++) {
                int row = Integer.parseInt(MSG[i][j].substring(0, 1), 16);

                int col = Integer.parseInt(MSG[i][j].substring(1), 16);

                MSG[i][j] = Integer.toHexString(INV_SBOX[row][col]);
                if (MSG[i][j].length() != 2) {
                    MSG[i][j] = "0" + MSG[i][j];
                }
                System.out.print(MSG[i][j] + " ");
            }
            System.out.println();
        }

        // Shift rows
        System.out.println("\nShifted row matrix at round: " + (count));
        for (int i = 1; i < MSG.length; i++) {
            String str = "";
            for (int j = 0; j < MSG.length; j++) {
                str += MSG[i][j];
            }
            System.out.printf("\nBefore shifting rows at round: %d - %s\n", (count), str);

            str = rowShifter(str, str.length() - (i * 2));
            System.out.printf("\nAfter shifting rows at round: %d - %s\n", (count), str);

            short start = 0, end = 2;
            for (int j = 0; j < MSG.length; j++) {
                MSG[i][j] = str.substring(start, end);
                System.out.print(MSG[i][j] + " ");
                start = end;
                end += 2;
            }
            System.out.println();
        }

        // Add round key
        System.out.println("\nCipher matrix at round: " + (count));
        for (int i = 0; i < MSG.length; i++) {
            for (int j = 0; j < MSG.length; j++) {
                MSG[i][j] = Integer
                        .toHexString(Integer.parseInt(MSG[i][j], 16) ^ Integer.parseInt(KEYGEN[count][i][j], 16));
                if (MSG[i][j].length() != 2) {
                    MSG[i][j] = "0" + MSG[i][j];
                }
                System.out.print(MSG[i][j] + " ");
            }
            System.out.println();
        }

        // Mix columns
        if (count != 0) {
            System.out.println("\nMix column matrix at round: " + (count));
            MSG = matrixMul(MSG, FIXEDMATRIX2);
        }
        return deCoder(MSG, --count);
    }

    private static String keyGenerator(String[][][] KEYGEN, int count) {
        if (count > 9) {
            return "Gotcha";
        }
        String[] rotNsubWords = new String[4];

        //rot word and sub word, loop starts form 2 nd element  because 1 shift element in rot word will be left sfited(comes last)
        for (int i = 1; i < 4; i++) {
            //For Eg.: Element = a2 => row = a
            int row = Integer.parseInt(KEYGEN[count][i][3].substring(0, 1), 16);
            System.out.print("  Rot word 1st half: " + KEYGEN[count][i][3].substring(0, 1));
            //For Eg.: Element = a2 => col = 2
            int col = Integer.parseInt(KEYGEN[count][i][3].substring(1), 16);
            System.out.print("  Rot word 2nd half: " + KEYGEN[count][i][3].substring(1));

            //converting the int value to hex
            rotNsubWords[i - 1] = Integer.toHexString(SBOX[row][col]);
            if (rotNsubWords[i - 1].length() != 2) {
                rotNsubWords[i - 1] = "0" + rotNsubWords[i - 1];
            }
        }
        //take the first element and shift left(down)
        rotNsubWords[3] = Integer.toHexString(SBOX[Integer.parseInt(KEYGEN[count][0][3].substring(0, 1), 16)][Integer
                .parseInt(KEYGEN[count][0][3].substring(1), 16)]);
        //Xoring sub word with round constant
        rotNsubWords[0] = Integer.toHexString(
                Integer.parseInt(rotNsubWords[0], 16) ^ Integer.parseInt(Integer.toHexString(RCON[count]), 16));
        if (rotNsubWords[0].length() != 2) {
            rotNsubWords[0] = "0" + rotNsubWords[0];
        }

        //printing word 3
        System.out.println("G(W3) in Round: " + count);
        for (String st : rotNsubWords) {
            System.out.printf(st + " ");
        }
        System.out.println();

        //performing G(w3) ^ W0
        System.out.printf("P[0] - column in Round %d\n", count);
        for (int i = 0; i < 4; i++) {
            KEYGEN[count + 1][i][0] = Integer
                    .toHexString(Integer.parseInt(KEYGEN[count][i][0], 16) ^ Integer.parseInt(rotNsubWords[i], 16));
            if (KEYGEN[count + 1][i][0].length() != 2) {
                KEYGEN[count + 1][i][0] = "0" + KEYGEN[count + 1][i][0];
            }
            System.out.print(KEYGEN[count + 1][i][0] + " ");
        }
        System.out.println();

        //calculating other words Eg.: W0 ^ W4 = W5, W2 ^ W5 = W6............
        for (int i = 1; i < 4; i++) {
            System.out.printf("P[%d] - column in Round %d\n", i, count);
            for (int j = 0; j < 4; j++) {
                KEYGEN[count + 1][j][i] = Integer.toHexString(
                        Integer.parseInt(KEYGEN[count][j][i], 16) ^ Integer.parseInt(KEYGEN[count + 1][j][i - 1], 16));
                if (KEYGEN[count + 1][j][i].length() != 2) {
                    KEYGEN[count + 1][j][i] = "0" + KEYGEN[count + 1][j][i];
                }
                System.out.print(KEYGEN[count + 1][j][i] + " ");
            }
            System.out.println();
        }

        return keyGenerator(KEYGEN, ++count);
    }

    //Converting hex string to matrix form and add round key for the first round
    private static String[][] firstMat(String keyHex, String option, String typ) {
        String[][] arr = new String[4][4];
        short start = 0, end = 2;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j][i] = keyHex.substring(start, end);
                // System.out.print(arr[j][i]);
                start = end;
                end += 2;
            }
            // System.out.println();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j] + " ");
                if (option == "KEY") {
                    KEYGEN[0][i][j] = arr[i][j];
                }
            }
            System.out.println();
        }

        if (option == "MESSAGE") {
            System.out.println("\nRound 0 Matrix: ");
            int in = (typ == "EN") ? 0 : 10;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    arr[i][j] = Integer
                            .toHexString(Integer.parseInt(KEYGEN[in][i][j], 16) ^ Integer.parseInt(arr[i][j], 16));
                    if (arr[i][j].length() != 2) {
                        arr[i][j] = "0" + arr[i][j];
                    }
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }

        return arr;
    }

    //String to hex
    private static String toHex(String arg) throws UnsupportedEncodingException {
        return String.format("%x", new BigInteger(1, arg.getBytes("UTF-8")));
    }

    // private static String toBin(String s, int base) {
    // String Bi = new BigInteger(s, base).toString(2);
    // while (true) {
    // if (Bi.length() == 128) {
    // break;
    // }
    // Bi = '0' + Bi;
    // }
    // return Bi;
    // }
    public static void main(String[] args) throws UnsupportedEncodingException {
        String key = "Thats my Kung Fu";
        String keyHex = toHex(key);// Thats my Kung Fu
        System.out.println("Hex key: " + keyHex);// 736174697368636a6973626f72696e67

        // String keyBin = toBin(keyHex, 16);
        // System.out.println(keyBin);

        firstMat(keyHex, "KEY", "KEY");

        // for(int i = 0; i < SBOX.length; i++){
        // for(int j = 0; j < SBOX.length; j++){
        // System.out.print(Integer.toHexString(SBOX[i][j]) + " ");
        // }
        // System.out.println();
        // }
        keyGenerator(KEYGEN, 0);

        for (int i = 0; i < KEYGEN.length; i++) {
            System.out.println("Key: " + (i + 1));
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(KEYGEN[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        String message = "Two One Nine Two";
        String msg = toHex(message);
        System.out.println("Hex msg: " + msg);// 54776f204f6e65204e696e652054776f

        String[][] MSG = firstMat(msg, "MESSAGE", "EN");
        String cipher = enCoder(MSG, 1, "ENCODE");// 29c3505f571420f6402299b31a02d73a
        System.out.println("\n******Encrypted Hex: " + cipher + " ******\n");

        String[][] CIPHER = firstMat(cipher, "MESSAGE", "DE");
        String decoded = deCoder(CIPHER, 9);
        System.out.println("\nKey: \t      : " + key);
        System.out.println("\nKey Hex\t      : " + keyHex);
        System.out.println("\nMessage\t      : " + message);
        System.out.println("\nMessage Hex   : " + msg);
        System.out.println("\nEncrypted Hex : " + cipher + "\n");
        System.out.println("Encrypted Text: " + hexToAscii(cipher) + "\n");
        System.out.println("Decrypted Hex : " + decoded + "\n");
        System.out.println("Decrypted Text: " + hexToAscii(decoded));
    }

    //Hex to String
    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }
}
