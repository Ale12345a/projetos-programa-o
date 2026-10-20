codigo = 401
url = "/login"

if codigo == 401 && url == "/login"
  puts "ALERTA: tentativa de login falhada"
end

if codigo == 401 || codigo == 403
  puts "Acesso recusado"
end

#ALERTA: tentativa de login falhada
#Acesso recusado