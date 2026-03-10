import java.util.*;


class PageEvent {
    String url;
    String userId;
    String source;


    public PageEvent(String url, String userId, String source) {
        this.url = url;
        this.userId = userId;
        this.source = source;
    }
}


public class Q5 {


    // pageUrl -> visit count
    private HashMap<String, Integer> pageViews = new HashMap<>();


    // pageUrl -> unique visitors
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();


    // source -> visit count
    private HashMap<String, Integer> trafficSources = new HashMap<>();




    // Process incoming event
    public void processEvent(PageEvent event) {


        // update page views
        pageViews.put(event.url,
                pageViews.getOrDefault(event.url, 0) + 1);


        // update unique visitors
        uniqueVisitors.putIfAbsent(event.url, new HashSet<>());
        uniqueVisitors.get(event.url).add(event.userId);


        // update traffic sources
        trafficSources.put(event.source,
                trafficSources.getOrDefault(event.source, 0) + 1);
    }




    // Get top 10 pages
    public List<Map.Entry<String, Integer>> getTopPages() {


        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(
                        (a, b) -> b.getValue() - a.getValue()
                );


        pq.addAll(pageViews.entrySet());


        List<Map.Entry<String, Integer>> topPages = new ArrayList<>();


        int count = 0;


        while (!pq.isEmpty() && count < 10) {
            topPages.add(pq.poll());
            count++;
        }


        return topPages;
    }




    // Display dashboard
    public void getDashboard() {


        System.out.println("Top Pages:");


        List<Map.Entry<String, Integer>> topPages = getTopPages();


        for (int i = 0; i < topPages.size(); i++) {


            String url = topPages.get(i).getKey();
            int views = topPages.get(i).getValue();
            int unique = uniqueVisitors.get(url).size();


            System.out.println(
                    (i + 1) + ". " + url +
                            " - " + views + " views (" +
                            unique + " unique)"
            );
        }




        System.out.println("\nTraffic Sources:");


        int total = trafficSources.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();


        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {


            double percent = (entry.getValue() * 100.0) / total;


            System.out.println(
                    entry.getKey() + ": " +
                            String.format("%.1f", percent) + "%"
            );
        }
    }




    // Test program
    public static void main(String[] args) {


        Q5 analytics = new Q5();


        analytics.processEvent(new PageEvent(
                "/article/breaking-news", "user_123", "google"));


        analytics.processEvent(new PageEvent(
                "/article/breaking-news", "user_456", "facebook"));


        analytics.processEvent(new PageEvent(
                "/sports/championship", "user_123", "direct"));


        analytics.processEvent(new PageEvent(
                "/article/breaking-news", "user_789", "google"));


        analytics.getDashboard();
    }
}
