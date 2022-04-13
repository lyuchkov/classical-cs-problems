package search_problems.river;

import java.util.List;

public class Missionaries {
    public static void displaySolution(List<MCState> path){
        if(path.isEmpty()){
            return;
        }
        MCState oldState = path.get(0);
        System.out.println(oldState);
        for(MCState currentState : path.subList(1, path.size())){
            if(currentState.boat){
                System.out.printf("%d missionaries and %d cannibals moved from the east bank to the west bank.%n",
                        oldState.em - currentState.em,
                        oldState.ec - currentState.ec);
            }else{
                System.out.printf("%d missionaries and %d cannibals moved from the west bank to the east bank.%n",
                        oldState.wm - currentState.wm,
                        oldState.wc - currentState.wc);
            }
            System.out.println(currentState);
            oldState = currentState;
        }
    }
}