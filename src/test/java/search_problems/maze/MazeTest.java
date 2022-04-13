package search_problems.maze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search_problems.GenericSearch;

import java.util.List;

class MazeTest {
    static Maze maze;

    @BeforeEach
    void initMaze() {
        maze = new Maze();
        System.out.println(maze);
    }

    @Test
    void defaultInitTest() {
        Maze maze = new Maze();
        System.out.println(maze);
    }

    @Test
    void customInitTest() {
        Maze maze = new Maze(15, 10, new MazeLocation(0, 2), new MazeLocation(14, 9), 0.5);
        System.out.println(maze);
    }

    @Test
    void dfsSolutionTest() {
        GenericSearch.Node<MazeLocation> solution1 = GenericSearch.dfs(maze.start, maze::isGoal, maze::successors);
        if (solution1 == null) System.out.println("No solution");
        else {
            List<MazeLocation> path = GenericSearch.nodeToPath(solution1);
            maze.mark(path);
            System.out.println(maze);
            maze.clear(path);
        }
    }

    @Test
    void bfsSolutionTest() {
        GenericSearch.Node<MazeLocation> sol = GenericSearch.bfs(maze.start, maze::isGoal, maze::successors);
        if (sol == null) System.out.println("No solution");
        else {
            List<MazeLocation> path = GenericSearch.nodeToPath(sol);
            maze.mark(path);
            System.out.println(maze);
            maze.clear(path);
        }
    }

    @Test
    void aStarSolutionTest() {
        GenericSearch.Node<MazeLocation> sol = GenericSearch.aStar(maze.start, maze::isGoal, maze::successors, maze::manhattanDistance);
        if (sol == null) System.out.println("No solution");
        else {
            List<MazeLocation> path = GenericSearch.nodeToPath(sol);
            maze.mark(path);
            System.out.println(maze);
            maze.clear(path);
        }
    }
}