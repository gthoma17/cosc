import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.Scanner;
public class maze{
       tile[][] maze;
       int rows;
       int cols;
       int startRow;
       int startCol;
       int endRow;
       int endCol;
       String path;
       boolean foundEnd;
       
       public maze(String path){
            boolean isWall;
            this.foundEnd = false;
            try{  //create a matrix from the file
                FileReader fileReader = new FileReader(new File(path));
                BufferedReader br = new BufferedReader(fileReader);
                String line = br.readLine();
            
                String[] sizeParms = line.split("\\s+");
                this.rows = Integer.valueOf(sizeParms[0]); // get height and width
                this.cols = Integer.valueOf(sizeParms[1]);
                this.maze = new tile[this.rows][this.cols];
            
                for(int row=0;row<rows;row++){     //read the file into an array
                    line = br.readLine();
                    for(int col=0;col<cols*2;col=col+2){
                        if(line.charAt(col) == '1'){
                            isWall = true;
                        }
                        else{
                            isWall = false;    
                        }
                        this.maze[row][col/2] = new tile(isWall, row, col/2);
                    }
                }
            
                line = br.readLine(); // get start point
                String[] startpoint = line.split("\\s+");
                this.startRow = Integer.valueOf(startpoint[0]);
                this.startCol = Integer.valueOf(startpoint[1]);
                
                line = br.readLine(); // get end point
                String[] endpoint = line.split("\\s+");
                this.endRow = Integer.valueOf(endpoint[0]);
                this.endCol = Integer.valueOf(endpoint[1]);
            }
            catch (Exception e) {
            System.out.println(e.getClass());
            } 
       }
      
       public void solve(){
           System.out.println(solve(startRow,startCol));
       }
       
       public String solve(int row, int col){
           Scanner keyboard = new Scanner(System.in);  
           maze[row][col].visited = true; 
           if(row == this.endRow && col == this.endCol){
               //found the end, do stuff
               foundEnd = true;
               path = "(" + row + "," + col + ")";
               return path;
           }
           else{
               if(this.isValid(row+1,col)){
                   solve(row+1,col);
                   if(foundEnd){
                       path = path = "(" + row + "," + col + ")" + path;
                       return path;
                   }
               }
               if(this.isValid(row-1,col)){
                   solve(row-1,col);
                   if(foundEnd){
                       path = path = "(" + row + "," + col + ")" + path;
                       return path;
                   }
               }
               if(this.isValid(row,col-1)){
                   solve(row,col-1);
                   if(foundEnd){
                       path = path = "(" + row + "," + col + ")" + path;
                       return path;
                   }
               }
               if(this.isValid(row,col+1)){
                   solve(row,col+1);
                   if(foundEnd){
                       path = path = "(" + row + "," + col + ")" + path;
                       return path;
                   }
               }
           }
           if(row == startRow && col == startCol && !(foundEnd)){
               path = "Maze Unsolvable";
           }
           return path;
       }

       public boolean isValid(int row, int col){
           /*
            * Things that cause a tile to be invalid as a movement option:
            * -It's a wall
            * -It's out of bounds
            * -It's already been visited
            */
           //System.out.println("Checking tile: " + row + "," + col);
           if(row == -1 ||
              col == -1 ||
              row == rows ||
              col == cols){
              //System.out.println("An index would be out of bounds");
              return false;
           }
           else if (maze[row][col].isWall()){
              //System.out.println("The tile " + row + "," + col + " is a wall");
              return false;
           }
           else if ( maze[row][col].visited){
               //System.out.println("The tile" + row + "," + col + " has already been visited");
               return false;
           }
           else{
           //System.out.println("Row " + row + " Col " + col + " is a valid move");
           return true;
           }
       }
}