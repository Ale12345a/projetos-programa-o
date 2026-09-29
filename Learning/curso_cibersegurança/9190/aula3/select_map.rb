codigos = [200, 401, 404, 200, 500]

erros = codigos.select { |c| c >= 400 }
texto = erros.map { |c| "codigo #{c}" }

p erros
p texto

# quantos erros, sem os listar:
puts codigos.count { |c| c >= 400 }

#[401, 404, 500]
#["codigo 401", "codigo 404", "codigo 500"]
#3