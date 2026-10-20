require "ipaddr"

# Versao pedida: "marca como interno o que for da rede da empresa"
def interno_texto?(ip)
  ip.start_with?("192.168.1") || ip.start_with?("10.")
end

# Versao correta: compara redes, nao texto
REDE_EMPRESA = [IPAddr.new("192.168.1.0/24"), IPAddr.new("10.0.0.0/8")]

def interno_rede?(ip)
  alvo = IPAddr.new(ip)
  REDE_EMPRESA.any? { |r| r.include?(alvo) }
end

["192.168.1.10", "192.168.11.5", "192.168.100.7",
  "10.0.0.55", "100.64.3.9"].each do |ip|
  t = interno_texto?(ip) ? "INTERNO" : "externo"
  r = interno_rede?(ip) ? "INTERNO" : "externo"
  marca = t == r ? "" : " <<< DIVERGENCIA"
  puts "#{ip.ljust(15)} por texto: #{t.ljust(8)} por rede: #{r.ljust(8)}#{marca}"
end

#192.168.1.10    por texto: INTERNO  por rede: INTERNO
#192.168.11.5    por texto: INTERNO  por rede: externo  <<< DIVERGENCIA
#192.168.100.7   por texto: INTERNO  por rede: externo  <<< DIVERGENCIA
#10.0.0.55       por texto: INTERNO  por rede: INTERNO
#100.64.3.9      por texto: externo  por rede: externo

#def interno_rede?(ip) com any? {|r| r.include?(alvo) } - Pertença calculada sobre os bits do endereço. 192.168.11.5 não está em 192.168.1.0/24 e a resposta é não.
#marca = t == r ? "" : " <<< DIVERGENCIA" - Assinala os casos em que os dois métodos discordam. Correr as duas versões lado a lado é a técnica: é assim que se demonstra um defeito, em vez de o argumentar.
#.start_with?(prefixo) - Método de texto Responde se o texto começa por aquele prefixo. Correto para texto; enganador para endereços, porque um endereço não é uma sequência de caracteres — é um número.
