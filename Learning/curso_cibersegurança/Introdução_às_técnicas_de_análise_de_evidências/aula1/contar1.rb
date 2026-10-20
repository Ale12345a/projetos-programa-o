ficheiros = Dir.glob("evidencias/**/*")
             .select { |c| File.file?(c) }
 
puts ficheiros.size

#13

#Dir.glob("evidencias/**/*") - procura caminhos dentro da pasta evidencias, incluindo as respetivas subpastas.
#Dir - módulo Ruby que permite trabalhar com diretórios (pastas).
#glob - método que procura caminhos que correspondem a um padrão.
#"evidencias/**/*" - padrão de pesquisa.
#evidencias/ - Começa na pasta evidencias.
#**/ - Procura também dentro de subpastas, incluindo vários níveis.
#* - corresponde a nomes de ficheiros ou pastas.
#.select { |c| File.file?(c) } - Esta parte filtra os resultados anteriores e mantém apenas os ficheiros.
#.select: seleciona os elementos que satisfazem uma condição.
#{ ... }: bloco de código que define essa condição.
#|c|: variável temporária que representa cada caminho analisado. O nome c foi escolhido pelo programador; poderia ser caminho.
#File.file?(c): verifica se o caminho guardado em c corresponde a um ficheiro normal.