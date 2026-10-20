require "time"

MINIMO_LIGACOES = 10

def calc_media(v)
  v.sum.to_f / v.size
end

def calc_desvio(v)
  m = calc_media(v)
  Math.sqrt(v.map { |x| (x - m)**2 }.sum / v.size)
end

ligacoes = Hash.new { |h, k| h[k] = [] }

File.foreach("evidencias/evidencias/proxy/proxy.log") do |l|
  origem, ts, _metodo, destino, _codigo, bytes = l.chomp.split(" ")
  ligacoes[[origem, destino]] << [Time.parse(ts), bytes.to_i]
end

puts "ANALISE DE PERIODICIDADE".ljust(46) + "N MEDIA DESVIO CV BYTES"
puts "-" * 92

resultados = ligacoes.map do |(origem, destino), eventos|
  next if eventos.size < MINIMO_LIGACOES
  eventos.sort_by!(&:first)
  
  intervalos = eventos.each_cons(2).map { |(t1, _), (t2, _)| t2 - t1 }
  m = calc_media(intervalos)
  d = calc_desvio(intervalos)
  cv = m.zero? ? 0 : d / m

  [origem, destino, eventos.size, m, d, cv, eventos.map(&:last).uniq]
end.compact

resultados.sort_by { |r| r[5] }.each do |origem, destino, n, m, d, cv, tamanhos|
  alerta = cv < 0.10 ? " <<< BEACONING" : ""
  bytes = tamanhos.size == 1 ? "#{tamanhos.first} (constante)" : "#{tamanhos.size} valores"

  puts "#{origem.ljust(14)} -> #{destino.ljust(28)} #{n.to_s.rjust(3)} " \
       "#{m.round(1).to_s.rjust(7)} #{d.round(1).to_s.rjust(7)} " \
       "#{cv.round(3).to_s.rjust(6)} #{bytes}#{alerta}"
end

#ANALISE DE PERIODICIDADE                      N MEDIA DESVIO CV BYTES
#--------------------------------------------------------------------------------------------
#10.0.0.55      -> cdn-update.example.net:443    40    60.0     2.6  0.043 512 (constante) <<< BEACONING
#192.168.1.10   -> teams.microsoft.com:443       25    71.5    61.9  0.866 25 valores
#192.168.1.10   -> www.publico.pt:443            25    89.8    80.4  0.896 25 valores
#192.168.1.10   -> www.iefp.pt:443               25    99.3    89.2  0.898 25 valores
#192.168.1.10   -> www.google.com:443            25    92.3   105.1  1.139 25 valores


#intervalos = eventos.each_cons(2).map { |(t1,_), (t2, _)| t2 - t1 } - A operação central: transforma uma lista de instantes numa lista de intervalos entre eles. É o each_cons(2) do capítulo 6 outra vez
#eventos.map(&:last).uniq - Os tamanhos distintos das transferências. Um único valor em 40 ligações é o segundo indicador — independente do primeiro.
#.compact - Remove os nil produzidos pelos pares saltados.
#.map(&:last) - O último elemento de cada par.