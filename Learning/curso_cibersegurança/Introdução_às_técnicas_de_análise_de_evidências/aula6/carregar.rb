require "ipaddr"

BASE = []
File.foreach("evidencias/geo_didatico.csv") do |l|
  next if l.start_with?("#") || l.strip.empty?
  rede, pais, cidade, lat, lon = l.chomp.split(",")
  BASE << { rede: IPAddr.new(rede), pais: pais, cidade: cidade, lat: lat.to_f, lon: lon.to_f }
end

def localizar(ip)
  alvo = IPAddr.new(ip)
  BASE.find { |e| e[:rede].include?(alvo) }
end

["192.168.1.10", "203.0.113.9", "198.51.100.77"].each do |ip|
  g = localizar(ip)
  puts "#{ip.ljust(15)} #{g ? "#{g[:cidade]}, #{g[:pais]}" : "desconhecido"}"
end

#192.168.1.10    Lisboa, PT
#203.0.113.9     Sao Paulo, BR
#198.51.100.77   Moscovo, RU

#BASE << { rede:IPAddr.new(rede), pais: pais,... } - Cada entrada fica num hash com chaves simbólicas. A rede é convertida em objeto logo à entrada, para não ser reconvertida em cada consulta
#lat.to_f Conversão para número decimal. Sem ela, as contas de distância seriam feitas sobre texto e falhariam

#{ chave: valor} - Forma abreviada de { :chave => valor }. Acede-se com h[:chave]. É a forma normal de representar um registo com campos nomeados.
#.find { } - Devolve o primeiro elemento que cumpre a condição, ou nil. Pára na primeira correspondência.
#.to_f - Converte texto em número decimal. Como o to_i, devolve 0 quando o texto não é um número — em silêncio
#.start_with?("#") - Aqui o uso é legítimo: estamos mesmo a comparar texto.
#CSV - Valores separados por vírgulas. Simples e frágil: se um campo contiver uma vírgula, o split parte-o. Para dados a sério usa-se a biblioteca csv.