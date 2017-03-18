package balintbabics.queenproblem;

/**
 * Created by BalintBabics on 2017. 03. 18..
 */

public class QueensLogic {

    private int[] x;

    private QueensLogic(int N) {
        x = new int[N];
    }

    private boolean canPlaceQueen(int row, int column) {
        for (int i = 0; i < row; i++) {
            if (x[i] == column || (i - row) == (x[i] - column) ||(i - row) == (column - x[i])) {
                return false;
            }
        }
        return true;
    }

    private void printQueens(int[] x) {
        int N = x.length;
        for(int aX : x) {
            for(int j = 0; j < N; j++) {
                if(aX == j) {
                    //Queen here
                } else {
                    //Empty cell
                }
            }
            // draw
        }
    }

    private void placeQueens(int row, int n) {
        for (int column = 0; column < n; column++) {
            if (canPlaceQueen(row, column)) {
                x[row] = column;
                if (row == n - 1) {
                    printQueens(x);
                } else {
                    placeQueens(row + 1, n);
                }
            }
        }
    }

    private void executePlacingQueens() {
        placeQueens(0, x.length);
    }

    public static void main(String args[]) {
        QueensLogic Q = new QueensLogic(8);
        Q.executePlacingQueens();

    }
}
