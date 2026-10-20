require "time"

puts Time.at(1791879331).utc

puts Time.strptime("2026-10-13T09:15:31+01:00","%Y-%m-%dT%H:%M:%S%z").utc

#2026-10-13 08:15:31 UTC
#2026-10-13 08:15:31 UTC

#require "time" - Traz as ferramentas de horas que não vêm de origem, entre elas o Time.strptime.
#puts Time.at(1791879331).utc - o cronómetro: segundos desde 1 de janeiro de 1970, 00:00 UTC. O .utc escreve o instante em UTC
#puts Time.strptime("2026-10-13T09:15:31+01:00", - O formulário, primeiro o texto a ler
#"%Y-%m-%dT%H:%M:%S%z").utc - depois as casas: %Y ano, %m mês, %d dia, %H:%M:%S hora, %z fuso. Se o texto não encaixar, dá erro