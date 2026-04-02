package Week01_02;

import java.util.*;


class TokenBucket {


    int tokens;
    int maxTokens;
    long lastRefillTime;
    double refillRate; // tokens per second


    public TokenBucket(int maxTokens, double refillRate) {
        this.maxTokens = maxTokens;
        this.tokens = maxTokens;
        this.refillRate = refillRate;
        this.lastRefillTime = System.currentTimeMillis();
    }


    // refill tokens based on elapsed time
    private void refill() {


        long now = System.currentTimeMillis();
        double seconds = (now - lastRefillTime) / 1000.0;


        int refillTokens = (int)(seconds * refillRate);


        if (refillTokens > 0) {
            tokens = Math.min(maxTokens, tokens + refillTokens);
            lastRefillTime = now;
        }
    }


    // consume token
    public synchronized boolean allowRequest() {


        refill();


        if (tokens > 0) {
            tokens--;
            return true;
        }


        return false;
    }


    public int getRemainingTokens() {
        return tokens;
    }
}


public class Q6 {


    // clientId -> Week01_02.TokenBucket
    private HashMap<String, TokenBucket> clients = new HashMap<>();


    private final int LIMIT = 1000; // requests per hour


    public String checkRateLimit(String clientId) {


        clients.putIfAbsent(clientId,
                new TokenBucket(LIMIT, LIMIT / 3600.0));


        TokenBucket bucket = clients.get(clientId);


        if (bucket.allowRequest()) {


            return "Allowed (" +
                    bucket.getRemainingTokens() +
                    " requests remaining)";
        }


        return "Denied (0 requests remaining)";
    }


    public void getRateLimitStatus(String clientId) {


        TokenBucket bucket = clients.get(clientId);


        if (bucket == null) {
            System.out.println("Client not found");
            return;
        }


        int used = LIMIT - bucket.getRemainingTokens();


        System.out.println("{used: " + used +
                ", limit: " + LIMIT +
                ", remaining: " + bucket.getRemainingTokens() + "}");
    }


    public static void main(String[] args) {


        Q6 limiter = new Q6();


        System.out.println(limiter.checkRateLimit("abc123"));
        System.out.println(limiter.checkRateLimit("abc123"));
        System.out.println(limiter.checkRateLimit("abc123"));


        limiter.getRateLimitStatus("abc123");
    }
}
