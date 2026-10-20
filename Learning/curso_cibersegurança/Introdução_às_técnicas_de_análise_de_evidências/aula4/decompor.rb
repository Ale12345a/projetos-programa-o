require "uri"

TEXTO = File.read("evidencias/evidencias/email/phishing.eml", encoding: "UTF-8")

EMAIL = /[A-Za-z0-9._%+\-]+@[A-Za-z0-9.\-]+\.[A-Za-z]{2,}/
URL = /https?:\/\/[^\s<>"']+/

puts "URLs"
TEXTO.scan(URL).uniq.sort.each { |u| puts " #{u}" }
puts

dominios = TEXTO.scan(URL).map { |u| URI.parse(u).host } + TEXTO.scan(EMAIL).map { |e| e.split("@").last }

puts "DOMINIOS (de URLs e de emails)"
dominios.uniq.sort.each { |d| puts " #{d}" }

#URLs
# https://secure-iefp-pt.com/area-reservada/login?id=88213
# https://www.iefp.pt/

#DOMINIOS (de URLs e de emails)
# exemplo.pt
# iefp.pt
# mail.secure-iefp-pt.com
# secure-iefp-pt.com
# www.iefp.pt

#require "uri" - Carrega a biblioteca de manipulação de endereços web.

#URL = /https?:\/\/[^\s<>"']+/ - Encontra URLs no meio do texto: http com um s opcional, duas barras
#escapadas, e tudo o que não seja espaço nem um dos caracteres que tipicamente delimitam uma URL num documento.

#TEXTO.scan(URL).uniq.sort.each {|u| puts ... } - Lista as URLs distintas, ordenadas.

#TEXTO.scan(URL).map { |u| URI.parse(u).host } - A divisão de trabalho deste capítulo: a expressão regular encontrou a URL no
#texto; a biblioteca extrai-lhe a máquina.

#+ TEXTO.scan(EMAIL).map { |e| e.split("@").last } -  Junta os domínios vindos dos endereços de correio. O sinal de mais concatena as duas listas.

#dominios.uniq.sort.each - A lista consolidada de domínios do caso, sem repetições.