package commandLineView;

import commandLineView.Command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private final Map<Integer, Command> commands;
    public TextMenu(){ commands=new HashMap<>(); }
    public void addCommand(Command c){ commands.put(Integer.parseInt(c.getKey()),c);}
    private void printMenu(){
        for(Command com : commands.values()){
            String line=String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
        System.out.println("Obs: An example can be run only once.");
    }
    public void show(){
    Scanner scanner = new Scanner(System.in);
    while(true){
        printMenu();
        System.out.println("Input the option: ");
        String key = scanner.nextLine();
        Command command = commands.get(Integer.parseInt(key));
        if(command == null){
            System.out.println("Invalid Option");
            continue;
        }
        command.execute();
    }
    }
}