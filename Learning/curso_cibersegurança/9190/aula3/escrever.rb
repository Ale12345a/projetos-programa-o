contagem = Hash.new(0)

File.foreach("acesso.log") do |linha|
  contagem[linha.split(" ").last.to_i] += 1
end

File.open("relatorio.txt", "w") do |f|
  contagem.each { |cod, n| f.puts "#{cod}: #{n}" }
end

puts File.read("relatorio.txt")

#200: 4
#401: 3
#403: 1
#404: 2