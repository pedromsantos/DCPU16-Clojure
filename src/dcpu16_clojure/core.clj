(ns dcpu16-clojure.core)
	(:gen class)

(require ['clojure.string :as 'str])

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
(def re-whitespace #"\s+")

(defn token [word]
  (let [tok-types [
                   [:whitespace re-whitespace]
                   [:closebracket re-close-bracket]
                   [:coma re-comma]
                   [:comment re-comment]
                   [:hexadecimal re-hexadecimal]
                   [:decimal re-decimal]
                   [:instruction re-instruction]
                   [:openbracket re-open-bracket]
                   [:plus re-plus]
                   [:register re-register]
                   [:labelref re-label-ref]
                   [:label re-label]
                   [:string re-string]
                 ]
        match?   (fn [re-str word]
                   (re-find (re-pattern (str "^(?i)" re-str)) word))
        tok-type (fn [[tok re-str]]
                   (let [content (match? re-str word)]
                   (when content [tok content])))]
    (some tok-type tok-types)))

(defn consume [word]
  (let [[tok-id content] (token word)]
    [(subs word (count content) (count word)) [tok-id content]])
)

(defn tokenize-line [line]    
  (let [[remaining-line & tokens] (consume line)]
    ( cond 
     (> (count remaining-line) 0)
       (into [] (concat tokens (tokenize-line remaining-line)))
     :else 
       tokens
    )
  )
)

(defn tokenize [lines]
  (for [l lines
        t [(tokenize-line l)]
        :when (seq t)]
    t))

(defn lex [file]
  (tokenize (str/split-lines (slurp file)))
)

(tokenize-line "set a, 10")

(tokenize-line "set i, 0x20")
(tokenize ["set a, 10" "set i, 0x30"])

;(lex "/home/pedro/src/github/DCPU16-Clojure/src/dcpu16_clojure/hello.dasm16")

