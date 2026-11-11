
function getWeather(cities, info = "all") {
    // Se for apenas uma cidade, transforma-a num array
    if (!Array.isArray(cities)) {
        cities = [cities];
    }

    // Tipos de informação permitidos
    let validInfo = ["wind", "clouds", "temp", "precipitation"];

    // Se o tipo for inválido, obter todos os dados
    if (!validInfo.includes(info)) {
        info = "all";
    }

    // Criar um pedido para cada cidade
    let promises = cities.map(city => {
        let url = `http://localhost:3000/weather?city=${encodeURIComponent(city)}`;

        if (info !== "all") {
            url += `&info=${info}`;
        }

        return fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error retrieving weather for ${city}`);
                }
                return response.json();
            });
    });

    // Processar as respostas
    return Promise.all(promises)
        .then(results => {
            results.forEach(data => {
                console.log(`CITY: ${data.city}`);

                let weather = data.weather;

                if (!weather) {
                    return;
                }

                if (weather.wind) {
                    console.log(
                        `WIND: ${weather.wind.speed} m/s, ${weather.wind.deg} deg`
                    );

                    if (weather.wind.speed > 15) {
                        console.log("WARNING! Wind speed over 15 m/s");
                    }
                }

                if (weather.clouds !== undefined) {
                    console.log(`CLOUDS: ${weather.clouds} %`);
                }

                if (weather.temp !== undefined) {
                    console.log(`TEMP: ${weather.temp} C`);

                    if (weather.temp < -20) {
                        console.log("WARNING! Temperature below -20 degrees");
                    }
                }

                if (weather.precipitation !== undefined) {
                    console.log(`PRECIPITATION: ${weather.precipitation} %`);
                }

                console.log("");
            });

            return results;
        })
        .catch(error => {
            console.log(error.message);
        });
}

// TESTES

let weather1 = getWeather("Berlin", "wind");

let weather2 = getWeather(["Oslo", "Yakutsk"], "all");
