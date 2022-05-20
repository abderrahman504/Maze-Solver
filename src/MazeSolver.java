import DS.*;
import java.awt.Point;
import java.io.File;
import java.util.Scanner;
import java.lang.Math;


public class MazeSolver implements IMazeSolver
{
    static class Cell extends Point
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
    enum Direction
    {
        left, right, up, down
    }


    /**
     * Reads a maze from the file fMaze and returns it in the form of an array of strings. 
     * Each string in the array is a line in the file. If something is wrong with the file then an exception is thrown.
     * @param fMaze File object of the maze.
     * @return The contents of the file in the form of an array of strings.
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


    public int[][] solveDFS(File maze)
    {
        String[] sMaze;
        try
        {
            sMaze = file_to_strings(maze);
        }
        catch (Exception e)
        {
            //Handle invalid file
            return new int[0][0];
        }
        
        Cell Exit = find_path(sMaze, 'D');
        return trace_path(Exit);
    }


    public int[][] solveBFS(File maze)
    {
        String[] sMaze;
        try
        {
            sMaze = file_to_strings(maze);
        }
        catch (Exception e)
        {
            //Handle invalid file
            return new int[0][0];
        }
        
        Cell Exit = find_path(sMaze, 'B');
        return trace_path(Exit);
    }



    public int[][] solveBestFS(File maze)
    {
        String[] sMaze;
        try
        {
            sMaze = file_to_strings(maze);
        }
        catch (Exception e)
        {
            //Handle invalid file
            return new int[0][0];
        }

        Cell Exit = find_path(sMaze, 'E');
        return trace_path(Exit);
    }


    private static Cell find_path(String[] sMaze, char mode)
    {
        boolean[][] checkedCells = new boolean[sMaze.length][sMaze[0].length()];
        Point start, end;
        start = end = new Point(-1, -1);
        for (int i=0; i<sMaze.length; i++)
        {
            for (int j=0; j<sMaze[0].length(); j++)
            {
                checkedCells[i][j] = false;
                switch (sMaze[i].charAt(j))
                {
                    case 'S':
                    start = new Point(j, i);
                    break;
                    case 'E':
                    end = new Point(j, i);
                }
            }
        }
        boolean endFound = false;
        Cell currentCell = new Cell(null, start);
        switch (mode)
        {
            case 'D'://Depth.
            Stack S = new Stack();
            endFound = check_adjacents(currentCell, sMaze, checkedCells, end, S);
            while(!endFound && !S.isEmpty())
            {
                currentCell = (Cell) S.pop();
                endFound = check_adjacents(currentCell, sMaze, checkedCells, end, S);
            }
            break;

            case 'B'://Breath.
            Queue Q = new Queue();
            endFound = check_adjacents(currentCell, sMaze, checkedCells, end, Q);
            while(!endFound && !Q.isEmpty())
            {
                currentCell = (Cell) Q.dequeue();
                endFound = check_adjacents(currentCell, sMaze, checkedCells, end, Q);
            }
            break;

            default://Best.
            PQueue<Cell> PQ = new PQueue<Cell>();
            endFound = check_adjacents(currentCell, sMaze, checkedCells, end, PQ);
            while(!endFound && !PQ.isEmpty())
            {
                currentCell = (Cell) PQ.remove_min();
                endFound = check_adjacents(currentCell, sMaze, checkedCells, end, PQ);
            }
        }
        Cell End = new Cell(currentCell, end);
        return End;
    }


    private static boolean check_adjacents(Cell currentCell, String[] sMaze, boolean[][] checkedCells, Point end, Stack S)
    {
        boolean endFound = false;
        Direction checkingDirection = Direction.left;
        Point nextLoc = new Point((int) currentCell.getX()-1, (int) currentCell.getY());
        for (int i=0; i<4; i++)
        {
            //if the cell wasn't checked already.
            if ((int) nextLoc.getX() >= 0 && (int) nextLoc.getX() < sMaze[0].length() && (int) nextLoc.getY() >= 0 && (int) nextLoc.getY() < sMaze.length)
            {
                switch(sMaze[(int) nextLoc.getY()].charAt((int) nextLoc.getX()))
                {
                    case '#':
                    break;
                    case 'E':
                    endFound = true;
                    return endFound;
                    default:
                    if (checkedCells[(int)nextLoc.getY()][(int)nextLoc.getX()]) break;
                    Cell newCell = new Cell(currentCell, nextLoc);
                    checkedCells[(int)nextLoc.getY()][(int)nextLoc.getX()] = true;
                    S.push(newCell);
                }
            }
            
            switch(checkingDirection)
            {
                case left:
                checkingDirection = Direction.right;
                nextLoc.setLocation(currentCell.getX()+1, currentCell.getY());
                break;
                case right:
                checkingDirection = Direction.up;
                nextLoc.setLocation(currentCell.getX(), currentCell.getY()-1);
                break;
                default://up case.
                checkingDirection = Direction.down;
                nextLoc.setLocation(currentCell.getX(), currentCell.getY()+1);
            }
        }
        return endFound;
    }


    private static boolean check_adjacents(Cell currentCell, String[] sMaze, boolean[][] checkedCells, Point end, Queue Q)
    {
        boolean endFound = false;
        Direction checkingDirection = Direction.left;
        Point nextLoc = new Point((int) currentCell.getX()-1, (int) currentCell.getY());
        for (int i=0; i<4; i++)
        {
            if ((int) nextLoc.getX() >= 0 && (int) nextLoc.getX() < sMaze[0].length() && (int) nextLoc.getY() >= 0 && (int) nextLoc.getY() < sMaze.length)
            {
                switch(sMaze[(int) nextLoc.getY()].charAt((int) nextLoc.getX()))
                {
                    case '#':
                    break;
                    case 'E':
                    endFound = true;
                    return endFound;
                    default:
                    if (checkedCells[(int)nextLoc.getY()][(int)nextLoc.getX()]) break;
                    Cell newCell = new Cell(currentCell, nextLoc);
                    checkedCells[(int)nextLoc.getY()][(int)nextLoc.getX()] = true;
                    Q.enqueue(newCell);
                }
            }
            
            switch(checkingDirection)
            {
                case left:
                checkingDirection = Direction.right;
                nextLoc.setLocation(currentCell.getX()+1, currentCell.getY());
                break;
                case right:
                checkingDirection = Direction.up;
                nextLoc.setLocation(currentCell.getX(), currentCell.getY()-1);
                break;
                default://up case.
                checkingDirection = Direction.down;
                nextLoc.setLocation(currentCell.getX(), currentCell.getY()+1);
            }
        }
        return endFound;
    }


    private static boolean check_adjacents(Cell currentCell, String[] sMaze, boolean[][] checkedCells, Point end, PQueue<Cell> Q)
    {
        boolean endFound = false;
        Direction checkingDirection = Direction.left;
        Point nextLoc = new Point((int) currentCell.getX()-1, (int) currentCell.getY());
        for (int i=0; i<4; i++)
        {
            if ((int) nextLoc.getX() >= 0 && (int) nextLoc.getX() < sMaze[0].length() && (int) nextLoc.getY() >= 0 && (int) nextLoc.getY() < sMaze.length)
            {
                switch(sMaze[(int) nextLoc.getY()].charAt((int) nextLoc.getX()))
                {
                    case '#':
                    break;
                    case 'E':
                    endFound = true;
                    return endFound;
                    default:
                    if (checkedCells[(int)nextLoc.getY()][(int)nextLoc.getX()]) break;
                    Cell newCell = new Cell(currentCell, nextLoc);
                    checkedCells[(int)nextLoc.getY()][(int)nextLoc.getX()] = true;
                    /*
                    The heuristic function calculates the minimum possible number of moves it would
                    take to move from the current cell to the end of the maze. That is the score of the cell.
                    It also calculates the number of moves it took to reach this cell. That is the cost of the cell.
                    The sum of the cost and score is a cell's rating.
                    The cells with the lowest ratings are the most promising and so are checked first in the priority queue. 
                    */
                    int cellScore = (int) (Math.abs(currentCell.getX() - end.getX()) + Math.abs(currentCell.getY() - end.getY())); 
                    int cellCost = get_cell_cost(currentCell);
                    int rating = cellCost + cellScore;
                    Q.insert(newCell, rating);
                }
            }
            
            switch(checkingDirection)
            {
                case left:
                checkingDirection = Direction.right;
                nextLoc.setLocation(currentCell.getX()+1, currentCell.getY());
                break;
                case right:
                checkingDirection = Direction.up;
                nextLoc.setLocation(currentCell.getX(), currentCell.getY()-1);
                break;
                default://up case.
                checkingDirection = Direction.down;
                nextLoc.setLocation(currentCell.getX(), currentCell.getY()+1);
            }
        }
        return endFound;
    }


    private static int[][] trace_path(Cell currentCell)
    {
        Stack stck = new Stack();
        while (currentCell.entry != null)
        {
            stck.push(currentCell);
            currentCell = currentCell.entry();
        }
        stck.push(currentCell);

        int[][] path = new int[stck.size()][2];
        for (int i=0; i<path.length; i++)
        {
            Point loc = (Point) stck.pop();
            path[i][0] = (int) loc.getY();
            path[i][1] = (int) loc.getX();
        }
        return path;
    }
    private static int get_cell_cost(Cell currentCell)
    {
        int cost = 0;
        while (currentCell.entry != null)
        {
            cost++;
            currentCell = currentCell.entry;
        }
        return cost;
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

    /**
     * Reads the maze file and solves it using a Best First Search.
     * @param maze Maze file.
     * @return The path through the maze.
     */
    public int[][] solveBestFS(File maze);


}
    