
public class Blob {
    
    private String content; 

    public Blob(String content){
        this.content = content;        
    }

    public void catFile(){
        System.out.println(content);
    }

}
