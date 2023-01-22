package homework;

import homework.exceptions.BadIdValueException;
import homework.exceptions.NoFunctionsFoundException;
import homework.exceptions.StartValueIsBiggerThenEndValueException;
import homework.exceptions.TooBigStepValueException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.DoubleStream;

public class HomeWorkImpl implements HomeWork {
    private static final double a = 2.3;
    private static final double EPS = 0.0001;
    private final double start, end, step;

    private Result[] results;

    public HomeWorkImpl(double start, double end, double step) {
        checkData(start, end, step);
        this.start = start;
        this.end = end;
        this.step = step;
        generateResults();
    }

    @Override
    public Result getClosestOperand(double x) {
        for (var res : results) if (Math.abs(x - res.operand()) <= EPS) return res;
        return null;
    }

    @Override
    public double[] getOperands() {
        return Arrays.stream(results).mapToDouble(Result::operand).toArray();
    }

    @Override
    public double[] getFunctions() {
        return Arrays.stream(results).mapToDouble(Result::function).toArray();
    }

    @Override
    public Result[] getResults() {
        return results;
    }

    @Override
    public int getStepSize() {
        return (int) ((end - start) / step + EPS) + 1;
    }

    /**
     * a = 2.3 - const
     * EPS = 0.0001 - const
     * x <= 3 + EPS : y = 1.5 * a * cos(x)^2
     * 3 + EPS > x <= 2.3 + EPS : y = (x-2)^2 + 6 * a
     * x > 2.3 + EPS : y = 3 * a * tg(x)
     **/
    @Override
    public Result evaluateExpression(double x) {
        if (x <= 0.3 + EPS) return new Result(x, 1.5 * a * Math.pow(Math.cos(x), 2));
        else if (x <= 2.3 + EPS) return new Result(x, Math.pow((x - 2), 2) + 6 * a);
        else return new Result(x, 3 * a * Math.tan(x));
    }

    @Override
    public Result getMaxFunctionValue() {
        return Arrays.stream(results)
                .max(Comparator.comparingDouble(Result::function))
                .orElseThrow(NoFunctionsFoundException::new);
    }

    @Override
    public Result getMinFunctionValue() {
        return Arrays.stream(results)
                .min(Comparator.comparingDouble(Result::function))
                .orElseThrow(NoFunctionsFoundException::new);
    }

    @Override
    public double getFunctionAverage() {return getSumOfFunctions() / getStepSize();}

    @Override
    public double getSumOfFunctions() {
        var res = 0.0;
        for (var pair : results) res += pair.function();
        return res;
    }

    @Override
    public Result getById(int id) {
        if (id < getStepSize() && id >= 0) return results[id];
        else throw new BadIdValueException();
    }

    private void generateResults() {
        var size = getStepSize();
        results = new Result[size];
        var tmp = start;
        for (var i = 0; i < size; i++) {
            results[i] = evaluateExpression(tmp);
            tmp += step;
        }
    }

    private void checkData(double start, double end, double step) {
        if (start >= end) throw new StartValueIsBiggerThenEndValueException();
        if (end - start <= step) throw new TooBigStepValueException();
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        for (var data : results) str.append(data).append("\n");
        return str.toString();
    }
}
