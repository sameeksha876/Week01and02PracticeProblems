package Week01_02;

import java.util.*;


class DNSEntry {
    String ipAddress;
    long expiryTime;


    public DNSEntry(String ipAddress, long ttlSeconds) {
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
    }


    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}


public class Q3 {


    private final int MAX_CACHE_SIZE = 5;


    // LRU Cache using LinkedHashMap
    private LinkedHashMap<String, DNSEntry> cache =
            new LinkedHashMap<>(16, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                    return size() > MAX_CACHE_SIZE;
                }
            };


    private int cacheHits = 0;
    private int cacheMisses = 0;


    // Resolve domain
    public String resolve(String domain) {


        if (cache.containsKey(domain)) {


            DNSEntry entry = cache.get(domain);


            if (!entry.isExpired()) {
                cacheHits++;
                System.out.println("Cache HIT → " + entry.ipAddress);
                return entry.ipAddress;
            }


            // expired entry
            cache.remove(domain);
            System.out.println("Cache EXPIRED → querying upstream");
        }


        // Cache miss
        cacheMisses++;
        String ip = queryUpstreamDNS(domain);


        // TTL example 10 seconds
        cache.put(domain, new DNSEntry(ip, 10));


        System.out.println("Cache MISS → " + ip);


        return ip;
    }


    // Simulated upstream DNS
    private String queryUpstreamDNS(String domain) {


        // generate random IP for simulation
        Random rand = new Random();
        return "172.217.14." + rand.nextInt(255);
    }


    // Cache statistics
    public void getCacheStats() {


        int total = cacheHits + cacheMisses;
        double hitRate = (total == 0) ? 0 : (cacheHits * 100.0 / total);


        System.out.println("Cache Hits: " + cacheHits);
        System.out.println("Cache Misses: " + cacheMisses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }


    // Test program
    public static void main(String[] args) throws InterruptedException {


        Q3 dns = new Q3();


        dns.resolve("google.com");   // miss
        dns.resolve("google.com");   // hit
        dns.resolve("facebook.com"); // miss
        dns.resolve("google.com");   // hit


        dns.getCacheStats();


        // wait for TTL expiry
        Thread.sleep(11000);


        dns.resolve("google.com");   // expired → new query
    }
}
