# "usa scrub para evitar o erro de encoding"
alvo = "relatório.pdf"
encontrados = 0
 
File.foreach("evidencias/sistema/aplicacao.log",
             encoding: "UTF-8") do |bruta|
  linha = bruta.scrub("")
  encontrados += 1 if linha.include?(alvo)
end
 
puts "Ocorrencias de '#{alvo}': #{encontrados}"

#Ocorrências de 'relatório.pdf': 1

#foreach: percorre o ficheiro linha a linha, sem precisar de carregar todo o conteúdo de uma só vez.
#do |bruta|: inicia o bloco e guarda cada linha na variável bruta.
#bruta: linha original lida do ficheiro.
#encode: converte de uma codificação para outra.
#linha.include?(alvo): verifica se a linha contém o texto guardado em alvo.