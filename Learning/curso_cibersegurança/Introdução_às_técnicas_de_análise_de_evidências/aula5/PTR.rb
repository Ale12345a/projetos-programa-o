require "resolv"

["8.8.8.8", "213.205.70.14", "203.0.113.9"].each do |ip|
  begin
    puts "#{ip.ljust(16)} #{Resolv.getname(ip)}"
  rescue Resolv::ResolvError
    puts "#{ip.ljust(16)} sem registo PTR"
  end
end

#8.8.8.8          dns.google
#213.205.70.14    213-205-70-14.net.static.novis.pt
#203.0.113.9      sem registo PTR

#["8.8.8.8", "213.205.70.14","203.0.113.9"].each do |ip| - Três endereços: um serviço público conhecido, o servidor do IEFP, e o endereço do atacante.
#Resolv.getname(ip) A consulta inversa: de endereço para nome. Internamente consulta um registo PTR.
#rescue Resolv::ResolvError - Muitos endereços não têm PTR, e isso é normal. O tratamento é obrigatório, e a ausência é registada como resultado.