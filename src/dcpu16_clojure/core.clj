(ns dcpu16-clojure.core)

(def matchers
       {
                 :close-bracket #"^[\]\)]",
                 :comma #"^,",
                 :comment #"^;.*$",
                 :decimal #"^[0-9]+",
                 :hexadecimal #"^0(?i)x[0-9a-fA-F]+",
                 :instruction #"^(((?i)dat)|((?i)set)|((?i)add)|((?i)sub)|((?i)mul)|((?i)div)|((?i)mod)|((?i)shl)|((?i)shr)|((?i)and)|((?i)bor)|((?i)xor)|((?i)ife)|((?i)ifn)|((?i)ifg)|((?i)ifb)|((?i)jsr))",
                 :label-ref #"^[a-zA-Z0-9_]+"
                 :label #"^:\w+",
                 :open-bracket #"^[\[\(]",
                 :plus #"^\+"'
                 :register #"\b(((?i)a)|((?i)b)|((?i)c)|((?i)x)|((?i)y)|((?i)z)|((?i)i)|((?i)j)|((?i)pop)|((?i)push)|((?i)peek)|((?i)pc)|((?i)sp)|((?i)o))\b",
                 :string #"?""(""|[^""])*""",
                 :whitespace #"^(\r\n|\s+)"
      })

(re-seq (matchers :close-bracket) "] ")
(re-seq (matchers :close-bracket) ") ")
(re-seq (matchers :comma) ", ")
(re-seq (matchers :decimal) "10 ")
(re-seq (matchers :hexadecimal) "0x01 ")
(re-seq (matchers :instruction) "set ")
(re-seq (matchers :instruction) "SET ")
(re-seq (matchers :instruction) "Set ")
(re-seq (matchers :label-ref) "label ")
(re-seq (matchers :label) ":label ")
(re-seq (matchers :open-bracket) "[ ")
(re-seq (matchers :open-bracket) "( ")
(re-seq (matchers :close-bracket) "] ")
(re-seq (matchers :plus) "+ ")
(re-seq (matchers :register) "A ")
(re-seq (matchers :register) "x ")
(re-seq (matchers :register) "J ")
(re-seq (matchers :string) ""label" ")

