# 1. Guardar apenas as linhas com código 401 em falhas.log

File.open("falhas.log", "w") do |saida|
  File.foreach("acesso.log") do |linha|
    codigo = linha.split.last.to_i

    saida.puts linha if codigo == 401
  end
end


# 2. Guardar as linhas com código 400 ou superior em erros.log

File.open("erros.log", "w") do |saida|
  File.foreach("acesso.log") do |linha|
    codigo = linha.split.last.to_i

    saida.puts linha if codigo >= 400
  end
end