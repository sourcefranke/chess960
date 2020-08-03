package com.github.sourcefranke.chess960

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class Chess960KtTest {

    @Test
    fun printSetupTest() {
        assertThat(setupAsString(listOf(Piece.KNIGHT, Piece.KING))).isEqualTo("N, K")
    }
}