package org.bmsk.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `string을 UUID로 변환하기`() {
        val uuid = UUID.fromString("e3b4a97a-c7b7-4bbd-bf93-6fd65c281d4a")

        assertEquals("e3b4a97a-c7b7-4bbd-bf93-6fd65c281d4a", uuid.toString())
    }
}