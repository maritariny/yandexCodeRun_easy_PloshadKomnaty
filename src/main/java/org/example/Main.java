package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int n = Integer.parseInt(s);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int count = 0;

        int arr[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            String parts = reader.readLine();
            for (int j = 0; j < n; j++) {
                count++;
                List<Integer> list = new ArrayList<>();
                arr[i][j] = (parts.charAt(j) == '*') ? 0 : 1;
                if (arr[i][j] == 1) {
                    if ((j > 0) && (arr[i][j - 1] == 1)) {
                        list.add(count - 1);
                        List<Integer> l = graph.get(count - 1);
                        l.add(count);
                    }
                    if ((i > 0) && (arr[i - 1][j] == 1)) {
                        list.add(count - n);
                        List<Integer> l = graph.get(count - n);
                        if (!l.contains(count)) {
                            l.add(count);
                        }
                    }
                }
                graph.put(count, list);
            }
        }
        String[] parts = reader.readLine().split(" ");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        int ver = (row - 1) * n + col;
        Set<Integer> doneNode = new HashSet<>();
        Stack<Integer> nodeStack = new Stack<>();
        nodeStack.add(ver);
        doneNode.add(ver);
        int result = 1;
        while ((!nodeStack.empty()) ) {
            Integer v = nodeStack.peek();
            List<Integer> list = graph.get(v);
            boolean itsStop = true;
            for (Integer next : list) {
                if (doneNode.contains(next)) {
                    continue;
                }
                nodeStack.add(next);
                result++;
                doneNode.add(next);
                itsStop = false;
                break;
            }
            if (itsStop) {
                nodeStack.pop();
            }
        }
        System.out.println(result);
    }
}