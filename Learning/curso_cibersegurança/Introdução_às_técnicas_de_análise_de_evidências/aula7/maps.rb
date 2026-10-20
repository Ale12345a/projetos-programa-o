require "uri"

# A chave NUNCA fica escrita no codigo. Vem do ambiente.
CHAVE = ENV["GOOGLE_MAPS_API_KEY"]

def url_geocodificacao(morada, chave)
  URI::HTTPS.build(host: "maps.googleapis.com",path: "/maps/api/geocode/json",query: URI.encode_www_form("address" => morada, "key" => chave)).to_s
end

def url_mapa_estatico(pontos, chave)
  marcadores = pontos.map { |lat, lon| "#{lat},#{lon}" }
  URI::HTTPS.build(host: "maps.googleapis.com",path: "/maps/api/staticmap",query: URI.encode_www_form([["size", "640x400"], ["maptype", "roadmap"]] + marcadores.map { |m| ["markers", "color:red|#{m}"] } + [["key", chave]])).to_s
end

if CHAVE.nil? || CHAVE.empty?
  puts "GOOGLE_MAPS_API_KEY nao esta definida no ambiente."
  puts "As chamadas nao sao feitas; abaixo ficam os URLs que seriam usados."
  puts
end

exemplo = CHAVE || "<CHAVE>"

puts "Geocodificacao:"
puts " " + url_geocodificacao("Rua de Sao Francisco 4, Maia, Portugal", exemplo)
puts
puts "Mapa estatico com os IPs do caso:"
puts " " + url_mapa_estatico([[38.7223, -9.1393],[-23.5505, -46.6333],[55.7558, 37.6173]], exemplo)

#GOOGLE_MAPS_API_KEY nao esta definida no ambiente.
#As chamadas nao sao feitas; abaixo ficam os URLs que seriam usados.

#Geocodificacao:
# https://maps.googleapis.com/maps/api/geocode/json?address=Rua+de+Sao+Francisco+4%2C+Maia%2C+Portugal&key=%3CCHAVE%3E

#Mapa estatico com os IPs do caso:
# https://maps.googleapis.com/maps/api/staticmap?size=640x400&maptype=roadmap&markers=color%3Ared%7C38.7223%2C-9.1393&markers=color%3Ared%7C-23.5505%2C-46.6333&markers=color%3Ared%7C55.7558%2C37.6173&key=%3CCHAVE%3E


#CHAVE = - A chave vem do ambiente, nunca do ficheiro. O ENV é o conjunto de variáveis
#ENV["GOOGLE_MAPS_API_KEY"] - definidas fora do programa. Assim a chave nunca entra no código nem num repositório.
#def url_geocodificacao(morada,chave) - Constrói o endereço do pedido. Repare que constrói apenas: não faz chamada nenhuma.
#URI::HTTPS.build(host:, path:,query:) - Monta a URL a partir das partes, com a biblioteca. Escrever a URL à mão com interpolação é onde nascem os erros de codificação.
#URI.encode_www_form("address" =>morada, "key" => chave) - Converte os parâmetros para o formato de consulta, codificando o que for preciso: espaços em +, vírgulas em %2C
#CHAVE || "<CHAVE>" - Um marcador de posição para a demonstração.

#ENV["NOME"] - Valores definidos fora do programa, tipicamente na sessão da linha de comandos. É onde vivem chaves, palavras-passe e configurações que não podem ficar no código.
#URI::HTTPS.build(...) - Monta um endereço a partir das partes, aplicando as regras de codificação.
#URI.encode_www_form(hash) - Converte pares nome-valor para o formato de consulta, escapando os caracteres especiais.
#host: path: query: - Passam-se pelo nome e não pela posição, o que torna a chamada legível e independente da ordem.
