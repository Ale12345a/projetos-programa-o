# Distancia de Levenshtein: numero minimo de insercoes, remocoes
# ou substituicoes para transformar uma palavra na outra.
def levenshtein(a, b)
  return b.length if a.empty?
  return a.length if b.empty?

  # linha anterior da matriz: distancia de "" a cada prefixo de b
  anterior = (0..b.length).to_a

  a.each_char.with_index do |ca, i|
    atual = [i + 1]
    b.each_char.with_index do |cb, j|
      custo = ca == cb ? 0 : 1
      atual << [atual[j] + 1, # remocao
      anterior[j + 1] + 1, # insercao
      anterior[j] + custo # substituicao
      ].min
    end
    anterior = atual
  end

  anterior.last
end

# casos de verificacao com resultado conhecido
[["kitten", "sitting", 3],["iefp", "ieefp", 1],["iefp", "iefp", 0],["", "abc", 3],["abc", "", 3],["flaw", "lawn", 2]].each do |a, b, esperado|
  obtido = levenshtein(a, b)
  estado = obtido == esperado ? "ok " : "FALHA"
  puts "#{estado} levenshtein(#{a.inspect}, #{b.inspect}) = " \
  "#{obtido} (esperado #{esperado})"
end

#ok  levenshtein("kitten", "sitting") = 3 (esperado 3)
#ok  levenshtein("iefp", "ieefp") = 1 (esperado 1)
#ok  levenshtein("iefp", "iefp") = 0 (esperado 0)
#ok  levenshtein("", "abc") = 3 (esperado 3)
#ok  levenshtein("abc", "") = 3 (esperado 3)
#ok  levenshtein("flaw", "lawn") = 2 (esperado 2)

#anterior = (0..b.length).to_a A primeira linha da matriz de cálculo: o custo de transformar a palavra vazia nos primeiros caracteres de b.
#.inspect nos casos de teste - Mostra a string com aspas visíveis, o que torna evidente qual dos casos é a palavra vazia.