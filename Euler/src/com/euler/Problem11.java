package com.euler;
/**

https://projecteuler.net/problem=11

Largest product in a grid


Problem 11 
In the 20×20 grid below, four numbers along a diagonal line have been marked in red.

08 02 22 97 38 15 00 40  00   75  04  05 07 78 52 12 50 77 91 08
49 49 99 40 17 81 18 57  60   87  17  40 98 43 69 48 04 56 62 00
81 49 31 73 55 79 14 29  93   71  40  67 53 88 30 03 49 13 36 65
52 70 95 23 04 60 11 42  69   24  68  56 01 32 56 71 37 02 36 91
22 31 16 71 51 67 63 89  41   92  36  54 22 40 40 28 66 33 13 80
24 47 32 60 99 03 45 02  44   75  33  53 78 36 84 20 35 17 12 50
32 98 81 28 64 23 67 10 @26   38  40  67 59 54 70 66 18 38 64 70
67 26 20 68 02 62 12 20  95  @63  94  39 63 08 40 91 66 49 94 21
24 55 58 05 66 73 99 26  97   17 @78  78 96 83 14 88 34 89 63 72
21 36 23 09 75 00 76 44  20   45  35 @14 00 61 33 97 34 31 33 95
78 17 53 28 22 75 31 67  15   94  03  80 04 62 16 14 09 53 56 92
16 39 05 42 96 35 31 47  55   58  88  24 00 17 54 24 36 29 85 57
86 56 00 48 35 71 89 07  05   44  44  37 44 60 21 58 51 54 17 58
19 80 81 68 05 94 47 69  28   73  92  13 86 52 17 77 04 89 55 40
04 52 08 83 97 35 99 16  07   97  57  32 16 26 26 79 33 27 98 66
88 36 68 87 57 62 20 72  03   46  33  67 46 55 12 32 63 93 53 69
04 42 16 73 38 25 39 11  24   94  72  18 08 46 29 32 40 62 76 36
20 69 36 41 72 30 23 88  34   62  99  69 82 67 59 85 74 04 36 16
20 73 35 29 78 31 90 01  74   31  49  71 48 86 81 16 23 57 05 54
01 70 54 71 83 51 54 69  16   92  33  48 61 43 52 01 89 19 67 48

The product of these numbers is 26 × 63 × 78 × 14 = 1788696.

What is the greatest product of four adjacent numbers in the
 same direction (up, down, left, right, or diagonally) in the 20×20 grid?
 */


public class Problem11 {
    private int[][] array = new int[20][20];
    int a, b, c, d;

    public static void main(String[] args) {
        Problem11 problem11 = new Problem11();
        problem11.populate();
        problem11.findProduct(4);
    }

