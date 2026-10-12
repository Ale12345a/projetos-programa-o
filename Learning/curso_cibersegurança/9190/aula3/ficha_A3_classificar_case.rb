File.foreach("acesso.log") do |linha|
  codigo = linha.split.last.to_i

  case codigo
  when 200..299 then
    puts "#{codigo} - sucesso"
  when 400..499 then
    puts "#{codigo} - erro do cliente"
  when 500..599 then
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