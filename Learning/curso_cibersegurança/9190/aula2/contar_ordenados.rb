ips = [
  "192.168.1.10",
  "10.0.0.55",
  "10.0.0.55",
  "192.168.1.10",
  "10.0.0.55",
  "203.0.113.9",
  "192.168.1.22",
  "203.0.113.9",
  "192.168.1.10",
  "10.0.0.55",
]

contagem = Hash.new(0)

ips.each do |ip|
  contagem[ip] += 1
end

contagem.sort_by { |ip, n| -n }.each do |ip, n|
  puts "#{ip.ljust(16)} #{n}"
end