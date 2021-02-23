package commandLineView;

import Controller.Controller;
import GUI.HardcodedPrograms;
import Model.ProgramState;
import Model.Statement.Statement;
import Repository.Repository;
import Repository.memoryRepository;
import commandLineView.Command.ExitCommand;
import commandLineView.Command.RunExample;

import java.util.ArrayList;

public class oldMain {

    public static void main(String[] args) {
        HardcodedPrograms.deletePreviousLogs();
        ArrayList<Statement> programs = HardcodedPrograms.getHardcodedPrograms();
        ProgramState program1 = new ProgramState(programs.get(0));
        Repository repository1 = new memoryRepository(program1, "log1.txt");
        Controller controller1 = new Controller(repository1);

        ProgramState program2 = new ProgramState(programs.get(1));
        Repository repository2 = new memoryRepository(program2, "log2.txt");
        Controller controller2 = new Controller(repository2);

        ProgramState program3 = new ProgramState(programs.get(2));
        Repository repository3 = new memoryRepository(program3, "log3.txt");
        Controller controller3 = new Controller(repository3);

        ProgramState program4 = new ProgramState(programs.get(3));
        Repository repository4 = new memoryRepository(program4, "log4.txt");
        Controller controller4 = new Controller(repository4);

        ProgramState program5 = new ProgramState(programs.get(4));
        Repository repository5 = new memoryRepository(program5, "log5.txt");
        Controller controller5 = new Controller(repository5);

        ProgramState program6 = new ProgramState(programs.get(5));
        Repository repository6 = new memoryRepository(program6, "log6.txt");
        Controller controller6 = new Controller(repository6);

        ProgramState program7 = new ProgramState(programs.get(6));
        Repository repository7 = new memoryRepository(program7, "log7.txt");
        Controller controller7 = new Controller(repository7);

       ProgramState program8 = new ProgramState(programs.get(7));
        Repository repository8 = new memoryRepository(program8, "log8.txt");
        Controller controller8 = new Controller(repository8);

        ProgramState program9 = new ProgramState(programs.get(8));
        Repository repository9 = new memoryRepository(program9, "log9.txt");
        Controller controller9 = new Controller(repository9);

        ProgramState program10 = new ProgramState(programs.get(9));
        Repository repository10 = new memoryRepository(program10, "log10.txt");
        Controller controller10 = new Controller(repository10);

        ProgramState program11 = new ProgramState(programs.get(10));
        Repository repository11 = new memoryRepository(program11, "log11.txt");
        Controller controller11 = new Controller(repository11);

        ProgramState program12 = new ProgramState(programs.get(11));
        Repository repository12 = new memoryRepository(program12, "log12.txt");
        Controller controller12 = new Controller(repository12);

        ProgramState program13 = new ProgramState(programs.get(12));
        Repository repository13 = new memoryRepository(program13, "log13.txt");
        Controller controller13 = new Controller(repository13);


        ProgramState program14 = new ProgramState(programs.get(13));
        Repository repository14 = new memoryRepository(program14, "log14.txt");
        Controller controller14 = new Controller(repository14);

        ProgramState program15 = new ProgramState(programs.get(14));
        Repository repository15 = new memoryRepository(program15, "log15.txt");
        Controller controller15 = new Controller(repository15);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", programs.get(0).toString(), controller1));
        menu.addCommand(new RunExample("2", programs.get(1).toString(), controller2));
        menu.addCommand(new RunExample("3", programs.get(2).toString(), controller3));
        menu.addCommand(new RunExample("4", programs.get(3).toString(), controller4));
        menu.addCommand(new RunExample("5", programs.get(4).toString(), controller5));
        menu.addCommand(new RunExample("6", programs.get(5).toString(), controller6));
        menu.addCommand(new RunExample("7", programs.get(6).toString(), controller7));
        menu.addCommand(new RunExample("8", programs.get(7).toString(), controller8));
        menu.addCommand(new RunExample("9", programs.get(8).toString(), controller9));
        menu.addCommand(new RunExample("10", programs.get(9).toString(), controller10));
        menu.addCommand(new RunExample("11", programs.get(10).toString(), controller11));
        menu.addCommand(new RunExample("12", programs.get(11).toString(), controller12));
        menu.addCommand(new RunExample("13", programs.get(12).toString(), controller13));
        menu.addCommand(new RunExample("14", programs.get(13).toString(), controller14));
        menu.addCommand(new RunExample("15", programs.get(14).toString(), controller15));
        menu.show();
    }
}
