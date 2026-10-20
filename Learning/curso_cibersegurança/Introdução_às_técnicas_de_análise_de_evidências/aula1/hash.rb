require "digest"
puts Digest::SHA256
        .file("evidencias/sistema/auth.log")
        .hexdigest

#HASH DO FICHEIRO
#90633c09f5f3d4451904af3bbeef182aabe2cb661c11556cb7b7211e9ed44ed7        

#require - carrega uma biblioteca que o Ruby precisa para executar determinadas operações.
#"digest": biblioteca que disponibiliza métodos para calcular hashes, como SHA-256 e SHA-512.
#Digest: módulo Ruby com funcionalidades para calcular hashes.
#:: - operador de resolução de âmbito, usado para aceder a um elemento dentro de um módulo ou classe.
#SHA256: algoritmo que calcula um resumo (hash) de 256 bits, normalmente representado por 64 caracteres hexadecimais.
#.file("evidencias/sistema/auth.log") - O método file lê o ficheiro indicado e calcula o seu hash.
#.hexdigest - Este método devolve o hash calculado numa representação hexadecimal, usando os algarismos 0–9 e as letras a–f.