import java.util.ArrayList;
import java.util.List;

public class Tree {

    private List<Blob>blobs;
    private List<Tree>trees;
    private List<String>blob_name;
    private List<String>tree_name;

    public Tree(){
        blobs = new ArrayList<>();
        trees = new ArrayList<>();
        blob_name = new ArrayList<>();
        tree_name = new ArrayList<>();

    }


    public void addBlob(Blob blob, String name){ //add blob object from staging area to tree object
        blobs.add(blob);
        blob_name.add(name);
    }

    public void addTree(Tree tree, String name){ //add tree object from staging area to tree object
        trees.add(tree);
        tree_name.add(name);
    }

    public void catFile(){
        for(int i = 0; i < blob_name.size(); i++){
            System.out.println(blob_name.get(i));
        }

        for(int i = 0; i < tree_name.size(); i++){
            System.out.println(tree_name.get(i));
        }
    }
    
}
