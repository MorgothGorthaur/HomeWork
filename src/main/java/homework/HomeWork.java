package homework;

public interface HomeWork {
    int getStepSize();
    double getFunctionAverage();
    double getSumOfFunctions();
    double[] getOperands();
    double[] getFunctions();
    Result evaluateExpression(double x);
    Result getMaxFunctionValue();
    Result getMinFunctionValue();
    Result getById(int id);
    Result getClosestOperand(double x);
    Result[] getResults();

}
