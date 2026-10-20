# As funcoes de apoio vem de um ficheiro ao lado, ja escritas e testadas.
# Nao e preciso perceber o que esta la dentro para as usar aqui.
require_relative "lev_funcao"

MARCAS = { "iefp" => "iefp.pt", "cgd" => "cgd.pt", "ctt" => "ctt.pt", "millenniumbcp" => "millenniumbcp.pt" }

def sinais(dominio)
  d = dominio.sub(/\Awww\./, "")
  nome_sem_tld = d.sub(/\.[a-z.]+\z/, "")
  achados = []

  MARCAS.each do |marca, oficial|
    # 1. erro de digitacao: muito perto do dominio oficial
    dist = levenshtein(d, oficial)
    achados << "erro de digitacao (distancia #{dist})" if dist.between?(1, 2)

    # 2. a marca aparece dentro do nome, mas nao e o dominio oficial
    if nome_sem_tld.gsub(/[^a-z0-9]/, "").include?(marca) && d != oficial
      achados << "contem a marca '#{marca}' fora do dominio oficial (#{oficial})"
    end
  end
  achados.uniq
end
%w[iefp.pt iefp-pt.com secure-iefp-pt.com ieefp.pt lefp.pt
  cgd-pt.com ctt-encomendas.pt millenniumbcp-seguro.pt
  www.publico.pt].each do |d|
  s = sinais(d)
  puts d.ljust(24) + (s.empty? ? "sem sinais" : "ALERTA: " + s.join("; "))
end

#iefp.pt sem sinais
#iefp-pt.com ALERTA: contem a marca 'iefp' fora do dominio oficial (iefp.pt)
#secure-iefp-pt.com ALERTA: contem a marca 'iefp' fora do dominio oficial (iefp.pt)
#ieefp.pt ALERTA: erro de digitacao (distancia 1)
#lefp.pt ALERTA: erro de digitacao (distancia 1)
#cgd-pt.com ALERTA: contem a marca 'cgd' fora do dominio oficial (cgd.pt)
#ctt-encomendas.pt ALERTA: contem a marca 'ctt' fora do dominio oficial (ctt.pt)
#millenniumbcp-seguro.pt ALERTA: contem a marca 'millenniumbcp' fora do dominio oficial (millenniumbcp.pt)
#www.publico.pt sem sinais

#MARCAS = { "iefp" => "iefp.pt",... } - Agora um hash: o nome da marca e o domínio oficial. Separar os dois é o que permite fazer as duas perguntas.
#d = dominio.sub(/\Awww\./, "") - Normaliza retirando o www..
#.between?(a, b) - Responde se o valor está entre os dois limites, inclusive. Mais legível do que duas comparações.