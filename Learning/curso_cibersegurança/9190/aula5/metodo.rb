# Um metodo e um programa com nome. Escreve-se uma vez, chama-se as vezes que forem
# precisas. E a peca que falta antes das classes.

def classificar(codigo)
  if codigo >= 400
    "erro"
  else
    "sucesso"
  end
end

puts classificar(401)
puts classificar(200)

#$ ruby metodo.rb
#erro
#sucesso