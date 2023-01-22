package homework;


import homework.exceptions.BadIdValueException;
import homework.exceptions.StartValueIsBiggerThenEndValueException;
import homework.exceptions.TooBigStepValueException;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class HomeWorkTest {
    HomeWork homeWork;
    static final double PERCENTAGE = 0.01;

    @ParameterizedTest
    @CsvSource({"0, 1, 0.1, 11", "0.2, 2.8, 0.002, 1301", "0, 100, 0.0001, 1000001"})
    void testGetStepSize(double start, double end, double step, int expected) {
        homeWork = new HomeWorkImpl(start, end, step);
        assertEquals(homeWork.getStepSize(), expected);
    }


    @ParameterizedTest
    @CsvSource({"0.14, 3.383", "0.3, 3.149", "1.5, 14.05", "2.3, 13.89", "2.8, -2.453"})
    void testEvaluateExpression(double x, double expected) {
        homeWork = new HomeWorkImpl(0.2, 2.8, 0.002);
        assertThat(homeWork.evaluateExpression(x))
                .isCloseTo(expected, Percentage.withPercentage(PERCENTAGE));
    }

    @ParameterizedTest
    @CsvSource({"0.2, 2.8, 0.002, 16.683", "0.2, 0.3, 0.002, 3.314",
            "0.302, 2.3, 0.002, 16.683", "2.302, 2.8, 0.002, -2.453"})
    void testGetMaxFunctionValue(double start, double end, double step, double expected) {
        homeWork = new HomeWorkImpl(start, end, step);
        assertThat(homeWork.getMaxFunctionValue().function())
                .isCloseTo(expected, Percentage.withPercentage(PERCENTAGE));
    }

    @ParameterizedTest
    @CsvSource({"0.2, 2.8, 0.002, -7.692", "0.2, 0.3, 0.002, 3.149",
            "0.302, 2.3, 0.002, 13.8", "2.302, 2.8, 0.002, -7.692"})
    void testGetMinFunctionValue(double start, double end, double step, double expected) {
        homeWork = new HomeWorkImpl(start, end, step);
        assertThat(homeWork.getMinFunctionValue().function())
                .isCloseTo(expected, Percentage.withPercentage(PERCENTAGE));
    }

    @ParameterizedTest
    @CsvSource({"0.2, 2.8, 0.002, 10.449", "0.2, 0.3, 0.002, 3.236",
            "0.302, 2.3, 0.002, 14.622", "2.302, 2.8, 0.002, -4.772"})
    void testGetFunctionAverage(double start, double end, double step, double expected) {
        homeWork = new HomeWorkImpl(start, end, step);
        assertThat(homeWork.getFunctionAverage()).isCloseTo(expected, Percentage.withPercentage(PERCENTAGE));
    }

    @ParameterizedTest
    @CsvSource({"0.2, 2.8, 0.002, 13594.06", "0.2, 0.3, 0.002, 165.047",
            "0.302, 2.3, 0.002, 14621.934", "2.302, 2.8, 0.002, -1192.92"})
    void testGetSumOfFunctions(double start, double end, double step, double expected) {
        homeWork = new HomeWorkImpl(start, end, step);
        assertThat(homeWork.getSumOfFunctions()).isCloseTo(expected, Percentage.withPercentage(PERCENTAGE));
    }

    @ParameterizedTest
    @CsvSource({"50, 3.149", "1050, 13.89", "1300, -2.453", "700, 13.96", "650, 14.05", "600, 14.16", "500, 14.44", "100, 16.36"})
    void testGetById(int id, double expected) {
        homeWork = new HomeWorkImpl(0.2, 2.8, 0.002);
        assertThat(homeWork.getById(id).function()).isCloseTo(expected, Percentage.withPercentage(PERCENTAGE));
    }

    @ParameterizedTest
    @CsvSource({"1500", "-1", "1301"})
    void testGetById_shouldThrowBadIdValueException(int id) {
        homeWork = new HomeWorkImpl(0.2, 2.8, 0.002);
        assertThatThrownBy(() -> homeWork.getById(id)).isInstanceOf(BadIdValueException.class);
    }

    @ParameterizedTest
    @CsvSource({"10, 5, 4", "5, 5, 3"})
    void testCheckData_shouldThrowStartValueIsBiggerThenEndValueException(double start, double end, double step) {
        assertThatThrownBy(() -> new HomeWorkImpl(start, end, step))
                .isInstanceOf(StartValueIsBiggerThenEndValueException.class);
    }

    @ParameterizedTest
    @CsvSource({"5, 10, 20", "0, 1, 1"})
    void testCheckData_shouldThrowTooBigStepValueException(double start, double end, double step) {
        assertThatThrownBy(() -> new HomeWorkImpl(start, end, step)).isInstanceOf(TooBigStepValueException.class);
    }
    @ParameterizedTest
    @CsvSource({"0.5, 16.05", "0.7, 15.49", "0.8, 15.24", "1.3, 14.29", "1.4, 14.16", "1.5, 14.05", "1.6, 13.96"})
    void  testClosedOperand(double x, double expected) {
        homeWork = new HomeWorkImpl(0.2, 2.8, 0.002);
        assertThat(homeWork.getClosestOperand(x).function()).isCloseTo(expected, Percentage.withPercentage(PERCENTAGE));
    }
}