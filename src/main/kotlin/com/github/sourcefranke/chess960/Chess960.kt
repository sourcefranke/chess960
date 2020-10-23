package com.github.sourcefranke.chess960

import com.github.sourcefranke.chess960.Piece.*

/**
 * Generating a random order based on given rules
 * @param rule to be used for validation the given order of pieces
 * @param pieces list of pieces, that will be shuffled
 * @return randomly ordered pieces based on rules
 */
fun generateSetup(
        rule: Rule = RuleSet(),
        pieces: List<Piece> = listOf(KING, QUEEN, ROOK, ROOK, BISHOP, BISHOP, KNIGHT, KNIGHT)
): List<Piece> {
    var result: List<Piece>

    do result = pieces.shuffled()
    while (!rule.validate(result))

    return result
}

/**
 * Prints the given pieces into as string
 * @param pieces ordered pieces to be converted to its string representation
 * @param mapping how to convert given pieces into strings
 * @param separator
 * @return setup of pieces as single string
 */
fun asString(
        pieces: List<Piece> = generateSetup(),
        mapping: Map<Piece, String> = symbols_EN,
        separator: String = " "
): String =
        pieces.map { mapping[it] }.joinToString(separator = separator)

/* Main method: Print a generated setup to the console */
fun main() = println(asString(mapping = terms_EN))
