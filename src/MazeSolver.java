import DS.*;
import java.awt.Point;
import java.io.File;
import java.util.Scanner;


public class MazeSolver 
{
    class Cell extends Point
    {
        private Cell entry;
        private boolean checked = false;

        Cell(Cell entry, Point coordinates)
        {
            this.setLocation(coordinates);
            this.entry = entry;
        }

        /**
         * A cell is considered checked if the search algorithm has already visited the cell
         * and added the cells that branch from it to a queue, stack or p. queue.
         * @return true if the cell has been checked already and false otherwise.
         */
        boolean checked()
        {
            return checked;
        }

        /**
         * 
         * @return the cell that we used to enter this cell. In other words it returns the parent cell.
         */
        Cell entry()
        {
            return entry;
        }
    }
    /**
     * Reads a maze from the file fMaze and returns it in the form of an array of strings. 
     * Each string in the array is a line in the file. If something is wrong with the file then an exception is thrown.
     * @param fMaze the file containing the maze.
     * @return the contents of the file in the form of an array of strings.
     * @throws Exception
     */
    private static String[] file_to_strings(File fMaze) throws Exception
    {
        String[] maze; 
        Scanner scn = new Scanner(fMaze);
        int height = scn.nextInt(), width = scn.nextInt();
        maze = new String[height];
        if (!scn.hasNextLine())
        {
            scn.close();
            throw new Exception();
        }
        scn.nextLine();
        
        for (int i=0; i<height; i++)
        {
            maze[i] = scn.nextLine();
            if (maze[i].length() != width)
            {
                scn.close();
                throw new Exception();
            }
        }
        if (scn.hasNextLine())
        {
            scn.close();
            throw new Exception();
        }


        scn.close();
        return maze;
    }

}


interface IMazeSolver 
{
    /**
    * Read the maze file, and solve it using Breadth First Search
    * @param maze maze file
    * @return the coordinates of the found path from point ’S’
    *
    to point ’E’ inclusive, or null if no path is found.
    */
    public int[][] solveBFS(File maze);
    /**
    * Read the maze file, and solve it using Depth First Search
    * @param maze maze file
    * @return the coordinates of the found path from point ’S’
    *
    to point ’E’ inclusive, or null if no path is found.
    */
    public int[][] solveDFS(File maze);


    public int[][] solveBestFS(File maze);


}
    