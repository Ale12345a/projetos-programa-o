
falhas = Hash.new(0)

File.foreach("acesso_alargado.log") do |linha|
  resultado = linha.match(/^(\d+\.\d+\.\d+\.\d+).*"\s+(\d{3})\s*$/)

  if resultado
    ip = resultado[1]
    codigo = resultado[2]

    falhas[ip] += 1 if codigo == "401"
  end
end

falhas.each do |ip, total|
  if total >= 3
    puts "#{ip} #{total} falhas SUSPEITO"
  end
end

#10.0.0.55 3 falhas SUSPEITO
#203.0.113.9 3 falhas SUSPEITO