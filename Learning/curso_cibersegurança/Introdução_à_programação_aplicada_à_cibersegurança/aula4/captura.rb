linha = '10.0.0.55 - - [29/Sep/2026:09:15:31] "POST /login" 401'

if linha =~ /(\d+\.\d+\.\d+\.\d+)/
  puts "IP encontrado: #{$1}"
end

#IP encontrado: 10.0.0.55