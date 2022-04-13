package search_problems;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class GenericSearch {
    public static <T extends Comparable<T>> boolean linearContains(List<T> list, T toFind) {
        for (T elem :
                list) {
            if (elem.compareTo(toFind) == 0) return true;
        }
        return false;
    }

    public static <T extends Comparable<T>> boolean binaryContains(List<T> list, T toFind) {
        Collections.sort(list);
        int begin = 0;
        int end = list.size() - 1;
        while (begin <= end) {
            int middle = (begin + end) / 2;
            int res = toFind.compareTo(list.get(middle));
            if (res > 0) begin = middle + 1;
            else if (res < 0) end = middle - 1;
            else return true;
        }
        return false;
    }

    public static <T> Node<T> dfs(T initial, Predicate<T> isGoal, Function<T, List<T>> successors) {
        Stack<Node<T>> frontier = new Stack<>();
        frontier.push(new Node<>(initial, null));
        Set<T> explored = new HashSet<>();
        explored.add(initial);
        while (!frontier.isEmpty()) {
            Node<T> currentNode = frontier.pop();
            T currentState = currentNode.state;
            if (isGoal.test(currentState)) return currentNode;
            for (T child :
                    successors.apply(currentState)) {
                if (explored.contains(child)) continue;
                explored.add(child);
                frontier.push(new Node<>(child, currentNode));

            }
        }
        return null;
    }

    public static <T> Node<T> bfs(T initial, Predicate<T> isGoal, Function<T, List<T>> successors) {
        Queue<Node<T>> frontier = new LinkedList<>();
        frontier.offer(new Node<>(initial, null));
        Set<T> explored = new HashSet<>();
        explored.add(initial);
        while (!frontier.isEmpty()) {
            Node<T> currentNode = frontier.poll();
            T currentState = currentNode.state;
            if (isGoal.test(currentState)) return currentNode;
            for (T child :
                    successors.apply(currentState)) {
                if (explored.contains(child)) continue;
                explored.add(child);
                frontier.offer(new Node<>(child, currentNode));

            }
        }
        return null;
    }

    public static <T> Node<T> aStar(T initial, Predicate<T> isGoal,
                                    Function<T, List<T>> successors, ToDoubleFunction<T> heuristic) {
        PriorityQueue<Node<T>> frontier = new PriorityQueue<>();
        frontier.offer(new Node<>(initial, null, 0.0, heuristic.applyAsDouble(initial)));
        Map<T, Double> explored = new HashMap<>();
        explored.put(initial, 0.0);
        while(!frontier.isEmpty()){
            Node<T> currentNode = frontier.poll();
            T currentState = currentNode.state;
            if(isGoal.test(currentState)){
                return currentNode;
            }
            for (T child: successors.apply(currentState)) {
                double newCost = currentNode.cost+1;
                if(!explored.containsKey(child)||explored.get(child) > newCost){
                    explored.put(child, newCost);
                    frontier.offer(new Node<>(child, currentNode, newCost, heuristic.applyAsDouble(child)));
                }
            }
        }
        return null;
    }

    public static <T> List<T> nodeToPath(Node<T> node) {
        List<T> path = new ArrayList<>();
        path.add(node.state);
        while (node.parent != null) {
            node = node.parent;
            path.add(0, node.state);
        }
        return path;
    }

    public static class Node<T> implements Comparable<Node<T>> {
        final T state;
        Node<T> parent;
        double cost;
        double heuristic;

        public Node(T state, Node<T> parent) {
            this.state = state;
            this.parent = parent;
        }

        public Node(T state, Node<T> parent, double cost, double heuristic) {
            this.state = state;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }

        @Override
        public int compareTo(Node<T> o) {
            Double mine = cost + heuristic;
            Double theirs = o.cost + o.heuristic;
            return mine.compareTo(theirs);
        }
    }
}
