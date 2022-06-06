
public class Commit {
    
    private String message;
    private String author;
    private String commiter;
    private String date;
    private Tree tree;
    private int hash;
    private Commit child;

    public Commit(Tree tree, String message, String author, String commiter, String date){
        this.tree = tree;

        this.message = message;
        this.author = author;
        this.commiter = commiter;
        this.date = date;
        this.hash = 0;
        child = null;
        
    }

    public void setHash(int hash){
        this.hash = hash;
    }

    public int getHash(){
        return this.hash;
    }

    public void setChild(Commit child){
        this.child = child;
    }

    public Commit getChild(){
        return this.child;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getDate(){
        return this.date;
    }

    public String getMessage(){
        return this.message;
    }

    public void catFile(){
        System.out.println("tree "+tree.getHash());
        System.out.println("author "+author);
        System.out.println("commiter "+commiter);
        System.out.println(message);

    }

}
