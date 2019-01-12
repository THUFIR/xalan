package xalan;

public class App {

    private X x = new X();
    
    public static void main(String[] args) throws Exception {
        new App().transform();
    }

    public void transform() throws Exception {
        x.transform();
    }
}
