e_erro = Proc.new { |codigo| codigo >= 400 }

puts e_erro.call(200)
puts e_erro.call(404)

codigos = [200, 401, 500]
puts codigos.select(&e_erro).inspect

#false
#true
#[401, 500]