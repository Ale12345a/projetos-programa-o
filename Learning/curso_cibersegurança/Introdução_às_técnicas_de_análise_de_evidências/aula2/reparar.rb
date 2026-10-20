File.open("evidencias/evidencias/sistema/aplicacao.log", "rb") do |f|
  f.each_line.with_index(1) do |bruta, n|
    linha  = bruta.force_encoding("UTF-8")
    origem = nil
 
    unless linha.valid_encoding?
      tentativa = bruta.dup.force_encoding("ISO-8859-1").encode("UTF-8")
      if tentativa.valid_encoding?
        linha  = tentativa
        origem = "convertida de ISO-8859-1"
      else
        linha  = linha.scrub("?")
        origem = "bytes irrecuperaveis substituidos por ?"
      end
    end
 
    puts "#{n}: #{linha.chomp}"
    puts "   ^ #{origem}" if origem
  end
end

#1: 2026-10-13 09:02:11 INFO  Utilizador 'joao' autenticado com sucesso
#2: 2026-10-13 09:03:47 WARN  Sessão expirada para 'ana'
#   ^ convertida de ISO-8859-1
#3: 2026-10-13 09:04:02 ERROR Falha ao gravar ficheiro 'relatório.pdf'
#   ^ convertida de ISO-8859-1
#4: 2026-10-13 09:05:19 INFO  Pedido concluído em 42ms
#5: 2026-10-13 09:06:33 ERROR Payload corrompido: ÿþ dados ilegiveis
#   ^ convertida de ISO-8859-1

#File.open: abre um ficheiro.
#rb" significa read binary: abrir para leitura em modo binário.
#- r = leitura (read).
#- b = modo binário (binary).
#do |f|: guarda o ficheiro aberto na variável f.
#each_line: percorre o ficheiro linha a linha.
#with_index(1): numera as linhas a partir de 1, em vez de 0.
#force_encoding("UTF-8") atribui à string a etiqueta de codificação UTF-8. Não converte nem altera os bytes.
#origem = nil indica que ainda não foi registado nenhum tratamento especial para a linha.
#valid_encoding? verifica se os bytes são válidos na codificação atribuída.
#unless significa “a menos que” ou, neste contexto, “se não”.
#bruta.dup: cria uma cópia da string original, para trabalhar sem alterar a variável bruta.
#force_encoding("ISO-8859-1"): interpreta os bytes dessa cópia como ISO-8859-1.
#encode("UTF-8"): converte o texto interpretado para UTF-8.
#scrub("?"): substitui sequências inválidas na string por ?.