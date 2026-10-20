TEXTO = File.read("evidencias/evidencias/email/phishing.eml", encoding: "UTF-8")

EMAIL = /[A-Za-z0-9._%+\-]+@[A-Za-z0-9.\-]+\.[A-Za-z]{2,}/ #a peneira

puts "ENDERECOS DE EMAIL"
TEXTO.scan(EMAIL).uniq.sort.each { |e| puts " #{e}" }

#ENDERECOS DE EMAIL
# 7f3a2b1c9d@secure-iefp-pt.com
# bounce@mail.secure-iefp-pt.com
# geral@secure-iefp-pt.com
# marconi.pereira@exemplo.pt
# no-reply@iefp.pt
# suporte@secure-iefp-pt.com

#TEXTO =File.read("...phishing.eml",encoding: "UTF-8") - Lê a mensagem inteira. Aqui o File.read justifica-se: uma mensagem de
#correio é pequena e os indicadores podem estar espalhados por várias linhas,incluindo partidas a meio

#EMAIL = /[A-Za-z0-9._%+\-]+@[A-Za-z0-9.\-]+\.[A-Za-z]{2,}/ - O padrão, lido em português: um ou mais caracteres de nome, o arroba, um
#ou mais caracteres de domínio, um ponto, e pelo menos duas letras. O traço aparece escapado para não ser confundido com uma gama de caracteres.
#TEXTO.scan(EMAIL) Encontra todas as ocorrências no texto inteiro.
#.uniq.sort Elimina repetições e ordena. Numa lista de indicadores, ambas as operações
#são obrigatórias: repetições inflacionam a contagem e a falta de ordem impede a comparação.