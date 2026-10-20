File.readlines("ola.log", encoding: "UTF-8").each_with_index do |l, i|
  estado = l.valid_encoding? ? "OK" : "INVALIDA"
  puts "linha #{i + 1}: #{estado}"
end

#linha 1: OK
#linha 2: OK
#linha 3: OK