# MERL
abstract programming language
https://sites.google.com/view/meerl

1. Read symbols
2. put symbols into: a) group b) semicolon sections c) loops d) ids e) literals f) function calls
	*keep track of completeness
3. build a tree based on operator precedence (make all tokens complete)
4. analyze all tokens
   a) literal or group followed by id: treat id as suffix and call function accordingly
   b) id followed by group: call function on group accordingly (index, structure, or call)
   c) id followed by id: if trailing is a field of heading, field, else check if either is an oper
   d) true operator (=, <<, >>, ->, with, **print): check children type and determine if special action needed (init, func def, store away, zoning) before evaluating children
5. get all types
6. get byte sizes and memory positions
7. for every token translate to byte code
   7.1 for every token, run
8. implement missing functionality (OrderedSet, OrderedMap, UnorderedMap, LambdaFunctions, TODO Ls)
