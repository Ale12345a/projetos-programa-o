# Um metodo e um programa com nome. Escreve-se uma vez, chama-se as vezes que forem
# precisas. E a peca que falta antes das classes.


# Metodo que classifica um codigo HTTP
def classificar(codigo)
  if codigo >= 400
    "erro"
  else
    "sucesso"
  end
end

puts classificar(401)
puts classificar(200)

# Classe Pedido
class Pedido
  attr_reader :ip, :metodo, :url, :codigo

  def initialize(ip, metodo, url, codigo)
    @ip = ip
    @metodo = metodo
    @url = url
    @codigo = codigo.to_i
  end

  # Indica se o pedido teve um erro
  def erro?
    @codigo >= 400
  end

  # Indica se o login foi bem-sucedido
  def login_com_sucesso?
    @url.start_with?("/login") && @codigo == 200
  end
end

# Criar dois objetos com dados diferentes
pedido1 = Pedido.new("10.0.0.55", "POST", "/login", 401)
pedido2 = Pedido.new("192.168.1.10", "GET", "/index.html", 200)

# Imprimir os campos do primeiro pedido
puts "\n--- Pedido 1 ---"
puts "IP: #{pedido1.ip}"
puts "Metodo: #{pedido1.metodo}"
puts "URL: #{pedido1.url}"
puts "Codigo: #{pedido1.codigo}"

# Imprimir os campos do segundo pedido
puts "\n--- Pedido 2 ---"
puts "IP: #{pedido2.ip}"
puts "Metodo: #{pedido2.metodo}"
puts "URL: #{pedido2.url}"
puts "Codigo: #{pedido2.codigo}"

# Testar os metodos
puts "\n--- Testes ---"
puts "Pedido 1 - erro?: #{pedido1.erro?}"
puts "Pedido 1 - login_com_sucesso?: #{pedido1.login_com_sucesso?}"

puts "Pedido 2 - erro?: #{pedido2.erro?}"
puts "Pedido 2 - login_com_sucesso?: #{pedido2.login_com_sucesso?}"

#$ ruby metodo.rb
#erro
#sucesso

#--- Pedido 1 ---
#IP: 10.0.0.55
#Metodo: POST
#URL: /login
#Codigo: 401

#--- Pedido 2 ---
#IP: 192.168.1.10
#Metodo: GET
#URL: /index.html
#Codigo: 200

#--- Testes ---
#Pedido 1 - erro?: true
#Pedido 1 - login_com_sucesso?: false
#Pedido 2 - erro?: false
#Pedido 2 - login_com_sucesso?: false