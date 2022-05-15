import DS.*;
import java.awt.Point;
import java.io.File;
import java.util.Scanner;


public class MazeSolver //implememts IMazeSolver
{
    class Cell extends Point
    {
        private Cell entry;

        Cell(Cell entry, Point coordinates)
        {
            this.entry = entry;
            this.setLocation(coordinates);
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



    public int[][] bestFS(File maze)
    {
        String[] sMaze;
        try
        {
            sMaze = file_to_strings(maze);
        }
        catch (Exception e)
        {
            //Handle invalid file
            return new int[1][1];
        }
        boolean[][] checkedCells = new boolean[sMaze.length][sMaze[0].length()];
        Cell start, end;
        start = end = new Cell(null, new Point(-1, -1));
        for (int i=0; i<sMaze.length; i++)
        {
            for (int j=0; j<sMaze[0].length(); j++)
            {
                checkedCells[i][j] = false;
                switch (sMaze[i].charAt(j))
                {
                    case "S":

                    case "E":

                }
            }
        }



        return new int[1][1];
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
    