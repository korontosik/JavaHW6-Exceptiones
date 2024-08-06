import HW6.Controller.Checks;
import HW6.Controller.CreateFile;
import HW6.View.View;

public class Program {
    public static void main(String[] args) {
        Checks checks = new Checks();
        View view = new View();
        CreateFile create = new CreateFile(checks, view);
        
        create.start();
    }

}
