import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.Stack;
import java.util.Scanner;

public class main
{
   public main(){
    }
   private class tile{
       boolean visited;
       boolean isWall;
       int x;
       int y;
       
       private tile(boolean isWall, int x, int y){
           this.isWall = isWall;
           this.x = x;
           this.y = y;
           this.visited = false;
       }
   }
   
   public boolean isSolvable(String path){
       boolean solvable = false;
       boolean isWall;
       int tmp;
       tile cLocation;
       
       try{  //create a matrix from the file
            FileReader fileReader = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();
            
            String[] sizeParms = line.split("\\s+");
            int height = Integer.valueOf(sizeParms[0]); // get height and width
            int width = Integer.valueOf(sizeParms[1]);
            
            tile[][] maze = new tile[height+1][width+1];
            
            for(int y=0;y<height;y++){     //read the file into an array
                line = br.readLine();
                for(int x=0;x<width;x++){
                    if(line.charAt(x) == '1')
                        isWall = true;
                    else
                        isWall = false;    
                    maze[y][x] = new tile(isWall, x, y);

                }
            }
            
            line = br.readLine(); // get start point
            String[] startPoint = line.split("\\s+");
            line = br.readLine(); // get end point
            String[] endPoint = line.split("\\s+");
            
            
            //prepare the stack
            Stack<tile> stack = new Stack<tile>(); //_the_ stack
            stack.push(maze[Integer.valueOf(startPoint[0])]
                           [Integer.valueOf(startPoint[1])]);
            
            while(!stack.isEmpty()){
               cLocation = stack.pop();
               maze[cLocation.y][cLocation.x].visited = true;
               if(cLocation.y == Integer.valueOf(endPoint[0]) && 
                  cLocation.x == Integer.valueOf(endPoint[1])){
                   solvable = true;
                   break;
                }
               else{
                   if(!(cLocation.y == height-1)){
                       if(!(maze[cLocation.y+1][cLocation.x].isWall ||
                            maze[cLocation.y+1][cLocation.x].visited)){
                                stack.push(maze[cLocation.y+1][cLocation.x]);
                       }
                   }
                   if(!(cLocation.x == width-1)){
                       if(!(maze[cLocation.y][cLocation.x+1].isWall ||
                            maze[cLocation.y][cLocation.x+1].visited)){
                                stack.push(maze[cLocation.y][cLocation.x+1]);
                       }
                   }
                   if(!(cLocation.y == 0)){
                       if(!(maze[cLocation.y-1][cLocation.x].isWall ||
                            maze[cLocation.y-1][cLocation.x].visited)){
                                stack.push(maze[cLocation.y-1][cLocation.x]);
                       }
                   }
                   if(!(cLocation.x == 0)){
                       if(!(maze[cLocation.y][cLocation.x-1].isWall ||
                            maze[cLocation.y][cLocation.x-1].visited)){
                                stack.push(maze[cLocation.y][cLocation.x-1]);
                       }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getClass());
        } 
        
        
       return (solvable);
   }
   
    private static void displayMenu(){
        System.out.println("Enter a the path to maze file to have it evaluated \n" +
                           "Enter 'q' to quit\n");
    }
    
    public static void main(String [ ] args){

        main ms = new main();
        Scanner keyboard = new Scanner(System.in);    
        displayMenu();
        String option = keyboard.nextLine();
        boolean solvable;
        
        while(!option.contains("q")){
            solvable = ms.isSolvable(option);
            if(solvable){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
            displayMenu();
            option = keyboard.nextLine();
            
        }
            
    }
}
