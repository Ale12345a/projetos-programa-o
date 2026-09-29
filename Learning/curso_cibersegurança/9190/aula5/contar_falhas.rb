# Escrito a partir do pedido:
# "escreve um script Ruby que leia um log de acessos e diga
# que IPs estao a tentar entrar a forca"

falhas = Hash.new(0)

File.foreach("acesso_alargado.log") do |linha|
  campos = linha.split(" ")
  ip = campos[0]
  codigo = campos[6]

  falhas[ip] += 1 if codigo == "401"
end
puts "IPs com tentativas de autenticacao falhadas:"
falhas.sort_by { |_ip, n| -n }.each do |ip, n|
  estado = n >= 3 ? "SUSPEITO" : "a vigiar"
  puts " #{ip.ljust(16)} #{n} falhas #{estado}"
end

#IPs com tentativas de autenticacao falhadas:
#10.0.0.55 3 falhas SUSPEITO