
public class Blob {
    
    private String content; 
    private int hash;

    public Blob(String content){
        this.content = content;
        this.hash = 0;
        
    }

    public void setHash(int hash){
        this.hash = hash;
    }

    public int getHash(){
        return this.hash;
    }

    public void catFile(){
        System.out.println(content);
    }

}
