contagem = Hash.new(0)
File.foreach("evidencias/web/acesso.log") do |linha|
  contagem[linha.split(" ")[0]] += 1
end
contagem.each { |ip, n| puts "#{ip}  #{n}" }

#192.168.1.10  3
#10.0.0.55  4
#203.0.113.9  2
#192.168.1.22  1