File.readlines("evidencias/evidencias/sistema/aplicacao.log", encoding: "UTF-8").each_with_index do |l, i|
  estado = l.valid_encoding? ? "OK" : "INVALIDA"
  puts "linha #{i + 1}: #{estado}"
end

#linha 1: OK
#linha 2: INVALIDA
#linha 3: INVALIDA
#linha 4: OK
#linha 5: INVALIDA

#readlines: lê o ficheiro e devolve um array com as linhas.
#"evidencias/evidencias/sistema/aplicacao.log": caminho do ficheiro que queremos ler.
#encoding: "UTF-8": indica ao Ruby que deve interpretar os bytes do ficheiro como UTF-8.
#each_with_index: percorre os elementos do array e disponibiliza também o índice de cada elemento.
#l.valid_encoding?: verifica se a linha é válida segundo a codificação que lhe está atribuída.
#?: significa que, se a condição for verdadeira, é escolhido o primeiro valor.
