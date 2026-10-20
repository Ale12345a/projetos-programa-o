require "ipaddr"

ips = %w[192.168.1.100 192.168.1.9 192.168.1.20 10.0.0.55]

puts "por texto : #{ips.sort.join(" ")}"
puts "por valor : #{ips.sort_by { |i| IPAddr.new(i).to_i }.join(" ")}"

#por texto : 10.0.0.55 192.168.1.100 192.168.1.20 192.168.1.9
#por valor : 10.0.0.55 192.168.1.9 192.168.1.20 192.168.1.100

#IPAddr#to_i Método Converte o endereço no número inteiro que ele realmente é. Um IPv4 é um número de 32 bits; a notação com pontos é apenas uma forma de o escrever.