# analisador.rb - projeto final da UFCD 9190 (linha v2)
# Uso: ruby analisador.rb acesso_alargado.log
#
# Duas diferencas em relacao ao script auditado na sessao 5:
# 1. a URL e capturada ate as aspas de fecho, com [^"]+, e nao com \S+.
# Uma URL com espaco deixa de fazer a linha desaparecer em silencio.
# 2. o relatorio nao para em "alguem tentou": verifica se alguem ENTROU
# depois de tres falhas repetidas, que e a diferenca entre um alerta
# que se arquiva e um incidente que se declara.

PADRAO =
/^(?<ip>\d+\.\d+\.\d+\.\d+).*\[(?<data>[^\]]+)\]\s+"(?<metodo>\w+)\s+(?<url>[^"]+)"\s+(?<codigo>\d{3})/

class Pedido
  attr_reader :ip, :data, :metodo, :url, :codigo


  def initialize(dados)
    @ip = dados[:ip]
    @data = dados[:data]
    @metodo = dados[:metodo]
    @url = dados[:url]
    @codigo = dados[:codigo].to_i
  end

  def erro?
    @codigo >= 400
  end

  def falha_login?
    @url.start_with?("/login") && @codigo == 401
  end

  def login_com_sucesso?
    @url.start_with?("/login") && @codigo == 200
  end
end

module Relatorio
  def self.titulo(texto)
    "=" * 52 + "\n" + texto.center(52) + "\n" + "=" * 52
  end

  def self.linha(rotulo, valor)
    rotulo.to_s.ljust(34) + valor.to_s.rjust(18)
  end
end
ficheiro = ARGV[0]

if ficheiro.nil?
  puts "Uso: ruby analisador.rb <ficheiro.log>"
  exit 1
end


pedidos = []
ignoradas = 0

begin
  File.foreach(ficheiro) do |linha|
    m = linha.match(PADRAO)
    if m
      pedidos << Pedido.new(m)
    else
      ignoradas += 1
    end
  end
rescue Errno::ENOENT
  puts "ERRO: o ficheiro '#{ficheiro}' nao existe."
  exit 1
end


por_ip = Hash.new(0)
por_codigo = Hash.new(0)
falhas = Hash.new(0)
comprometidos = []

pedidos.each do |p|
  por_ip[p.ip] += 1
  por_codigo[p.codigo] += 1
 
  if p.falha_login?
    falhas[p.ip] += 1
  elsif p.login_com_sucesso? && falhas[p.ip] >= 3
    comprometidos << [p.ip, falhas[p.ip]]
  end
end

puts Relatorio.titulo("RELATORIO DE ANALISE DE ACESSOS")
puts Relatorio.linha("Ficheiro", ficheiro)
puts Relatorio.linha("Pedidos analisados", pedidos.length)
puts Relatorio.linha("Linhas ignoradas", ignoradas)
puts Relatorio.linha("Pedidos com erro", pedidos.count { |p| p.erro? })
puts

puts "PEDIDOS POR IP"
por_ip.sort_by { |ip, n| -n }.each do |ip, n|
  puts Relatorio.linha(" #{ip}", n)
end
puts

puts "PEDIDOS POR CODIGO"
por_codigo.sort.each do |codigo, n|
  puts Relatorio.linha(" #{codigo}", n)
end
puts

suspeitos = falhas.select { |ip, n| n >= 3 }

if suspeitos.empty?
  puts "Sem IPs suspeitos."
else
  puts "ALERTA - IPs COM 3 OU MAIS FALHAS DE AUTENTICACAO"
  suspeitos.each do |ip, n|
    puts Relatorio.linha(" #{ip}", "#{n} falhas")
  end
end
puts

if comprometidos.empty?
  puts "Nenhuma entrada com sucesso depois de falhas repetidas."
else
  puts "CRITICO - ENTRARAM DEPOIS DE FALHAS REPETIDAS"
  comprometidos.each do |ip, n|
    puts Relatorio.linha(" #{ip}", "#{n} falhas antes")
  end
end

File.open("relatorio.txt", "w") do |f|
  f.puts Relatorio.titulo("RELATORIO DE ANALISE DE ACESSOS")
  f.puts Relatorio.linha("Pedidos analisados", pedidos.length)
  suspeitos.each { |ip, n| f.puts Relatorio.linha(" SUSPEITO #{ip}", "#{n} falhas") }
  comprometidos.each { |ip, n| f.puts Relatorio.linha(" CRITICO #{ip}", "entrou apos #{n}
falhas") }
end

puts
puts "Relatorio gravado em relatorio.txt"

#====================================================
#RELATORIO DE ANALISE DE ACESSOS
#====================================================
#Ficheiro acesso_alargado.log
#Pedidos analisados 13
#Linhas ignoradas 0
#Pedidos com erro 9
#PEDIDOS POR IP
#203.0.113.9 5
#10.0.0.55 4
#192.168.1.10 3
#192.168.1.22 1
#PEDIDOS POR CODIGO
#200 4
#401 6
#403 1
#404 2
#ALERTA - IPs COM 3 OU MAIS FALHAS DE AUTENTICACAO
#10.0.0.55 3 falhas
#203.0.113.9 3 falhas
#CRITICO - ENTRARAM DEPOIS DE FALHAS REPETIDAS
#10.0.0.55 3 falhas antes
#Relatorio gravado em relatorio.txt