    private void populate() {

        String s = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08 "
                 + "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00 "
                 + "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65 "
                 + "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91 "
                 + "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80 "
                 + "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50 "
                 + "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70 "
                 + "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21 "
                 + "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72 "
                 + "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95 "
                 + "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92 "
                 + "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57 "
                 + "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58 "
                 + "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40 "
                 + "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66 "
                 + "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69 "
                 + "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36 "
                 + "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16 "
                 + "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54 "
                 + "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";
        String[] tokens = s.split(" ");
        int tokenIndex = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                array[i][j] = Integer.parseInt(tokens[tokenIndex]);
                tokenIndex++;
            }
        }
    }

    private void findProduct(int adjacentNumbers) {
        // int productCount = 0;
        // adjacentNumbers
        // i =0;j=0
        // i = 0 /j = 0, 1, 2, 3
        // i = 0, 1, 2, 3 /j = 0
        // 00, 11, 22, 33
        int maxProduct = 1;
        maxProduct = maxHorizontalVerticalProducts(adjacentNumbers, maxProduct, "horizontal");
        maxProduct = maxHorizontalVerticalProducts(adjacentNumbers, maxProduct, "vertical");
        
        // process diagonal left to right
        int iStart = 0;
        int jStart = 0;
        int diagonalLimit = 16;
        maxProduct = processDiagonalLtoR(maxProduct, iStart, jStart, diagonalLimit, adjacentNumbers,
                "verticalIndexDiagonal");
        iStart = 0;
        jStart = 1;
        maxProduct = processDiagonalLtoR(maxProduct, iStart, jStart, diagonalLimit, adjacentNumbers,
                "horizontalIndexDiagonal");
        
        
        // process diagonal right to left
        iStart = 0;
        jStart = 19;
        diagonalLimit = 3;
        maxProduct = processDiagonalRtoL(maxProduct, iStart, jStart, diagonalLimit, adjacentNumbers,
                "verticalIndexDiagonal");
        iStart = 0;
        jStart = 18;
        diagonalLimit = 3;
        maxProduct = processDiagonalRtoL(maxProduct, iStart, jStart, diagonalLimit, adjacentNumbers,
                "horizontalIndexDiagonal");
        System.out.println(maxProduct);
        System.out.println(a + " " + b + " " + c + " " + d);
    }

    private int processDiagonalLtoR(int maxProduct, int iStart, int jStart, int diagonalLimit, int adjacentNumbers,
            String direction) {
        int ind = 0;
        int init = 0;
        int startIndex = 0;
        if (direction.equals("verticalIndexDiagonal")) {
            startIndex = iStart;
        } else if (direction.equals("horizontalIndexDiagonal")) {
            startIndex = jStart;
        }
        while (startIndex <= diagonalLimit) {
            System.out.println("starting number = " + array[iStart][jStart]);
            while (startIndex <= diagonalLimit) {
                int iLimit = iStart + adjacentNumbers;
                int jLimit = jStart + adjacentNumbers;
                int i = iStart, j = jStart;
                int product = 1;
                for (; i < iLimit && j < jLimit; i++, j++) {
                    int no = array[i][j];
                    product = product * no;
                }
                if (maxProduct < product) {
                    a = array[i-1][j-1];
                    b = array[i-2][j-2];
                    c = array[i-3][j-3];
                    d = array[i-4][j-4];
                    maxProduct = product;
                }
                iStart++;
                jStart++;
                if (direction.equals("verticalIndexDiagonal")) {
                    startIndex = iStart;
                } else if (direction.equals("horizontalIndexDiagonal")) {
                    startIndex = jStart;
                }
            }
            ind++;
            if (direction.equals("verticalIndexDiagonal")) {
                iStart = init + ind; // Left diagonal
                startIndex = iStart;
                jStart = init;
            } else if (direction.equals("horizontalIndexDiagonal")) {
                jStart = init + ind; // right diagonal
                startIndex = jStart;
                iStart = init;
            }
        }
        return maxProduct;
    }

    private int processDiagonalRtoL(int maxProduct, int iStart, int jStart, int diagonalLimit, int adjacentNumbers,
            String direction) {
        int ind = 0;
        int init = 0;
        if (direction.equals("horizontalIndexDiagonal")) {
            ind = 0;
            init = 18;
        } else if (direction.equals("verticalIndexDiagonal")) {
            ind = 19;
            init = 19;
        } 
        
        int startIndex = 0;
        startIndex = jStart;
        while (startIndex >= diagonalLimit) {
            System.out.println("starting number = " + array[iStart][jStart]);
            while (startIndex >= diagonalLimit) {
                int iLimit = iStart + adjacentNumbers;
                int jLimit = jStart - adjacentNumbers;
                int i = iStart, j = jStart;
                int product = 1;
                
                for (; i < iLimit && j > jLimit; i++, j--) {
                    int no = array[i][j];
                    product = product * no;
                }
                if (maxProduct < product) {
                    a = array[i-1][j+1];
                    b = array[i-2][j+2];
                    c = array[i-3][j+3];
                    d = array[i-4][j+4];
                    maxProduct = product;
                }
                iStart++;
                jStart--;
                startIndex = jStart;
            }
            if (direction.equals("horizontalIndexDiagonal")) {
                ind++;
                iStart = 0;
                jStart = init - ind;
                // diagonal limit is same?
            } else if (direction.equals("verticalIndexDiagonal")) {
                diagonalLimit++;
                ind--;
                jStart = init;
                iStart = init - ind;
            }
            startIndex = jStart;
           
        }
        return maxProduct;
    }
    // horizontal and vertical products
    private int maxHorizontalVerticalProducts(int adjacentNumbers, int maxProduct, String flag) {
        int start = 0;
        int limit = start + adjacentNumbers;
        while (start <= 19) {
            // every row
            int i = start;
            int g = 0;
            limit = g + adjacentNumbers;
            while (g <= 16) {
                int product = 1;
                // group of 4 in each row
                for (int j = g; j < limit; j++) {
                    int no = 1;
                    if (flag.equalsIgnoreCase("horizontal")) {
                        no = array[i][j];
                    } else if (flag.equalsIgnoreCase("vertical")) {
                        no = array[j][i];
                    }
                    product = product * no;
                }
                if (maxProduct < product) {
                    if (flag.equalsIgnoreCase("horizontal")) {
                        a = array[i][g];
                        b = array[i][g+1];
                        b = array[i][g+2];
                        b = array[i][g+3];
                    } else if (flag.equalsIgnoreCase("vertical")) {
                        a = array[g][i];
                        b = array[g+1][i];
                        b = array[g+2][i];
                        b = array[g+3][i];
                    }
                    maxProduct = product;
                }
                g++;
                limit = g + adjacentNumbers;
            }
            start++;
        }
        return maxProduct;
    }

}
