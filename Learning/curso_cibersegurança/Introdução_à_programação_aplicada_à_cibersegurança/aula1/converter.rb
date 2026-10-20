nome = ARGV[0]
idade = ARGV[1]
puts "A idade chegou como um #{idade.class}."
# Para provocar o TypeError, retira o # apenas da linha seguinte:
#puts 2026 - idade
puts "Ola, #{nome}! Nasceste em #{2026 - idade.to_i}."

#> ruby converter.rb Ana 30
#A idade chegou como um String.
#Ola, Ana! Nasceste em 1996.