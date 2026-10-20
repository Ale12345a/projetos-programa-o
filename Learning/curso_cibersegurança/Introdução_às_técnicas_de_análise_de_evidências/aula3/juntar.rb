# Ficheiro do passo 4 da ficha A3 — RECEBES PELO CHAT.
# As tres fontes do caso, cada linha com a hora ja convertida para UTC.
# Aqui NAO ha ordenacao nenhuma: cada fonte e impressa por sua vez, tal como
# esta no ficheiro. Junta-las e ordena-las e o passo 5, que escreves tu.
require "time"

eventos = []

File.foreach("evidencias/evidencias/web/fonte_a.log") do |l|
  epoch, ip, ev = l.split(" ")
  eventos << "#{Time.at(epoch.to_i).utc.iso8601} fonte_a #{ip} #{ev.chomp}"
end

File.foreach("evidencias/evidencias/web/fonte_b.log") do |l|
  ts, resto = l.split(" ", 2)
  eventos << "#{Time.parse(ts).utc.iso8601} fonte_b #{resto.chomp}"
end

# Fonte C — syslog sem ano, fuso escrito por extenso no texto.
# O ano vem do contexto do caso; o fuso e declarado a mao.
FUSOS = { "EDT" => "-04:00", "WEST" => "+01:00", "UTC" => "+00:00" }
ANO_DO_CASO = 2026

File.foreach("evidencias/evidencias/web/fonte_c.log") do |l|
  m = l.match(/^(\w{3}\s+\d{1,2}\s\d{2}:\d{2}:\d{2})\s+(\S+)\s+(\w+)\s+(.*)$/)
  next unless m

  t = Time.strptime("#{m[1]} #{ANO_DO_CASO} #{FUSOS.fetch(m[3])}",
                    "%b %d %H:%M:%S %Y %z").utc
  eventos << "#{t.iso8601} fonte_c #{m[4].chomp}"
end

eventos.sort.each { |e| puts e }

#2026-10-13T08:15:31Z fonte_a 10.0.0.55 login_failed
#2026-10-13T08:15:31Z fonte_b firewall deny 203.0.113.9 -> 10.0.0.10:22
#2026-10-13T08:15:31Z fonte_c alerta autenticacao 10.0.0.55
#2026-10-13T08:15:48Z fonte_a 10.0.0.55 login_failed
#2026-10-13T08:16:12Z fonte_a 10.0.0.55 login_failed
#2026-10-13T08:16:40Z fonte_b firewall deny 203.0.113.9 -> 10.0.0.10:22
#2026-10-13T08:17:02Z fonte_b firewall allow 203.0.113.9 -> 10.0.0.10:22
#2026-10-13T08:31:19Z fonte_a 10.0.0.55 login_ok
#2026-10-13T08:31:19Z fonte_c alerta sessao 10.0.0.55

#epoch, ip, ev = l.split(" ") - epoch → "1791879331" ip → "10.0.0.55" ev → "login_failed"
#epoch.to_i - Converte epoch de texto para um número inteiro (Integer), porque o valor representa um timestamp Unix.
#Time.at(epoch.to_i) - Converte o timestamp Unix numa data e hora. Um timestamp Unix representa segundos decorridos desde 1 de janeiro de 1970, em UTC.
#.utc - Converte a data e hora para o fuso horário UTC.
#.iso8601 - Formata a data num formato padronizado, como 2026-10-12T09:00:00Z.
#eventos << ... - O operador << acrescenta o evento ao final do array eventos.
#ev.chomp - remove a quebra de linha que normalmente existe no final da linha lida.

#ts, resto = l.split(" ", 2) - ts recebe o primeiro elemento, que contém a data e hora. resto recebe tudo o que vem depois do primeiro espaço, incluindo os restantes espaços.
#Time.parse(ts).utc.iso8601 - Time.parse(ts) interpreta a data e hora que estão escritas como texto.
#timestamp e todo o resto, mesmo que tenha espaços. Sem esse limite, a
#descrição vinha partida.

#FUSOS = { "EDT" => "-04:00", ...} - A tabela que traduz o nome do fuso para o deslocamento. É um pressuposto declarado, não um dado observado.

#unless m significa “se m não existir ou for falso”. next termina a iteração atual e avança para a seguinte.
#FUSOS.fetch(m[3]) - Procura no hash FUSOS o desfasamento correspondente. Por exemplo, se m[3] for "WEST", devolve "+01:00".
