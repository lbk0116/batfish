parser grammar FlatJuniperGrammar_mpls;

import FlatJuniperGrammarCommonParser;

options {
   tokenVocab = FlatJuniperGrammarLexer;
}

s_protocols_mpls
:
   PROTOCOLS MPLS ~NEWLINE*
;
