package GUI;

import Model.Expression.*;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntegerType;
import Model.Type.ReferenceType;
import Model.Type.StringType;
import Model.Value.*;

import javax.management.ValueExp;
import java.io.File;
import java.util.ArrayList;

public class HardcodedPrograms {

    public static ArrayList<Statement> getHardcodedPrograms(){
        ArrayList<Statement> programs = new ArrayList<>();

        Statement ex1 = new CompoundStatement(new VariableDeclarationStatement("v",new IntegerType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new NumberValue(2))),
                        new PrintStatement(new VariableNameExpression("v"))));
        programs.add(ex1);

        Statement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new NumberValue(2)),
                                new ArithmeticExpression('*',new ValueExpression(new NumberValue(3)), new ValueExpression(new NumberValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableNameExpression("a"),
                                        new ValueExpression(new NumberValue(1)))), new PrintStatement(new VariableNameExpression("b"))))));
        programs.add(ex2);

        Statement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new ConditionalStatement(new VariableNameExpression("a"),new AssignmentStatement("v",
                                        new ValueExpression(new NumberValue(2))), new AssignmentStatement("v",
                                        new ValueExpression(new NumberValue(3)))), new PrintStatement(new VariableNameExpression("v"))))));
        programs.add(ex3);

        Statement ex4 = new CompoundStatement(new VariableDeclarationStatement("a", new IntegerType()), new AssignmentStatement("a", new ValueExpression(new NumberValue(5))));
        programs.add(ex4);

        Statement ex5 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()), new AssignmentStatement("a", new ValueExpression(new NumberValue(5))));
        programs.add(ex5);

        Statement ex6 = new CompoundStatement(
                new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()), new AssignmentStatement("a", new ValueExpression(new BooleanValue(false)))),
                new PrintStatement(new LogicExpression('&', new VariableNameExpression("a"), new ValueExpression(new BooleanValue(true)))));
        programs.add(ex6);

        Statement ex7 = new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(
                new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in")))),
                new OpenReadFileStatement(new VariableNameExpression("varf"))), new VariableDeclarationStatement("varc", new IntegerType())),
                new ReadNumberFromFile(new VariableNameExpression("varf"), "varc")), new PrintStatement(new VariableNameExpression("varc"))),
                new ReadNumberFromFile(new VariableNameExpression("varf"), "varc")), new PrintStatement(new VariableNameExpression("varc"))),
                new CloseReadFileStatement(new VariableNameExpression("varf")));
        programs.add(ex7);

        Statement ex8 = new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(
                new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test2.in")))),
                new OpenReadFileStatement(new VariableNameExpression("varf"))), new VariableDeclarationStatement("varc", new IntegerType())),
                new ReadNumberFromFile(new VariableNameExpression("varf"), "varc")), new PrintStatement(new VariableNameExpression("varc"))),
                new ReadNumberFromFile(new VariableNameExpression("varf"), "varc")), new PrintStatement(new VariableNameExpression("varc"))),
                new CloseReadFileStatement(new VariableNameExpression("varf")));
        programs.add(ex8);

        Statement ex9 = new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())), new HeapAllocationStatement("v", new ValueExpression(new NumberValue(20)))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntegerType())))), new HeapAllocationStatement("a", new VariableNameExpression("v"))), new PrintStatement(new VariableNameExpression("v"))), new PrintStatement(new VariableNameExpression("a")));
        programs.add(ex9);

        Statement ex10 = new CompoundStatement(
                new CompoundStatement(
                        new CompoundStatement(
                                new CompoundStatement(
                                        new CompoundStatement(
                                                new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())),
                                                new HeapAllocationStatement("v", new ValueExpression(new NumberValue(20)))),
                                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntegerType())))),
                                new HeapAllocationStatement("a", new VariableNameExpression("v"))),
                        new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v")))),
                new PrintStatement(new ArithmeticExpression('+',new ReadHeapExpression(new ReadHeapExpression(new VariableNameExpression("a"))), new ValueExpression(new NumberValue(5)))));
        programs.add(ex10);

        Statement ex11 = new CompoundStatement(
                new CompoundStatement(
                        new CompoundStatement(
                                new CompoundStatement(
                                        new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())),
                                        new HeapAllocationStatement("v", new ValueExpression(new NumberValue(20)))),
                                new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v")))),
                        new WriteHeapStatement("v", new ValueExpression(new NumberValue(30)))),
                new PrintStatement(new ArithmeticExpression('+', new ReadHeapExpression(new VariableNameExpression("v")), new ValueExpression(new NumberValue(5)))));
        programs.add(ex11);

        Statement ex12 = new CompoundStatement(
                new CompoundStatement(
                        new CompoundStatement(
                                new CompoundStatement(
                                        new CompoundStatement(
                                                new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())), new HeapAllocationStatement("v", new ValueExpression(new NumberValue(20)))),
                                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntegerType())))),
                                new HeapAllocationStatement("a", new VariableNameExpression("v"))),
                        new HeapAllocationStatement("v", new ValueExpression(new NumberValue(30)))),
                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableNameExpression("a")))));
        programs.add(ex12);

        Statement ex13 = new CompoundStatement(
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntegerType()), new AssignmentStatement("v", new ValueExpression(new NumberValue(0)))),
                new WhileStatement(new RelationalExpression("<", new VariableNameExpression("v"), new ValueExpression(new NumberValue(5))), new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new AssignmentStatement("v", new ArithmeticExpression('+', new VariableNameExpression("v"), new ValueExpression(new NumberValue(1)))))));
        programs.add(ex13);

        Statement ex14 = new CompoundStatement(new VariableDeclarationStatement("v",new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntegerType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new NumberValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new NumberValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(
                                                new WriteHeapStatement("a",new ValueExpression(new NumberValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new NumberValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableNameExpression("v")),
                                                                new PrintStatement(new ReadHeapExpression(new VariableNameExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableNameExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VariableNameExpression("a")))))))));
        programs.add(ex14);

        Statement ex15 = new CompoundStatement(new VariableDeclarationStatement("a", new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new NumberValue(1))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new NumberValue(100))),
                                        new CompoundStatement(
                                                new ForkStatement(new WhileStatement(new RelationalExpression("<", new VariableNameExpression("a"), new ValueExpression(new NumberValue(100))),
                                                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new VariableNameExpression("a"), new ValueExpression(new NumberValue(1)))),
                                                                new AssignmentStatement("b", new ArithmeticExpression('-', new VariableNameExpression("b"), new ValueExpression(new NumberValue(1))))))),
                                                new WhileStatement(new RelationalExpression("!=", new VariableNameExpression("a"), new ValueExpression(new NumberValue(0))), new NopStatement())
                                        )))));
        programs.add(ex15);

        Statement ex16 = new CompoundStatement(new VariableDeclarationStatement("a", new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntegerType()),
                        new CompoundStatement(new VariableDeclarationStatement("c", new IntegerType()),
                                new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new NumberValue(1))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new NumberValue(2))),
                                        new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new NumberValue(5))),
                                                new CompoundStatement(
                                                new SwitchStatement(new ArithmeticExpression('*', new VariableNameExpression("a"),
                                                        new ValueExpression(new NumberValue(10))),
                                                        new ArithmeticExpression('*', new VariableNameExpression("b"), new VariableNameExpression("c")),
                                                        new CompoundStatement(new PrintStatement(new VariableNameExpression("a")), new PrintStatement(new VariableNameExpression("b"))),
                                                        new ValueExpression(new NumberValue(10)), new CompoundStatement(new PrintStatement(new ValueExpression(new NumberValue(100))), new PrintStatement(new ValueExpression(new NumberValue(200)))),
                                                        new PrintStatement(new ValueExpression(new NumberValue(300)))), new PrintStatement(new ValueExpression(new NumberValue(300))))))))));
        programs.add(ex16);

        Statement ex17 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntegerType()),
                        new CompoundStatement(new VariableDeclarationStatement("c", new IntegerType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new NumberValue(2))),
                                                new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new NumberValue(5))),
                                                        new CompoundStatement(
                                                                new SwitchStatement(new VariableNameExpression("a"),
                                                                        new ArithmeticExpression('*', new VariableNameExpression("b"), new VariableNameExpression("c")),
                                                                        new CompoundStatement(new PrintStatement(new VariableNameExpression("a")), new PrintStatement(new VariableNameExpression("b"))),
                                                                        new ValueExpression(new NumberValue(10)), new CompoundStatement(new PrintStatement(new ValueExpression(new NumberValue(100))), new PrintStatement(new ValueExpression(new NumberValue(200)))),
                                                                        new PrintStatement(new ValueExpression(new NumberValue(300)))), new PrintStatement(new ValueExpression(new NumberValue(300))))))))));
        programs.add(ex17);

        Statement ex18 = new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntegerType())),
                new CompoundStatement(new VariableDeclarationStatement("b", new ReferenceType(new IntegerType())),
                        new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompoundStatement(
                                new HeapAllocationStatement("a", new ValueExpression(new NumberValue(0))),
                                new CompoundStatement(new HeapAllocationStatement("b", new ValueExpression(new NumberValue(0))), new CompoundStatement(
                                        new WriteHeapStatement("a", new ValueExpression(new NumberValue(1))), new CompoundStatement(
                                                new WriteHeapStatement("b", new ValueExpression(new NumberValue(2))), new CompoundStatement(
                                                        new ConditionalAssignmentStatement("v", new RelationalExpression("<", new ReadHeapExpression(new VariableNameExpression("a")), new ReadHeapExpression(new VariableNameExpression("b"))),
                                                                new ValueExpression(new NumberValue(100)), new ValueExpression(new NumberValue(200))), new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new CompoundStatement(
                                                                        new ConditionalAssignmentStatement("v", new RelationalExpression(">", new ArithmeticExpression('-', new ReadHeapExpression(new VariableNameExpression("b")), new ValueExpression(new NumberValue(2))), new ReadHeapExpression(new VariableNameExpression("a"))),
                                                                                new ValueExpression(new NumberValue(100)), new ValueExpression(new NumberValue(200))), new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new NopStatement()
                                ))
                                )
                                ))))))));
        programs.add(ex18);

        Statement ex19 = new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntegerType())),
                new CompoundStatement(new VariableDeclarationStatement("b", new ReferenceType(new IntegerType())),
                        new CompoundStatement(new VariableDeclarationStatement("v", new BoolType()), new CompoundStatement(
                                new HeapAllocationStatement("a", new ValueExpression(new NumberValue(0))),
                                new CompoundStatement(new HeapAllocationStatement("b", new ValueExpression(new NumberValue(0))), new CompoundStatement(
                                        new WriteHeapStatement("a", new ValueExpression(new NumberValue(1))), new CompoundStatement(
                                        new WriteHeapStatement("b", new ValueExpression(new NumberValue(2))), new CompoundStatement(
                                        new ConditionalAssignmentStatement("v", new RelationalExpression("<", new ReadHeapExpression(new VariableNameExpression("a")), new ReadHeapExpression(new VariableNameExpression("b"))),
                                                new ValueExpression(new NumberValue(100)), new ValueExpression(new NumberValue(200))), new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new CompoundStatement(
                                        new ConditionalAssignmentStatement("v", new RelationalExpression(">", new ArithmeticExpression('-', new ReadHeapExpression(new VariableNameExpression("b")), new ValueExpression(new NumberValue(2))), new ReadHeapExpression(new VariableNameExpression("a"))),
                                                new ValueExpression(new NumberValue(100)), new ValueExpression(new NumberValue(200))), new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new NopStatement()
                                ))
                                )
                                ))))))));
        programs.add(ex19);

        Statement ex20 = new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntegerType())),
                new CompoundStatement(new VariableDeclarationStatement("b", new ReferenceType(new IntegerType())),
                        new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompoundStatement(
                                new HeapAllocationStatement("a", new ValueExpression(new NumberValue(0))),
                                new CompoundStatement(new HeapAllocationStatement("b", new ValueExpression(new NumberValue(0))), new CompoundStatement(
                                        new WriteHeapStatement("a", new ValueExpression(new NumberValue(1))), new CompoundStatement(
                                        new WriteHeapStatement("b", new ValueExpression(new NumberValue(2))), new CompoundStatement(
                                        new ConditionalAssignmentStatement("v", new RelationalExpression("<", new ReadHeapExpression(new VariableNameExpression("a")), new ReadHeapExpression(new VariableNameExpression("b"))),
                                                new ValueExpression(new NumberValue(100)), new ValueExpression(new NumberValue(200))), new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new CompoundStatement(
                                        new ConditionalAssignmentStatement("v", new VariableNameExpression("v"),
                                                new ValueExpression(new NumberValue(100)), new ValueExpression(new NumberValue(200))), new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new NopStatement()
                                ))
                                )
                                ))))))));
        programs.add(ex20);

        Statement ex21 = new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntegerType())), new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new NumberValue(20))), new CompoundStatement(
                new ForStatement("v", new ValueExpression(new NumberValue(0)), new ValueExpression(new NumberValue(3)), new ArithmeticExpression('+', new VariableNameExpression("v"), new ValueExpression(new NumberValue(1))), new ForkStatement(new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new AssignmentStatement("v", new ArithmeticExpression('*', new VariableNameExpression("v"), new ReadHeapExpression(new VariableNameExpression("a"))))))),
                new PrintStatement(new ReadHeapExpression(new VariableNameExpression("a"))))));
        programs.add(ex21);

        Statement ex22 = new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new NumberValue(0))),
                new CompoundStatement(new RepeatUntilStatement(new CompoundStatement(new ForkStatement(new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new AssignmentStatement("v", new ArithmeticExpression('-', new VariableNameExpression("v"), new ValueExpression(new NumberValue(1)))))), new AssignmentStatement("v", new ArithmeticExpression('+', new VariableNameExpression("v"), new ValueExpression(new NumberValue(1))))), new RelationalExpression("==", new VariableNameExpression("v"), new ValueExpression(new NumberValue(3)))),
                        new CompoundStatement(new PrintStatement(new ArithmeticExpression('*', new VariableNameExpression("v"), new ValueExpression(new NumberValue(10)))), new NopStatement()))));
        programs.add(ex22);

        Statement ex23 = new CompoundStatement(new VariableDeclarationStatement("v1", new IntegerType()), new CompoundStatement(new AssignmentStatement("v1", new ValueExpression(new NumberValue(2))),
                new CompoundStatement(new VariableDeclarationStatement("v2", new IntegerType()), new CompoundStatement(new AssignmentStatement("v2", new ValueExpression(new NumberValue(3))), new ConditionalStatement(
                        new RelationalExpression("!=", new VariableNameExpression("v1"), new ValueExpression(new NumberValue(0))), new PrintStatement(new MulExpression(new VariableNameExpression("v1"), new VariableNameExpression("v2"))), new PrintStatement(new VariableNameExpression("v1"))
                )))));
        programs.add(ex23);

        Statement ex24 = new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompoundStatement(
                new AssignmentStatement("v", new ValueExpression(new NumberValue(20))), new CompoundStatement(
                        new WaitStatement(new NumberValue(10)), new PrintStatement(new ArithmeticExpression('*', new VariableNameExpression("v"), new ValueExpression(new NumberValue(10))))
                ))
        );
        programs.add(ex24);

        Statement ex25 = new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()), new CompoundStatement(
                new AssignmentStatement("v", new ValueExpression(new NumberValue(0))), new CompoundStatement(
                        new WhileStatement(new RelationalExpression("<", new VariableNameExpression("v"), new ValueExpression(new NumberValue(3))),
                                new CompoundStatement(new ForkStatement(new CompoundStatement(new PrintStatement(new VariableNameExpression("v")), new AssignmentStatement("v", new ArithmeticExpression('+', new VariableNameExpression("v"), new ValueExpression(new NumberValue(1)))))), new AssignmentStatement("v", new ArithmeticExpression('+', new VariableNameExpression("v"), new ValueExpression(new NumberValue(1)))))),
                new CompoundStatement(new SleepStatement(new NumberValue(5)), new PrintStatement(new ArithmeticExpression('*', new VariableNameExpression("v"), new ValueExpression(new NumberValue(10))))))));
        programs.add(ex25);

        Statement ex26 = new CompoundStatement(
                new VariableDeclarationStatement("v1", new ReferenceType(new IntegerType())),
                new CompoundStatement(
                        new VariableDeclarationStatement("v2", new ReferenceType(new IntegerType())),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v3", new ReferenceType(new IntegerType())),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("cnt", new IntegerType()),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("v1", new ValueExpression(new NumberValue(2))),
                                                new CompoundStatement(
                                                        new HeapAllocationStatement("v2", new ValueExpression(new NumberValue(3))),
                                                        new CompoundStatement(
                                                                new HeapAllocationStatement("v3", new ValueExpression(new NumberValue(4))),
                                                                new CompoundStatement(
                                                                        new newLatch("cnt", new ReadHeapExpression(new VariableNameExpression("v2"))),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(
                                                                                                new WriteHeapStatement("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableNameExpression("v1")), new ValueExpression(new NumberValue(10)))),
                                                                                                new CompoundStatement(
                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v1"))),
                                                                                                        new CompoundStatement(
                                                                                                                new countDown("cnt"),
                                                                                                                        new ForkStatement(
                                                                                                                                new CompoundStatement(
                                                                                                                                        new WriteHeapStatement("v2", new ArithmeticExpression('*', new ReadHeapExpression(new VariableNameExpression("v2")), new ValueExpression(new NumberValue(10)))),
                                                                                                                                        new CompoundStatement(
                                                                                                                                                new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v2"))),
                                                                                                                                                new CompoundStatement(
                                                                                                                                                        new countDown("cnt"),
                                                                                                                                                        new ForkStatement(
                                                                                                                                                                new CompoundStatement(
                                                                                                                                                                        new WriteHeapStatement("v3", new ArithmeticExpression('*', new ReadHeapExpression(new VariableNameExpression("v3")), new ValueExpression(new NumberValue(10)))),
                                                                                                                                                                        new CompoundStatement(
                                                                                                                                                                                new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v3"))),
                                                                                                                                                                                new countDown("cnt")
                                                                                                                                                                        )
                                                                                                                                                                )
                                                                                                                                                        )
                                                                                                                                                )
                                                                                                                                        )
                                                                                                                                )
                                                                                                                        )
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompoundStatement(
                                                                                        new await("cnt"),
                                                                                        new CompoundStatement(
                                                                                                new PrintStatement(new ValueExpression(new NumberValue(100))),
                                                                                                new CompoundStatement(
                                                                                                        new countDown("cnt"),
                                                                                                        new PrintStatement(new ValueExpression(new NumberValue(100)))
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        programs.add(ex26);

        Statement ex27 = new CompoundStatement(
                new VariableDeclarationStatement("v1", new ReferenceType(new IntegerType())),
                new CompoundStatement(
                        new VariableDeclarationStatement("v2", new ReferenceType(new IntegerType())),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v3", new ReferenceType(new IntegerType())),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("cnt", new IntegerType()),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("v1", new ValueExpression(new NumberValue(2))),
                                                new CompoundStatement(
                                                        new HeapAllocationStatement("v2", new ValueExpression(new NumberValue(3))),
                                                        new CompoundStatement(
                                                                new HeapAllocationStatement("v3", new ValueExpression(new NumberValue(4))),
                                                                new CompoundStatement(
                                                                        new newLatch("cnt", new ReadHeapExpression(new VariableNameExpression("v2"))),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(
                                                                                                new WriteHeapStatement("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableNameExpression("v1")), new ValueExpression(new NumberValue(10)))),
                                                                                                new CompoundStatement(
                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v1"))),
                                                                                                        new countDown("cnt")
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompoundStatement(
                                                                                        new ForkStatement(
                                                                                                new CompoundStatement(
                                                                                                        new WriteHeapStatement("v2", new ArithmeticExpression('*', new ReadHeapExpression(new VariableNameExpression("v2")), new ValueExpression(new NumberValue(10)))),
                                                                                                        new CompoundStatement(
                                                                                                                new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v2"))),
                                                                                                                new countDown("cnt")
                                                                                                        )
                                                                                                )
                                                                                        ),
                                                                                        new CompoundStatement(
                                                                                                new ForkStatement(
                                                                                                        new CompoundStatement(
                                                                                                                new WriteHeapStatement("v3", new ArithmeticExpression('*', new ReadHeapExpression(new VariableNameExpression("v3")), new ValueExpression(new NumberValue(10)))),
                                                                                                                new CompoundStatement(
                                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableNameExpression("v3"))),
                                                                                                                        new countDown("cnt")
                                                                                                                )
                                                                                                        )
                                                                                                ),
                                                                                                new CompoundStatement(
                                                                                                        new await("cnt"),
                                                                                                        new CompoundStatement(
                                                                                                                new PrintStatement(new ValueExpression(new NumberValue(100))),
                                                                                                                new CompoundStatement(
                                                                                                                        new countDown("cnt"),
                                                                                                                        new PrintStatement(new ValueExpression(new NumberValue(100)))
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        programs.add(ex27);
        return programs;
    }

    public static void deletePreviousLogs() {
        int numberOfPrograms = getHardcodedPrograms().size();
        for(int i = 1; i <= numberOfPrograms; i++) {
            File fileToDelete = new File("log" + i + ".txt");
            if(fileToDelete.delete())
                System.out.println("deleted log file " + fileToDelete.toString());
        }
    }
}
