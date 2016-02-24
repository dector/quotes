package io.github.dector.knight.testing

import org.testng.Assert.assertFalse

fun assertNotExecuted() {
    assertFalse(true, "This code shouldn't be executed")
}