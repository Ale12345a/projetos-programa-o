# 25 de outubro de 2026, fim do horario de verao
antes = Time.at(1792888200).getlocal("+01:00")
depois = Time.at(1792891800).getlocal("+00:00")

puts antes # 01:30
puts depois # 01:30 outra vez

#2026-10-25 01:30:00 +0100
#2026-10-25 01:30:00 +0000

# 25 de outubro de 2026, fim do horario de verao - a madrugada em que portugal, o relógio volta das 02:00 para a 01:00
#antes = Time.at(1792888200).getlocal("+01:00") - um instante (00:30 UTC) escrito com o fuso de verão, +01:00: no relógio, 01:30
#depois = Time.at(1792891800).getlocal("+00:00") - Uma hora depois (01:30 UTC), escrito com o fuso  de inverno, +00:00: no relógio, 01:30 outra vez.
#puts antes # 01: - Escreve 2026-10-25 01:30:00 +0100
#puts depois # 01:30 outra vez - #2026-10-25 01:30:00 +0000. Sem o fuso ao lado, as duas linhas seriam iguais

#.getlocal("+01:00") - Mostra o mesmo instante na hora de um fuso escolhido à mão. Não muda o instante:
#muda só a forma de o escrever, que é o que distingue as duas 01:30 da madrugada em que os relógios atrasam.