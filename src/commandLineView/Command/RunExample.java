package commandLineView.Command;

import Controller.Controller;
import CustomException.TypecheckException;

public class RunExample extends  Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.typecheck();
            controller.allStepExecution();
        } catch (Exception exception) {
            if (exception instanceof TypecheckException) {
                System.out.println("Typechecking failed: " + exception.getMessage());
            } else {
                System.out.println("ERROR! " + exception.getMessage());
            }
        }
    }
}
