require "time"

eventos = []

# Fonte A — epoch Unix, ja em UTC por definicao
File.foreach("evidencias/evidencias/web/fonte_a.log") do |l|
  epoch, ip, ev = l.split(" ")
  eventos << [Time.at(epoch.to_i).utc, "fonte_a", "#{ip} #{ev}"]
end
# Fonte B — ISO 8601 com fuso explicito: conversao segura
File.foreach("evidencias/evidencias/web/fonte_b.log") do |l|
ts, resto = l.split(" ", 2)
eventos << [Time.parse(ts).utc, "fonte_b", resto.chomp]
end
# Fonte C — syslog sem ano, fuso escrito por extenso no texto.
# O ano vem do contexto do caso; o fuso e declarado a mao.
FUSOS = { "EDT" => "-04:00", "WEST" => "+01:00", "UTC" => "+00:00" }
ANO_DO_CASO = 2026
File.foreach("evidencias/evidencias/web/fonte_c.log") do |l|
m = l.match(/^(\w{3}\s+\d{1,2}\s\d{2}:\d{2}:\d{2})\s+(\S+)\s+(\w+)\s+(.*)$/)
next unless m
offset = FUSOS.fetch(m[3])
t = Time.strptime("#{m[1]} #{ANO_DO_CASO} #{offset}",
"%b %d %H:%M:%S %Y %z").utc
eventos << [t, "fonte_c", m[4].chomp]
end
puts "TIMELINE UNIFICADA (UTC)"
puts "-" * 72
eventos.sort_by { |t, _, _| t }.each do |t, fonte, desc|
puts "#{t.iso8601} #{fonte.ljust(8)} #{desc}"
end

#TIMELINE UNIFICADA (UTC)
#-----------------------------------------------------------------------
#2026-10-13T08:15:31Z fonte_a  10.0.0.55 login_failed
#2026-10-13T08:15:31Z fonte_b  firewall deny 203.0.113.9 -> 10.0.0.10:22
#2026-10-13T08:15:31Z fonte_c  alerta autenticacao 10.0.0.55
#2026-10-13T08:15:48Z fonte_a  10.0.0.55 login_failed
#2026-10-13T08:16:12Z fonte_a  10.0.0.55 login_failed
#2026-10-13T08:16:40Z fonte_b  firewall deny 203.0.113.9 -> 10.0.0.10:22
#2026-10-13T08:17:02Z fonte_b  firewall allow 203.0.113.9 -> 10.0.0.10:22
#2026-10-13T08:31:19Z fonte_a  10.0.0.55 login_ok
#2026-10-13T08:31:19Z fonte_c  alerta sessao 10.0.0.55

#eventos = [] - Uma lista única onde vão cair os eventos das três fontes. É a estrutura central:
#um formato comum para dados de origens diferentes.

#epoch, ip, ev = l.split(" ") - Atribuição múltipla: os três campos da linha são distribuídos por trêsvariáveis de uma só vez.

#eventos << [Time.at(epoch.to_i).utc, "fonte_a", "#{ip} #{ev}"] - Cada evento é uma lista de três elementos: instante, fonte e descrição.
#Guardar a fonte é obrigatório — sem ela, não se consegue voltar ao ficheirooriginal para verificar.

#ts, resto = l.split(" ", 2) O segundo argumento do split limita o número de pedaços a dois: o
#timestamp e todo o resto, mesmo que tenha espaços. Sem esse limite, a
#descrição vinha partida.

#FUSOS = { "EDT" => "-04:00", ...} - A tabela que traduz o nome do fuso para o deslocamento. É um pressuposto declarado, não um dado observado.

#unless m significa “se m não existir ou for falso”. next termina a iteração atual e avança para a seguinte.
#FUSOS.fetch(m[3]) - Procura no hash FUSOS o desfasamento correspondente. Por exemplo, se m[3] for "WEST", devolve "+01:00".
