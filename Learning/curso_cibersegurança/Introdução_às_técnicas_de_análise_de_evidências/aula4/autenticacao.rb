cab = File.read("evidencias/evidencias/email/phishing.eml", encoding: "UTF-8").split(/\n\n/, 2).first

%w[spf dkim dmarc].each do |mec|
  m = cab.match(/#{mec}=(\w+)/)
  resultado = m ? m[1] : "ausente"
  veredito = case resultado
             when "pass" then "OK"
             when "fail", "softfail" then "FALHA"
             when "none" then "NAO CONFIGURADO"
             else "?"
             end
  puts "#{mec.upcase.ljust(6)} #{resultado.ljust(10)} #{veredito}"
end

de = cab[/^From:.*<([^>]+)>/, 1]
resp = cab[/^Reply-To:\s*(\S+)/, 1]
puts
puts "From : #{de}"
puts "Reply-To : #{resp}"

if de.split("@").last != resp.split("@").last
  puts "AVISO: o dominio de resposta difere do dominio do remetente."
end

#SPF    fail       FALHA
#DKIM   none       NAO CONFIGURADO
#DMARC  fail       FALHA

#From : no-reply@iefp.pt
#Reply-To : suporte@secure-iefp-pt.com
#AVISO: o dominio de resposta difere do dominio do remetente.

#cab =File.read(...).split(/\n\n/, - Uma mensagem de correio é o cabeçalho, uma linha em branco, e o corpo.
#Partir na primeira linha dupla e ficar com a primeira metade isola o cabeçalho.
#2).first — que é onde está a evidência.

#%w[spf dkim dmarc].each do |mec| - Percorre os três mecanismos, para não escrever três vezes o mesmo código.
#m = cab.match(/#{mec}=(\w+)/) - Repare na interpolação dentro da expressão regular: o padrão é construído a partir da variável do ciclo. Numa volta procura spf=, na seguinte dkim=.
#resultado = m ? m[1] : "ausente" - O grupo capturado, ou a palavra «ausente».
#de = cab[/^From:.*<([^>]+)>/, 1] - Extrai o endereço do remetente. A forma texto[padrao, n] devolve diretamente o grupo n, sem passar por um objeto de correspondência.
#resp = cab[/^Reply-To:\s*(\S+)/,1] - O endereço para onde a resposta iria — que o utilizador nunca vê antes de carregar em «responder».
#if de.split("@").last != resp.split("@").last - Compara os domínios dos dois endereços. Diferirem é um sinal clássico de fraude.