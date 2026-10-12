codigos = [
  "200",
  "401",
  "401",
  "200",
  "401",
  "403",
  "200",
  "404",
  "404",
  "200",
]

contagem = Hash.new(0)

codigos.each do |codigo|
  contagem[codigo] += 1
end

contagem.each do |codigo, n|
  puts "#{codigo}: #{n}"
end