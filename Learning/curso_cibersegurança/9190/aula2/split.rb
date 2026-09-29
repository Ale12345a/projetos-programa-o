linha = '10.0.0.55 - - [29/Sep/2026:09:15:31] "POST /login" 401'
campos = linha.split(" ")
puts campos[0]
puts campos[6]
puts "4: #{campos[4]}"
# A lista inteira, tal como o Ruby a guarda:
puts campos.inspect

#10.0.0.55
#401
#4: "POST
#["10.0.0.55", "-", "-", "[29/Sep/2026:09:15:31]", "\"POST", "/login\"", "401"]