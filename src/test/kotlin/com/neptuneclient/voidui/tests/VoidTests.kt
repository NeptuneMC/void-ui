package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import org.junit.jupiter.api.Test

internal class VoidTests {

    @Test
    fun goofyAhhTest() {
        val voidui = VoidUI()
        assert(voidui.complexFunction(10, 4) == 14)
    }

}