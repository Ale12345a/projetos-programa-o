# ETAPA 1 de 5 — o padrao
# Captura o endereco, a data, o metodo, a URL e o codigo.

PADRAO = /^(?<ip>\d+\.\d+\.\d+\.\d+).*?\[(?<data>[^\]]+)\]\s+"(?<metodo>\w+)\s+(?<url>[^"]+)"\s+(?<codigo>\d{3})/


# ETAPA 2 de 5 — a classe Pedido e o modulo Relatorio

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


# ETAPA 3 de 5 — ler o ficheiro e contar.
# A leitura e o processamento ficam protegidos por begin/rescue.

begin
  ficheiro = ARGV[0]

  if ficheiro.nil?
    puts "Uso: ruby analisador.rb <ficheiro.log>"
    exit 1
  end

  pedidos = []
  ignoradas = 0

  File.foreach(ficheiro) do |linha|
    next if linha.strip.empty?

    m = linha.match(PADRAO)

    if m
      pedidos << Pedido.new(m)
    else
      ignoradas += 1
    end
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


  # ETAPA 4 de 5 — o relatorio no ecra

  puts Relatorio.titulo("RELATORIO DE ANALISE DE ACESSOS")

  puts Relatorio.linha("Ficheiro", ficheiro)
  puts Relatorio.linha("Pedidos analisados", pedidos.length)
  puts Relatorio.linha("Linhas ignoradas", ignoradas)
  puts Relatorio.linha("Pedidos com erro", pedidos.count { |p| p.erro? })

  puts

  puts "PEDIDOS POR IP"

  por_ip.sort_by { |ip, n| -n }.each do |ip, n|
    puts Relatorio.linha("  #{ip}", n)
  end

  puts

  puts "PEDIDOS POR CODIGO"

  por_codigo.sort.each do |codigo, n|
    puts Relatorio.linha("  #{codigo}", n)
  end


  # ETAPA 5 de 5 — alertas, quem entrou depois das falhas
  # e gravacao do relatorio em relatorio.txt

  suspeitos = falhas.select { |ip, total| total >= 3 }

  puts
  puts Relatorio.titulo("ALERTA - 3 OU MAIS FALHAS DE AUTENTICACAO")

  if suspeitos.empty?
    puts "Nenhum IP identificado."
  else
    suspeitos.sort.each do |ip, total|
      puts Relatorio.linha(ip, "#{total} falhas")
    end
  end

  total_falhas = suspeitos.values.sum
  puts Relatorio.linha("TOTAL DE FALHAS", total_falhas)

  puts
  puts Relatorio.titulo("CRITICO - LOGIN APOS FALHAS REPETIDAS")

  if comprometidos.empty?
    puts "Nenhum login bem-sucedido apos falhas repetidas."
  else
    comprometidos.each do |ip, total|
      puts Relatorio.linha("IP que entrou: #{ip}", "LOGIN COM SUCESSO")
      puts Relatorio.linha("Falhas anteriores", total)
    end
  end

  # Preparar o mesmo relatorio para guardar no ficheiro

  linhas = []

  linhas << Relatorio.titulo("RELATORIO DE ANALISE DE ACESSOS")
  linhas << Relatorio.linha("Ficheiro", ficheiro)
  linhas << Relatorio.linha("Pedidos analisados", pedidos.length)
  linhas << Relatorio.linha("Linhas ignoradas", ignoradas)
  linhas << Relatorio.linha("Pedidos com erro", pedidos.count { |p| p.erro? })

  linhas << ""
  linhas << "PEDIDOS POR IP"

  por_ip.sort_by { |ip, n| -n }.each do |ip, n|
    linhas << Relatorio.linha("  #{ip}", n)
  end

  linhas << ""
  linhas << "PEDIDOS POR CODIGO"

  por_codigo.sort.each do |codigo, n|
    linhas << Relatorio.linha("  #{codigo}", n)
  end

  linhas << ""
  linhas << Relatorio.titulo("ALERTA - 3 OU MAIS FALHAS DE AUTENTICACAO")

  if suspeitos.empty?
    linhas << "Nenhum IP identificado."
  else
    suspeitos.sort.each do |ip, total|
      linhas << Relatorio.linha(ip, "#{total} falhas")
    end
  end

  linhas << Relatorio.linha("TOTAL DE FALHAS", total_falhas)
  linhas << ""
  linhas << Relatorio.titulo("CRITICO - LOGIN APOS FALHAS REPETIDAS")

  if comprometidos.empty?
    linhas << "Nenhum login bem-sucedido apos falhas repetidas."
  else
    comprometidos.each do |ip, total|
      linhas << Relatorio.linha(ip, "#{total} falhas antes")
    end
  end

  File.write("relatorio.txt", linhas.join("\n") + "\n")

  puts
  puts "Relatorio guardado em relatorio.txt"

rescue Errno::ENOENT
  puts "ERRO: o ficheiro '#{ficheiro}' nao existe."
  puts "Verifique o nome e a pasta onde esta a correr o script."

rescue Errno::EACCES
  puts "ERRO: sem permissao para ler o ficheiro ou gravar o relatorio."

ensure
  puts "Analise terminada."
end

#====================================================
#          RELATORIO DE ANALISE DE ACESSOS
#====================================================
#Ficheiro                          acesso_alargado.log
#Pedidos analisados                                13
#Linhas ignoradas                                   0
#Pedidos com erro                                   9

#PEDIDOS POR IP
#  203.0.113.9                                      5
#  10.0.0.55                                        4
#  192.168.1.10                                     3
#  192.168.1.22                                     1

#PEDIDOS POR CODIGO
#  200                                              4
#  401                                              6
#  403                                              1
#  404                                              2

#====================================================
#     ALERTA - 3 OU MAIS FALHAS DE AUTENTICACAO
#====================================================
#10.0.0.55                                   3 falhas
#203.0.113.9                                 3 falhas
#TOTAL DE FALHAS                                    6

#====================================================
#       CRITICO - LOGIN APOS FALHAS REPETIDAS
#====================================================
#IP que entrou: 10.0.0.55           LOGIN COM SUCESSO
#Falhas anteriores                                  3

#Relatorio guardado em relatorio.txt
#Analise terminada.