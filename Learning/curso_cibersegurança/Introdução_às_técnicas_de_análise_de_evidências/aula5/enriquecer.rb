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
  begin
    ips = Resolv.getaddresses(d)
    if ips.empty?
      puts "#{d.ljust(34)} NXDOMAIN ou sem registo A"
    else
      puts "#{d.ljust(34)} #{ips.join(", ")}"
    end
  rescue Resolv::ResolvError => e
    puts "#{d.ljust(34)} NAO RESOLVE (#{e.class})"
  end
end

#iefp.pt                            213.205.70.14
#dominio-que-nao-existe-9191.pt     NXDOMAIN ou sem registo A
#www.google.com                     142.251.150.119, 142.251.151.119, 142.251.155.119, 142.251.154.119, 142.251.153.119, 142.251.157.119, 142.251.156.119, 142.251.152.119

#begin - Dentro do ciclo. É esta a diferença decisiva: cada domínio é protegido individualmente, e um erro num deles não interrompe os restantes.
#ips = Resolv.getaddresses(d) - Todos os endereços, não um só. Corrige o segundo defeito.
#if ips.empty? - O getaddresses devolve lista vazia em vez de erro nalguns casos — tem de se verificar as duas coisas.

#puts "#{d.ljust(34)} NXDOMAIN ousem registo A" - O «não resolve» é impresso como resultado, na mesma coluna dos outros.
#Numa análise, a ausência de resposta é informação.

#rescue Resolv::ResolvError => e Apanha o erro específico e guarda-o na variável e, para poder identificar o tipo na mensagem
