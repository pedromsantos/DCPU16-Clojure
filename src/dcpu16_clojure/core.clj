(ns dcpu16-clojure.core)

(def re-close-bracket #"^[\]\)]")
(def re-comma #"^,")
(def re-comment #"^;.*$")
(def re-decimal #"^[0-9]+")
(def re-hexadecimal #"^0(?i)x[0-9a-fA-F]+")
(def re-instruction #"^dat|^set|^add|^sub|^mul|^div|^mod|^shl|^shr|^and|^bor|^xor|^ife|^ifn|^ifg|^ifb|^jsr")
(def re-label-ref #"^[a-zA-Z0-9_]+")
(def re-label #"^:\w+")
(def re-open-bracket #"^[\[\(]")
(def re-plus #"^\+")
(def re-register #"^a|^b|^c|^x|^y|^z|^i|^j|^pop|^push|^peek|^pc|^sp|^o")
(def re-string #"^\"(\"|[^\"])*\"")
(def re-whitespace #"^(\r\n|\s+)")

(def matchers
       {
                 :close-bracket re-close-bracket,
                 :comma re-comma,
                 :comment re-comment,
                 :decimal re-decimal,
                 :hexadecimal re-hexadecimal,
                 :instruction re-instruction,
                 :label-ref re-label-ref,
                 :label re-label, 
                 :open-bracket re-open-bracket,
                 :plus re-plus,
                 :register re-register,
                 :string re-string,
                 :whitespace re-whitespace,
              })
 
(re-seq (matchers :close-bracket) "] ")
(re-seq (matchers :close-bracket) ") ")
(re-seq (matchers :comma) ", ")
(re-seq (matchers :decimal) "10 ")
(re-seq (matchers :hexadecimal) "0x01 ")
(re-seq (matchers :instruction) "set ")
(re-seq (matchers :instruction) "IFE ")
(re-seq (matchers :instruction) "o jsr ")
(re-seq (matchers :label-ref) "label ")
(re-seq (matchers :label) ":label ")
(re-seq (matchers :open-bracket) "[ ")
(re-seq (matchers :open-bracket) "( ")
(re-seq (matchers :close-bracket) "] ")
(re-seq (matchers :plus) "+ ")
(re-seq (matchers :register) "A ")
(re-seq (matchers :register) "x ")
(re-seq (matchers :register) "J ")
(re-seq (matchers :string) "\"label\" ")


