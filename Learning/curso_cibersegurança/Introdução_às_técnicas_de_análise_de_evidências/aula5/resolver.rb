require "resolv"
# O RESOLVEDOR DE DNS, E A LINHA COMENTADA E A LENTA.
#
# Por omissao o Resolv usa a lista de servidores de nomes que a TUA rede anuncia
# e tenta-os pela ordem em que la estao. E vulgar um router anunciar servidores
# que nao respondem, sobretudo servidores IPv6: nesse caso cada consulta espera
# pelo tempo limite de cada um antes de chegar ao que funciona. Medido num
# portatil ligado a uma rede dessas — dos cinco servidores anunciados so um
# respondia — DEZ segundos por dominio, e QUARENTA num dominio que nao existe.
#
# As tres linhas a seguir mandam perguntar a dois servidores publicos e poem um
# tempo limite de 3 segundos. A resposta e a mesma, a mensagem de erro e a mesma,
# e a consulta passa a 0,07 segundos. O tempo limite nao e enfeite: numa rede que
# bloqueie o DNS externo, sem ele sao 150 segundos ate desistir, com ele sao 6.
# Resolv::DefaultResolver.replace_resolvers([Resolv::Hosts.new, Resolv::DNS.new])
RESOLVEDOR = Resolv::DNS.new(nameserver: ["1.1.1.1", "8.8.8.8"])
RESOLVEDOR.timeouts = 3
Resolv::DefaultResolver.replace_resolvers([Resolv::Hosts.new, RESOLVEDOR])

SUSPEITOS = ["iefp.pt", "dominio-que-nao-existe-9191.pt", "www.google.com"]

SUSPEITOS.each do |d|
  puts "#{d.ljust(34)} #{Resolv.getaddress(d)}"
end

#iefp.pt 213.205.70.14
#resolv.rb:96:in `getaddress': no address for dominio-que-nao-existe-9191.pt (Resolv::ResolvError)
#from resolv.rb:46:in `getaddress'
#from resolver.rb:24:in `block in <main>'
#from resolver.rb:23:in `each'
#from resolver.rb:23:in `<main>'

#SUSPEITOS = [...] - Três domínios: um que existe, um que não existe, e outro que existe. A ordem é deliberada.
#puts "#{d.ljust(34)} #{Resolv.getaddress(d)}" - Dois defeitos numa linha. O getaddress devolve um endereço em vez de
#todos, e não há tratamento de exceção: quando o segundo domínio não resolve, o programa termina e o terceiro nunca é consultado.