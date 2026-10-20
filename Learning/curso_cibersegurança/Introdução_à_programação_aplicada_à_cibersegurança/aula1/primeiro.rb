# O primeiro programa da UFCD 9190.
# Cinco linhas. Le um registo de acessos e diz quem falhou a entrar.
# Na sessao 1 nao e preciso perceber nenhuma delas: e preciso corre-lo.
falhas = Hash.new(0)

File.foreach("acesso.log") do |linha| #percorre cada linha do ficheiro separadamente
  falhas[linha.split(" ")[0]] += 1 if linha.include?("401") #divide cada linha por espaços e se tiver 401 acrescenta a linha á variavel falha
end

falhas.each { |ip, n| puts "#{ip} falhou #{n} vezes a entrar" } #output de sempre que o 401 falha e quantas vezes esse ip falhou

#10.0.0.55 falhou 3 vezes a entrar