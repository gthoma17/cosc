import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.Reader;
public class sudukoSolver
{
    Integer[][] puzzle;
    boolean isSolved;
    public sudukoSolver(String path)
    {
        isSolved = false;
        try{  //create a matrix from the file
                FileReader fileReader = new FileReader(new File(path));
                BufferedReader br = new BufferedReader(fileReader);
                this.puzzle = new Integer[10][10];
                String line;
                for(int x=0;x<=8;x++){     //read the file into an array
                    line = br.readLine();
                    for(int y=0;y<=8;y++){
                        puzzle[x][y] = Integer.valueOf(line.substring(y,y+1));
                    }
                }
            }
            catch (Exception e) {
            System.out.println(e.getClass());
            }
            
            
    
    }
    
    public void solve(int x, int y){
        if(y ==9){ //kluge
            return;
        }
        if(puzzleComplete()){ //don't keep going if we're done
            isSolved = true;
            return;
        }
        if(!(puzzle[x][y] == 0)){ //if there is already a number here, move on
            if(x<8){
                solve(x+1,y);
            }
            else{
                solve(0,y+1);
            }
        }
        else{
            for(int i=0;i<=9;i++){
                if(!hasConflicts(x,y,i)){ //if no conflicts, roll with it
                    puzzle[x][y] = i;
                    if(!(x==8 && y==8)){ //unless this is the final cell, keep moving
                        if(x<8){
                            solve(x+1,y);
                            if(isSolved){return;} //if the puzzle is solved, break out
                        }
                        else{
                            solve(0,y+1);
                            if(isSolved){return;} //if the puzzle is solved, break out
                        }
                    }
                    else{
                        if(puzzleComplete()){ //if this is the final cell, check for done
                            isSolved = true;
                            return;
                        }
                    }
                }
            }
            puzzle[x][y] = 0;
            if(x==0 && y==0){ //if we're here, it mean we fell through on the first cell. 
                System.out.println("Puzzle is unsolvable");
                return;
            }
        }
    }
    
