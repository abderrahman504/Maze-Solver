import java.io.File;



public class Tester 
{
    public static void main(String[] args)
    {
        File fMaze = new File(".\\maze6.txt");
        int[][] result = new MazeSolver().solveBFS(fMaze);

        if (result.length == 0)
        {
            System.out.print("The maze file is invalid.");
            return;
        }
        System.out.println("Path with BFS algorithm:");
        for (int i=0; i<result.length; i++)
        {
            System.out.println(result[i][0] + ", " + result[i][1]);
        }
        System.out.println("\n");


        result = new MazeSolver().solveDFS(fMaze);

        System.out.println("Path with DFS algorithm:");
        for (int i=0; i<result.length; i++)
        {
            System.out.println(result[i][0] + ", " + result[i][1]);
        }
        System.out.println("\n");


        result = new MazeSolver().solveBestFS(fMaze);

        System.out.println("Path with Best-FS algorithm:");
        for (int i=0; i<result.length; i++)
        {
            System.out.println(result[i][0] + ", " + result[i][1]);
        }
    }
}
