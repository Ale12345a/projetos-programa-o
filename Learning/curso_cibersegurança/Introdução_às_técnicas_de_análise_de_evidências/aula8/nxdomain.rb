# Proporcao de consultas de nomes que nao existem.
# Um ciclo e uma divisao. Num computador normal a esmagadora maioria das consultas
# tem resposta: as pessoas escrevem enderecos que existem.
linhas = File.readlines("evidencias/evidencias/sistema/dns_queries.log")
falhas = linhas.count { |l| l.include?("NXDOMAIN") }

puts "#{falhas} de #{linhas.size} = #{(100.0 * falhas / linhas.size).round(1)}%"

#39 de 64 = 60.9%