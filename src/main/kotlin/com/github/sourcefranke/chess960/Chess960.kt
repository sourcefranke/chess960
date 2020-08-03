package com.github.sourcefranke.chess960

enum class Piece(val symbol: Char) {
    KING('K'), QUEEN('Q'), ROOK('R'), KNIGHT('N'), BISHOP('B')
}

interface Rule {
    fun validate(pieces: List<Piece>): Boolean
}

/**
 * Rule: the king to be placed between the two rooks
 */
class KingRule : Rule {
    override fun validate(pieces: List<Piece>): Boolean {
        return pieces.indexOf(Piece.ROOK) < pieces.indexOf(Piece.KING)
                && pieces.indexOf(Piece.KING) < pieces.lastIndexOf(Piece.ROOK)
    }
}

/**
 * Rule: the bishops to be placed on fields of different colours
 */
class BishopRule : Rule {
    override fun validate(pieces: List<Piece>): Boolean {
        return pieces.indexOf(Piece.BISHOP) % 2 != pieces.lastIndexOf(Piece.BISHOP) % 2
    }
}

/**
 * Set of rules, where every single rule has to be fulfilled
 */
class RuleSet (var rules: List<Rule> = listOf(BishopRule(), KingRule())) : Rule {
    override fun validate(pieces: List<Piece>): Boolean {
        return rules.all{ rule -> rule.validate(pieces) }
    }
}

/**
 * Generating a random setup based on given (set of) rules
 */
fun generateValidSetup(rules: Rule = RuleSet()): List<Piece> {
    var setup = listOf(Piece.ROOK, Piece.KNIGHT, Piece.BISHOP, Piece.QUEEN, Piece.KING, Piece.BISHOP, Piece.KNIGHT, Piece.ROOK)

    do {
        setup = setup.shuffled()
    } while (!rules.validate(setup))

    return setup
}

/**
 * Transform the given setup of pieces into its string representation
 */
fun setupAsString(pieces: List<Piece> = generateValidSetup()): String  {
    return pieces.map { it.symbol }.joinToString()
}

/**
 * Main method:
 * Print a generated setup to the console
 */
fun main() {
    println(setupAsString())
}