
bootstrap: lein boot

lein:
	wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
	chmod +x lein

boot:
	wget https://github.com/boot-clj/boot/releases/download/2.0.0/boot.sh -O boot
	chmod +x boot

repl: boot
	./boot -s src repl -e "(require '[clojure.tools.namespace.repl :refer [refresh]])"
