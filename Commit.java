
public final class Commit {
    
    private final String message;
    private final String author;
    private final String commiter;
    private final String date;
    private final Tree tree;
    private final Commit prev;

    public Commit(Tree tree, String message, String author, String commiter, String date, Commit prev) {
        this.tree = tree;
        this.message = message;
        this.author = author;
        this.commiter = commiter;
        this.date = date;
        this.prev = prev;
    }

    public Commit getPrev(){
        return this.prev;
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

    public Tree getTree() throws CloneNotSupportedException{
        return this.tree;
    }

    public void catFile(){
        System.out.println("tree "+tree.hashCode());
        System.out.println("author "+author);
        System.out.println("commiter "+commiter);
        System.out.println(message);
    }

}
