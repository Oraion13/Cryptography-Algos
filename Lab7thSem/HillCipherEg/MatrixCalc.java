package Lab7thSem.HillCipherEg;

import java.util.ArrayList;
import java.util.Iterator;

public class MatrixCalc {
    public static int determinent(int[][] a) {
        if (a[0].length == 2) {
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        } else {
            ArrayList<Integer> arr = new ArrayList<>();
            int index = 0;
            for (int e : a[0]) {
                if ((index++ % 2) == 0) {
                    arr.add(e);
                } else {
                    arr.add(e * -1);
                }
            }

            int i = 0;
            for (Integer e : arr) {
                arr.set(i, e *= determinent(subMatrix(a, 0, i++)));
            }  

            int sum = 0;
            Iterator<Integer> itr = arr.iterator();

            while (itr.hasNext()) {
                sum += itr.next();
            }

            return sum;
        }
    }

    private static int[][] subMatrix(int[][] src, int x, int y) {
        int[][] des = new int[src[0].length - 1][src[0].length - 1];

        int p = 0;
        for (int i = 0; i < src[0].length; i++) {
            if (i == x)
                continue;
            int q = 0;
            for (int j = 0; j < src[0].length; j++) {
                if (j == y)
                    continue;

                des[p][q] = src[i][j];
                ++q;
            }
            ++p;
        }
        /*System.out.println("submatrix: ");
        for(int i = 0; i < des[0].length; i++){   
            for(int j = 0; j < des[0].length; j++){
                System.out.print(des[i][j] + " ");
            }
            System.out.println();
        }*/

        return des;
    }

    public static int[][] adjoint(int[][] a, int alphaLength){
        int len = a[0].length;
        int adjMatrix[][] = new int[len][len];

        if(a.length == 2){
            int[][] smallMatrix = {{a[1][1], a[0][1]*-1}, {a[1][0]*-1, a[0][0]}};
            return smallMatrix;
        }else {
            for(int i = 0; i < len; i++){
                for(int j = 0; j < len; j++) {
                    adjMatrix[i][j] = determinent(subMatrix(a, i, j));
                    //System.out.println("Determinent of " + i+j + " :"+adjMatrix[i][j]);
                    if((i+j) % 2 != 0){ //missed braces for i,j THATS A BIIIIIIIIIIIIGGGGGGGGGGGG MISTAKE
                        adjMatrix[i][j] *= -1;
                    }
                    adjMatrix[i][j] %= alphaLength; // You should come first
                    if(adjMatrix[i][j] < 0){
                        //System.out.println("Got a negative for "+ i+j + ": " + adjMatrix[i][j]);
                        adjMatrix[i][j] = (adjMatrix[i][j] + alphaLength) % alphaLength;
                        //System.out.println("solved negative for "+ i+j + ": " + adjMatrix[i][j]);
                        continue;
                    }
                    //System.out.println("solved for "+ i+j + ": " + adjMatrix[i][j]);
                }
            }
        }
        adjMatrix = transpose(adjMatrix);
        return adjMatrix;
    }

    public static int[][] transpose(int[][] a){
        int len = a[0].length;
        int transMatrix[][] = new int[len][len];

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                transMatrix[i][j] = a[j][i];
            }
        }
        return transMatrix;
    }

    public static int[][] invMatrix(int[][] a, int alphaLength){
        int len = a[0].length;
        int det = determinent(a);
        //System.out.println("Determinent of matrix: " + det);
        a = adjoint(a, alphaLength);
        
        det %= alphaLength;
        if(det < 0){
            det += alphaLength;
        }
        
        //System.out.println("Determinent of matrix after mod: " + det);
        int mod = 1;
        while(mod < det){
            if((det*mod) % alphaLength == 1){
                det = mod;
            }
            ++mod;
        }

        //System.out.println("Mod: " + det);

        /*for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }*/

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                a[i][j] = (det * a[i][j]) % alphaLength;
            }
        }

        //Inversion of the matrix
        /*for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }*/
        return a;
    }

    public static int[] matrixMultiplication(int[] MSG, int[][] KEY, int alphaLength){
        int sum, k = 0,z = 0;
        int[] storage = new int[MSG.length];
        
        while(k < MSG.length){
            for(int i = 0; i < KEY[0].length; i++){
                sum = 0;
                for(int j = 0; j < KEY[0].length; j++){
                    sum += MSG[k + j]*KEY[j][i];
                    //System.out.print("K= " + k + " J= " + j + " i= " + i + " " + MSG[k + j] + " * " + KEY[j][i] + " ");
                }
                //System.out.print(" = " + sum + " Storage= ");
                //CAUTION: Do not update the list before calculation
                //MSG[k] = sum % alphaLength;
                storage[z] = sum % alphaLength;
                //System.out.println(storage[z]);
                ++z;
            }
             k += KEY[0].length;
        }
        return storage;
    }
}
