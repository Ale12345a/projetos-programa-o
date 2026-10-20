# As funcoes de apoio vem de um ficheiro ao lado, ja escritas e testadas.
# Nao e preciso perceber o que esta la dentro para as usar aqui.
require_relative "geo_funcoes"

require "time"

VELOCIDADE_MAXIMA = 900.0 # km/h — aviao comercial

sessoes = Hash.new { |h, k| h[k] = [] }

File.foreach("evidencias/evidencias/web/autenticacoes.log") do |l|
  ts, utilizador, ip, evento = l.chomp.split(" ")
  next unless evento == "login_ok"
  sessoes[utilizador] << [Time.parse(ts), ip]
end

sessoes.each do |utilizador, lista|
  puts "UTILIZADOR: #{utilizador}"
  lista.sort_by(&:first).each_cons(2) do |(t1, ip1), (t2, ip2)|
    g1, g2 = localizar(ip1), localizar(ip2)
    next unless g1 && g2

    km = distancia_km(g1[:lat], g1[:lon], g2[:lat], g2[:lon])
    horas = (t2 - t1) / 3600.0
    vel = horas.zero? ? Float::INFINITY : km / horas

    puts " #{t1.utc.iso8601} #{g1[:cidade]} (#{ip1})"
    puts " #{t2.utc.iso8601} #{g2[:cidade]} (#{ip2})"
    puts " #{km} km em #{(horas * 60).round} min -> #{vel.round} km/h"
    puts " >>> DESLOCACAO IMPOSSIVEL" if vel > VELOCIDADE_MAXIMA
  end
  puts
end

#UTILIZADOR: alexandra.dias
#2026-10-13T08:02:11Z Lisboa (192.168.1.10)
#2026-10-13T08:17:22Z Sao Paulo (203.0.113.9)
#7949 km em 15 min -> 31412 km/h
#>>> DESLOCACAO IMPOSSIVEL
#2026-10-13T08:17:22Z Sao Paulo (203.0.113.9)
#2026-10-13T09:44:10Z Moscovo (198.51.100.77)
#11806 km em 87 min -> 8161 km/h
#>>> DESLOCACAO IMPOSSIVEL
#UTILIZADOR: rui.marques
#2026-10-13T10:05:52Z Lisboa (192.168.1.22)
#2026-10-13T11:12:00Z Lisboa (192.168.1.22)
#0 km em 66 min -> 0 km/h

#Hash.new { |h,k| h[k] = [] } - Cria uma lista nova sempre que uma chave é usada pela primeira vez. Não confundir com Hash.new([]), que partilharia a mesma lista por todas as
#chaves — um erro clássico e difícil de diagnosticar.

#.each_cons(2) - Percorre pares consecutivos: (1,2), (2,3), (3,4).
#|(t1, ip1),(t2, ip2)| - Os parênteses dentro das barras partem cada elemento nos seus componentes. Poupa duas linhas de atribuições.
#t2 - t1 entre instantes - Devolve a diferença em segundos, como número decimal.
#Float::INFINITY - O valor infinito. Comparável com números, o que permite usá-lo numa condição sem tratamento especial.
#.zero? - Responde se o valor é zero. Mais legível do que == 0
