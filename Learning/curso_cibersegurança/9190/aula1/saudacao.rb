nome = ARGV[0]

if nome.nil?
  puts "Uso: ruby saudacao.rb <nome>"
else
  puts "Ola, #{nome}! Bem-vindo ao curso."
end

#> ruby saudacao.rb Ana
#Ola, Ana! Bem-vindo ao curso.
#> ruby saudacao.rb
#Uso: ruby saudacao.rb <nome> 