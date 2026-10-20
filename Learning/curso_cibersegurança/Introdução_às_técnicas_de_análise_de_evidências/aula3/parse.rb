require "time"
puts Time.parse("Oct 13 04:15:31 srv-app EDT alerta autenticacao 10.0.0.55").utc
 

# nao da erro. nao avisa. esta 5 horas errada.

#2026-10-13 03:15:31 UTC

#puts Time.parse("Oct 13 04:15:31 srv-app EDT").utc - O adivinho: lê a data e a hora; o srv-app fica entre a hora e
#o EDT, e o fuso não é ligado. Usa o do computador.

# não da erro. nao avisa. esta 5 horas errada. - num computador em Portugal sai 03:15:31 UTC. A hora certa é 08:15:31 UTC

#o EDT escreve-se como o que é, quatro horas a menos do que o UTC