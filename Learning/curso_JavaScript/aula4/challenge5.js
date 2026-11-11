
async function getWeather(cities, info = "all") {
    try {
        // Se for apenas uma cidade, transforma-a num array
        if (!Array.isArray(cities)) {
            cities = [cities];
        }

        // Tipos de informação permitidos
        let validInfo = ["wind", "clouds", "temp", "precipitation"];

        if (!validInfo.includes(info)) {
            info = "all";
        }

        // Criar os pedidos
        let promises = cities.map(async city => {
            let url = `http://localhost:3000/weather?city=${encodeURIComponent(city)}`;

            if (info !== "all") {
                url += `&info=${info}`;
            }

            let response = await fetch(url);

            if (!response.ok) {
                throw new Error(`Error retrieving weather for ${city}`);
            }

            return await response.json();
        });

        // Esperar por todos os pedidos
        let results = await Promise.all(promises);

        // Apresentar os resultados
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

    } catch (error) {
        console.log(error.message);
    }
}

// TESTES

let weather1 = getWeather("Berlin", "wind");

let weather2 = getWeather(["Oslo", "Yakutsk"], "all");
