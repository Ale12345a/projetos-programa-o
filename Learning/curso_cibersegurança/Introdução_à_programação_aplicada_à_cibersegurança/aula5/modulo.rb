module Relatorio
  def self.titulo(texto)
    "=" * 52 + "\n" + texto.center(52) + "\n" + "=" * 52
  end

  def self.linha(rotulo, valor)
    rotulo.to_s.ljust(34) + valor.to_s.rjust(18)
  end
end

puts Relatorio.titulo("RELATORIO DE ACESSOS")
puts Relatorio.linha("Pedidos analisados", 10)
puts Relatorio.linha("Pedidos com erro", 6)

#====================================================
#RELATORIO DE ACESSOS
#====================================================
#Pedidos analisados 10
#Pedidos com erro 6