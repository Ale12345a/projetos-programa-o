# Escrito a partir do pedido:
# "escreve um script Ruby que procure um termo num ficheiro de log
# e diga em que linhas esse termo aparece"

termo = ARGV[0]

if termo.nil?
  puts "Uso: ruby procura.rb <termo>"
  exit
end

padrao = Regexp.new(termo)
encontradas = 0

File.foreach("acesso.log").with_index(1) do |linha, n|
  if linha =~ padrao
    encontradas += 1
    puts " linha #{n}: #{linha.chomp}"
  end
end

puts "#{encontradas} ocorrencias de '#{termo}'."

#$ ruby procura.rb 192.168.1.10
  #linha 1: 192.168.1.10 - - [29/Sep/2026:09:12:04] "GET /index.html" 200
  #linha 4: 192.168.1.10 - - [29/Sep/2026:09:16:02] "GET /produtos" 200
  #linha 9: 192.168.1.10 - - [29/Sep/2026:09:25:44] "GET /favicon.ico" 404
#3 ocorrencias de '192.168.1.10'.

#$ ruby procura.rb "/login?next=/area"
#0 ocorrencias de '/login?next=/area'.