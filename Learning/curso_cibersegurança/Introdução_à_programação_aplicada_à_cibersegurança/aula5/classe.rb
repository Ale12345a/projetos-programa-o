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
  
  def falha_login?
    @url.start_with?("/login") && @codigo == 401
  end

  def to_s
    "#{@ip} #{@metodo} #{@url} -> #{@codigo}"
  end
end

p1 = Pedido.new("10.0.0.55", "POST", "/login", "401")

puts p1
puts p1.ip
puts p1.erro?
puts p1.falha_login?

#10.0.0.55 POST /login -> 401
#10.0.0.55
#true
#true