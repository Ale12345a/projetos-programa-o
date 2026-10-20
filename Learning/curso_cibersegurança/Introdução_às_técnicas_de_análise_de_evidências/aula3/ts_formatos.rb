require "time"

exemplos = {
  "ISO 8601 com fuso" => "2026-10-13T09:15:31+01:00",
  "ISO 8601 em UTC"   => "2026-10-13T08:15:31Z",
  "epoch Unix"        => "1791879331",
  "Apache CLF"        => "13/Oct/2026:09:15:31 +0100",
  "syslog RFC 3164"   => "Oct 13 08:15:31",
}

exemplos.each do |nome, texto|
  t = case texto
      when /\A\d+\z/                    then Time.at(texto.to_i).utc
      when %r{\A\d{2}/\w{3}/\d{4}:}     then Time.strptime(texto, "%d/%b/%Y:%H:%M:%S %z").utc
      when /\A\w{3}\s+\d{1,2}\s\d{2}:/ then nil
      else Time.parse(texto).utc
      end

  puts "#{nome.ljust(20)} #{texto.ljust(28)} -> " \
       "#{t ? t.iso8601 : "INDETERMINADO"}"
end

#ISO 8601 com fuso    2026-10-13T09:15:31+01:00    -> 2026-10-13T08:15:31Z
#ISO 8601 em UTC      2026-10-13T08:15:31Z         -> 2026-10-13T08:15:31Z
#epoch Unix     1791879331                   -> 2026-10-13T08:15:31Z
#Apache CLF           13/Oct/2026:09:15:31 +0100   -> 2026-10-13T08:15:31Z
#sslog RFC 3164      Oct 13 08:15:31              -> INDETERMINADO