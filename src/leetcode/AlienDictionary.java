package leetcode;

import java.util.*;

class AlienDictSolution {
    Map<Character, Set<Character>> adj;
    Map<Character, Boolean> visited;
    List<Character> result;
    AlienDictSolution() {
        adj = new HashMap<>();
        visited = new HashMap<>();
        result = new ArrayList<>();
    }

    public String foreignDictionary(String[] words) {
        for(String word: words) {
            for(char ch : word.toCharArray()) {
                adj.putIfAbsent(ch, new HashSet<>());
            }
        }

        for(int i=1; i<words.length; i++) {
            String prev = words[i-1];
            String curr = words[i];

            int minLen = Math.min(prev.length(), curr.length());
            if(prev.length() > curr.length() && prev.substring(0, minLen).equals(curr.substring(0, minLen))) {
                return "";
            }

            for(int j=0; j<minLen; j++) {
                if(prev.charAt(j) != curr.charAt(j)) {
                    adj.get(prev.charAt(j)).add(curr.charAt(j));
                    break;
                }
            }
        }

        for(char ch: adj.keySet()) {
            if (dfs(ch)) {
                return "";
            }
        }

        Collections.reverse(result);
        StringBuilder sb = new StringBuilder();
        for(char c: result) {
            sb.append(c);
        }

        return sb.toString();
    }

    private boolean dfs(char v) {
        if(visited.containsKey(v)) {
            return visited.get(v);
        }

        visited.put(v, true);
        for (char ch : adj.get(v)) {
            if(dfs(ch)) {
                return true;
            }
        }

        visited.put(v, false);
        result.add(v);
        return false;
    }
}

public class AlienDictionary {
    public static void main(String[] args) {
        AlienDictSolution solution = new AlienDictSolution();
        System.out.println(solution.foreignDictionary(new String[]{"z", "o"}));
    }
}


