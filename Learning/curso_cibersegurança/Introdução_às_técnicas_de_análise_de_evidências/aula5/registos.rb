require "resolv"
# O RESOLVEDOR DE DNS, E A LINHA COMENTADA E A LENTA. Este programa constroi o
# seu proprio resolvedor, por isso a configuracao vai onde ele e criado, dentro
# do metodo. Por omissao o Resolv::DNS usa a lista de servidores de nomes que a
# TUA rede anuncia e tenta-os pela ordem em que la estao; se o router anunciar
# servidores que nao respondem — e e vulgar anunciar servidores IPv6 mortos —,
# cada consulta espera pelo tempo limite de cada um. Medido: DEZ segundos por
# consulta, contra 0,07 perguntando a dois servidores publicos.
#
# O `timeouts = 3` ja aqui estava e continua a ser o que evita o pior caso: numa
# rede que bloqueie o DNS externo sao 6 segundos ate desistir, em vez de 150.
def registos(dominio)
  # r = Resolv::DNS.new
  r = Resolv::DNS.new(nameserver: ["1.1.1.1", "8.8.8.8"])
  r.timeouts = 3
  {
    "A" => r.getresources(dominio, Resolv::DNS::Resource::IN::A).map { |x| x.address.to_s },
    "AAAA" => r.getresources(dominio, Resolv::DNS::Resource::IN::AAAA).map { |x| x.address.to_s },
    "MX" => r.getresources(dominio, Resolv::DNS::Resource::IN::MX).sort_by(&:preference).map { |x| "#{x.preference} #{x.exchange}" },
    "NS" => r.getresources(dominio, Resolv::DNS::Resource::IN::NS).map { |x| x.name.to_s },
    "TXT" => r.getresources(dominio, Resolv::DNS::Resource::IN::TXT).map { |x| x.strings.join },
  }
ensure
  r.close if r
end

d = "iefp.pt"
puts "DOMINIO: #{d}"
registos(d).each do |tipo, valores|
  puts " #{tipo.ljust(5)} #{valores.empty? ? "(nenhum)" : valores.first(3).join(" | ")}"
end

#DOMINIO: iefp.pt
# A     213.205.70.14
# AAAA  (nenhum)
# MX    10 iefp-pt.mail.protection.outlook.com
# NS    ns1-02.azure-dns.com | ns2-02.azure-dns.net | ns3-02.azure-dns.org
# TXT   adobe-idp-site-verification=845176ec7e237c4a932ada0910b09ebeec7a3a4041d99f9b2ce1c81c4e0cfc32 | google-site-verification=S6E2hxBhb3aBcY0cIqsuSf9Srbhm_AkwYysY2C5YoMA | v=spf1 a mx ip4:213.205.70.13 ip4:213.205.70.12 include:spf.protection.outlook.com include:_spf.kmitd.com ~all

#r = Resolv::DNS.new Cria um resolvedor — um objeto que sabe fazer consultas. Usar o mesmo para as cinco consultas evita abrir e fechar ligação cinco vezes.
#r.timeouts = 3 Três segundos por consulta. Sem isto, um domínio que não responda deixa o programa pendurado indefinidamente

#r.getresources(dominio,Resolv::DNS::Resource::IN::A) - Pede os registos de um tipo. O IN é a classe da Internet, e o A é o tipo.
#Devolve uma lista de objetos, não de textos.

#.map { |x| x.address.to_s } - Extrai o endereço de cada objeto e converte-o em texto. Cada tipo de registo tem os seus campos: address no A, exchange e preference no MX, name
#no NS, strings no TXT.

#.sort_by(&:preference) - no MX Ordena pela preferência: o servidor com número mais baixo é o primeiro a ser tentado.
#ensure r.close if r - Fecha a ligação haja ou não erro. Aparece dentro do método, sem begin: num método, o ensure aplica-se ao corpo todo.
#valores.first(3).join(" | ") - Mostra no máximo três valores por tipo, para a saída caber no ecrã