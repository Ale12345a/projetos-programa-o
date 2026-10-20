# Ficheiro do passo 4 da ficha A3 — RECEBES PELO CHAT.
# As tres fontes do caso, cada linha com a hora ja convertida para UTC.
# Aqui NAO ha ordenacao nenhuma: cada fonte e impressa por sua vez, tal como
# esta no ficheiro. Junta-las e ordena-las e o passo 5, que escreves tu.
require "time"

puts "FONTE A — epoch Unix, ja em UTC por definicao"
File.foreach("evidencias/evidencias/web/fonte_a.log") do |l|
  epoch, ip, ev = l.split(" ")
  puts "#{Time.at(epoch.to_i).utc.iso8601}  #{ip} #{ev.chomp}"
end

puts
puts "FONTE B — ISO 8601 com fuso explicito: conversao segura"
File.foreach("evidencias/evidencias/web/fonte_b.log") do |l|
  ts, resto = l.split(" ", 2)
  puts "   #{Time.parse(ts).utc.iso8601}  #{resto.chomp}"
end

# Fonte C — syslog sem ano, fuso escrito por extenso no texto.
# O ano vem do contexto do caso; o fuso e declarado a mao.
FUSOS = { "EDT" => "-04:00", "WEST" => "+01:00", "UTC" => "+00:00" }
ANO_DO_CASO = 2026

puts
puts "FONTE C — syslog sem ano e com o fuso por extenso"
File.foreach("evidencias/evidencias/web/fonte_c.log") do |l|
  m = l.match(/^(\w{3}\s+\d{1,2}\s\d{2}:\d{2}:\d{2})\s+(\S+)\s+(\w+)\s+(.*)$/)
  next unless m
  t = Time.strptime("#{m[1]} #{ANO_DO_CASO} #{FUSOS.fetch(m[3])}",
                    "%b %d %H:%M:%S %Y %z").utc
  puts "   #{t.iso8601}  #{m[4].chomp}"
end

#FONTE A — epoch Unix, ja em UTC por definicao
#   2026-10-13T08:15:31Z  10.0.0.55 login_failed
#   2026-10-13T08:15:48Z  10.0.0.55 login_failed
#   2026-10-13T08:16:12Z  10.0.0.55 login_failed
#   2026-10-13T08:31:19Z  10.0.0.55 login_ok

#FONTE B — ISO 8601 com fuso explicito: conversao segura
#   2026-10-13T08:15:31Z  firewall deny 203.0.113.9 -> 10.0.0.10:22
#   2026-10-13T08:16:40Z  firewall deny 203.0.113.9 -> 10.0.0.10:22
#   2026-10-13T08:17:02Z  firewall allow 203.0.113.9 -> 10.0.0.10:22

#FONTE C — syslog sem ano e com o fuso por extenso
#   2026-10-13T08:15:31Z  alerta autenticacao 10.0.0.55
#   2026-10-13T08:31:19Z  alerta sessao 10.0.0.55