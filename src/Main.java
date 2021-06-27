import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {


        int[][] parentChildPairs1 = new int[][] {
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5},
                {4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9},
                {15, 13}
        };



        int[][] parentChildPairs2 = new int[][] {
                {1, 3}, {11, 10}, {11, 12}, {2, 3}, {10, 2},
                {10, 5}, {3, 4}, {5, 6}, {5, 7}, {7, 8}
        };


        System.out.println(hasCommonAncestor(parentChildPairs1, 3, 8));

    }
    static boolean hasCommonAncestor(int[][] parentChildPairs, int v1, int v2) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < parentChildPairs.length; i++) {
            int child = parentChildPairs[i][1];
            int parent = parentChildPairs[i][0];
            if (!map.containsKey(child)) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(parent);
                map.put(child, list1);
            } else {
                List<Integer> list = map.get(child);
                list.add(parent);
                map.put(child, list);
            }
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<>());
            }
        }

        List<Integer> parents1 = findParents(v1, map, map.get(v1));
        parents1.forEach(key -> System.out.println(key));
        List<Integer> parents2 = findParents(v2, map, map.get(v1));
        parents2.forEach(key -> System.out.println(key));

        Set<Integer> set = parents1.stream().distinct().filter(parents2::contains).collect(Collectors.toSet());
        return set.isEmpty();
    }

    static List<Integer> findParents(int key, Map<Integer, List<Integer>> parentMap, List<Integer> parents) {
        if (!parentMap.containsKey(key)) return parents;
        for (int i = 0; i < parents.size(); i++) {
            if (parentMap.containsKey(parents.get(i)) && parentMap.get(parents.get(i)) != null && !parentMap.get(parents.get(i)).isEmpty()) {
                List<Integer> list = parentMap.get(parents.get(i));
                return findParents(parents.get(i), parentMap, list);
            }

        }
        return parents;
    }
}
