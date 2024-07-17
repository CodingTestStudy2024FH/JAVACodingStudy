class Solution {
    public int solution(String[] board) {
        int oCount = 0;
        int xCount = 0;
        
        // O, X 개수 세기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') oCount++;
                if (board[i].charAt(j) == 'X') xCount++;
            }
        }
        
        // 개수 차이 조건 걸기
        if (oCount < xCount || oCount > xCount + 1) return 0;
        
        boolean oWin = checkWin(board, 'O');
        boolean xWin = checkWin(board, 'X');
        
        // 두 명 동시 승리 불가
        if (oWin && xWin) return 0;
        
        // O 승리 경우, X 보다 하나 더 많아야 함
        if (oWin && oCount != xCount + 1) return 0;
        
        // X 승리 경우, O와 같은 개수여야 함
        if (xWin && oCount != xCount) return 0;
        
        return 1;
    }
    
    private boolean checkWin(String[] board, char player) {
        // 가로, 세로, 대각선에서 승리 조건 확인
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player && board[i].charAt(1) == player && board[i].charAt(2) == player) return true;
            if (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player) return true;
        }
        if (board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player) return true;
        if (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player) return true;
        
        return false;
    }
}
