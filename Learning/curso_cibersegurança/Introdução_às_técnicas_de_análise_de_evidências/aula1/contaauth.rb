contagem = Hash.new(0)
File.foreach("evidencias/sistema/auth.log") do |linha|
  contagem[linha.split(" ")[0]] += 1
end
contagem.each { |ip, n| puts "#{ip}  #{n}" }

#Oct 7