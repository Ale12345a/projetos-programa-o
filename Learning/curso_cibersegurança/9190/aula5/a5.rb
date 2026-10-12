class Pedido
  attr_reader :ip, :metodo, :url, :codigo

  def initialize(ip, metodo, url, codigo)
    @ip = ip
    @metodo = metodo
    @url = url
    @codigo = codigo.to_i
  end

  def erro?
    @codigo >= 400
  end

  def login_com_sucesso?
    @url.start_with?("/login") && @codigo == 200
  end
end

pedido1 = Pedido.new("192.168.1.10", "GET", "/index.html", 200)
pedido2 = Pedido.new("10.0.0.55", "POST", "/login", 401)
pedido3 = Pedido.new("10.0.0.55", "POST", "/login", 401)
pedido4 = Pedido.new("192.168.1.10", "GET", "/produtos", 200)
pedido5 = Pedido.new("10.0.0.55", "POST", "/login", 401)
pedido6 = Pedido.new("10.0.0.55", "POST", "/login", 200)

# Guardar os seis objetos num array
pedidos = [pedido1, pedido2, pedido3, pedido4, pedido5, pedido6]

# Imprimir os campos e testar os métodos
pedidos.each_with_index do |pedido, indice|
  puts "Pedido #{indice + 1}"
  puts "IP: #{pedido.ip}"
  puts "Método: #{pedido.metodo}"
  puts "URL: #{pedido.url}"
  puts "Código HTTP: #{pedido.codigo}"

  puts "Erro? #{pedido.erro?}"
  puts "Login com sucesso? #{pedido.login_com_sucesso?}"

  puts "-------------------------------"
end

