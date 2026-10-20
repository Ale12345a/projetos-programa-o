termo  = ARGV[0]

if termo.nil?
  puts "Uso: ruby procura.rb <termo>"
  exit
end

padrao      = Regexp.new(Regexp.escape(termo))
encontradas = 0

File.foreach("acesso_alargado.log").with_index(1) do |linha, n|
  if linha =~ padrao
    encontradas += 1
    puts "  linha #{n}: #{linha.chomp}"
  end
end

puts "#{encontradas} ocorrencias de '#{termo}'."
