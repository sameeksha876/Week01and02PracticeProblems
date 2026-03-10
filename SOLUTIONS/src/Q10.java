import java.util.*;


class Video {
    String id;
    String data;


    Video(String id, String data) {
        this.id = id;
        this.data = data;
    }
}


public class Q10 {


    private LinkedHashMap<String, Video> L1 =
            new LinkedHashMap<>(10000,0.75f,true) {
                protected boolean removeEldestEntry(Map.Entry e) {
                    return size() > 10000;
                }
            };


    private HashMap<String, Video> L2 = new HashMap<>();


    private HashMap<String, Video> database = new HashMap<>();


    public Q10() {


        database.put("video123", new Video("video123","MovieData"));
        database.put("video999", new Video("video999","OtherMovie"));
    }


    public Video getVideo(String id) {


        if (L1.containsKey(id)) {
            System.out.println("L1 Cache HIT (0.5ms)");
            return L1.get(id);
        }


        if (L2.containsKey(id)) {
            System.out.println("L2 Cache HIT (5ms)");
            Video v = L2.get(id);
            L1.put(id,v);
            return v;
        }


        System.out.println("L3 Database HIT (150ms)");


        Video v = database.get(id);


        if (v != null) {
            L2.put(id,v);
        }


        return v;
    }


    public static void main(String[] args) {


        Q10 cache = new Q10();


        cache.getVideo("video123");
        cache.getVideo("video123");


        cache.getVideo("video999");
    }
}
