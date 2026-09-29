ficheiro = "nao_existe.log"

begin
  conteudo = File.read(ficheiro)
  puts conteudo
rescue Errno::ENOENT
  puts "ERRO: o ficheiro '#{ficheiro}' nao existe."
  puts "Verifique o nome e a pasta onde esta a correr o script."
ensure
  puts "Analise terminada."
end

#ERRO: o ficheiro 'nao_existe.log' nao existe.
#Verifique o nome e a pasta onde esta a correr o script.
#Analise terminada.