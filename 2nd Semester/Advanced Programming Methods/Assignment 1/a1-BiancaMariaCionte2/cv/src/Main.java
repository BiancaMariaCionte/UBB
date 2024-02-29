import repository.Repository;
import service.Service;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository();
        Service serv = new Service(repo);
        UI ui = new UI(serv);
        serv.addPatient(1000, "Cionte Bianca", "27/03/2004");
        serv.addPatient(1001, "Pavel Andra", "15/05/2003");
        serv.addPatient(1002, "Pop Cristina", "20/05/2003");

        ui.run();
    }
}