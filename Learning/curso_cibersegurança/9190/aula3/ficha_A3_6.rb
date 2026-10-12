#mudar ciclos.br de 3 para 5
i = 1

while i <= 5
  puts i
  i += 1
end


j = 1

until j > 5
  puts j
  j += 1
end


5.times { |i| puts i + 1 }


(1..5).each { |i| puts i }


for i in 1..5
  puts i
end