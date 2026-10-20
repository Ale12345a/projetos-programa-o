VOGAIS = "aeiou"

def entropia(s)
  return 0.0 if s.empty?
  contagem = s.each_char.each_with_object(Hash.new(0)) { |c, h| h[c] += 1 }
  contagem.values.reduce(0.0) do |soma, n|
    p = n.to_f / s.length
    soma - p * Math.log2(p)
  end
end

def racio_vogais(s)
  letras = s.gsub(/[^a-z]/, "")
  return 0.0 if letras.empty?
  letras.count(VOGAIS).to_f / letras.length
end

# ATENCAO: retira-se o www. ANTES de tirar o primeiro rotulo,
# senao analisa-se "www" em vez do nome do dominio.
def nucleo(dominio)
  dominio.downcase.sub(/\Awww\./, "").split(".").first
end

consultas = []
File.foreach("evidencias/evidencias/sistema/dns_queries.log") do |l|
  _ts, origem, dominio, resposta = l.chomp.split(" ")
  consultas << { origem: origem, dominio: dominio, resposta: resposta }
end

total = consultas.size
nxdomain = consultas.count { |c| c[:resposta] == "NXDOMAIN" }
puts "Consultas: #{total} NXDOMAIN: #{nxdomain} " \
  "(#{(100.0 * nxdomain / total).round(1)}%)"
puts "Numa rede saudavel a taxa de NXDOMAIN anda abaixo dos 5%."
puts

puts "DOMINIO".ljust(24) + "ENTROPIA VOGAIS RESPOSTA VEREDITO"
puts "-" * 70

consultas.map { |c| c[:dominio] }.uniq.each do |d|
  n = nucleo(d)
  e = entropia(n)
  v = racio_vogais(n)
  resposta = consultas.find { |c| c[:dominio] == d }[:resposta]

  suspeito = e > 2.7 && v < 0.32 && n.length >= 8
  puts d.ljust(24) + e.round(2).to_s.ljust(10) + v.round(2).to_s.ljust(8) +
    resposta.ljust(11) + (suspeito ? "SUSPEITO (DGA)" : "normal")
end

#Consultas: 64 NXDOMAIN: 39 (60.9%)
#Numa rede saudavel a taxa de NXDOMAIN anda abaixo dos 5%.

