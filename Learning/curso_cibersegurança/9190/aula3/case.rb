falhas = 7

case falhas
when 0..4 then puts "normal"
when 5..9 then puts "a vigiar"
when 10..99 then puts "alerta"
else puts "fora de escala"
end

#a vigiar