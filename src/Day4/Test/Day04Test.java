package Day4.Test;

import Day4.Day04;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

    @Test
    void part1() {
        Assertions.assertEquals(256, new Day04().part1());
    }

}