# Usar a distancia de Levenshtein — a funcao vem DADA.
# Nao e preciso perceber como esta escrita para a usar: e como usar uma
# calculadora sem saber construir uma.
require_relative "lev_funcao"

MARCAS = %w[iefp.pt cgd.pt ctt.pt millenniumbcp.pt]

SUSPEITOS = %w[ieefp.pt lefp.pt cgd-pt.com secure-iefp-pt.com]

puts "SUSPEITO".ljust(22) + "MARCA".ljust(20) + "ALTERACOES"
puts "-" * 54

SUSPEITOS.each do |s|
  # para cada marca, calcula a distancia; fica com a menor
  marca, dist = MARCAS.map { |m| [m, levenshtein(s, m)] }.min_by { |_m, d| d }
  puts s.ljust(22) + marca.ljust(20) + dist.to_s
end

#SUSPEITO MARCA ALTERACOES
#------------------------------------------------------
#ieefp.pt iefp.pt 1
#lefp.pt iefp.pt 1
#cgd-pt.com cgd.pt 5
#secure-iefp-pt.com iefp.pt 12

#require_relative -  Carrega outro ficheiro .rb que está na mesma pasta. Serve para usar código escrito noutro sítio sem o copiar. O nome vai sem a extensão .rb.
#%w[...] - Cria uma lista de palavras sem ser preciso pôr aspas e vírgulas em cada uma. %w[a b c] é o mesmo que ["a", "b", "c"] e é mais legível.
#.min_by { ... } - Percorre uma lista e devolve o elemento para o qual o bloco dá o menor valor. Existe o .max_by, que faz o contrário.
#_m - Um nome de variável começado por sublinhado diz a quem lê que aquele valor não vai ser usado. Aqui interessa-nos a distância, não a marca.