    public boolean puzzleComplete(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (puzzle[i][j] == 0){
                    return false;
                }
            }
        }
        printPuzzle();
        return true;
    }
    
    public void printPuzzle(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean hasConflicts(int x, int y, int val){
        for(int i=0;i<9;i++){
            if(puzzle[x][i] == val && i != y){
                return true;
            }
            else if(puzzle[i][y] == val && i != x){
                return true;
            }
        }
        
        //This is definately not the best way to do this. But I already wrote it so...
        if(x<=2){
            if(y<=2){
                if (puzzle[0][0] == val && x != 0 && y != 0){return true;}
                else if (puzzle[0][1] == val && x != 0 && y != 1){return true;}
                else if (puzzle[0][2] == val && x != 0 && y != 2){return true;}
                else if (puzzle[1][0] == val && x != 1 && y != 0){return true;}
                else if (puzzle[1][1] == val && x != 1 && y != 1){return true;}
                else if (puzzle[1][2] == val && x != 1 && y != 2){return true;}
                else if (puzzle[2][0] == val && x != 2 && y != 0){return true;}
                else if (puzzle[2][1] == val && x != 2 && y != 1){return true;}
                else if (puzzle[2][2] == val && x != 2 && y != 2){return true;}
            }
            else if(y>2 && y<=5){
                if (puzzle[0][3] == val && x != 0 && y != 3){return true;}
                else if (puzzle[0][4] == val && x != 0 && y != 4){return true;}
                else if (puzzle[0][5] == val && x != 0 && y != 5){return true;}
                else if (puzzle[1][3] == val && x != 1 && y != 3){return true;}
                else if (puzzle[1][4] == val && x != 1 && y != 4){return true;}
                else if (puzzle[1][5] == val && x != 1 && y != 5){return true;}
                else if (puzzle[2][3] == val && x != 2 && y != 3){return true;}
                else if (puzzle[2][4] == val && x != 2 && y != 4){return true;}
                else if (puzzle[2][5] == val && x != 2 && y != 5){return true;}
            }
            else if(y<=8){
                if (puzzle[0][6] == val && x != 0 && y != 6){return true;}
                else if (puzzle[0][7] == val && x != 0 && y != 7){return true;}
                else if (puzzle[0][8] == val && x != 0 && y != 8){return true;}
                else if (puzzle[1][6] == val && x != 1 && y != 6){return true;}
                else if (puzzle[1][7] == val && x != 1 && y != 7){return true;}
                else if (puzzle[1][8] == val && x != 1 && y != 8){return true;}
                else if (puzzle[2][6] == val && x != 2 && y != 6){return true;}
                else if (puzzle[2][7] == val && x != 2 && y != 7){return true;}
                else if (puzzle[2][8] == val && x != 2 && y != 8){return true;}
            } 
        }
        else if(x>2 && x<=5){
            if(y<=2){
                if (puzzle[3][0] == val && x != 3 && y != 0){return true;}
                else if (puzzle[3][1] == val && x != 3 && y != 1){return true;}
                else if (puzzle[3][2] == val && x != 3 && y != 2){return true;}
                else if (puzzle[4][0] == val && x != 4 && y != 0){return true;}
                else if (puzzle[4][1] == val && x != 4 && y != 1){return true;}
                else if (puzzle[4][2] == val && x != 4 && y != 2){return true;}
                else if (puzzle[5][0] == val && x != 5 && y != 0){return true;}
                else if (puzzle[5][1] == val && x != 5 && y != 1){return true;}
                else if (puzzle[5][2] == val && x != 5 && y != 2){return true;}
            }
            else if(y>2 && y<=5){
                if (puzzle[3][3] == val && x != 3 && y != 3){return true;}
                else if (puzzle[3][4] == val && x != 3 && y != 4){return true;}
                else if (puzzle[3][5] == val && x != 3 && y != 5){return true;}
                else if (puzzle[4][3] == val && x != 4 && y != 3){return true;}
                else if (puzzle[4][4] == val && x != 4 && y != 4){return true;}
                else if (puzzle[4][5] == val && x != 4 && y != 5){return true;}
                else if (puzzle[5][3] == val && x != 5 && y != 3){return true;}
                else if (puzzle[5][4] == val && x != 5 && y != 4){return true;}
                else if (puzzle[5][5] == val && x != 5 && y != 5){return true;}
            }
            else if(y<=8){
                if (puzzle[3][6] == val && x != 3 && y != 6){return true;}
                else if (puzzle[3][7] == val && x != 3 && y != 7){return true;}
                else if (puzzle[3][8] == val && x != 3 && y != 8){return true;}
                else if (puzzle[4][6] == val && x != 4 && y != 6){return true;}
                else if (puzzle[4][7] == val && x != 4 && y != 7){return true;}
                else if (puzzle[4][8] == val && x != 4 && y != 8){return true;}
                else if (puzzle[5][6] == val && x != 5 && y != 6){return true;}
                else if (puzzle[5][7] == val && x != 5 && y != 7){return true;}
                else if (puzzle[5][8] == val && x != 5 && y != 8){return true;}
            } 
        }
        else if(x<=8){
            if(y<=2){
                if (puzzle[6][0] == val && x != 6 && y != 0){return true;}
                else if (puzzle[6][1] == val && x != 6 && y != 1){return true;}
                else if (puzzle[6][2] == val && x != 6 && y != 2){return true;}
                else if (puzzle[7][0] == val && x != 7 && y != 0){return true;}
                else if (puzzle[7][1] == val && x != 7 && y != 1){return true;}
                else if (puzzle[7][2] == val && x != 7 && y != 2){return true;}
                else if (puzzle[8][0] == val && x != 8 && y != 0){return true;}
                else if (puzzle[8][1] == val && x != 8 && y != 1){return true;}
                else if (puzzle[8][2] == val && x != 8 && y != 2){return true;}
            }
            else if(y>2 && y<=5){
                if (puzzle[6][3] == val && x != 6 && y != 3){return true;}
                else if (puzzle[6][4] == val && x != 6 && y != 4){return true;}
                else if (puzzle[6][5] == val && x != 6 && y != 5){return true;}
                else if (puzzle[7][3] == val && x != 7 && y != 3){return true;}
                else if (puzzle[7][4] == val && x != 7 && y != 4){return true;}
                else if (puzzle[7][5] == val && x != 7 && y != 5){return true;}
                else if (puzzle[8][3] == val && x != 8 && y != 3){return true;}
                else if (puzzle[8][4] == val && x != 8 && y != 4){return true;}
                else if (puzzle[8][5] == val && x != 8 && y != 5){return true;}
            }
            else if(y<=8){
                if (puzzle[6][6] == val && x != 6 && y != 6){return true;}
                else if (puzzle[6][7] == val && x != 6 && y != 7){return true;}
                else if (puzzle[6][8] == val && x != 6 && y != 8){return true;}
                else if (puzzle[7][6] == val && x != 7 && y != 6){return true;}
                else if (puzzle[7][7] == val && x != 7 && y != 7){return true;}
                else if (puzzle[7][8] == val && x != 7 && y != 8){return true;}
                else if (puzzle[8][6] == val && x != 8 && y != 6){return true;}
                else if (puzzle[8][7] == val && x != 8 && y != 7){return true;}
                else if (puzzle[8][8] == val && x != 8 && y != 8){return true;}
            } 
        } 
        return false;
    }
}
