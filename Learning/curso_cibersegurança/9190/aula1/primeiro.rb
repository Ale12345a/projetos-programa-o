# O primeiro programa da UFCD 9190.
# Cinco linhas. Le um registo de acessos e diz quem falhou a entrar.
# Na sessao 1 nao e preciso perceber nenhuma delas: e preciso corre-lo.
falhas = Hash.new(0)

File.foreach("acesso.log") do |linha|
  falhas[linha.split(" ")[0]] += 1 if linha.include?("401")
end

falhas.each { |ip, n| puts "#{ip} falhou #{n} vezes a entrar" }

#10.0.0.55 falhou 3 vezes a entrar