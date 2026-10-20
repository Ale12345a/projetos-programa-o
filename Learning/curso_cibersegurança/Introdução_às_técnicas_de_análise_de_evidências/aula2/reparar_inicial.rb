File.readlines("evidencias/evidencias/sistema/aplicacao.log",
               encoding: "UTF-8").each do |l|
  next if l.valid_encoding?
  puts l.encode("UTF-8", "ISO-8859-1")
end

#2026-10-13 09:03:47 WARN  Sessão expirada para 'ana'
#2026-10-13 09:04:02 ERROR Falha ao gravar ficheiro 'relatório.pdf'
#2026-10-13 09:06:33 ERROR Payload corrompido: ÿþ dados ilegiveis

#encoding: "UTF-8": indica que o conteúdo deve ser interpretado como UTF-8.
#l.valid_encoding?: verifica se a linha é válida na codificação atribuída.
#next if: se a condição for verdadeira, salta imediatamente para a próxima linha. Ou seja, as linhas válidas são ignoradas.
#encode: converte o texto de uma codificação para outra.
#"UTF-8": codificação de destino.
#"ISO-8859-1": codificação de origem assumida.