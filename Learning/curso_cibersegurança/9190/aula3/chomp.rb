#o caractere invisivel chama-se \n
linha = "10.0.0.55 falhou a entrar\n"

puts linha + "<"
puts linha.chomp + "<" # o fim da linha vem agarrado

#10.0.0.55 falhou a entrar
#<
#10.0.0.55 falhou a entrar<