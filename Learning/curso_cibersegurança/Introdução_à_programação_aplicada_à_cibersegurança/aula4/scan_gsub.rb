texto = "Acessos de 10.0.0.55 e de 192.168.1.10 registados"

puts texto.scan(/\d+\.\d+\.\d+\.\d+/).inspect

puts texto.sub(/\d+\.\d+\.\d+\.\d+/, "[OCULTO]")
puts texto.gsub(/\d+\.\d+\.\d+\.\d+/, "[OCULTO]")

emails = "contactos: ana@exemplo.pt e joao.silva@empresa.com"
puts emails.scan(/[\w.+-]+@[\w-]+\.[\w.]+/).inspect

#["10.0.0.55", "192.168.1.10"]
#Acessos de [OCULTO] e de 192.168.1.10 registados
#Acessos de [OCULTO] e de [OCULTO] registados
#["ana@exemplo.pt", "joao.silva@empresa.com"]