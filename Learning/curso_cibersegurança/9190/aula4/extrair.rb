PADRAO =
/^(?<ip>\d+\.\d+\.\d+\.\d+).*\[(?<data>[^\]]+)\]\s+"(?<metodo>\w+)\s+(?<url>\S+)"\s+(?<codigo>\d{3})/

validas = 0
ignoradas = 0

File.foreach("acesso.log") do |linha|
  m = linha.match(PADRAO)

  if m.nil?
    ignoradas += 1
    next
  end
  
  validas += 1
  puts "#{m[:ip].ljust(16)} #{m[:metodo].ljust(6)} #{m[:url].ljust(14)} #{m[:codigo]}"
end

puts
puts "Linhas processadas: #{validas}"
puts "Linhas ignoradas: #{ignoradas}"

#192.168.1.10 GET /index.html 200
#10.0.0.55 POST /login 401
#10.0.0.55 POST /login 401
#192.168.1.10 GET /produtos 200
#10.0.0.55 POST /login 401
#203.0.113.9 GET /admin 403
#192.168.1.22 GET /contactos 200
#203.0.113.9 GET /wp-admin 404
#192.168.1.10 GET /favicon.ico 404
#10.0.0.55 POST /login 200

#Linhas processadas: 10
#Linhas ignoradas: 0