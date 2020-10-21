package com.github.sourcefranke.chess960

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.kotest.core.spec.style.ShouldSpec

import com.github.sourcefranke.chess960.Piece.*

class Chess960Test : ShouldSpec ({

    context("KingRule") {
        should("true cases") {
            listOf(
                    listOf(ROOK, KING, ROOK)
            ).forEach { pieces -> assertThat(KingRule().validate(pieces)).isTrue() }
        }

        should("false cases") {
            listOf(
                    listOf(KING, ROOK, ROOK),
                    listOf(ROOK, ROOK, KING)
            ).forEach { pieces -> assertThat(KingRule().validate(pieces)).isFalse() }
        }
    }

    context("BishopRule") {
        should("true cases") {
            listOf(
                    listOf(BISHOP, BISHOP),
                    listOf(BISHOP, ROOK, KING, BISHOP)
            ).forEach { pieces -> assertThat(BishopRule().validate(pieces)).isTrue() }
        }

        should("false cases") {
            listOf(
                    listOf(BISHOP, KING, BISHOP),
                    listOf(BISHOP, ROOK, KING, ROOK, BISHOP)
            ).forEach { pieces -> assertThat(BishopRule().validate(pieces)).isFalse() }
        }
    }

    context("RuleSet") {
        should("true cases") {
            listOf(
                    listOf(BISHOP, BISHOP, ROOK, KING, ROOK),
                    listOf(BISHOP, ROOK, KING, BISHOP, ROOK)
            ).forEach { pieces -> assertThat(RuleSet().validate(pieces)).isTrue() }
        }

        should("false cases") {
            listOf(
                    listOf(BISHOP, ROOK, BISHOP, KING, ROOK),
                    listOf(BISHOP, ROOK, ROOK, BISHOP, KING),
                    listOf(BISHOP, ROOK, BISHOP, ROOK, KING)
            ).forEach { pieces -> assertThat(RuleSet().validate(pieces)).isFalse() }
        }
    }

    context("setupAsString") {
        listOf(
                listOf(KNIGHT, KING, ROOK, QUEEN, BISHOP) to "NKRQB",
                listOf(BISHOP, ROOK, BISHOP) to "BRB"
        ).forEach { (pieces, result) -> assertThat(setupAsString(pieces)).isEqualTo(result) }
    }
})
