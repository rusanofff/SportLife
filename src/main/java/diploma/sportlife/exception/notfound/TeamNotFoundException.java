package diploma.sportlife.exception.notfound;

public class TeamNotFoundException extends NotFoundException{
    public TeamNotFoundException() {
        super("Team not found");
    }
}
