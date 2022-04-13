package search_problems.river;

import org.junit.jupiter.api.Test;
import search_problems.GenericSearch;
import search_problems.dna_search.Gene;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static search_problems.river.Missionaries.displaySolution;

class MissionariesTest {
    @Test
    void simpleSolutionTest(){
        MCState start = new MCState(3,3, true);
        GenericSearch.Node<MCState> solution = GenericSearch.bfs(start, MCState::isGoal, MCState::successors);
        if (solution==null)
            System.out.println("Решение не найдено");
        else{
            List<MCState> path = GenericSearch.nodeToPath(solution);
            displaySolution(path);
        }
    }
}