require "time"

# "converte estes timestamps para UTC"
["Oct 13 04:15:31 2026 -04:00","Oct 13 04:31:19 2026 -04:00"].each do |texto|
puts "#{texto} -> #{Time.parse(texto).utc.iso8601}"
end

#Oct 13 04:15:31 2026 -04:00 -> 2026-10-13T03:15:31Z
#Oct 13 04:31:19 2026 -04:00 -> 2026-10-13T03:31:19Z

#["Oct 13 04:15:31 2026 -04:00","Oct 13 04:31:19 2026 -04:00"].each do |texto| - Dois timestamps no formato de um sistema americano, com o fuso escrito no próprio texto

#Time.parse(texto).utc.iso8601  - O defeito. O Time.parse não reconhece este arranjo particular de campos,
#desiste do -04:00 sem avisar, assume o fuso da máquina e devolve um
#instante errado.

#Time.parse(texto) - Tenta adivinhar o formato do texto. Acerta quase sempre, e quando não
#acerta não levanta exceção: aplica o fuso local e devolve um valor plausível. É
#essa a armadilha.

