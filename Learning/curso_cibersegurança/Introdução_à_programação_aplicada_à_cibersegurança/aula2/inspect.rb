ips = ["10.0.0.55", "10.0.0.55"]

puts ips.uniq.length
h = {"a" => 1, "a" => 2}
puts h.length
c = Hash.new(0)
puts c.inspect
ips.each { |i| c[i] += 1}
puts c.inspect

#1
#1
#{}
#{"10.0.0.55"=>2}