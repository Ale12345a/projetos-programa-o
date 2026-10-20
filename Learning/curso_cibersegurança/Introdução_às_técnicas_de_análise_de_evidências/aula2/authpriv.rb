pri = 86
facility = pri / 8 # divisao inteira
severity = pri % 8 # o resto
puts "facility=#{facility} severity=#{severity}"

#facility=10 severity=6