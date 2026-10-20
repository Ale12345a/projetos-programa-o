ficheiros = Dir.glob("evidencias/**/*",
                     File::FNM_DOTMATCH)
             .select { |c| File.file?(c) }
puts ficheiros.size

#14

#File::FNM_DOTMATCH é uma opção do Ruby que permite ao Dir.glob incluir também nomes de ficheiros e pastas ocultos, como os que começam por .