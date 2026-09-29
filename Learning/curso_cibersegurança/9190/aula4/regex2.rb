# CLASSES e QUANTIFICADORES.
# A classe diz que TIPO de caractere se procura. O quantificador diz QUANTAS
# VEZES pode aparecer o que esta imediatamente antes dele.
texto = "Servidor 10.0.0.55 respondeu 401 em 250ms"

# Classes: \d e um algarismo, e o \. e um ponto a serio.
puts texto.scan(/\d+/).inspect
puts texto.scan(/\d+\.\d+\.\d+\.\d+/).inspect
puts texto.scan(/\d{3}/).inspect

# A tabela dos quantificadores, cada um sobre o mesmo texto.
puts "+ uma ou mais vezes /50+/ -> " + texto.scan(/50+/).inspect
puts "* zero ou mais vezes /50*/ -> " + texto.scan(/50*/).inspect
puts "{3} exatamente tres /\\d{3}/ -> " + texto.scan(/\d{3}/).inspect

# O ? e ZERO OU UMA vez: torna OPCIONAL o caractere que esta antes dele.
# E o defeito do programa da abertura: em /login?/ o ? nao e um ponto de
# interrogacao — e o "n" que passa a ser opcional.
puts "? zero ou uma vez /login?/ em 'login' -> " + "login".scan(/login?/).inspect
puts "? zero ou uma vez /login?/ em 'logi' -> " + "logi".scan(/login?/).inspect

#["10", "0", "0", "55", "401", "250"]
#["10.0.0.55"]
#["401", "250"]
#+ uma ou mais vezes /50+/ -> ["50"]
#* zero ou mais vezes /50*/ -> ["5", "5", "50"]
#{3} exatamente tres /\d{3}/ -> ["401", "250"]
#? zero ou uma vez /login?/ em 'login' -> ["login"]
#? zero ou uma vez /login?/ em 'logi' -> ["logi"]