package search_problems.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
    final MazeLocation start;
    private final int rows, columns;
    private final MazeLocation goal;
    private final Cell[][] grid;

    public Maze(int rows, int columns, MazeLocation start, MazeLocation goal, double sparseness) {
        this.rows = rows;
        this.columns = columns;
        this.start = start;
        this.goal = goal;
        grid = new Cell[rows][columns];
        for (Cell[] row :
                grid) {
            Arrays.fill(row, Cell.EMPTY);
        }
        randomlyFill(sparseness);
        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    public Maze() {
        this(10, 10, new MazeLocation(0, 0), new MazeLocation(9, 9), 0.2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row :
                grid) {
            for (Cell cell :
                    row) {
                sb.append(cell.toString());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private void randomlyFill(double sparseness) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (Math.random() < sparseness)
                    grid[row][column] = Cell.BLOCKED;
            }
        }
    }

    public boolean isGoal(MazeLocation m) {
        return goal.equals(m);
    }

    public List<MazeLocation> successors(MazeLocation current) {
        List<MazeLocation> locations = new ArrayList<>();
        if (current.row + 1 < rows && grid[current.row + 1][current.column] != Cell.BLOCKED) {
            locations.add(new MazeLocation(current.row + 1, current.column));
        }
        if (current.row - 1 >= 0 && grid[current.row - 1][current.column] != Cell.BLOCKED) {
            locations.add(new MazeLocation(current.row - 1, current.column));
        }

        if (current.column + 1 < columns && grid[current.row][current.column + 1] != Cell.BLOCKED) {
            locations.add(new MazeLocation(current.row, current.column + 1));
        }

        if (current.column - 1 >= 0 && grid[current.row][current.column - 1] != Cell.BLOCKED) {
            locations.add(new MazeLocation(current.row, current.column - 1));
        }
        return locations;
    }

    public void mark(List<MazeLocation> path) {
        for (MazeLocation cur : path) {
            grid[cur.row][cur.column] = Cell.PATH;
        }
        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.row] = Cell.GOAL;
    }

    public void clear(List<MazeLocation> path) {
        for (MazeLocation cur : path) {
            grid[cur.row][cur.column] = Cell.EMPTY;
        }
        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.row] = Cell.GOAL;
    }

    public double manhattanDistance(MazeLocation start) {
        int x = Math.abs(start.column - goal.column);
        int y = Math.abs(start.row - goal.row);
        return x + y;
    }

    public enum Cell {
        EMPTY(" "),
        BLOCKED("X"),
        START("S"),
        GOAL("G"),
        PATH("*");

        public final String code;

        Cell(String s) {
            code = s;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}
