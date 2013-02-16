(ns dcpu16-clojure.core)
(req str)

(def re-close-bracket #"[\]\)]")
(def re-comma #",")
(def re-comment #";.*$")
(def re-decimal #"[0-9]+")
(def re-hexadecimal #"0(?i)x[0-9a-fA-F]+")
(def re-instruction #"dat|^set|^add|^sub|^mul|^div|^mod|^shl|^shr|^and|^bor|^xor|^ife|^ifn|^ifg|^ifb|^jsr")
(def re-label-ref #"[a-zA-Z0-9_]+")
(def re-label #":\w+")
(def re-open-bracket #"[\[\(]")
(def re-plus #"\+")
(def re-register #"a|b|c|x|y|z|i|j|pop|push|peek|pc|sp|o")
(def re-string #"\"(\"|[^\"])*\"")
(def re-whitespace #"(\r\n|\s+)")

(defn match? [re-str word] (re-find (re-pattern (str "^" re-str)) word)) 
(defn tok-type [[tok re-str] word] (when (match? re-str word) tok))

(match? re-comma ",")
(tok-type [:comma re-comma] ",")

(def tok-types [
                 [:closebracket re-close-bracket]
                 [:coma re-comma]
                 [:comment re-comment]
                 [:decimal re-decimal]
                 [:hexadecimal re-hexadecimal]
                 [:instruction re-instruction]
                 [:openbracket re-open-bracket]
                 [:plus re-plus]
                 [:register re-register]
                 [:labelref re-label-ref]
                 [:label re-label]
                 [:string re-string]
                 [:whitespace re-whitespace]])

(some #(match? % "a") tok-types)
(some #(tok-type % "a") tok-types)

(defn token [word]
  (let [tok-types [
                 [:closebracket re-close-bracket]
                 [:coma re-comma]
                 [:comment re-comment]
                 [:decimal re-decimal]
                 [:hexadecimal re-hexadecimal]
                 [:instruction re-instruction]
                 [:openbracket re-open-bracket]
                 [:plus re-plus]
                 [:register re-register]
                 [:labelref re-label-ref]
                 [:label re-label]
                 [:string re-string]
                 [:whitespace re-whitespace]]
        match?   (fn [re-str word]
                   (re-find (re-pattern (str "^" re-str)) word))
        tok-type (fn [[tok re-str]]
                   (when (match? re-str word) tok))]
    {(some tok-type tok-types) word}))

(token "a")


