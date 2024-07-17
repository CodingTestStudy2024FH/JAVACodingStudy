class Solution {
    public int solution(String[] board) {
        int ocnt = 0;
        int xcnt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') ocnt++;
                if (board[i].charAt(j) == 'X') xcnt++;
            }
        }

        if (ocnt > xcnt + 1 || ocnt < xcnt) return 0;

        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');

        if (oWin && xWin) return 0;

        // O가 승리 시 X보다 하나 더 많아야 함
        if (oWin && ocnt != xcnt + 1) return 0;

        // X가 승리 시 O와 숫자가 같아야 함
        if (xWin && ocnt != xcnt) return 0;

        return 1;
    }

    boolean isWin(String[] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player && board[i].charAt(1) == player && board[i].charAt(2) == player) return true;
            if (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player) return true;
        }
        if (board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player) return true;
        if (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player) return true;

        return false;
    }
}