#DOMINIO                 ENTROPIA VOGAIS RESPOSTA VEREDITO
#----------------------------------------------------------------------
#lxgtmfyy.info           2.75      0.0     NXDOMAIN   SUSPEITO (DGA)
#qacyjogeojxdd.info      3.24      0.31    NXDOMAIN   SUSPEITO (DGA)
#qbaqwmhxkjd.info        3.28      0.09    NXDOMAIN   SUSPEITO (DGA)
#hxxavedbdbtet.info      2.93      0.23    NXDOMAIN   SUSPEITO (DGA)
#www.google.com          1.92      0.5     NOERROR    normal
#zfjrctssvxd.info        3.28      0.0     NXDOMAIN   SUSPEITO (DGA)
#teams.microsoft.com     2.32      0.4     NOERROR    normal
#ljjmdoouvpdq.info       3.08      0.25    NXDOMAIN   SUSPEITO (DGA)
#iwlpxnshdmm.info        3.28      0.09    NXDOMAIN   SUSPEITO (DGA)
#gvgvmhrkpfeujj.info     3.38      0.14    NXDOMAIN   SUSPEITO (DGA)
#outlook.office365.com   2.13      0.57    NOERROR    normal
#wcmgickourgk.info       3.08      0.25    NXDOMAIN   SUSPEITO (DGA)
#cdn.jsdelivr.net        1.58      0.0     NOERROR    normal
#prwmjiwpfjxve.info      3.24      0.15    NXDOMAIN   SUSPEITO (DGA)
#gybvvlytxxz.info        2.91      0.0     NXDOMAIN   SUSPEITO (DGA)
#jgplsqfdnavxb.info      3.7       0.08    NXDOMAIN   SUSPEITO (DGA)
#onjkacqxgsjj.info       3.19      0.17    NXDOMAIN   SUSPEITO (DGA)
#boqqzrobvts.info        2.91      0.18    NXDOMAIN   SUSPEITO (DGA)
#kuixbezmtwkhr.info      3.55      0.23    NXDOMAIN   SUSPEITO (DGA)
#hmqamjoycq.info         2.92      0.2     NXDOMAIN   SUSPEITO (DGA)
#qjlkaieflroku.info      3.39      0.38    NXDOMAIN   normal
#psqopntjnydh.info       3.25      0.08    NOERROR    SUSPEITO (DGA)
#ablegromlm.info         2.92      0.3     NXDOMAIN   SUSPEITO (DGA)
#albiubpwy.info          2.95      0.33    NXDOMAIN   normal
#memxulrsyv.info         3.12      0.2     NXDOMAIN   SUSPEITO (DGA)
#www.publico.pt          2.81      0.43    NOERROR    normal
#wsfhxffgq.info          2.64      0.0     NXDOMAIN   normal
#eogncmwyxqofij.info     3.66      0.29    NXDOMAIN   SUSPEITO (DGA)
#gcfwrojdpuadvg.info     3.52      0.21    NXDOMAIN   SUSPEITO (DGA)
#ubvclple.info           2.75      0.25    NXDOMAIN   SUSPEITO (DGA)
#lhvhvthwnsdrb.info      3.18      0.0     NXDOMAIN   SUSPEITO (DGA)
#pksrhainymrc.info       3.42      0.17    NXDOMAIN   SUSPEITO (DGA)
#arlbffwqjtls.info       3.25      0.08    NXDOMAIN   SUSPEITO (DGA)
#piuzqkancw.info         3.32      0.3     NXDOMAIN   SUSPEITO (DGA)
#vpungjjje.info          2.64      0.22    NXDOMAIN   normal
#cewfpdfswooe.info       2.92      0.33    NXDOMAIN   normal
#www.iefp.pt             2.0       0.5     NOERROR    normal
#mfzoxfylvc.info         3.12      0.1     NXDOMAIN   SUSPEITO (DGA)
#tcskedeih.info          2.95      0.33    NXDOMAIN   normal
#hykieaurxu.info         3.12      0.5     NXDOMAIN   normal
#rqkwsacu.info           3.0       0.25    NXDOMAIN   SUSPEITO (DGA)
#kaacdxwjd.info          2.73      0.22    NXDOMAIN   SUSPEITO (DGA)
#nywxtlylvwechj.info     3.38      0.07    NXDOMAIN   SUSPEITO (DGA)
#igrqhwyveo.info         3.32      0.3     NXDOMAIN   SUSPEITO (DGA)
#xomeqiva.info           3.0       0.5     NXDOMAIN   normal
#fmbvzurzwvg.info        3.1       0.09    NXDOMAIN   SUSPEITO (DGA)


#nxdomain = consultas.count { |c| c[:resposta] == "NXDOMAIN" } - A taxa de domínios inexistentes. É o primeiro sinal e o mais barato: 60,9% contra os menos de 5% de uma rede saudável.
#suspeito = e > 2.7 && v < 0.32 && n.length >= 8 - Três condições ligadas por E. Entropia alta, poucas vogais e nome comprido. Nenhuma delas isolada serve.
#Math.log2(x) - Logaritmo de base 2. Na entropia de Shannon, dá o resultado em bits.
#.reduce(0.0) {|soma, n| ... } - Percorre acumulando um valor único. O 0.0 inicial força aritmética decimal. É o irmão do each_with_object para quando o acumulador é um número.
#String#count(conjunto) - Conta quantos caracteres pertencem ao conjunto indicado. Cuidado: tem o mesmo nome do count das listas e comporta-se de forma diferente.