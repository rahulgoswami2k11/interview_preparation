package leetcode;

import java.util.*;

public class CurrencyConversion {

    public static void main(String[] args) {
        // Example rates
        List<ConversionRate> rates = Arrays.asList(
                new ConversionRate("USD", "JPY", 110),
                new ConversionRate("USD", "AUD", 1.45),
                new ConversionRate("JPY", "GBP", 0.0070)
        );

        // From and To currencies
        String[] fromTo = {"GBP", "AUD"};

        // Calculate the conversion rate
        double conversionRate = getConversionRate(rates, fromTo);
        System.out.println("Conversion Rate: " + conversionRate); // Output: 1.89
    }

    // Method to build the graph
    private static Map<String, Map<String, Double>> buildGraph(List<ConversionRate> rates) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (ConversionRate rate : rates) {
            graph.putIfAbsent(rate.from, new HashMap<>());
            graph.putIfAbsent(rate.to, new HashMap<>());
            graph.get(rate.from).put(rate.to, rate.rate);
            graph.get(rate.to).put(rate.from, 1 / rate.rate);
        }
        return graph;
    }

    // Method to find the conversion rate using BFS
    private static double findConversionRate(Map<String, Map<String, Double>> graph, String from, String to) {
        if (from.equals(to)) {
            return 1.0; // If the currencies are the same, the rate is 1.0
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(from, 1.0));
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            String currentCurrency = current.currency;
            double currentRate = current.rate;

            if (currentCurrency.equals(to)) {
                return currentRate; // Found the target currency
            }

            visited.add(currentCurrency);

            // Traverse all neighbors
            for (Map.Entry<String, Double> neighbor : graph.getOrDefault(currentCurrency, new HashMap<>()).entrySet()) {
                String nextCurrency = neighbor.getKey();
                double nextRate = neighbor.getValue();

                if (!visited.contains(nextCurrency)) {
                    queue.add(new Pair(nextCurrency, currentRate * nextRate));
                }
            }
        }

        return -1.0; // If no path is found
    }

    // Wrapper method to get the conversion rate
    public static double getConversionRate(List<ConversionRate> rates, String[] fromTo) {
        String from = fromTo[0];
        String to = fromTo[1];
        Map<String, Map<String, Double>> graph = buildGraph(rates);
        return findConversionRate(graph, from, to);
    }

    // Helper class to represent a conversion rate
    static class ConversionRate {
        String from;
        String to;
        double rate;

        ConversionRate(String from, String to, double rate) {
            this.from = from;
            this.to = to;
            this.rate = rate;
        }
    }

    // Helper class to represent a pair of currency and rate
    static class Pair {
        String currency;
        double rate;

        Pair(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
        }
    }
}