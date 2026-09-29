ips = []
ips << "10.0.0.55"
ips << "192.168.1.10"
ips << "10.0.0.55"
puts ips.length
puts ips.uniq.length
# E o resto do que uma lista sabe fazer:
puts ips.first
puts ips.uniq.inspect
puts ips.sort.inspect