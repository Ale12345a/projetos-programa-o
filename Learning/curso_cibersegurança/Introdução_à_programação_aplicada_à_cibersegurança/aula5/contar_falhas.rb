falhas = Hash.new(0)

File.foreach("acesso_alargado.log") do |linha|
  campos = linha.split(" ")
  ip = campos[0]
  codigo = campos[6]

  falhas[ip] += 1 if codigo == "401"
end

falhas.each do |ip, total|
  puts "#{ip} #{total} falhas SUSPEITO" if total >= 3
end

#10.0.0.55 3 falhas SUSPEITO