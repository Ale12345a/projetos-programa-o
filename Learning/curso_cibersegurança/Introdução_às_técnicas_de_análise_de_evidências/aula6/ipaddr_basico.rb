require "ipaddr"

rede = IPAddr.new("192.168.1.0/24")
puts "Rede               : #{rede}"
puts "Mascara            : #{rede.prefix}"
puts "Primeiro/ultimo    : #{rede.to_range.first} .. #{rede.to_range.last}"
puts "Enderecos          : #{rede.to_range.count}"
puts

["192.168.1.10", "192.168.2.10", "10.0.0.55"].each do |ip|
  dentro = rede.include?(IPAddr.new(ip)) ? "sim" : "nao"
  puts "#{ip.ljust(14)} pertence a 192.168.1.0/24? #{dentro}"
end
puts

# IPv6 funciona exatamente da mesma maneira
v6 = IPAddr.new("2001:db8::/32")
puts "IPv6 : #{v6} prefixo /#{v6.prefix}"
puts "2001:db8::1 dentro? #{v6.include?(IPAddr.new("2001:db8::1"))}"

#Rede               : 192.168.1.0
#Mascara            : 24
#Primeiro/ultimo    : 192.168.1.0 .. 192.168.1.255
#Enderecos          : 256

#192.168.1.10   pertence a 192.168.1.0/24? sim
#192.168.2.10   pertence a 192.168.1.0/24? nao
#10.0.0.55      pertence a 192.168.1.0/24? nao

#IPv6 : 2001:db8:: prefixo /32
#2001:db8::1 dentro? true


#require "ipaddr" Carrega a biblioteca de endereços. Vem com o Ruby.
#IPAddr.new(texto) - Cria um endereço ou uma rede a partir de texto, em IPv4 ou IPv6. Levanta erro se o texto não for válido — o que é uma validação de graça.
#.include?(outro) - Responde se o endereço pertence à rede. Compara valores, não caracteres.
#.prefix - O número de bits da máscara.
#.to_range - A gama de endereços da rede, como intervalo. Aceita .first, .last e .count.
#.to_i - O endereço como número inteiro. É o que permite ordenar e comparar corretamente.
#Notação CIDR - /24 significa que os primeiros 24 bits identificam a rede. Quantos menos bits, maior a rede: um /8 tem mais de dezasseis milhões de endereços.