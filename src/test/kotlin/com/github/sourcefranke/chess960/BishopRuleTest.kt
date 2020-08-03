package com.github.sourcefranke.chess960

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BishopRuleTest {

    private lateinit var rule: Rule

    @BeforeEach
    fun setUp() {
        rule = BishopRule()
    }

    @ParameterizedTest
    @MethodSource("trueTests")
    fun trueTests(pieces: List<Piece>) {
        val result = rule.validate(pieces)
        assertThat(result).isTrue()
    }

    @ParameterizedTest
    @MethodSource("falseTests")
    fun falseTests(pieces: List<Piece>) {
        val result = rule.validate(pieces)
        assertThat(result).isFalse()
    }

    fun trueTests(): Stream<List<Piece>> = Stream.of(
            listOf(Piece.BISHOP, Piece.BISHOP),
            listOf(Piece.BISHOP, Piece.ROOK, Piece.KING, Piece.BISHOP)
    )

    fun falseTests(): Stream<List<Piece>> = Stream.of(
            listOf(Piece.BISHOP, Piece.KING, Piece.BISHOP),
            listOf(Piece.BISHOP, Piece.ROOK, Piece.KING, Piece.ROOK, Piece.BISHOP)
    )
}