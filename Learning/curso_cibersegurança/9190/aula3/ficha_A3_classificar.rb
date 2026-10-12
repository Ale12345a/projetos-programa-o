File.foreach("acesso.log") do |linha|
  codigo = linha.split.last.to_i

  if codigo >= 200 && codigo < 300
    puts "#{codigo} - sucesso"
  elsif codigo >= 400 && codigo < 500
    puts "#{codigo} - erro do cliente"
  elsif codigo >= 500 && codigo < 600
    puts "#{codigo} - erro do servidor"
  else
    puts "#{codigo} - outro código"
  end
end

#200 - sucesso
#401 - erro do cliente
#401 - erro do cliente
#200 - sucesso
#401 - erro do cliente
#403 - erro do cliente
#200 - sucesso
#404 - erro do cliente
#404 - erro do cliente
#200 - sucesso