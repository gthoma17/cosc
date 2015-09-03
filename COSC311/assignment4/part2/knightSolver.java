public class knightSolver
{
    boolean solved;
    int rows;
    int cols;
    int startRow;
    int startCol;
    int[][] board;
    
    public knightSolver(int rows, int cols, int startRow, int startCol){
        board = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.startRow = startRow;
        this.startCol = startCol;
        this.solved = false;
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                board[i][j] = 0;
            }
        }
    }
    public void solve(){
        solve(startRow, startCol, 1);
    }
    public void solve(int row, int col, int currNum){
        board[row][col] = currNum;
        //check if we're done
        if(isSolved()){
            solved = true;
            return;
        }
        
        //crank through possible solutions
        if(isValid(row+2,col+1)){
             solve(row+2,col+1,currNum+1);
             if(solved){return;}
        }
        if(isValid(row+2,col-1)){
             solve(row+2,col-1,currNum+1);
             if(solved){return;}
        }
        if(isValid(row+1,col+2)){
             solve(row+1,col+2,currNum+1);
             if(solved){return;}
        }
        if(isValid(row+1,col-2)){
             solve(row+1,col-2,currNum+1);
             if(solved){return;}
        }
        if(isValid(row-1,col+2)){
             solve(row-1,col+2,currNum+1);
             if(solved){return;}
        }
        if(isValid(row-1,col-2)){
             solve(row-1,col-2,currNum+1);
             if(solved){return;}
        }
        if(isValid(row-2,col+1)){
             solve(row-2,col+1,currNum+1);
             if(solved){return;}
        }
        if(isValid(row-2,col-1)){
             solve(row-2,col-1,currNum+1);
             if(solved){return;}
        }
        
        //if we fell through and are in starting position, it's unsolvable
        if(row == startRow && col == startCol){
            System.out.println("This board is unsolvable");
            return;
        }
        
        //fell through, remove number so we can come back here
        board[row][col] = 0;
        return;
        
    }
    public boolean isSolved(){
        for(int i=0;i<=rows-1;i++){
            for(int j=0;j<=cols-1;j++){
                if(board[i][j] == 0){
                    return false;
                }
            }
        }
        printBoard();
        return true;
    }
    public boolean isValid(int row, int col){
        /* 
       * Things that cause a tile to be invalid:
       * -already having a number
       * -being out of boaunds
       */
       if(row < 0 ||
           col < 0 ||
           row >= rows ||
           col >= cols){
               return false;
           }
       else if(!(board[row][col] == 0)){
               return false;
       }
       else{
           return true;
       }
    }
    public void printBoard(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
}
