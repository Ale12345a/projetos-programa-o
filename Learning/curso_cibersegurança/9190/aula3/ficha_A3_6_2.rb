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


# 3. select e map
codigos = [200, 401, 404, 200, 500]

erros = codigos.select { |c| c >= 400 }

texto = erros.map { |c| "codigo #{c}" }

p erros
p texto

#1
#2
#3
#4
#5
#1
#2
#3
#4
#5
#1
#2
#3
#4
#5
#1
#2
#3
#4
#5
#1
#2
#3
#4
#5
#401
#401
#401
#403
#[401, 404, 500]
#["codigo 401", "codigo 404", "codigo 500"]