# Deteção de beaconing SEM estatística nenhuma.
#
# O que se procura é regularidade: uma pessoa não faz nada de sessenta em sessenta
# segundos durante quarenta minutos seguidos. Basta olhar para os intervalos e para
# o número de tamanhos diferentes — dois indicadores independentes.
#
# A versão com desvio padrão e coeficiente de variação é a beaconing.rb, e corre no
# ecrã do formador como confirmação.
require "time"

MINIMO_LIGACOES = 10

ligacoes = Hash.new { |h, k| h[k] = [] }

File.foreach("evidencias/evidencias/proxy/proxy.log") do |linha|
  origem, hora, _metodo, destino, _codigo, bytes = linha.chomp.split(" ")
  ligacoes[[origem, destino]] << [Time.parse(hora), bytes.to_i]
end

puts "LIGACOES POR PAR ORIGEM-DESTINO"
puts "-" * 76
puts

ligacoes.each do |(origem, destino), eventos|
  next if eventos.size < MINIMO_LIGACOES

  horas = eventos.map { |h, _b| h }.sort
  intervalos = horas.each_cons(2).map { |a, b| (b - a).to_i }
  tamanhos = eventos.map { |_h, b| b }.uniq

  puts "#{origem} -> #{destino}"
  puts " #{eventos.size} ligacoes"
  puts " intervalos mais curtos: #{intervalos.uniq.sort.first(4).join(', ')} segundos"
  puts " tamanhos diferentes: #{tamanhos.size}"
  puts
end

#LIGACOES POR PAR ORIGEM-DESTINO
#----------------------------------------------------------------------------

#192.168.1.10 -> www.iefp.pt:443
# 25 ligacoes
# intervalos mais curtos: 1, 4, 5, 10 segundos
# tamanhos diferentes: 25

#10.0.0.55 -> cdn-update.example.net:443
# 40 ligacoes
# intervalos mais curtos: 55, 56, 57, 58 segundos
# tamanhos diferentes: 1

#192.168.1.10 -> www.google.com:443
# 25 ligacoes
# intervalos mais curtos: 1, 6, 10, 21 segundos
# tamanhos diferentes: 25

#192.168.1.10 -> www.publico.pt:443
# 25 ligacoes
# intervalos mais curtos: 8, 12, 22, 28 segundos
# tamanhos diferentes: 25

#192.168.1.10 -> teams.microsoft.com:443
# 25 ligacoes
# intervalos mais curtos: 5, 15, 17, 24 segundos
# tamanhos diferentes: 25



#horas.each_cons(2).map { |a, b|(b - a).to_i } - Percorre as horas duas a duas — a 1.ª com a 2.ª, a 2.ª com a 3.ª — e calcula a diferença de cada par. 
#tamanhos = eventos.map { |_h, b|b }.uniq - Recolhe os tamanhos em bytes e tira os repetidos. O que fica é quantos tamanhos DIFERENTES aquele par de máquinas usou.

#Hash.new { |h,k| h[k] = [] } - Igual ao Hash.new(0) que já conhece, mas o valor por omissão é criado por um bloco. Serve para agrupar coisas por chave sem escrever um if a
#perguntar se a chave já existe.
