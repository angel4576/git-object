import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*-----------Blob------------- */
        String content1 = "Version 1"; //echo 'Version 1' > test.txt
        Blob blob = new Blob(content1);
        //add content of file.txt as an object to database
        System.out.println("git hash-object -w test.txt"); //gives the unique key that refers to the data object
        System.out.println(blob.hashCode());//hash value for the object
        
        System.out.println("git cat-file -p"); //check object content
        blob.catFile();//file content

        //write new content to same file and add it to database
        //the first content still exists
        String content2 = "Version 2"; //echo 'Version 2' > test.txt
        Blob blob2 = new Blob(content2);
        System.out.println("git hash-object -w test.txt");
        System.out.println(blob2.hashCode());

        System.out.println("git cat-file -p");
        blob2.catFile();//file content


        /*------------Tree-------------*/
        System.out.println();      
        HashMap<String, Blob>blobs1 = new HashMap<>();
        HashMap<String, Tree>trees1 = new HashMap<>();
        blobs1.put("test.txt", blob);
        System.out.println("git write-tree");//write the content in staging area to a tree object
        
        //create a tree object based on files in staging area
        Tree tree = new Tree(blobs1, trees1);
        System.out.println(tree.hashCode());//return hash value

        System.out.println("git cat-file -p");//check tree content
        tree.catFile();

        //new file in staging area (new.txt)
        //test.txt (Version 2) in staging area
        String newContent = "new file";
        Blob newBlob = new Blob(newContent);

        HashMap<String, Blob>blobs2 = new HashMap<>();
        HashMap<String, Tree>trees2 = new HashMap<>();
        blobs2.put("new.txt", newBlob);
        blobs2.put("test.txt", blob2);

        System.out.println("git write-tree"); //write files in staging area into tree object
        //create a new tree object
        Tree newTree = new Tree(blobs2, trees2);
        System.out.println(newTree.hashCode());
        System.out.println("git cat-file -p");//check new tree content
        newTree.catFile();

        HashMap<String, Blob>blobs3 = new HashMap<>();
        HashMap<String, Tree>subTree = new HashMap<>();
        subTree.put("bak", tree);
        blobs3.put("new.txt", newBlob);
        blobs3.put("test.txt", blob2);

        //git read-tree: read tree object to staging area
        //git write-tree: write tree object in staging area into new tree object
        System.out.println("git write-tree");
        Tree superTree = new Tree(blobs3, subTree);
        System.out.println("git cat-file -p");//check tree content
        superTree.catFile();


        System.out.println();
        /*------------commit object----------------- */
        //commit object 1
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        Commit commit = new Commit(tree, "first commit", "zih17", "zih17", timeStamp, null); //includes the first created tree object

        System.out.println("git commit-tree");
        System.out.println(commit.hashCode());

        System.out.println("git cat-file -p");//check commit content
        commit.catFile();

        //second commit, referencing the commit before it
        String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        Commit commit2 = new Commit(newTree, "second commit", "zih17", "zih17", timeStamp2, commit); //reference commit object 1
        //third commit
        String timeStamp3 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        Commit commit3 = new Commit(superTree, "third commit", "zih17", "zih17", timeStamp3, commit2); //reference commit object 2

        System.out.println();        
        System.out.println("git log"); //git commit history

        int num_of_commit = 3;
        Commit cur = commit3;
        for(int i = 0; i < num_of_commit; i++){
            System.out.println("commit "+cur.hashCode());
            System.out.println("Author: "+cur.getAuthor());
            System.out.println("Date: "+cur.getDate());
            System.out.println(cur.getMessage());

            cur = cur.getPrev();
        }

        //superTree.getTrees().get("bak").catFile();

        //test tree immutability | output should be the same
        /*
        System.out.println();
        tree.catFile(); // blobs1, trees1
        blobs1.put("new", new Blob("new test file"));
        tree.catFile();
        //test get
        HashMap<String, Blob>testBlobs = tree.getBlobs();
        testBlobs.put("new2", new Blob("new test file 2"));
        tree.catFile();
        */
    }
    
}
