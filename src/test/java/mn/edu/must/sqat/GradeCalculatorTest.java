package mn.edu.must.sqat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    private GradeCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new GradeCalculator();
    }

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (Хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        String grade = calc.letterGrade(90.0);
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (Хязгаарын тохиолдол)")
    void boundary89Point99IsB() {
        assertEquals("B", calc.letterGrade(89.99));
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой (Хязгаарын тохиолдол)")
    void boundary59Point99IsF() {
        assertEquals("F", calc.letterGrade(59.99));
    }

    @Test
    @DisplayName("Буруу оноо (101.0 эсвэл -1.0) оруулахад IllegalArgumentException шидэх")
    void invalidScoreThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101.0));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1.0));
    }

    @Test
    @DisplayName("Зөв онооны бүрэлдэхүүнүүдийн нийлбэрийг бодох")
    void validTotalScore() {
        double total = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, total, 0.001);
    }

    @Test
    @DisplayName("Нийлбэр онооны буруу оролтод Exception шидэх")
    void invalidTotalScoreComponentsThrowException() {
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(-1, 40, 10, 10, 30));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10, 41, 10, 10, 30));
    }

    @ParameterizedTest
    @DisplayName("letterGrade хуваарилалтын хязгаарын утгуудыг тестлэх")
    @CsvSource({"95,A", "90,A", "89.99,B", "80,B", "70,C", "60,D", "59.99,F", "0,F"})
    void letterGradeBoundaries(double score, String expected) {
        assertEquals(expected, calc.letterGrade(score));
    }

    @ParameterizedTest
    @DisplayName("totalScore бүрэлдэхүүнүүдийг тооцох параметржүүлсэн тест")
    @CsvSource({
        "10, 40, 10, 10, 30, 100.0",
        "8, 32, 8, 8, 24, 80.0",
        "0, 0, 0, 0, 0, 0.0"
    })
    void totalScoreParameterized(double att, double lab, double q1, double q2, double exam, double expected) {
        assertEquals(expected, calc.totalScore(att, lab, q1, q2, exam), 0.001);
    }
}
