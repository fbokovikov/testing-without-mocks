package ru.yandex.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author fbokovikov
 */
class BaseTest {

    @Test
    void assertTrue() {
        Assertions.assertTrue(1 == 1);
    }
}
