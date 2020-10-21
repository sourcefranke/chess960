package com.github.sourcefranke.chess960

/** Functional interface of validation rules to be used to verify the correctness of a given setup of pieces */
fun interface Rule {
    /** @param pieces setup of pieces to be verified for correctness */
    fun validate(pieces: List<Piece>): Boolean
}

/* Rule: the king to be placed between the two rooks */
val kingRule = Rule {
    it.indexOf(Piece.ROOK) < it.indexOf(Piece.KING) && it.indexOf(Piece.KING) < it.lastIndexOf(Piece.ROOK)
}

/* Rule: the bishops to be placed on fields of different colours */
val bishopRule = Rule {
    it.indexOf(Piece.BISHOP) % 2 != it.lastIndexOf(Piece.BISHOP) % 2
}

/**
 * Set of validation rules, all to be executed successfully for a given setup of pieces
 * @param rules list of rules to be used to verify a given setup of pieces
 */
class RuleSet (private var rules: List<Rule> = listOf(kingRule, bishopRule)) : Rule {
    override fun validate(pieces: List<Piece>): Boolean = rules.all{ rule -> rule.validate(pieces) }
}
