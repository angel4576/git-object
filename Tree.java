import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class Tree {

    private final HashMap<String, Blob>blobs;
    private final HashMap<String, Tree>trees;   

    public Tree(HashMap<String, Blob>blobs, HashMap<String, Tree>trees) {
        this.blobs = new HashMap<>();
        this.trees = new HashMap<>();

        Iterator<String>blob_it = blobs.keySet().iterator();
        Iterator<String>tree_it = trees.keySet().iterator();

        String key;
        while(blob_it.hasNext()){
            key = blob_it.next();
            this.blobs.put(key, blobs.get(key));
        }

        while(tree_it.hasNext()){
            key = tree_it.next();
            this.trees.put(key, trees.get(key));
        }

    }

    public HashMap<String, Blob> getBlobs(){
        //return this.blobs;
        return (HashMap<String, Blob>) this.blobs.clone();
    }

    public HashMap<String, Tree> getTrees(){
        return (HashMap<String, Tree>) this.trees.clone();
    }

    public void catFile(){
        for(Map.Entry<String, Blob>entry : blobs.entrySet()){
            String name = entry.getKey();
            Blob blob = entry.getValue();
            System.out.println(name);
        }

        for(Map.Entry<String, Tree>entry : trees.entrySet()){
            String name = entry.getKey();
            Tree tree = entry.getValue();
            System.out.println(name);
        }
    }

    
}
