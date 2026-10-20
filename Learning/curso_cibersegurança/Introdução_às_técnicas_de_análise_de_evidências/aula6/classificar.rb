require "ipaddr"

PRIVADAS = ["10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16"].map { |r| IPAddr.new(r) }

def privado?(ip)
  PRIVADAS.any? { |r| r.include?(ip) }
end

["192.168.1.10", "10.0.0.55", "203.0.113.9", "198.51.100.77"].each do |s|
  tipo = privado?(IPAddr.new(s)) ? "interno (RFC 1918)" : "publico"
  puts "#{s.ljust(14)} #{tipo}"
end

#192.168.1.10   interno (RFC 1918)
#10.0.0.55      interno (RFC 1918)
#203.0.113.9    publico
#198.51.100.77  publico

#tipo = privado?(IPAddr.new(s)) ? "interno (RFC 1918)" : "publico" - Converte o texto em objeto e classifica.

#.any? { } - Responde se pelo menos um elemento cumpre a condição. Existem também .all?, .none? e .one?, com o significado óbvio.
#RFC 1918 - Norma Define as gamas de endereços reservadas a redes privadas: 10.0.0.0/8, 172.16.0.0/12 e 192.168.0.0/16. Não são encaminháveis na Internet.