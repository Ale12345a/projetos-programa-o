nome = ARGV[0]
idade = ARGV[1]

if nome.nil? || idade.nil?
  puts "Uso: ruby saudacao.rb <nome> <idade>"
else
  nascimento = 2026 -idade.to_i
  puts "Ola, #{nome}! Bem-vindo ao curso. Tu tens #{idade} e, por isso, nasceste em #{nascimento}"
end

#> ruby saudacao.rb Alexandre 25
#Ola, Alexandre! Bem-vindo ao curso. Tu tens 25 e, por isso, nasceste em 2001
#> ruby saudacao.rb
#Uso: ruby saudacao.rb <nome> <idade>