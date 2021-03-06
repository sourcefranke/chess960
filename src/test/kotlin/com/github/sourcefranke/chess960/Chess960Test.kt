package com.github.sourcefranke.chess960

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.kotest.core.spec.style.ShouldSpec

import com.github.sourcefranke.chess960.Piece.*

class Chess960Test : ShouldSpec ({

    context("asString") {
        should("default with given pieces") {
            listOf(
                    listOf(KNIGHT, KING, ROOK, QUEEN, BISHOP) to "N K R Q B",
                    listOf(BISHOP, ROOK, BISHOP) to "B R B"
            ).forEach { (pieces, result) -> assertThat(asString(pieces)).isEqualTo(result) }
        }

        should("german symbols") {
            assertThat(asString(listOf(KNIGHT, KING, ROOK, QUEEN, BISHOP), symbols_DE, ""))
                    .isEqualTo("SKTDL")
        }

        should("english terms") {
            assertThat(asString(listOf(KNIGHT, KING, ROOK, QUEEN, BISHOP), terms_EN, "-"))
                    .isEqualTo("Knight-King-Rook-Queen-Bishop")
        }

        should("german terms") {
            assertThat(asString(listOf(KNIGHT, KING, ROOK, QUEEN, BISHOP), terms_DE, ", "))
                    .isEqualTo("Springer, Koenig, Turm, Dame, Laeufer")
        }
    }
})
