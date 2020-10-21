package com.github.sourcefranke.chess960

import com.github.sourcefranke.chess960.Piece.*

interface Rule {
    fun validate(pieces: List<Piece>): Boolean
}

/**
 * Rule: the king to be placed between the two rooks
 */
class KingRule : Rule {
    override fun validate(pieces: List<Piece>): Boolean =
            pieces.indexOf(ROOK) < pieces.indexOf(KING)
                    && pieces.indexOf(KING) < pieces.lastIndexOf(ROOK)
}

/**
 * Rule: the bishops to be placed on fields of different colours
 */
class BishopRule : Rule {
    override fun validate(pieces: List<Piece>): Boolean =
            pieces.indexOf(BISHOP) % 2 != pieces.lastIndexOf(BISHOP) % 2
}

/**
 * Set of rules, where every single rule has to be fulfilled
 */
class RuleSet (private var rules: List<Rule> = listOf(BishopRule(), KingRule())) : Rule {
    override fun validate(pieces: List<Piece>): Boolean = rules.all{ rule -> rule.validate(pieces) }
}

/**
 * Generating a random setup based on given rules
 */
fun generateValidSetup(
        rules: Rule = RuleSet(),
        setup: List<Piece> = listOf(KING, QUEEN, ROOK, ROOK, BISHOP, BISHOP, KNIGHT, KNIGHT)
): List<Piece> {
    var result: List<Piece>

    do result = setup.shuffled()
    while (!rules.validate(result))

    return result
}

/**
 * Transform the given setup of pieces into its string representation
 */
fun setupAsString(pieces: List<Piece> = generateValidSetup(), separator: String = ""): String =
        pieces.map { it.symbol }.joinToString(separator = separator)

/**
 * Main method:
 * Print a generated setup to the console
 */
fun main() = println(setupAsString(separator = " "))
