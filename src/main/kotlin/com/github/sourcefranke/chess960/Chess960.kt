package com.github.sourcefranke.chess960

import com.github.sourcefranke.chess960.Piece.*

/**
 * Generating a random setup based on given rules
 * @param rule to be used for validation the given setup of pieces
 * @param pieces list of pieces, that will be shuffled
 */
fun generateValidSetup(
        rule: Rule = RuleSet(),
        pieces: List<Piece> = listOf(KING, QUEEN, ROOK, ROOK, BISHOP, BISHOP, KNIGHT, KNIGHT)
): List<Piece> {
    var result: List<Piece>

    do result = pieces.shuffled()
    while (!rule.validate(result))

    return result
}

/* Mapping pieces to their string representations */
val symbols_EN = mapOf(KING to "K", QUEEN to "Q", ROOK to "R", KNIGHT to "N", BISHOP to "B")
val symbols_DE = mapOf(KING to "K", QUEEN to "D", ROOK to "T", KNIGHT to "S", BISHOP to "L")
val terms_EN = mapOf(KING to "King", QUEEN to "Queen", ROOK to "Rook", KNIGHT to "Knight", BISHOP to "Bishop")
val terms_DE = mapOf(KING to "Koenig", QUEEN to "Dame", ROOK to "Turm", KNIGHT to "Springer", BISHOP to "Laeufer")

/**
 * Transform the given setup of pieces into its string representation
 * @param pieces setup of pieces to be converted to its string representation
 * @param mapping map of how to write a given piece as string
 * @param separator string of how to separate the pieces from each other within the resulting string
 */
fun setupAsString(
        pieces: List<Piece> = generateValidSetup(),
        mapping: Map<Piece, String> = symbols_EN,
        separator: String = " "
): String =
        pieces.map { mapping[it] }.joinToString(separator = separator)

/* Main method: Print a generated setup to the console */
fun main() = println(setupAsString(mapping = terms_EN))
