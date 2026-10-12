# 1. Contar as linhas
contador = 0

File.foreach("acesso.log") do |linha|
  contador += 1
end

puts "Número de linhas: #{contador}"


# 2. Primeira linha com e sem chomp
File.foreach("acesso.log").with_index do |linha, i|
  if i == 0
    puts "Com chomp:"
    puts linha.chomp + "<"

    puts "Sem chomp:" + "<"
    puts linha
  end
end


# 3. Numerar cada linha
File.foreach("acesso.log").with_index(1) do |linha, numero|
  puts "#{numero}: #{linha.chomp}"
end

#Número de linhas: 10
#Com chomp:
#192.168.1.10 - - [29/Sep/2026:09:12:04] "GET /index.html" 200
#Sem chomp:
#192.168.1.10 - - [29/Sep/2026:09:12:04] "GET /index.html" 200
#1: 192.168.1.10 - - [29/Sep/2026:09:12:04] "GET /index.html" 200
#2: 10.0.0.55 - - [29/Sep/2026:09:15:31] "POST /login" 401
#3: 10.0.0.55 - - [29/Sep/2026:09:15:48] "POST /login" 401
#4: 192.168.1.10 - - [29/Sep/2026:09:16:02] "GET /produtos" 200
#5: 10.0.0.55 - - [29/Sep/2026:09:16:12] "POST /login" 401
#6: 203.0.113.9 - - [29/Sep/2026:09:18:55] "GET /admin" 403
#7: 192.168.1.22 - - [29/Sep/2026:09:21:37] "GET /contactos" 200
#8: 203.0.113.9 - - [29/Sep/2026:09:23:10] "GET /wp-admin" 404
#9: 192.168.1.10 - - [29/Sep/2026:09:25:44] "GET /favicon.ico" 404
#10: 10.0.0.55 - - [29/Sep/2026:09:31:19] "POST /login" 200