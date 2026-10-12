ips = []
ips << "10.0.0.55"
ips << "192.168.1.10"
ips << "10.0.0.55"
puts ips.length #tamanho do array
puts ips.uniq.length #tamanho diferente
# E o resto do que uma lista sabe fazer:
puts ips.first #primeiro elemento da lista
puts ips.uniq.inspect #devolve a lista sem elementos repetidos
puts ips.sort.inspect #devolve a lista ordenada

#3
#2
#10.0.0.55
#["10.0.0.55", "192.168.1.10"]
#["10.0.0.55", "10.0.0.55", "192.168.1.10"]