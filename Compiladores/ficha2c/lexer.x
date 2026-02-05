{
module Lexer where

data Token
    = TokNum Int
    | TokIdent String
    | TokPlus
    | TokMinus
    | TokTimes
    | TokDiv
    | TokAssign
    | TokSemi
    | TokLParen
    | TokRParen
    | TokInc
    deriving (Show)
}

-- %% marca a separação entre código Haskell e regras do lexer
%%

[ \t\n\r]+                 ; -- ignora espaços

[0-9]+                     { \s -> TokNum (read s) }
[a-zA-Z_][a-zA-Z0-9_]*     { \s -> TokIdent s }
"++"                       { \_ -> TokInc }
"+"                        { \_ -> TokPlus }
"-"                        { \_ -> TokMinus }
"*"                        { \_ -> TokTimes }
"/"                        { \_ -> TokDiv }
"="                        { \_ -> TokAssign }
";"                        { \_ -> TokSemi }
"("                        { \_ -> TokLParen }
")"                        { \_ -> TokRParen }
.                          { \c -> error $ "Unknown character: " ++ show c }
