
public final class Blob implements Cloneable{
    
    private final String content; 

    public Blob(String content){
        this.content = content;        
    }

    public String getContent(){
        return this.content;
    }

    public void catFile(){
        System.out.println(content);
    }


}
