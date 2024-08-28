package org.example.backtracking;

public class NQueens extends CompleteKaryTreeTemplate {

    public NQueens(int n) {
        super(n, n);
    }

    @Override
    boolean whetherTheConstraintConditionsAreMet(int level, int[] vector) {
        for (int i = 0; i < level; i++) {
            if (vector[level] == vector[i]) {
                return false;
            }


            int deltaX = vector[level] - vector[i];
            int deltaY = level - i;

            if ((deltaY / (float) deltaX) == 1) {
                return false;
            }

            if ((deltaY / (float) deltaX) == -1) {
                return false;
            }
        }

        return true;
    }

}
