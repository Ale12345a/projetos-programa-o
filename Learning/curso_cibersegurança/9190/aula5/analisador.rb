# ETAPA 1 de 5 — o padrao. Cola no TOPO de um ficheiro novo, analisador.rb.
# Captura o endereco, a data, o metodo, a URL (ate as aspas, com [^"]+) e o codigo.
PADRAO = /^(?<ip>\d+\.\d+\.\d+\.\d+).*\[(?<data>[^\]]+)\]\s+"(?<metodo>\w+)\s+(?<url>[^"]+)"\s+(?<codigo>\d{3})/

# ETAPA 2 de 5 — a classe Pedido e o modulo Relatorio. Cola a seguir a etapa 1.
class Pedido
  attr_reader :ip, :data, :metodo, :url, :codigo
 
  def initialize(dados)
    @ip     = dados[:ip]
    @data   = dados[:data]
    @metodo = dados[:metodo]
    @url    = dados[:url]
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

# ETAPA 3 de 5 — ler o ficheiro e contar. Cola a seguir a etapa 2.
# A leitura vai SEM protecao: o begin/rescue a volta dela escreves tu
# (ficha A5, passo 4).

ficheiro = ARGV[0]

if ficheiro.nil?
  puts "Uso: ruby analisador.rb <ficheiro.log>"
  exit 1
end

pedidos   = []
ignoradas = 0

# colocar o tratamento de exceções - Begin, rescue

begin

  File.foreach(ficheiro) do |linha|
    next if linha.strip.empty?
    m = linha.match(PADRAO)

    if m
      pedidos << Pedido.new(m)
    else
      ignoradas += 1
    end
  end

  por_ip        = Hash.new(0)
  por_codigo    = Hash.new(0)
  falhas        = Hash.new(0)
  comprometidos = []

  pedidos.each do |p|
    por_ip[p.ip]         += 1
    por_codigo[p.codigo] += 1

    if p.falha_login?
      falhas[p.ip] += 1
    elsif p.login_com_sucesso? && falhas[p.ip] >= 3
      comprometidos << [p.ip, falhas[p.ip]]
    end
  end

  # ETAPA 4 de 5 — o relatorio no ecra. Cola a seguir a etapa 3.
  # A etapa 5 escreves tu: os IPs com 3 ou mais falhas, quem entrou depois
  # delas, e a gravacao do relatorio em relatorio.txt.

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

#ETAPA 5:
#endereços IP com 3 ou mais falhas de autenticacao, ordenado por número de falhas

# Passo 1: Filtrar os IPs suspeitos com 3 ou mais falhas

  suspeitos = falhas.select do |ip, total| 
    total >= 3 
  end 
  
  suspeitos = suspeitos.sort_by do |ip, total| 
    -total 
  end 
  
# Passo 2: Mostrar os alertas no ecra

  puts Relatorio.titulo("ALERTA - IPs COM 3 OU MAIS FALHAS DE AUTENTICACAO")
  
  if suspeitos.empty? 
    puts "Nenhum IP suspeito identificado."
  else 
    suspeitos.each do |ip, total| 
      puts Relatorio.linha("SUSPEITO #{ip}", "#{total} falhas")
    end 
  end 

  #puts Relatorio.linha("TOTAL DE FALHAS", total_falhas) 
  #texto += Relatorio.linha("TOTAL DE FALHAS", total_falhas) + "\n" 
  
  #puts 
  #texto += "\n" 

  # Passo 3: Gravar o relatorio completo em relatorio.txt

  File.open("relatorio.txt", "w") do |ficheiro_saida| 
    ficheiro_saida.puts Relatorio.titulo("RELATORIO DE ANALISE DE ACESSOS") 
    ficheiro_saida.puts Relatorio.linha("Ficheiro", ficheiro) 
    ficheiro_saida.puts Relatorio.linha("Pedidos analisados", pedidos.length) 
    ficheiro_saida.puts Relatorio.linha("Linhas ignoradas", ignoradas) 
    ficheiro_saida.puts Relatorio.linha("Pedidos com erro", pedidos.count { |p| p.erro? }) 

    ficheiro_saida.puts 
    ficheiro_saida.puts "PEDIDOS POR IP" 
    
    por_ip.sort_by { |ip, n| -n }.each do |ip, n| 
      ficheiro_saida.puts Relatorio.linha(" #{ip}", n) 
    end 
    
    ficheiro_saida.puts 
    ficheiro_saida.puts "PEDIDOS POR CODIGO" 
    
    por_codigo.sort.each do |codigo, n| 
      ficheiro_saida.puts Relatorio.linha(" #{codigo}", n) 
    end 
    
    ficheiro_saida.puts 
    ficheiro_saida.puts Relatorio.titulo( "ALERTA - IPs COM 3 OU MAIS FALHAS DE AUTENTICACAO" ) 
    
    if suspeitos.empty? 
      ficheiro_saida.puts "Nenhum IP suspeito identificado." 
    else 
      suspeitos.each do |ip, total| 
        ficheiro_saida.puts Relatorio.linha( "SUSPEITO #{ip}", "#{total} falhas" ) 
      end 
    end 
    
    total_falhas = suspeitos.sum { |ip, total| total } 
    ficheiro_saida.puts Relatorio.linha("TOTAL DE FALHAS", total_falhas) 
    
    ficheiro_saida.puts 
    ficheiro_saida.puts Relatorio.titulo( "CRITICO - LOGIN APOS FALHAS REPETIDAS" ) 
    
    if comprometidos.empty? 
      ficheiro_saida.puts "Nenhum login bem-sucedido apos falhas repetidas." 
    else 
      comprometidos.each do |ip, total| 
        ficheiro_saida.puts Relatorio.linha("IP que entrou: #{ip}", "LOGIN COM SUCESSO") 
        ficheiro_saida.puts Relatorio.linha("Codigo HTTP", 200) 
        ficheiro_saida.puts Relatorio.linha("Falhas anteriores", total) 
      end 
    end 
  end 
  
  puts "\nRelatorio guardado em relatorio.txt"

rescue Errno::ENOENT
  puts "ERRO: O ficheiro '#{ficheiro}' nao existe."
  puts "Verifica o nome e a pasta onde estas a executar o script."
rescue Errno::EACCES
  puts "ERRO: Sem permissao para ler o ficheiro ou gravar o relatorio."
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
# 203.0.113.9                                       5
# 10.0.0.55                                         4
# 192.168.1.10                                      3
# 192.168.1.22                                      1

#PEDIDOS POR CODIGO
# 200                                               4
# 401                                               6
# 403                                               1
# 404                                               2

#====================================================
# ALERTA - IPs COM 3 OU MAIS FALHAS DE AUTENTICACAO
#====================================================
#SUSPEITO 10.0.0.55                          3 falhas
#SUSPEITO 203.0.113.9                        3 falhas

#Relatorio guardado em relatorio.txt
#Analise terminada.