require "digest"

Dir.glob("evidencias/**/*").each do |caminho|
    next unless File.file?(caminho)
    puts "#{caminho}      #{File.size(caminho)} bytes"
    puts "#{Digest::SHA256.file(caminho).hexdigest}"
end

total = Dir.glob("evidencias/**/*").count { |c| File.file?(c)}
puts "total: #{total} ficheiros"

#evidencias/analise.txt      638 bytes
#a3a18d97d98cea7f46122be5eaf34783358c499fa7ed09e3729c814015ffb119
#evidencias/email/phishing.eml      261 bytes
#357bd3154af4013bce34187f129529a186b12e5ed09349f92a2c63e61c980e0c
#evidencias/proxy/proxy.log      9836 bytes
#69273cbb9c3765b074b67f92f6c54e0e684f2d0d6f148b22aa1c3b0dac6a9c11
#evidencias/sistema/aplicacao      316 bytes
#c033bccceacfac131836acdf141dccb8c04285d0a7866dd345bced76896993a6
#evidencias/sistema/auth.log      680 bytes
#90633c09f5f3d4451904af3bbeef182aabe2cb661c11556cb7b7211e9ed44ed7
#evidencias/sistema/authCopy.log      683 bytes
#94389b99c531eb2cf11bb004c0b52148625b5caf6d1fc2211f73f0e480ccaa8a
#evidencias/sistema/dns_queries.log      3687 bytes
#2cc5c160cb4b01707d8d83f1781d21f46f8e2dba614d72ecd7dc182f10ec2932
#evidencias/sistema/syslog5424      608 bytes
#5e9437883c1e666ca63b623a8a6259e5c4eb173e78e86e52c0f43dbdeaef4368
#evidencias/web/acesso.log      783 bytes
#6502620d42de0996a6667e4c6653c199ecf64834da50d15365587dedeccc6127
#evidencias/web/autenticacoes.log      413 bytes
#9e4459146ba748318aa9ecdfdd0311f33dac7d1cd260b1cbfe9ba3d913d07424
#evidencias/web/fonte_a      136 bytes
#3f41d2b7699d4dc6189e882fca8d99187c10a8ada28d8097665dc79518c82348
#evidencias/web/fonte_b      208 bytes
#ca9cad76d06f8fae8d20e942a792a06f4a9bd1c5ef1364bf34f5014aad7f617f
#evidencias/web/fonte_c      112 bytes
#919a47e8305c2fa009d71c396d45b7a974ff2e5553b7b7c15c4cde00bf83dd24
#total: 13 ficheiros