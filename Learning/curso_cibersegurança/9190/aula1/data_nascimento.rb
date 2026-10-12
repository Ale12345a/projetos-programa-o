nome  = ARGV[0]
idade = ARGV[1]
sn    = ARGV[2] # "s" ou "n"
 
if nome.nil? || idade.nil? || sn.nil?
  puts "Uso: ruby saudacao.rb <nome> <idade> <s/n> (sendo s se já fizeste aniversário este ano e n se não)"
elsif sn == "s"
  puts "Ola, #{nome}, tens #{idade}, nasceste em #{2026 - idade.to_i} e bem vindo ao curso"
elsif sn == "n"
 puts "Ola, #{nome}, tens #{idade}, nasceste em #{2025 - idade.to_i} e bem vindo ao curso"
else
  puts "Por favor insere 's' para sim ou 'n' para nao, para saber se já fez aniversário este ano ou não no ultimo argumento."
end