def distancia_km(lat1, lon1, lat2, lon2)
  r = 6371.0 # raio medio da Terra, em km
  rad = Math::PI / 180
  dlat = (lat2 - lat1) * rad
  dlon = (lon2 - lon1) * rad
  a = Math.sin(dlat / 2)**2 +
    Math.cos(lat1 * rad) * Math.cos(lat2 * rad) * Math.sin(dlon / 2)**2
  (2 * r * Math.asin(Math.sqrt(a))).round
end

# verificacao com um par conhecido
puts "Lisboa-Porto: #{distancia_km(38.7223, -9.1393, 41.1579, -8.6291)} km"

#Lisboa-Porto: 274 km

#dlat e dlon - As diferenças de latitude e de longitude, já convertidas para radianos.
#(2 * r *Math.asin(Math.sqrt(a))).round - O resultado em quilómetros, arredondado ao inteiro.

#Math::PI - O valor de pi, dentro do módulo Math.
#Math.sin .cos.asin .sqrt - Seno, cosseno, arco-seno e raiz quadrada.
#x**2 - Eleva ao quadrado.
#.round - Arredonda ao inteiro mais próximo; com argumento, ao número de casas indicado.
