INGENUA = /\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}/

ESTRITA = /\b(?:(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)\.){3}(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)\b/x

casos = [
  "ligacao de 10.0.0.555 recusada",
  "endereco invalido 999.1.1.1 no log",
  "agente Mozilla/5.0 build 10.0.19045.3086",
  "origem legitima 192.168.1.10",
]

casos.each do |c|
  puts c
  puts " ingenua: #{c.scan(INGENUA).inspect}"
  puts " estrita: #{c.scan(ESTRITA).inspect}"
end

#ligacao de 10.0.0.555 recusada
# ingenua: ["10.0.0.555"]
# estrita: []
#endereco invalido 999.1.1.1 no log
# ingenua: ["999.1.1.1"]
# estrita: []
#agente Mozilla/5.0 build 10.0.19045.3086
# ingenua: []
# estrita: []
#origem legitima 192.168.1.10
# ingenua: ["192.168.1.10"]
# estrita: ["192.168.1.10"]

#INGENUA =/\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}/ - Um a três algarismos, ponto, quatro vezes. É a expressão que toda a gente escreve.
#ESTRITA = /\b(?:(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)\.){3}... - Valida cada octeto entre 0 e 255. Lê-se aos bocados: 25[0-5] apanha 250 a
# 255; 2[0-4]\d apanha 200 a 249; 1\d{2} apanha 100 a 199; [1-9]?\dapanha 0 a 99. O (?:...) agrupa sem capturar, e o {3} repete o conjunto
#«octeto e ponto» três vezes, seguido do quarto octeto sem ponto.
#\b nas duas pontas As fronteiras de palavra. São elas que impedem apanhar 10.0.0.55 de dentro de 10.0.0.555 — a peça mais importante de toda a expressão.
#O ciclo com os dois scan Aplica as duas expressões ao mesmo texto e imprime os resultados lado a lado. Comparar é a única forma de mostrar a diferença.
