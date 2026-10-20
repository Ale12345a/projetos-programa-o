
origem = "evidencias/evidencias/sistema/aplicacao.log"
destino = "aplicacao_normalizado.log"

numero = 0

File.open(destino, "w", encoding: "UTF-8") do |saida|
  File.foreach(origem, encoding: "UTF-8") do |bruta|
    numero += 1

    if bruta.valid_encoding?
      linha = bruta
      estado = "mantida — UTF-8 válida"
    else
      linha = bruta.encode("UTF-8", "ISO-8859-1")
      estado = "convertida de ISO-8859-1 para UTF-8"
    end

    saida.write(linha)
    puts "Linha #{numero}: #{estado}"
  end
end

puts "\nFicheiro normalizado guardado em: #{destino}"

#Linha 1: mantida — UTF-8 válida
#Linha 2: convertida de ISO-8859-1 para UTF-8
#Linha 3: convertida de ISO-8859-1 para UTF-8
#Linha 4: mantida — UTF-8 válida
#Linha 5: convertida de ISO-8859-1 para UTF-8

#Ficheiro normalizado guardado em: aplicacao_normalizado.log


#origem: caminho do ficheiro original que queremos ler.
#destino: nome do novo ficheiro onde vamos guardar o resultado.
#File.open: abre ou cria um ficheiro.
#destino: indica o nome do ficheiro de destino.
#"w": modo de escrita (write). Cria o ficheiro se não existir e apaga o conteúdo anterior se já existir.
#encoding: "UTF-8": define UTF-8 como codificação de escrita.
#do |saida|: inicia um bloco e guarda o ficheiro aberto na variável saida.
#saida: representa o ficheiro de destino aberto.
#write: escreve o conteúdo de linha no ficheiro.
##{destino}: insere o nome do ficheiro criado.
