linha = '10.0.0.55 - - [29/Sep/2026:09:15:31] "POST /login" 401'

padrao =
/^(?<ip>\d+\.\d+\.\d+\.\d+).*\[(?<data>[^\]]+)\]\s+"(?<metodo>\w+)\s+(?<url>\S+)"\s+(?<codigo>\d{3})/

m = linha.match(padrao)

if m
  puts "IP: #{m[:ip]}"
  puts "Data: #{m[:data]}"
  puts "Metodo: #{m[:metodo]}"
  puts "URL: #{m[:url]}"
  puts "Codigo: #{m[:codigo]}"
else
  puts "A linha nao corresponde ao padrao"
end

#IP: 10.0.0.55
#Data: 29/Sep/2026:09:15:31
#Metodo: POST
#URL: /login
#Codigo: 401