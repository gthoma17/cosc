public class tile{
       boolean visited;
       boolean wall;
       int col;
       int row;
       boolean deadEnd;
       
       
       public tile(boolean wall, int row, int col){
           this.wall = wall;
           this.row = row;
           this.col = col;
           this.visited = false;
           this.deadEnd = false;
       }
       
       public boolean isWall(){
           return wall;
       }
   }