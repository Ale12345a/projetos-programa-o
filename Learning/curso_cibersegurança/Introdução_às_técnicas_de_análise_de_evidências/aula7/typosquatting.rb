# As funcoes de apoio vem de um ficheiro ao lado, ja escritas e testadas.
# Nao e preciso perceber o que esta la dentro para as usar aqui.
require_relative "lev_funcao"

MARCAS = %w[iefp.pt cgd.pt ctt.pt millenniumbcp.pt]

CANDIDATOS = %w[iefp.pt iefp-pt.com secure-iefp-pt.com ieefp.pt lefp.pt cgd-pt.com cgd.ptctt-encomendas.pt ctt.pt millenniumbcp-seguro.pt www.publico.pt]

def nucleo(d)
  d.sub(/\Awww\./, "")
end

puts "DOMINIO SUSPEITO".ljust(26) + "MARCA MAIS PROXIMA".ljust(20) + "DIST VEREDITO"
puts "-" * 74

CANDIDATOS.each do |c|
  alvo = nucleo(c)
  marca, dist = MARCAS.map { |m| [m, levenshtein(alvo, m)] }.min_by { |_, d| d }

  veredito = if dist.zero? then "legitimo (identico)"
      elsif dist <= 3 then "SUSPEITO — parecido com a marca"
      else "sem semelhanca"
    end

  puts c.ljust(26) + marca.ljust(20) + dist.to_s.ljust(6) + veredito
end

#DOMINIO SUSPEITO MARCA MAIS PROXIMA DIST VEREDITO
#--------------------------------------------------------------------------
#iefp.pt iefp.pt 0 legitimo (identico)
#iefp-pt.com iefp.pt 5 sem semelhanca
#secure-iefp-pt.com iefp.pt 12 sem semelhanca
#ieefp.pt iefp.pt 1 SUSPEITO — parecido com a marca
#lefp.pt iefp.pt 1 SUSPEITO — parecido com a marca
#cgd-pt.com cgd.pt 5 sem semelhanca
#cgd.pt cgd.pt 0 legitimo (identico)
#ctt-encomendas.pt ctt.pt 11 sem semelhanca
#ctt.pt ctt.pt 0 legitimo (identico)
#millenniumbcp-seguro.pt millenniumbcp.pt 7 sem semelhanca
#www.publico.pt iefp.pt 7 sem semelhanca

#.sub(padrao,"") - Remove a primeira ocorrência. Com \A no padrão, garante que é no princípio.