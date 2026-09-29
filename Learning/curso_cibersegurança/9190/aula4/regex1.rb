# Porque e que o split nao chega: esta linha tem um ESPACO dentro da URL.
linha = '203.0.113.9 - - [29/Sep/2026:09:44:11] "POST /login?next=/area privada" 401'
p linha.split(" ")[6]

# e com um padrao, em vez de contar campos:
puts "tem 401" if linha =~ /401/

# O =~ responde com a POSICAO onde o padrao encaixa, e nil se nao encaixar.
if linha =~ /POST/
  puts "Encontrei um pedido POST"
end

puts linha =~ /POST/

#"privada\""
#tem 401
#Encontrei um pedido POST
#40