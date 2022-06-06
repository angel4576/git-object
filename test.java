import java.text.SimpleDateFormat;

public class test {
    public static void main(String[] args) {
        /*-----------Blob------------- */
        //add content of file.txt as an object
        System.out.println("git hash-object -w file.txt");
        Blob blob = new Blob("Version 1");
        blob.setHash(blob.hashCode());
        System.out.println(blob.getHash());//hash value for the object
        
        System.out.println("git cat-file -p"); //check object content
        blob.catFile();//file content

        //write new content to same file
        System.out.println("git hash-object -w file.txt");
        //git adds content of every version of a file to an object
        Blob blob2 = new Blob("Version 2");
        blob2.setHash(blob2.hashCode());
        System.out.println(blob2.getHash());

        System.out.println("git cat-file -p");
        blob2.catFile();//file content


        /*------------Tree-------------*/
        System.out.println();
        //add files to staging area
        //create a tree object based on files in staging area
        Tree tree = new Tree();
        tree.addBlob(blob, "file.txt"); //store content in staging area to tree object
        tree.addBlob(new Blob("new file"), "new.txt"); //add a new file
        tree.setHash(tree.hashCode());

        System.out.println("git write-tree");
        System.out.println(tree.getHash());//return hash value

        System.out.println("git cat-file -p");//check tree content
        tree.catFile();

        //add a tree object to staging area
        Tree subtree = new Tree();
        //add a tree object to the tree object
        subtree.addBlob(new Blob("subtree file"), "sub.txt");
        tree.addTree(subtree, "dir");

        System.out.println("git cat-file -p");//check tree content
        tree.catFile();

        System.out.println();
        /*------------commit object----------------- */
        //commit object 1
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        Commit commit = new Commit(tree, "first commit", "zih17", "zih17", timeStamp);

        System.out.println("git commit-tree");
        commit.setHash(commit.hashCode());
        System.out.println(commit.getHash());

        System.out.println("git cat-file -p");//check commit content
        commit.catFile();


        //second commit 
        Tree newTree = new Tree();
        newTree.addBlob(new Blob("newTree file"), "newTree.txt");
        String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        Commit commit2 = new Commit(tree, "second commit", "zih17", "zih17", timeStamp2);
        commit2.setHash(commit2.hashCode());

        commit2.setChild(commit);

        //Two commit objects
        System.out.println();        
        System.out.println("git log");

        System.out.println("commit "+commit2.getHash());
        System.out.println("Author: "+commit2.getAuthor());
        System.out.println("Date: "+commit2.getDate());
        System.out.println(commit2.getMessage());

        System.out.println();

        System.out.println("commit "+commit2.getChild().getHash());
        System.out.println("Author: "+commit2.getChild().getAuthor());
        System.out.println("Date: "+commit2.getChild().getDate());
        System.out.println(commit2.getChild().getMessage());
        
    }
    
}
