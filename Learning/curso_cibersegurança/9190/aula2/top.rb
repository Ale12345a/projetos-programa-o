falhas = {"192.168.1.10"=>3, "10.0.0.55"=>1, "203.0.113.9"=>5}

falhas.sort_by { |ip, n| -n }.each do |ip, n|
  puts "#{ip.ljust(16)} #{n}"
end
# Tira o sinal menos e corre outra vez: a ordem inverte-se.

#203.0.113.9 5
#192.168.1.10 3
#10.0.0.55 1