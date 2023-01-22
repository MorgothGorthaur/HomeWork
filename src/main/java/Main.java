import homework.HomeWork;
import homework.HomeWorkImpl;
import homework.exceptions.NotDoubleException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
    public void run() throws IOException {
        var homework = setParams();
        System.out.println(getMenu());
        var command = "";
        while (!command.equals("exit")) {
            command = reader.readLine().strip().replaceAll("\\s+", " ");
            commandHandler(homework, command);
        }
    }
    private void commandHandler(HomeWork homeWork, String input) throws IOException {
        System.out.println(
                switch (input) {
                    case "get step size" -> homeWork.getStepSize();
                    case "get min value" -> homeWork.getMinFunctionValue();
                    case "get max value" -> homeWork.getMaxFunctionValue();
                    case "get sum" -> homeWork.getSumOfFunctions();
                    case "get average" -> homeWork.getFunctionAverage();
                    case "evaluate" -> homeWork.evaluateExpression(setDouble());
                    case "get by id" -> homeWork.getById((int) setDouble());
                    case "get functions" -> Arrays.toString(homeWork.getFunctions());
                    case "get operands" -> Arrays.toString(homeWork.getOperands());
                    case "get results" -> Arrays.toString(homeWork.getResults());
                    case "get careful results" -> homeWork.toString();
                    case "get closest" -> homeWork.getClosestOperand(setDouble());
                    case "menu" -> getMenu();
                    case "exit" -> "exit";
                    case "", "\n" -> "\n";
                    default -> "unknown operation! print \"menu\" to get list of available operations!";
                });
    }
    private double setDouble() throws IOException {
        try {
            System.out.print("insert your value: ");
            return Double.parseDouble(reader.readLine());
        } catch (NumberFormatException ex) {
            throw new NotDoubleException();
        }
    }
    private HomeWork setParams() throws IOException {
        System.out.println("start:");
        var start = setDouble();
        System.out.println("end:");
        var end = setDouble();
        System.out.println("step:");
        var step = setDouble();
        return new HomeWorkImpl(start, end, step);
    }
    private String getMenu() {
        return """
                +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                +                           commands                            +
                + get step size - getting number of steps                       +
                + get min value - getting minimal function value                +
                + get max value - getting maximal function value                +
                + get sum - getting sum of functions                            +
                + get average - getting functions average                       +
                + evaluate - getting result for your value                      +
                + get by id - getting result by step number (min id = 0,        +
                +                                          max = step size - 1) +
                + get functions - getting functions array                       +
                + get operands - getting operands array                         +
                + get results / get careful results - getting results array     +
                + get closest - getting the most closest operand                +
                + menu - reprints menu                                          +
                + exit - ends program                                           +
                +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                """;
    }
}
