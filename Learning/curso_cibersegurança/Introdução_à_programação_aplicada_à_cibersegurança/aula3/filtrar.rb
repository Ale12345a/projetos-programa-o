File.open("erros.log", "w") do |saida|
  File.foreach("acesso.log") do |linha|
    saida.puts linha if linha.split(" ")[6].to_i >= 400
  end
end

# E quantas linhas ficaram no erros.log:
puts "Gravado em erros.log: #{File.readlines("erros.log").length} linhas"

#Gravado em erros.log: 6 linhas