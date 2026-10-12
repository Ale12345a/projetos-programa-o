require 'date'

nome = ARGV[0]
data_str = ARGV[1] # Exemplo: "1995-10-25" (AAAA-MM-DD)
 
if nome.nil? || data_str.nil?
  puts "Uso: ruby saudacao_idade_correta.rb <nome> <YYYY-MM-DD>"
else
  data_nascimento = Date.parse(data_str)
  hoje = Date.today
 
  # Cálculo exato considerando mês e dia
  idade = hoje.year - data_nascimento.year
  idade -= 1 if hoje < data_nascimento.next_year(idade)
 
  puts "Olá, #{nome}! Bem-vindo ao curso."
  puts "Nasceste em #{data_nascimento.year} e tens #{idade} anos."
end