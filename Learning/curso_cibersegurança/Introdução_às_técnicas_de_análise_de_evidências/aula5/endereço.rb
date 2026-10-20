require "resolv"

d = "www.google.com"
puts "Resolv.getaddress -> #{Resolv.getaddress(d)}"
puts "Resolv.getaddresses -> #{Resolv.getaddresses(d).size} enderecos"

#Resolv.getaddress -> 142.251.157.119
#Resolv.getaddresses -> 8 enderecos

#Resolv.getaddress(d) Devolve um endereço — o primeiro que o resolvedor escolher. Qual deles é indeterminado e pode variar entre execuções.
#Resolv.getaddresses(d).size - Devolve todos os endereços, numa lista. A diferença no código são duas letras.